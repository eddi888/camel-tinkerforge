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

import com.tinkerforge.BrickletAmbientLight;

/**
 * Measures ambient light up to 900lux
 */
@UriEndpoint(scheme = "tinkerforgegen", syntax = "tinkerforgegen:[host[:port]/]ambientlight", consumerClass = AmbientLightConsumer.class, label = "iot", title = "Tinkerforge")
public class AmbientLightEndpoint extends TinkerforgeEndpoint<AmbientLightConsumer, AmbientLightProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(AmbientLightEndpoint.class);

    public static final String PERIOD="period";
    public static final String PERIOD2="period2";
    public static final String OPTION="option";
    public static final String MIN="min";
    public static final String MAX="max";
    public static final String OPTION2="option2";
    public static final String MIN2="min2";
    public static final String MAX2="max2";
    public static final String DEBOUNCE="debounce";

    
    private Long period;
    private Long period2;
    private Character option;
    private Short min;
    private Short max;
    private Character option2;
    private Integer min2;
    private Integer max2;
    private Long debounce;

        
    public AmbientLightEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new AmbientLightProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new AmbientLightConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletAmbientLight device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletAmbientLight device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "getIlluminance":
                response = device.getIlluminance();
                break;

            case "getAnalogValue":
                response = device.getAnalogValue();
                break;

            case "setIlluminanceCallbackPeriod":
                device.setIlluminanceCallbackPeriod(
                        getValue(long.class, "period", m, getPeriod())
                    );
                break;

            case "getIlluminanceCallbackPeriod":
                response = device.getIlluminanceCallbackPeriod();
                break;

            case "setAnalogValueCallbackPeriod":
                device.setAnalogValueCallbackPeriod(
                        getValue(long.class, "period2", m, getPeriod2())
                    );
                break;

            case "getAnalogValueCallbackPeriod":
                response = device.getAnalogValueCallbackPeriod();
                break;

            case "setIlluminanceCallbackThreshold":
                device.setIlluminanceCallbackThreshold(
                        getValue(char.class, "option", m, getOption()),
                        getValue(short.class, "min", m, getMin()),
                        getValue(short.class, "max", m, getMax())
                    );
                break;

            case "getIlluminanceCallbackThreshold":
                response = device.getIlluminanceCallbackThreshold();
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
     * Sets the period in ms with which the :func:`Illuminance` callback is triggered
     * periodically. A value of 0 turns the callback off.
     * 
     * :func:`Illuminance` is only triggered if the illuminance has changed since the
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
     * Sets the thresholds for the :func:`IlluminanceReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the illuminance is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the illuminance is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the illuminance is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the illuminance is greater than the min value (max is ignored)"
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
     * Sets the thresholds for the :func:`IlluminanceReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the illuminance is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the illuminance is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the illuminance is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the illuminance is greater than the min value (max is ignored)"
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
     * Sets the thresholds for the :func:`IlluminanceReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the illuminance is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the illuminance is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the illuminance is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the illuminance is greater than the min value (max is ignored)"
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
     * * :func:`IlluminanceReached`,
     * * :func:`AnalogValueReached`
     * 
     * are triggered, if the thresholds
     * 
     * * :func:`SetIlluminanceCallbackThreshold`,
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
