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

import com.tinkerforge.BrickletAmbientLightV2;

/**
 * Measures ambient light up to 64000lux
 */
@UriEndpoint(scheme = "tinkerforgegen", syntax = "tinkerforgegen:[host[:port]/]ambientlightv2", consumerClass = AmbientLightV2Consumer.class, label = "iot", title = "Tinkerforge")
public class AmbientLightV2Endpoint extends TinkerforgeEndpoint<AmbientLightV2Consumer, AmbientLightV2Producer> {

    private static final Logger LOG = LoggerFactory.getLogger(AmbientLightV2Endpoint.class);

    public static final String PERIOD="period";
    public static final String OPTION="option";
    public static final String MIN="min";
    public static final String MAX="max";
    public static final String DEBOUNCE="debounce";
    public static final String ILLUMINANCERANGE="illuminanceRange";
    public static final String INTEGRATIONTIME="integrationTime";

    
    private Long period;
    private Character option;
    private Long min;
    private Long max;
    private Long debounce;
    private Short illuminanceRange;
    private Short integrationTime;

        
    public AmbientLightV2Endpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new AmbientLightV2Producer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new AmbientLightV2Consumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletAmbientLightV2 device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletAmbientLightV2 device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "getIlluminance":
                response = device.getIlluminance();
                break;

            case "setIlluminanceCallbackPeriod":
                device.setIlluminanceCallbackPeriod(
                        getValue(long.class, "period", m, getPeriod())
                    );
                break;

            case "getIlluminanceCallbackPeriod":
                response = device.getIlluminanceCallbackPeriod();
                break;

            case "setIlluminanceCallbackThreshold":
                device.setIlluminanceCallbackThreshold(
                        getValue(char.class, "option", m, getOption()),
                        getValue(long.class, "min", m, getMin()),
                        getValue(long.class, "max", m, getMax())
                    );
                break;

            case "getIlluminanceCallbackThreshold":
                response = device.getIlluminanceCallbackThreshold();
                break;

            case "setDebouncePeriod":
                device.setDebouncePeriod(
                        getValue(long.class, "debounce", m, getDebounce())
                    );
                break;

            case "getDebouncePeriod":
                response = device.getDebouncePeriod();
                break;

            case "setConfiguration":
                device.setConfiguration(
                        getValue(short.class, "illuminanceRange", m, getIlluminanceRange()),
                        getValue(short.class, "integrationTime", m, getIntegrationTime())
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
    public Long getMin(){
        return min;
    }

    public void setMin(Long min){
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
     * * :func:`IlluminanceReached`,
     * 
     * are triggered, if the thresholds
     * 
     * * :func:`SetIlluminanceCallbackThreshold`,
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
     * Sets the configuration. It is possible to configure an illuminance range
     * between 0-600lux and 0-64000lux and an integration time between 50ms and 400ms.
     * 
     * .. versionadded:: 2.0.2$nbsp;(Plugin)
     *   The unlimited illuminance range allows to measure up to about 100000lux, but
     *   above 64000lux the precision starts to drop.
     * 
     * A smaller illuminance range increases the resolution of the data. A longer
     * integration time will result in less noise on the data.
     * 
     * .. versionchanged:: 2.0.2$nbsp;(Plugin)
     *   If the actual measure illuminance is out-of-range then the current illuminance
     *   range maximum +0.01lux is reported by :func:`GetIlluminance` and the
     *   :func:`Illuminance` callback. For example, 800001 for the 0-8000lux range.
     * 
     * .. versionchanged:: 2.0.2$nbsp;(Plugin)
     *   With a long integration time the sensor might be saturated before the measured
     *   value reaches the maximum of the selected illuminance range. In this case 0lux
     *   is reported by :func:`GetIlluminance` and the :func:`Illuminance` callback.
     * 
     * If the measurement is out-of-range or the sensor is saturated then you should
     * configure the next higher illuminance range. If the highest range is already
     * in use, then start to reduce the integration time.
     * 
     * The default values are 0-8000lux illuminance range and 200ms integration time.
     * 
     */
    public Short getIlluminanceRange(){
        return illuminanceRange;
    }

    public void setIlluminanceRange(Short illuminanceRange){
        this.illuminanceRange = illuminanceRange;
    }

    /**
     * 
     * Sets the configuration. It is possible to configure an illuminance range
     * between 0-600lux and 0-64000lux and an integration time between 50ms and 400ms.
     * 
     * .. versionadded:: 2.0.2$nbsp;(Plugin)
     *   The unlimited illuminance range allows to measure up to about 100000lux, but
     *   above 64000lux the precision starts to drop.
     * 
     * A smaller illuminance range increases the resolution of the data. A longer
     * integration time will result in less noise on the data.
     * 
     * .. versionchanged:: 2.0.2$nbsp;(Plugin)
     *   If the actual measure illuminance is out-of-range then the current illuminance
     *   range maximum +0.01lux is reported by :func:`GetIlluminance` and the
     *   :func:`Illuminance` callback. For example, 800001 for the 0-8000lux range.
     * 
     * .. versionchanged:: 2.0.2$nbsp;(Plugin)
     *   With a long integration time the sensor might be saturated before the measured
     *   value reaches the maximum of the selected illuminance range. In this case 0lux
     *   is reported by :func:`GetIlluminance` and the :func:`Illuminance` callback.
     * 
     * If the measurement is out-of-range or the sensor is saturated then you should
     * configure the next higher illuminance range. If the highest range is already
     * in use, then start to reduce the integration time.
     * 
     * The default values are 0-8000lux illuminance range and 200ms integration time.
     * 
     */
    public Short getIntegrationTime(){
        return integrationTime;
    }

    public void setIntegrationTime(Short integrationTime){
        this.integrationTime = integrationTime;
    }



}
