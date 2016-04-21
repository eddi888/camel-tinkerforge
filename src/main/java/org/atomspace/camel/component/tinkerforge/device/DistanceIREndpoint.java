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

import com.tinkerforge.BrickletDistanceIR;

/**
 * Measures distance up to 150cm with infrared light
 */
@UriEndpoint(scheme = "tinkerforgegen", syntax = "tinkerforgegen:[host[:port]/]distanceir", consumerClass = DistanceIRConsumer.class, label = "iot", title = "Tinkerforge")
public class DistanceIREndpoint extends TinkerforgeEndpoint<DistanceIRConsumer, DistanceIRProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(DistanceIREndpoint.class);

    public static final String POSITION="position";
    public static final String DISTANCE="distance";
    public static final String POSITION2="position2";
    public static final String PERIOD="period";
    public static final String PERIOD2="period2";
    public static final String OPTION="option";
    public static final String MIN="min";
    public static final String MAX="max";
    public static final String OPTION2="option2";
    public static final String MIN2="min2";
    public static final String MAX2="max2";
    public static final String DEBOUNCE="debounce";

    
    private Short position;
    private Integer distance;
    private Short position2;
    private Long period;
    private Long period2;
    private Character option;
    private Integer min;
    private Integer max;
    private Character option2;
    private Integer min2;
    private Integer max2;
    private Long debounce;

        
    public DistanceIREndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new DistanceIRProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new DistanceIRConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletDistanceIR device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletDistanceIR device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "getDistance":
                response = device.getDistance();
                break;

            case "getAnalogValue":
                response = device.getAnalogValue();
                break;

            case "setSamplingPoint":
                device.setSamplingPoint(
                        getValue(short.class, "position", m, getPosition()),
                        getValue(int.class, "distance", m, getDistance())
                    );
                break;

            case "getSamplingPoint":
                response = device.getSamplingPoint(
                        getValue(short.class, "position2", m, getPosition2())
                    );
                break;

            case "setDistanceCallbackPeriod":
                device.setDistanceCallbackPeriod(
                        getValue(long.class, "period", m, getPeriod())
                    );
                break;

            case "getDistanceCallbackPeriod":
                response = device.getDistanceCallbackPeriod();
                break;

            case "setAnalogValueCallbackPeriod":
                device.setAnalogValueCallbackPeriod(
                        getValue(long.class, "period2", m, getPeriod2())
                    );
                break;

            case "getAnalogValueCallbackPeriod":
                response = device.getAnalogValueCallbackPeriod();
                break;

            case "setDistanceCallbackThreshold":
                device.setDistanceCallbackThreshold(
                        getValue(char.class, "option", m, getOption()),
                        getValue(int.class, "min", m, getMin()),
                        getValue(int.class, "max", m, getMax())
                    );
                break;

            case "getDistanceCallbackThreshold":
                response = device.getDistanceCallbackThreshold();
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

            case "getIdentity":
                response = device.getIdentity();
                break;


            default: throw new Exception("unknown function '"+function+"'");
            
        }
        
        return response;
    }
    
    
    /**
     * 
     * Sets a sampling point value to a specific position of the lookup table.
     * The lookup table comprises 128 equidistant analog values with
     * corresponding distances.
     * 
     * If you measure a distance of 50cm at the analog value 2048, you
     * should call this function with (64, 5000). The utilized analog-to-digital
     * converter has a resolution of 12 bit. With 128 sampling points on the
     * whole range, this means that every sampling point has a size of 32
     * analog values. Thus the analog value 2048 has the corresponding sampling
     * point 64 = 2048/32.
     * 
     * Sampling points are saved on the EEPROM of the Distance IR Bricklet and
     * loaded again on startup.
     * 
     * .. note::
     *  An easy way to calibrate the sampling points of the Distance IR Bricklet is
     *  implemented in the Brick Viewer. If you want to calibrate your Bricklet it is
     *  highly recommended to use this implementation.
     * 
     */
    public Short getPosition(){
        return position;
    }

    public void setPosition(Short position){
        this.position = position;
    }

    /**
     * 
     * Sets a sampling point value to a specific position of the lookup table.
     * The lookup table comprises 128 equidistant analog values with
     * corresponding distances.
     * 
     * If you measure a distance of 50cm at the analog value 2048, you
     * should call this function with (64, 5000). The utilized analog-to-digital
     * converter has a resolution of 12 bit. With 128 sampling points on the
     * whole range, this means that every sampling point has a size of 32
     * analog values. Thus the analog value 2048 has the corresponding sampling
     * point 64 = 2048/32.
     * 
     * Sampling points are saved on the EEPROM of the Distance IR Bricklet and
     * loaded again on startup.
     * 
     * .. note::
     *  An easy way to calibrate the sampling points of the Distance IR Bricklet is
     *  implemented in the Brick Viewer. If you want to calibrate your Bricklet it is
     *  highly recommended to use this implementation.
     * 
     */
    public Integer getDistance(){
        return distance;
    }

    public void setDistance(Integer distance){
        this.distance = distance;
    }

    /**
     * 
     * Returns the distance to a sampling point position as set by
     * :func:`SetSamplingPoint`.
     * 
     */
    public Short getPosition2(){
        return position2;
    }

    public void setPosition2(Short position2){
        this.position2 = position2;
    }

    /**
     * 
     * Sets the period in ms with which the :func:`Distance` callback is triggered
     * periodically. A value of 0 turns the callback off.
     * 
     * :func:`Distance` is only triggered if the distance has changed since the
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
     * Sets the thresholds for the :func:`DistanceReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the distance is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the distance is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the distance is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the distance is greater than the min value (max is ignored)"
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
     * Sets the thresholds for the :func:`DistanceReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the distance is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the distance is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the distance is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the distance is greater than the min value (max is ignored)"
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
     * Sets the thresholds for the :func:`DistanceReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the distance is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the distance is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the distance is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the distance is greater than the min value (max is ignored)"
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
     * * :func:`DistanceReached`,
     * * :func:`AnalogValueReached`
     * 
     * are triggered, if the thresholds
     * 
     * * :func:`SetDistanceCallbackThreshold`,
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



}
