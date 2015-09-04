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

import com.tinkerforge.BrickletAnalogIn;

/**
 * Measures DC voltage between 0V and 45V
 */
@UriEndpoint(scheme = "tinkerforgegen", syntax = "tinkerforgegen:[host[:port]/]analogin", consumerClass = AnalogInConsumer.class, label = "iot", title = "Tinkerforge")
public class AnalogInEndpoint extends TinkerforgeEndpoint<AnalogInConsumer, AnalogInProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(AnalogInEndpoint.class);

    public static final String PERIOD="period";
    public static final String PERIOD2="period2";
    public static final String OPTION="option";
    public static final String MIN="min";
    public static final String MAX="max";
    public static final String OPTION2="option2";
    public static final String MIN2="min2";
    public static final String MAX2="max2";
    public static final String DEBOUNCE="debounce";
    public static final String RANGE="range";
    public static final String AVERAGE="average";

    
    private Long period;
    private Long period2;
    private Character option;
    private Short min;
    private Short max;
    private Character option2;
    private Integer min2;
    private Integer max2;
    private Long debounce;
    private Short range;
    private Short average;

        
    public AnalogInEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new AnalogInProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new AnalogInConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletAnalogIn device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletAnalogIn device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "getVoltage":
                response = device.getVoltage();
                break;

            case "getAnalogValue":
                response = device.getAnalogValue();
                break;

            case "setVoltageCallbackPeriod":
                device.setVoltageCallbackPeriod(
                        getValue(long.class, "period", m, getPeriod())
                    );
                break;

            case "getVoltageCallbackPeriod":
                response = device.getVoltageCallbackPeriod();
                break;

            case "setAnalogValueCallbackPeriod":
                device.setAnalogValueCallbackPeriod(
                        getValue(long.class, "period2", m, getPeriod2())
                    );
                break;

            case "getAnalogValueCallbackPeriod":
                response = device.getAnalogValueCallbackPeriod();
                break;

            case "setVoltageCallbackThreshold":
                device.setVoltageCallbackThreshold(
                        getValue(char.class, "option", m, getOption()),
                        getValue(short.class, "min", m, getMin()),
                        getValue(short.class, "max", m, getMax())
                    );
                break;

            case "getVoltageCallbackThreshold":
                response = device.getVoltageCallbackThreshold();
                break;

            case "setAnalogValueCallbackThreshold":
                device.setAnalogValueCallbackThreshold(
                        getValue(char.class, "option2", m, getOption2()),
                        getValue(int.class, "min2", m, getMin2()),
                        getValue(int.class, "max2", m, getMax2())
                    );
                break;

            case "getAnalogValueCallbackThreshold":
                response = device.getAnalogValueCallbackThreshold();
                break;

            case "setDebouncePeriod":
                device.setDebouncePeriod(
                        getValue(long.class, "debounce", m, getDebounce())
                    );
                break;

            case "getDebouncePeriod":
                response = device.getDebouncePeriod();
                break;

            case "setRange":
                device.setRange(
                        getValue(short.class, "range", m, getRange())
                    );
                break;

            case "getRange":
                response = device.getRange();
                break;

            case "setAveraging":
                device.setAveraging(
                        getValue(short.class, "average", m, getAverage())
                    );
                break;

            case "getAveraging":
                response = device.getAveraging();
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
     * Sets the period in ms with which the :func:`Voltage` callback is triggered
     * periodically. A value of 0 turns the callback off.
     * 
     * :func:`Voltage` is only triggered if the voltage has changed since the
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
     * Sets the period in ms with which the :func:`AnalogValue` callback is triggered
     * periodically. A value of 0 turns the callback off.
     * 
     * :func:`AnalogValue` is only triggered if the analog value has changed since the
     * last triggering.
     * 
     * The default value is 0.
     * 
     */
    public Long getPeriod2(){
        return period2;
    }

    public void setPeriod2(Long period2){
        this.period2 = period2;
    }

    /**
     * 
     * Sets the thresholds for the :func:`VoltageReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the voltage is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the voltage is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the voltage is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the voltage is greater than the min value (max is ignored)"
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
     * Sets the thresholds for the :func:`VoltageReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the voltage is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the voltage is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the voltage is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the voltage is greater than the min value (max is ignored)"
     * 
     * The default value is ('x', 0, 0).
     * 
     */
    public Short getMin(){
        return min;
    }

    public void setMin(Short min){
        this.min = min;
    }

    /**
     * 
     * Sets the thresholds for the :func:`VoltageReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the voltage is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the voltage is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the voltage is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the voltage is greater than the min value (max is ignored)"
     * 
     * The default value is ('x', 0, 0).
     * 
     */
    public Short getMax(){
        return max;
    }

    public void setMax(Short max){
        this.max = max;
    }

    /**
     * 
     * Sets the thresholds for the :func:`AnalogValueReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the analog value is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the analog value is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the analog value is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the analog value is greater than the min value (max is ignored)"
     * 
     * The default value is ('x', 0, 0).
     * 
     */
    public Character getOption2(){
        return option2;
    }

    public void setOption2(Character option2){
        this.option2 = option2;
    }

    /**
     * 
     * Sets the thresholds for the :func:`AnalogValueReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the analog value is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the analog value is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the analog value is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the analog value is greater than the min value (max is ignored)"
     * 
     * The default value is ('x', 0, 0).
     * 
     */
    public Integer getMin2(){
        return min2;
    }

    public void setMin2(Integer min2){
        this.min2 = min2;
    }

    /**
     * 
     * Sets the thresholds for the :func:`AnalogValueReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the analog value is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the analog value is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the analog value is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the analog value is greater than the min value (max is ignored)"
     * 
     * The default value is ('x', 0, 0).
     * 
     */
    public Integer getMax2(){
        return max2;
    }

    public void setMax2(Integer max2){
        this.max2 = max2;
    }

    /**
     * 
     * Sets the period in ms with which the threshold callbacks
     * 
     * * :func:`VoltageReached`,
     * * :func:`AnalogValueReached`
     * 
     * are triggered, if the thresholds
     * 
     * * :func:`SetVoltageCallbackThreshold`,
     * * :func:`SetAnalogValueCallbackThreshold`
     * 
     * keep being reached.
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
     * Sets the measurement range. Possible ranges:
     * 
     * * 0: Automatically switched
     * * 1: 0V - 6.05V, ~1.48mV resolution
     * * 2: 0V - 10.32V, ~2.52mV resolution
     * * 3: 0V - 36.30V, ~8.86mV resolution
     * * 4: 0V - 45.00V, ~11.25mV resolution
     * * 5: 0V - 3.3V, ~0.81mV resolution, new in version 2.0.3 (Plugin)
     * 
     * The default measurement range is 0.
     * 
     */
    public Short getRange(){
        return range;
    }

    public void setRange(Short range){
        this.range = range;
    }

    /**
     * 
     * Set the length of a averaging for the voltage value.
     * 
     * Setting the length to 0 will turn the averaging completely off. If the
     * averaging is off, there is more noise on the data, but the data is without
     * delay.
     * 
     * The default value is 50.
     * 
     */
    public Short getAverage(){
        return average;
    }

    public void setAverage(Short average){
        this.average = average;
    }



}
