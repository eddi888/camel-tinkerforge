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

import com.tinkerforge.BrickletSoundIntensity;

/**
 * Measures sound intensity
 */
@UriEndpoint(scheme = "tinkerforgegen", syntax = "tinkerforgegen:[host[:port]/]soundintensity", consumerClass = SoundIntensityConsumer.class, label = "iot", title = "Tinkerforge")
public class SoundIntensityEndpoint extends TinkerforgeEndpoint<SoundIntensityConsumer, SoundIntensityProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(SoundIntensityEndpoint.class);

    public static final String PERIOD="period";
    public static final String OPTION="option";
    public static final String MIN="min";
    public static final String MAX="max";
    public static final String DEBOUNCE="debounce";

    
    private Long period;
    private Character option;
    private Integer min;
    private Integer max;
    private Long debounce;

        
    public SoundIntensityEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new SoundIntensityProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new SoundIntensityConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletSoundIntensity device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletSoundIntensity device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "getIntensity":
                response = device.getIntensity();
                break;

            case "setIntensityCallbackPeriod":
                device.setIntensityCallbackPeriod(
                        getValue(long.class, "period", m, getPeriod())
                    );
                break;

            case "getIntensityCallbackPeriod":
                response = device.getIntensityCallbackPeriod();
                break;

            case "setIntensityCallbackThreshold":
                device.setIntensityCallbackThreshold(
                        getValue(char.class, "option", m, getOption()),
                        getValue(int.class, "min", m, getMin()),
                        getValue(int.class, "max", m, getMax())
                    );
                break;

            case "getIntensityCallbackThreshold":
                response = device.getIntensityCallbackThreshold();
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
     * Sets the period in ms with which the :func:`Intensity` callback is triggered
     * periodically. A value of 0 turns the callback off.
     * 
     * :func:`Intensity` is only triggered if the intensity has changed since the
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
     * Sets the thresholds for the :func:`IntensityReached` callback. 
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
     * Sets the thresholds for the :func:`IntensityReached` callback. 
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
    public Integer getMin(){
        return min;
    }

    public void setMin(Integer min){
        this.min = min;
    }

    /**
     * 
     * Sets the thresholds for the :func:`IntensityReached` callback. 
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
     * * :func:`IntensityReached`
     * 
     * is triggered, if the thresholds
     * 
     * * :func:`SetIntensityCallbackThreshold`
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



}
