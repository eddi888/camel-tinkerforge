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
import org.atomspace.camel.component.tinkerforge.TinkerforgeComponent;
import org.atomspace.camel.component.tinkerforge.TinkerforgeEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tinkerforge.BrickletIndustrialDual020mA;

/**
 * Measures two DC currents between 0mA and 20mA (IEC 60381-1)
 */
public class IndustrialDual020mAEndpoint extends TinkerforgeEndpoint<IndustrialDual020mAConsumer, IndustrialDual020mAProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(IndustrialDual020mAEndpoint.class);
    
    private Short sensor;
    private Short sensor2;
    private Long period;
    private Short sensor3;
    private Short sensor4;
    private Character option;
    private Integer min;
    private Integer max;
    private Short sensor5;
    private Long debounce;
    private Short rate;

        
    public IndustrialDual020mAEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new IndustrialDual020mAProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new IndustrialDual020mAConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletIndustrialDual020mA device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletIndustrialDual020mA device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "getCurrent":
                response = device.getCurrent(
                        getValue(short.class, "sensor", m, getSensor())
                    );
                break;

            case "setCurrentCallbackPeriod":
                device.setCurrentCallbackPeriod(
                        getValue(short.class, "sensor2", m, getSensor2()),
                        getValue(long.class, "period", m, getPeriod())
                    );
                break;

            case "getCurrentCallbackPeriod":
                response = device.getCurrentCallbackPeriod(
                        getValue(short.class, "sensor3", m, getSensor3())
                    );
                break;

            case "setCurrentCallbackThreshold":
                device.setCurrentCallbackThreshold(
                        getValue(short.class, "sensor4", m, getSensor4()),
                        getValue(char.class, "option", m, getOption()),
                        getValue(int.class, "min", m, getMin()),
                        getValue(int.class, "max", m, getMax())
                    );
                break;

            case "getCurrentCallbackThreshold":
                response = device.getCurrentCallbackThreshold(
                        getValue(short.class, "sensor5", m, getSensor5())
                    );
                break;

            case "setDebouncePeriod":
                device.setDebouncePeriod(
                        getValue(long.class, "debounce", m, getDebounce())
                    );
                break;

            case "getDebouncePeriod":
                response = device.getDebouncePeriod();
                break;

            case "setSampleRate":
                device.setSampleRate(
                        getValue(short.class, "rate", m, getRate())
                    );
                break;

            case "getSampleRate":
                response = device.getSampleRate();
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
     * Returns the current of the specified sensor (0 or 1). The value is in nA
     * and between 0nA and 22505322nA (22.5mA).
     * 
     * It is possible to detect if an IEC 60381-1 compatible sensor is connected
     * and if it works probably.
     * 
     * If the returned current is below 4mA, there is likely no sensor connected
     * or the sensor may be defect. If the returned current is over 20mA, there might
     * be a short circuit or the sensor may be defect.
     * 
     * If you want to get the current periodically, it is recommended to use the
     * callback :func:`Current` and set the period with 
     * :func:`SetCurrentCallbackPeriod`.
     * 
     */
    public Short getSensor(){
        return sensor;
    }

    public void setSensor(Short sensor){
        this.sensor = sensor;
    }

    /**
     * 
     * Sets the period in ms with which the :func:`Current` callback is triggered
     * periodically for the given sensor. A value of 0 turns the callback off.
     * 
     * :func:`Current` is only triggered if the current has changed since the
     * last triggering.
     * 
     * The default value is 0.
     * 
     */
    public Short getSensor2(){
        return sensor2;
    }

    public void setSensor2(Short sensor2){
        this.sensor2 = sensor2;
    }

    /**
     * 
     * Sets the period in ms with which the :func:`Current` callback is triggered
     * periodically for the given sensor. A value of 0 turns the callback off.
     * 
     * :func:`Current` is only triggered if the current has changed since the
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
     * Returns the period as set by :func:`SetCurrentCallbackPeriod`.
     * 
     */
    public Short getSensor3(){
        return sensor3;
    }

    public void setSensor3(Short sensor3){
        this.sensor3 = sensor3;
    }

    /**
     * 
     * Sets the thresholds for the :func:`CurrentReached` callback for the given
     * sensor.
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the current is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the current is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the current is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the current is greater than the min value (max is ignored)"
     * 
     * The default value is ('x', 0, 0).
     * 
     */
    public Short getSensor4(){
        return sensor4;
    }

    public void setSensor4(Short sensor4){
        this.sensor4 = sensor4;
    }

    /**
     * 
     * Sets the thresholds for the :func:`CurrentReached` callback for the given
     * sensor.
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the current is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the current is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the current is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the current is greater than the min value (max is ignored)"
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
     * Sets the thresholds for the :func:`CurrentReached` callback for the given
     * sensor.
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the current is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the current is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the current is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the current is greater than the min value (max is ignored)"
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
     * Sets the thresholds for the :func:`CurrentReached` callback for the given
     * sensor.
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the current is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the current is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the current is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the current is greater than the min value (max is ignored)"
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
     * Returns the threshold as set by :func:`SetCurrentCallbackThreshold`.
     * 
     */
    public Short getSensor5(){
        return sensor5;
    }

    public void setSensor5(Short sensor5){
        this.sensor5 = sensor5;
    }

    /**
     * 
     * Sets the period in ms with which the threshold callback
     * 
     * * :func:`CurrentReached`
     * 
     * is triggered, if the threshold
     * 
     * * :func:`SetCurrentCallbackThreshold`
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
     * Sets the sample rate to either 240, 60, 15 or 4 samples per second.
     * The resolution for the rates is 12, 14, 16 and 18 bit respectively.
     * 
     * .. csv-table::
     *  :header: "Value", "Description"
     *  :widths: 10, 100
     * 
     *  "0",    "240 samples per second, 12 bit resolution"
     *  "1",    "60 samples per second, 14 bit resolution"
     *  "2",    "15 samples per second, 16 bit resolution"
     *  "3",    "4 samples per second, 18 bit resolution"
     * 
     * The default value is 3: 4 samples per second with 18 bit resolution.
     * 
     */
    public Short getRate(){
        return rate;
    }

    public void setRate(Short rate){
        this.rate = rate;
    }



}
