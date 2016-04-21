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

import com.tinkerforge.BrickletThermocouple;

/**
 * Measures temperature with thermocouples
 */
@UriEndpoint(scheme = "tinkerforgegen", syntax = "tinkerforgegen:[host[:port]/]thermocouple", consumerClass = ThermocoupleConsumer.class, label = "iot", title = "Tinkerforge")
public class ThermocoupleEndpoint extends TinkerforgeEndpoint<ThermocoupleConsumer, ThermocoupleProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(ThermocoupleEndpoint.class);

    public static final String PERIOD="period";
    public static final String OPTION="option";
    public static final String MIN="min";
    public static final String MAX="max";
    public static final String DEBOUNCE="debounce";
    public static final String AVERAGING="averaging";
    public static final String THERMOCOUPLETYPE="thermocoupleType";
    public static final String FILTER="filter";

    
    private Long period;
    private Character option;
    private Integer min;
    private Integer max;
    private Long debounce;
    private Short averaging;
    private Short thermocoupleType;
    private Short filter;

        
    public ThermocoupleEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new ThermocoupleProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new ThermocoupleConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletThermocouple device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletThermocouple device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "getTemperature":
                response = device.getTemperature();
                break;

            case "setTemperatureCallbackPeriod":
                device.setTemperatureCallbackPeriod(
                        getValue(long.class, "period", m, getPeriod())
                    );
                break;

            case "getTemperatureCallbackPeriod":
                response = device.getTemperatureCallbackPeriod();
                break;

            case "setTemperatureCallbackThreshold":
                device.setTemperatureCallbackThreshold(
                        getValue(char.class, "option", m, getOption()),
                        getValue(int.class, "min", m, getMin()),
                        getValue(int.class, "max", m, getMax())
                    );
                break;

            case "getTemperatureCallbackThreshold":
                response = device.getTemperatureCallbackThreshold();
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
                        getValue(short.class, "averaging", m, getAveraging()),
                        getValue(short.class, "thermocoupleType", m, getThermocoupleType()),
                        getValue(short.class, "filter", m, getFilter())
                    );
                break;

            case "getConfiguration":
                response = device.getConfiguration();
                break;

            case "getErrorState":
                response = device.getErrorState();
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
     * Sets the period in ms with which the :func:`Temperature` callback is triggered
     * periodically. A value of 0 turns the callback off.
     * 
     * :func:`Temperature` is only triggered if the temperature has changed since the
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
     * Sets the thresholds for the :func:`TemperatureReached` callback. 
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
     * Sets the thresholds for the :func:`TemperatureReached` callback. 
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
     * Sets the thresholds for the :func:`TemperatureReached` callback. 
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
     * * :func:`TemperatureReached`
     * 
     * is triggered, if the threshold
     * 
     * * :func:`SetTemperatureCallbackThreshold`
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
     * You can configure averaging size, thermocouple type and frequency
     * filtering.
     * 
     * Available averaging sizes are 1, 2, 4, 8 and 16 samples.
     * 
     * As thermocouple type you can use B, E, J, K, N, R, S and T. If you have a
     * different thermocouple or a custom thermocouple you can also use
     * G8 and G32. With these types the returned value will not be in °C/100,
     * it will be calculated by the following formulas:
     * 
     * * G8: ``value = 8 * 1.6 * 2^17 * Vin``
     * * G32: ``value = 32 * 1.6 * 2^17 * Vin``
     * 
     * where Vin is the thermocouple input voltage.
     * 
     * The frequency filter can be either configured to 50Hz or to 60Hz. You should
     * configure it according to your utility frequency.
     * 
     * The conversion time depends on the averaging and filter configuration, it can
     * be calculated as follows:
     * 
     * * 60Hz: ``time = 82 + (samples - 1) * 16.67``
     * * 50Hz: ``time = 98 + (samples - 1) * 20``
     * 
     * The default configuration is 16 samples, K type and 50Hz.
     * 
     */
    public Short getAveraging(){
        return averaging;
    }

    public void setAveraging(Short averaging){
        this.averaging = averaging;
    }

    /**
     * 
     * You can configure averaging size, thermocouple type and frequency
     * filtering.
     * 
     * Available averaging sizes are 1, 2, 4, 8 and 16 samples.
     * 
     * As thermocouple type you can use B, E, J, K, N, R, S and T. If you have a
     * different thermocouple or a custom thermocouple you can also use
     * G8 and G32. With these types the returned value will not be in °C/100,
     * it will be calculated by the following formulas:
     * 
     * * G8: ``value = 8 * 1.6 * 2^17 * Vin``
     * * G32: ``value = 32 * 1.6 * 2^17 * Vin``
     * 
     * where Vin is the thermocouple input voltage.
     * 
     * The frequency filter can be either configured to 50Hz or to 60Hz. You should
     * configure it according to your utility frequency.
     * 
     * The conversion time depends on the averaging and filter configuration, it can
     * be calculated as follows:
     * 
     * * 60Hz: ``time = 82 + (samples - 1) * 16.67``
     * * 50Hz: ``time = 98 + (samples - 1) * 20``
     * 
     * The default configuration is 16 samples, K type and 50Hz.
     * 
     */
    public Short getThermocoupleType(){
        return thermocoupleType;
    }

    public void setThermocoupleType(Short thermocoupleType){
        this.thermocoupleType = thermocoupleType;
    }

    /**
     * 
     * You can configure averaging size, thermocouple type and frequency
     * filtering.
     * 
     * Available averaging sizes are 1, 2, 4, 8 and 16 samples.
     * 
     * As thermocouple type you can use B, E, J, K, N, R, S and T. If you have a
     * different thermocouple or a custom thermocouple you can also use
     * G8 and G32. With these types the returned value will not be in °C/100,
     * it will be calculated by the following formulas:
     * 
     * * G8: ``value = 8 * 1.6 * 2^17 * Vin``
     * * G32: ``value = 32 * 1.6 * 2^17 * Vin``
     * 
     * where Vin is the thermocouple input voltage.
     * 
     * The frequency filter can be either configured to 50Hz or to 60Hz. You should
     * configure it according to your utility frequency.
     * 
     * The conversion time depends on the averaging and filter configuration, it can
     * be calculated as follows:
     * 
     * * 60Hz: ``time = 82 + (samples - 1) * 16.67``
     * * 50Hz: ``time = 98 + (samples - 1) * 20``
     * 
     * The default configuration is 16 samples, K type and 50Hz.
     * 
     */
    public Short getFilter(){
        return filter;
    }

    public void setFilter(Short filter){
        this.filter = filter;
    }



}
