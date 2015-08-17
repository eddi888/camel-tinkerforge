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

import com.tinkerforge.BrickletAccelerometer;

/**
 * Measures acceleration in three axis
 */
public class AccelerometerEndpoint extends TinkerforgeEndpoint<AccelerometerConsumer, AccelerometerProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(AccelerometerEndpoint.class);
    
    private Long period;
    private Character option;
    private Short minX;
    private Short maxX;
    private Short minY;
    private Short maxY;
    private Short minZ;
    private Short maxZ;
    private Long debounce;
    private Short dataRate;
    private Short fullScale;
    private Short filterBandwidth;

        
    public AccelerometerEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new AccelerometerProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new AccelerometerConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletAccelerometer device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletAccelerometer device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "getAcceleration":
                response = device.getAcceleration();
                break;

            case "setAccelerationCallbackPeriod":
                device.setAccelerationCallbackPeriod(
                        getValue(long.class, "period", m, getPeriod())
                    );
                break;

            case "getAccelerationCallbackPeriod":
                response = device.getAccelerationCallbackPeriod();
                break;

            case "setAccelerationCallbackThreshold":
                device.setAccelerationCallbackThreshold(
                        getValue(char.class, "option", m, getOption()),
                        getValue(short.class, "minX", m, getMinX()),
                        getValue(short.class, "maxX", m, getMaxX()),
                        getValue(short.class, "minY", m, getMinY()),
                        getValue(short.class, "maxY", m, getMaxY()),
                        getValue(short.class, "minZ", m, getMinZ()),
                        getValue(short.class, "maxZ", m, getMaxZ())
                    );
                break;

            case "getAccelerationCallbackThreshold":
                response = device.getAccelerationCallbackThreshold();
                break;

            case "setDebouncePeriod":
                device.setDebouncePeriod(
                        getValue(long.class, "debounce", m, getDebounce())
                    );
                break;

            case "getDebouncePeriod":
                response = device.getDebouncePeriod();
                break;

            case "getTemperature":
                response = device.getTemperature();
                break;

            case "setConfiguration":
                device.setConfiguration(
                        getValue(short.class, "dataRate", m, getDataRate()),
                        getValue(short.class, "fullScale", m, getFullScale()),
                        getValue(short.class, "filterBandwidth", m, getFilterBandwidth())
                    );
                break;

            case "getConfiguration":
                response = device.getConfiguration();
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

            case "getIdentity":
                response = device.getIdentity();
                break;


            default: throw new Exception("unknown function '"+function+"'");
            
        }
        
        return response;
    }
    
    
    /**
     * 
     * Sets the period in ms with which the :func:`Acceleration` callback is triggered
     * periodically. A value of 0 turns the callback off.
     * 
     * :func:`Acceleration` is only triggered if the acceleration has changed since the
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
     * Sets the thresholds for the :func:`AccelerationReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the temperature is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the temperature is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the temperature is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the temperature is greater than the min value (max is ignored)"
     * 
     * The default value is ('x', 0, 0, 0, 0, 0, 0).
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
     * Sets the thresholds for the :func:`AccelerationReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the temperature is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the temperature is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the temperature is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the temperature is greater than the min value (max is ignored)"
     * 
     * The default value is ('x', 0, 0, 0, 0, 0, 0).
     * 
     */
    public Short getMinX(){
        return minX;
    }

    public void setMinX(Short minX){
        this.minX = minX;
    }

    /**
     * 
     * Sets the thresholds for the :func:`AccelerationReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the temperature is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the temperature is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the temperature is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the temperature is greater than the min value (max is ignored)"
     * 
     * The default value is ('x', 0, 0, 0, 0, 0, 0).
     * 
     */
    public Short getMaxX(){
        return maxX;
    }

    public void setMaxX(Short maxX){
        this.maxX = maxX;
    }

    /**
     * 
     * Sets the thresholds for the :func:`AccelerationReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the temperature is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the temperature is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the temperature is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the temperature is greater than the min value (max is ignored)"
     * 
     * The default value is ('x', 0, 0, 0, 0, 0, 0).
     * 
     */
    public Short getMinY(){
        return minY;
    }

    public void setMinY(Short minY){
        this.minY = minY;
    }

    /**
     * 
     * Sets the thresholds for the :func:`AccelerationReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the temperature is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the temperature is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the temperature is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the temperature is greater than the min value (max is ignored)"
     * 
     * The default value is ('x', 0, 0, 0, 0, 0, 0).
     * 
     */
    public Short getMaxY(){
        return maxY;
    }

    public void setMaxY(Short maxY){
        this.maxY = maxY;
    }

    /**
     * 
     * Sets the thresholds for the :func:`AccelerationReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the temperature is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the temperature is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the temperature is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the temperature is greater than the min value (max is ignored)"
     * 
     * The default value is ('x', 0, 0, 0, 0, 0, 0).
     * 
     */
    public Short getMinZ(){
        return minZ;
    }

    public void setMinZ(Short minZ){
        this.minZ = minZ;
    }

    /**
     * 
     * Sets the thresholds for the :func:`AccelerationReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the temperature is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the temperature is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the temperature is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the temperature is greater than the min value (max is ignored)"
     * 
     * The default value is ('x', 0, 0, 0, 0, 0, 0).
     * 
     */
    public Short getMaxZ(){
        return maxZ;
    }

    public void setMaxZ(Short maxZ){
        this.maxZ = maxZ;
    }

    /**
     * 
     * Sets the period in ms with which the threshold callback
     * 
     * * :func:`AccelerationReached`
     * 
     * is triggered, if the threshold
     * 
     * * :func:`SetAccelerationCallbackThreshold`
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
     * Configures the data rate, full scale range and filter bandwidth.
     * Possible values are:
     * 
     * * Data rate of 0Hz to 1600Hz.
     * * Full scale range of -2G to +2G up to -16G to +16G.
     * * Filter bandwidth between 50Hz and 800Hz.
     * 
     * Decreasing data rate or full scale range will also decrease the noise on 
     * the data.
     * 
     * The default values are 100Hz data rate, -4G to +4G range and 200Hz
     * filter bandwidth.
     * 
     */
    public Short getDataRate(){
        return dataRate;
    }

    public void setDataRate(Short dataRate){
        this.dataRate = dataRate;
    }

    /**
     * 
     * Configures the data rate, full scale range and filter bandwidth.
     * Possible values are:
     * 
     * * Data rate of 0Hz to 1600Hz.
     * * Full scale range of -2G to +2G up to -16G to +16G.
     * * Filter bandwidth between 50Hz and 800Hz.
     * 
     * Decreasing data rate or full scale range will also decrease the noise on 
     * the data.
     * 
     * The default values are 100Hz data rate, -4G to +4G range and 200Hz
     * filter bandwidth.
     * 
     */
    public Short getFullScale(){
        return fullScale;
    }

    public void setFullScale(Short fullScale){
        this.fullScale = fullScale;
    }

    /**
     * 
     * Configures the data rate, full scale range and filter bandwidth.
     * Possible values are:
     * 
     * * Data rate of 0Hz to 1600Hz.
     * * Full scale range of -2G to +2G up to -16G to +16G.
     * * Filter bandwidth between 50Hz and 800Hz.
     * 
     * Decreasing data rate or full scale range will also decrease the noise on 
     * the data.
     * 
     * The default values are 100Hz data rate, -4G to +4G range and 200Hz
     * filter bandwidth.
     * 
     */
    public Short getFilterBandwidth(){
        return filterBandwidth;
    }

    public void setFilterBandwidth(Short filterBandwidth){
        this.filterBandwidth = filterBandwidth;
    }



}
