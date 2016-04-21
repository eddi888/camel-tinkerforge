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

import com.tinkerforge.BrickletUVLight;

/**
 * Measures UV light
 */
@UriEndpoint(scheme = "tinkerforgegen", syntax = "tinkerforgegen:[host[:port]/]uvlight", consumerClass = UVLightConsumer.class, label = "iot", title = "Tinkerforge")
public class UVLightEndpoint extends TinkerforgeEndpoint<UVLightConsumer, UVLightProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(UVLightEndpoint.class);

    public static final String PERIOD="period";
    public static final String OPTION="option";
    public static final String MIN="min";
    public static final String MAX="max";
    public static final String DEBOUNCE="debounce";

    
    private Long period;
    private Character option;
    private Long min;
    private Long max;
    private Long debounce;

        
    public UVLightEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new UVLightProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new UVLightConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletUVLight device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletUVLight device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "getUvLight":
                response = device.getUVLight();
                break;

            case "setUvLightCallbackPeriod":
                device.setUVLightCallbackPeriod(
                        getValue(long.class, "period", m, getPeriod())
                    );
                break;

            case "getUvLightCallbackPeriod":
                response = device.getUVLightCallbackPeriod();
                break;

            case "setUvLightCallbackThreshold":
                device.setUVLightCallbackThreshold(
                        getValue(char.class, "option", m, getOption()),
                        getValue(long.class, "min", m, getMin()),
                        getValue(long.class, "max", m, getMax())
                    );
                break;

            case "getUvLightCallbackThreshold":
                response = device.getUVLightCallbackThreshold();
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
     * Sets the period in ms with which the :func:`UVLight` callback is triggered
     * periodically. A value of 0 turns the callback off.
     * 
     * :func:`UVLight` is only triggered if the intensity has changed since the
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
     * Sets the thresholds for the :func:`UVLightReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the intensity is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the intensity is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the intensity is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the intensity is greater than the min value (max is ignored)"
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
     * Sets the thresholds for the :func:`UVLightReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the intensity is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the intensity is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the intensity is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the intensity is greater than the min value (max is ignored)"
     * 
     * The default value is ('x', 0, 0).
     * 
     */
    public Long getMin(){
        return min;
    }

    public void setMin(Long min){
        this.min = min;
    }

    /**
     * 
     * Sets the thresholds for the :func:`UVLightReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the intensity is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the intensity is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the intensity is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the intensity is greater than the min value (max is ignored)"
     * 
     * The default value is ('x', 0, 0).
     * 
     */
    public Long getMax(){
        return max;
    }

    public void setMax(Long max){
        this.max = max;
    }

    /**
     * 
     * Sets the period in ms with which the threshold callbacks
     * 
     * * :func:`UVLightReached`,
     * 
     * are triggered, if the thresholds
     * 
     * * :func:`SetUVLightCallbackThreshold`,
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
