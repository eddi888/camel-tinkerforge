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

import com.tinkerforge.BrickletVoltageCurrent;

/**
 * Measures power, DC voltage and DC current up to 720W/36V/20A
 */
@UriEndpoint(scheme = "tinkerforgegen", syntax = "tinkerforgegen:[host[:port]/]voltagecurrent", consumerClass = VoltageCurrentConsumer.class, label = "iot", title = "Tinkerforge")
public class VoltageCurrentEndpoint extends TinkerforgeEndpoint<VoltageCurrentConsumer, VoltageCurrentProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(VoltageCurrentEndpoint.class);

    public static final String AVERAGING="averaging";
    public static final String VOLTAGECONVERSIONTIME="voltageConversionTime";
    public static final String CURRENTCONVERSIONTIME="currentConversionTime";
    public static final String GAINMULTIPLIER="gainMultiplier";
    public static final String GAINDIVISOR="gainDivisor";
    public static final String PERIOD="period";
    public static final String PERIOD2="period2";
    public static final String PERIOD3="period3";
    public static final String OPTION="option";
    public static final String MIN="min";
    public static final String MAX="max";
    public static final String OPTION2="option2";
    public static final String MIN2="min2";
    public static final String MAX2="max2";
    public static final String OPTION3="option3";
    public static final String MIN3="min3";
    public static final String MAX3="max3";
    public static final String DEBOUNCE="debounce";

    
    private Short averaging;
    private Short voltageConversionTime;
    private Short currentConversionTime;
    private Integer gainMultiplier;
    private Integer gainDivisor;
    private Long period;
    private Long period2;
    private Long period3;
    private Character option;
    private Integer min;
    private Integer max;
    private Character option2;
    private Integer min2;
    private Integer max2;
    private Character option3;
    private Integer min3;
    private Integer max3;
    private Long debounce;

        
    public VoltageCurrentEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new VoltageCurrentProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new VoltageCurrentConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletVoltageCurrent device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletVoltageCurrent device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "getCurrent":
                response = device.getCurrent();
                break;

            case "getVoltage":
                response = device.getVoltage();
                break;

            case "getPower":
                response = device.getPower();
                break;

            case "setConfiguration":
                device.setConfiguration(
                        getValue(short.class, "averaging", m, getAveraging()),
                        getValue(short.class, "voltageConversionTime", m, getVoltageConversionTime()),
                        getValue(short.class, "currentConversionTime", m, getCurrentConversionTime())
                    );
                break;

            case "getConfiguration":
                response = device.getConfiguration();
                break;

            case "setCalibration":
                device.setCalibration(
                        getValue(int.class, "gainMultiplier", m, getGainMultiplier()),
                        getValue(int.class, "gainDivisor", m, getGainDivisor())
                    );
                break;

            case "getCalibration":
                response = device.getCalibration();
                break;

            case "setCurrentCallbackPeriod":
                device.setCurrentCallbackPeriod(
                        getValue(long.class, "period", m, getPeriod())
                    );
                break;

            case "getCurrentCallbackPeriod":
                response = device.getCurrentCallbackPeriod();
                break;

            case "setVoltageCallbackPeriod":
                device.setVoltageCallbackPeriod(
                        getValue(long.class, "period2", m, getPeriod2())
                    );
                break;

            case "getVoltageCallbackPeriod":
                response = device.getVoltageCallbackPeriod();
                break;

            case "setPowerCallbackPeriod":
                device.setPowerCallbackPeriod(
                        getValue(long.class, "period3", m, getPeriod3())
                    );
                break;

            case "getPowerCallbackPeriod":
                response = device.getPowerCallbackPeriod();
                break;

            case "setCurrentCallbackThreshold":
                device.setCurrentCallbackThreshold(
                        getValue(char.class, "option", m, getOption()),
                        getValue(int.class, "min", m, getMin()),
                        getValue(int.class, "max", m, getMax())
                    );
                break;

            case "getCurrentCallbackThreshold":
                response = device.getCurrentCallbackThreshold();
                break;

            case "setVoltageCallbackThreshold":
                device.setVoltageCallbackThreshold(
                        getValue(char.class, "option2", m, getOption2()),
                        getValue(int.class, "min2", m, getMin2()),
                        getValue(int.class, "max2", m, getMax2())
                    );
                break;

            case "getVoltageCallbackThreshold":
                response = device.getVoltageCallbackThreshold();
                break;

            case "setPowerCallbackThreshold":
                device.setPowerCallbackThreshold(
                        getValue(char.class, "option3", m, getOption3()),
                        getValue(int.class, "min3", m, getMin3()),
                        getValue(int.class, "max3", m, getMax3())
                    );
                break;

            case "getPowerCallbackThreshold":
                response = device.getPowerCallbackThreshold();
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
     * Sets the configuration of the Voltage/Current Bricklet. It is
     * possible to configure number of averages as well as
     * voltage and current conversion time.
     * 
     * Averaging:
     * 
     * .. csv-table::
     *  :header: "Value", "Number of Averages"
     *  :widths: 20, 20
     * 
     *  "0",    "1"
     *  "1",    "4"
     *  "2",    "16"
     *  "3",    "64"
     *  "4",    "128"
     *  "5",    "256"
     *  "6",    "512"
     *  ">=7",  "1024"
     * 
     * Voltage/Current conversion:
     * 
     * .. csv-table::
     *  :header: "Value", "Conversion time"
     *  :widths: 20, 20
     * 
     *  "0",    "140µs"
     *  "1",    "204µs"
     *  "2",    "332µs"
     *  "3",    "588µs"
     *  "4",    "1.1ms"
     *  "5",    "2.116ms"
     *  "6",    "4.156ms"
     *  ">=7",  "8.244ms"
     * 
     * The default values are 3, 4 and 4 (64, 1.1ms, 1.1ms) for averaging, voltage 
     * conversion and current conversion.
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
     * Sets the configuration of the Voltage/Current Bricklet. It is
     * possible to configure number of averages as well as
     * voltage and current conversion time.
     * 
     * Averaging:
     * 
     * .. csv-table::
     *  :header: "Value", "Number of Averages"
     *  :widths: 20, 20
     * 
     *  "0",    "1"
     *  "1",    "4"
     *  "2",    "16"
     *  "3",    "64"
     *  "4",    "128"
     *  "5",    "256"
     *  "6",    "512"
     *  ">=7",  "1024"
     * 
     * Voltage/Current conversion:
     * 
     * .. csv-table::
     *  :header: "Value", "Conversion time"
     *  :widths: 20, 20
     * 
     *  "0",    "140µs"
     *  "1",    "204µs"
     *  "2",    "332µs"
     *  "3",    "588µs"
     *  "4",    "1.1ms"
     *  "5",    "2.116ms"
     *  "6",    "4.156ms"
     *  ">=7",  "8.244ms"
     * 
     * The default values are 3, 4 and 4 (64, 1.1ms, 1.1ms) for averaging, voltage 
     * conversion and current conversion.
     * 
     */
    public Short getVoltageConversionTime(){
        return voltageConversionTime;
    }

    public void setVoltageConversionTime(Short voltageConversionTime){
        this.voltageConversionTime = voltageConversionTime;
    }

    /**
     * 
     * Sets the configuration of the Voltage/Current Bricklet. It is
     * possible to configure number of averages as well as
     * voltage and current conversion time.
     * 
     * Averaging:
     * 
     * .. csv-table::
     *  :header: "Value", "Number of Averages"
     *  :widths: 20, 20
     * 
     *  "0",    "1"
     *  "1",    "4"
     *  "2",    "16"
     *  "3",    "64"
     *  "4",    "128"
     *  "5",    "256"
     *  "6",    "512"
     *  ">=7",  "1024"
     * 
     * Voltage/Current conversion:
     * 
     * .. csv-table::
     *  :header: "Value", "Conversion time"
     *  :widths: 20, 20
     * 
     *  "0",    "140µs"
     *  "1",    "204µs"
     *  "2",    "332µs"
     *  "3",    "588µs"
     *  "4",    "1.1ms"
     *  "5",    "2.116ms"
     *  "6",    "4.156ms"
     *  ">=7",  "8.244ms"
     * 
     * The default values are 3, 4 and 4 (64, 1.1ms, 1.1ms) for averaging, voltage 
     * conversion and current conversion.
     * 
     */
    public Short getCurrentConversionTime(){
        return currentConversionTime;
    }

    public void setCurrentConversionTime(Short currentConversionTime){
        this.currentConversionTime = currentConversionTime;
    }

    /**
     * 
     * Since the shunt resistor that is used to measure the current is not
     * perfectly precise, it needs to be calibrated by a multiplier and
     * divisor if a very precise reading is needed.
     * 
     * For example, if you are expecting a measurement of 1000mA and you
     * are measuring 1023mA, you can calibrate the Voltage/Current Bricklet 
     * by setting the multiplier to 1000 and the divisor to 1023.
     * 
     */
    public Integer getGainMultiplier(){
        return gainMultiplier;
    }

    public void setGainMultiplier(Integer gainMultiplier){
        this.gainMultiplier = gainMultiplier;
    }

    /**
     * 
     * Since the shunt resistor that is used to measure the current is not
     * perfectly precise, it needs to be calibrated by a multiplier and
     * divisor if a very precise reading is needed.
     * 
     * For example, if you are expecting a measurement of 1000mA and you
     * are measuring 1023mA, you can calibrate the Voltage/Current Bricklet 
     * by setting the multiplier to 1000 and the divisor to 1023.
     * 
     */
    public Integer getGainDivisor(){
        return gainDivisor;
    }

    public void setGainDivisor(Integer gainDivisor){
        this.gainDivisor = gainDivisor;
    }

    /**
     * 
     * Sets the period in ms with which the :func:`Current` callback is triggered
     * periodically. A value of 0 turns the callback off.
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
     * Sets the period in ms with which the :func:`Voltage` callback is triggered
     * periodically. A value of 0 turns the callback off.
     * 
     * :func:`Voltage` is only triggered if the voltage has changed since the
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
     * Sets the period in ms with which the :func:`Power` callback is triggered
     * periodically. A value of 0 turns the callback off.
     * 
     * :func:`Power` is only triggered if the power has changed since the
     * last triggering.
     * 
     * The default value is 0.
     * 
     */
    public Long getPeriod3(){
        return period3;
    }

    public void setPeriod3(Long period3){
        this.period3 = period3;
    }

    /**
     * 
     * Sets the thresholds for the :func:`CurrentReached` callback. 
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
     * Sets the thresholds for the :func:`CurrentReached` callback. 
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
     * Sets the thresholds for the :func:`CurrentReached` callback. 
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
    public Character getOption2(){
        return option2;
    }

    public void setOption2(Character option2){
        this.option2 = option2;
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
    public Integer getMin2(){
        return min2;
    }

    public void setMin2(Integer min2){
        this.min2 = min2;
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
    public Integer getMax2(){
        return max2;
    }

    public void setMax2(Integer max2){
        this.max2 = max2;
    }

    /**
     * 
     * Sets the thresholds for the :func:`PowerReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the power is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the power is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the power is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the power is greater than the min value (max is ignored)"
     * 
     * The default value is ('x', 0, 0).
     * 
     */
    public Character getOption3(){
        return option3;
    }

    public void setOption3(Character option3){
        this.option3 = option3;
    }

    /**
     * 
     * Sets the thresholds for the :func:`PowerReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the power is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the power is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the power is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the power is greater than the min value (max is ignored)"
     * 
     * The default value is ('x', 0, 0).
     * 
     */
    public Integer getMin3(){
        return min3;
    }

    public void setMin3(Integer min3){
        this.min3 = min3;
    }

    /**
     * 
     * Sets the thresholds for the :func:`PowerReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the power is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the power is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the power is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the power is greater than the min value (max is ignored)"
     * 
     * The default value is ('x', 0, 0).
     * 
     */
    public Integer getMax3(){
        return max3;
    }

    public void setMax3(Integer max3){
        this.max3 = max3;
    }

    /**
     * 
     * Sets the period in ms with which the threshold callbacks
     * 
     * * :func:`CurrentReached`,
     * * :func:`VoltageReached`,
     * * :func:`PowerReached`
     * 
     * are triggered, if the thresholds
     * 
     * * :func:`SetCurrentCallbackThreshold`,
     * * :func:`SetVoltageCallbackThreshold`,
     * * :func:`SetPowerCallbackThreshold`
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
