/**
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements. See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License. You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.atomspace.camel.component.tinkerforge.device;

import org.apache.camel.Consumer;
import org.apache.camel.Endpoint;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.spi.UriEndpoint;
import org.atomspace.camel.component.tinkerforge.TinkerforgeComponent;
import org.atomspace.camel.component.tinkerforge.TinkerforgeEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tinkerforge.BrickletLoadCell;

/**
 * Measures weight with a load cell
 */
@UriEndpoint(scheme = "tinkerforgegen", syntax = "tinkerforgegen:[host[:port]/]loadcell", consumerClass = LoadCellConsumer.class, label = "iot", title = "Tinkerforge")
public class LoadCellEndpoint extends TinkerforgeEndpoint<LoadCellConsumer, LoadCellProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(LoadCellEndpoint.class);

    public static final String PERIOD="period";
    public static final String OPTION="option";
    public static final String MIN="min";
    public static final String MAX="max";
    public static final String DEBOUNCE="debounce";
    public static final String AVERAGE="average";
    public static final String WEIGHT="weight";
    public static final String RATE="rate";
    public static final String GAIN="gain";

    
    private Long period;
    private Character option;
    private Integer min;
    private Integer max;
    private Long debounce;
    private Short average;
    private Long weight;
    private Short rate;
    private Short gain;

        
    public LoadCellEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new LoadCellProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new LoadCellConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletLoadCell device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletLoadCell device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "getWeight":
                response = device.getWeight();
                break;

            case "setWeightCallbackPeriod":
                device.setWeightCallbackPeriod(
                        getValue(long.class, "period", m, getPeriod())
                    );
                break;

            case "getWeightCallbackPeriod":
                response = device.getWeightCallbackPeriod();
                break;

            case "setWeightCallbackThreshold":
                device.setWeightCallbackThreshold(
                        getValue(char.class, "option", m, getOption()),
                        getValue(int.class, "min", m, getMin()),
                        getValue(int.class, "max", m, getMax())
                    );
                break;

            case "getWeightCallbackThreshold":
                response = device.getWeightCallbackThreshold();
                break;

            case "setDebouncePeriod":
                device.setDebouncePeriod(
                        getValue(long.class, "debounce", m, getDebounce())
                    );
                break;

            case "getDebouncePeriod":
                response = device.getDebouncePeriod();
                break;

            case "setMovingAverage":
                device.setMovingAverage(
                        getValue(short.class, "average", m, getAverage())
                    );
                break;

            case "getMovingAverage":
                response = device.getMovingAverage();
                break;

            case "ledOn":
                device.ledOn();
                break;

            case "ledOff":
                device.ledOff();
                break;

            case "isLedOn":
                response = device.isLEDOn();
                break;

            case "calibrate":
                device.calibrate(
                        getValue(long.class, "weight", m, getWeight())
                    );
                break;

            case "tare":
                device.tare();
                break;

            case "setConfiguration":
                device.setConfiguration(
                        getValue(short.class, "rate", m, getRate()),
                        getValue(short.class, "gain", m, getGain())
                    );
                break;

            case "getConfiguration":
                response = device.getConfiguration();
                break;

            case "getIdentity":
                response = device.getIdentity();
                break;


            default: throw new Exception("unknown function '"+function+"'");
            
        }
        
        return response;
    }
    
    
    /**
     * 
     * Sets the period in ms with which the :func:`Weight` callback is triggered
     * periodically. A value of 0 turns the callback off.
     * 
     * :func:`Weight` is only triggered if the weight has changed since the
     * last triggering.
     * 
     * The default value is 0.
     * 
     */
    public Long getPeriod(){
        return period;
    }

    public void setPeriod(Long period){
        this.period = period;
    }

    /**
     * 
     * Sets the thresholds for the :func:`WeightReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the weight is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the weight is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the weight is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the weight is greater than the min value (max is ignored)"
     * 
     * The default value is ('x', 0, 0).
     * 
     */
    public Character getOption(){
        return option;
    }

    public void setOption(Character option){
        this.option = option;
    }

    /**
     * 
     * Sets the thresholds for the :func:`WeightReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the weight is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the weight is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the weight is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the weight is greater than the min value (max is ignored)"
     * 
     * The default value is ('x', 0, 0).
     * 
     */
    public Integer getMin(){
        return min;
    }

    public void setMin(Integer min){
        this.min = min;
    }

    /**
     * 
     * Sets the thresholds for the :func:`WeightReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the weight is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the weight is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the weight is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the weight is greater than the min value (max is ignored)"
     * 
     * The default value is ('x', 0, 0).
     * 
     */
    public Integer getMax(){
        return max;
    }

    public void setMax(Integer max){
        this.max = max;
    }

    /**
     * 
     * Sets the period in ms with which the threshold callback
     * 
     * * :func:`WeightReached`
     * 
     * is triggered, if the threshold
     * 
     * * :func:`SetWeightCallbackThreshold`
     * 
     * keeps being reached.
     * 
     * The default value is 100.
     * 
     */
    public Long getDebounce(){
        return debounce;
    }

    public void setDebounce(Long debounce){
        this.debounce = debounce;
    }

    /**
     * 
     * Sets the length of a `moving averaging <https://en.wikipedia.org/wiki/Moving_average>`__
     * for the weight value.
     * 
     * Setting the length to 1 will turn the averaging off. With less
     * averaging, there is more noise on the data.
     * 
     * The range for the averaging is 1-40.
     * 
     * The default value is 4.
     * 
     */
    public Short getAverage(){
        return average;
    }

    public void setAverage(Short average){
        this.average = average;
    }

    /**
     * 
     * To calibrate your Load Cell Bricklet you have to
     * 
     * * empty the scale and call this function with 0 and
     * * add a known weight to the scale and call this function with the weight in 
     *   grams.
     * 
     * The calibration is saved in the EEPROM of the Bricklet and only
     * needs to be done once.
     * 
     * We recommend to use the Brick Viewer for calibration, you don't need
     * to call this function in your source code.
     * 
     */
    public Long getWeight(){
        return weight;
    }

    public void setWeight(Long weight){
        this.weight = weight;
    }

    /**
     * 
     * The measurement rate and gain are configurable.
     * 
     * The rate can be either 10Hz or 80Hz. A faster rate will produce more noise.
     * It is additionally possible to add a moving average
     * (see :func:`SetMovingAverage`) to the measurements.
     * 
     * The gain can be 128x, 64x or 32x. It represents a measurement range of
     * ±20mV, ±40mV and ±80mV respectively. The Load Cell Bricklet uses an
     * excitation voltage of 5V and most load cells use an output of 2mV/V. That
     * means the voltage range is ±15mV for most load cells (i.e. gain of 128x
     * is best). If you don't know what all of this means you should keep it at 
     * 128x, it will most likely be correct.
     * 
     * The configuration is saved in the EEPROM of the Bricklet and only
     * needs to be done once.
     * 
     * We recommend to use the Brick Viewer for configuration, you don't need
     * to call this function in your source code.
     * 
     * The default rate is 10Hz and the default gain is 128x.
     * 
     */
    public Short getRate(){
        return rate;
    }

    public void setRate(Short rate){
        this.rate = rate;
    }

    /**
     * 
     * The measurement rate and gain are configurable.
     * 
     * The rate can be either 10Hz or 80Hz. A faster rate will produce more noise.
     * It is additionally possible to add a moving average
     * (see :func:`SetMovingAverage`) to the measurements.
     * 
     * The gain can be 128x, 64x or 32x. It represents a measurement range of
     * ±20mV, ±40mV and ±80mV respectively. The Load Cell Bricklet uses an
     * excitation voltage of 5V and most load cells use an output of 2mV/V. That
     * means the voltage range is ±15mV for most load cells (i.e. gain of 128x
     * is best). If you don't know what all of this means you should keep it at 
     * 128x, it will most likely be correct.
     * 
     * The configuration is saved in the EEPROM of the Bricklet and only
     * needs to be done once.
     * 
     * We recommend to use the Brick Viewer for configuration, you don't need
     * to call this function in your source code.
     * 
     * The default rate is 10Hz and the default gain is 128x.
     * 
     */
    public Short getGain(){
        return gain;
    }

    public void setGain(Short gain){
        this.gain = gain;
    }



}
