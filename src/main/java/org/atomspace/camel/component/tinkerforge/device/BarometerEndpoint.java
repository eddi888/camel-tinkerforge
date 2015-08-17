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

import com.tinkerforge.BrickletBarometer;

/**
 * Measures air pressure and altitude changes
 */
public class BarometerEndpoint extends TinkerforgeEndpoint<BarometerConsumer, BarometerProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(BarometerEndpoint.class);
    
    private Long period;
    private Long period2;
    private Character option;
    private Integer min;
    private Integer max;
    private Character option2;
    private Integer min2;
    private Integer max2;
    private Long debounce;
    private Integer airPressure;
    private Short movingAveragePressure;
    private Short averagePressure;
    private Short averageTemperature;

        
    public BarometerEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new BarometerProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new BarometerConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletBarometer device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletBarometer device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "getAirPressure":
                response = device.getAirPressure();
                break;

            case "getAltitude":
                response = device.getAltitude();
                break;

            case "setAirPressureCallbackPeriod":
                device.setAirPressureCallbackPeriod(
                        getValue(long.class, "period", m, getPeriod())
                    );
                break;

            case "getAirPressureCallbackPeriod":
                response = device.getAirPressureCallbackPeriod();
                break;

            case "setAltitudeCallbackPeriod":
                device.setAltitudeCallbackPeriod(
                        getValue(long.class, "period2", m, getPeriod2())
                    );
                break;

            case "getAltitudeCallbackPeriod":
                response = device.getAltitudeCallbackPeriod();
                break;

            case "setAirPressureCallbackThreshold":
                device.setAirPressureCallbackThreshold(
                        getValue(char.class, "option", m, getOption()),
                        getValue(int.class, "min", m, getMin()),
                        getValue(int.class, "max", m, getMax())
                    );
                break;

            case "getAirPressureCallbackThreshold":
                response = device.getAirPressureCallbackThreshold();
                break;

            case "setAltitudeCallbackThreshold":
                device.setAltitudeCallbackThreshold(
                        getValue(char.class, "option2", m, getOption2()),
                        getValue(int.class, "min2", m, getMin2()),
                        getValue(int.class, "max2", m, getMax2())
                    );
                break;

            case "getAltitudeCallbackThreshold":
                response = device.getAltitudeCallbackThreshold();
                break;

            case "setDebouncePeriod":
                device.setDebouncePeriod(
                        getValue(long.class, "debounce", m, getDebounce())
                    );
                break;

            case "getDebouncePeriod":
                response = device.getDebouncePeriod();
                break;

            case "setReferenceAirPressure":
                device.setReferenceAirPressure(
                        getValue(int.class, "airPressure", m, getAirPressure())
                    );
                break;

            case "getChipTemperature":
                response = device.getChipTemperature();
                break;

            case "getReferenceAirPressure":
                response = device.getReferenceAirPressure();
                break;

            case "setAveraging":
                device.setAveraging(
                        getValue(short.class, "movingAveragePressure", m, getMovingAveragePressure()),
                        getValue(short.class, "averagePressure", m, getAveragePressure()),
                        getValue(short.class, "averageTemperature", m, getAverageTemperature())
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
     * Sets the period in ms with which the :func:`AirPressure` callback is triggered
     * periodically. A value of 0 turns the callback off.
     * 
     * :func:`AirPressure` is only triggered if the air pressure has changed since the
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
     * Sets the period in ms with which the :func:`Altitude` callback is triggered
     * periodically. A value of 0 turns the callback off.
     * 
     * :func:`Altitude` is only triggered if the altitude has changed since the
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
     * Sets the thresholds for the :func:`AirPressureReached` callback.
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the air pressure is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the air pressure is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the air pressure is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the air pressure is greater than the min value (max is ignored)"
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
     * Sets the thresholds for the :func:`AirPressureReached` callback.
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the air pressure is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the air pressure is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the air pressure is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the air pressure is greater than the min value (max is ignored)"
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
     * Sets the thresholds for the :func:`AirPressureReached` callback.
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the air pressure is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the air pressure is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the air pressure is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the air pressure is greater than the min value (max is ignored)"
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
     * Sets the thresholds for the :func:`AltitudeReached` callback.
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the altitude is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the altitude is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the altitude is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the altitude is greater than the min value (max is ignored)"
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
     * Sets the thresholds for the :func:`AltitudeReached` callback.
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the altitude is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the altitude is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the altitude is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the altitude is greater than the min value (max is ignored)"
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
     * Sets the thresholds for the :func:`AltitudeReached` callback.
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the altitude is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the altitude is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the altitude is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the altitude is greater than the min value (max is ignored)"
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
     * * :func:`AirPressureReached`,
     * * :func:`AltitudeReached`
     * 
     * are triggered, if the thresholds
     * 
     * * :func:`SetAirPressureCallbackThreshold`,
     * * :func:`SetAltitudeCallbackThreshold`
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
     * Sets the reference air pressure in mbar/1000 for the altitude calculation.
     * Setting the reference to the current air pressure results in a calculated
     * altitude of 0cm. Passing 0 is a shortcut for passing the current air pressure as
     * reference.
     * 
     * Well known reference values are the Q codes
     * `QNH <https://en.wikipedia.org/wiki/QNH>`__ and
     * `QFE <https://en.wikipedia.org/wiki/Mean_sea_level_pressure#Mean_sea_level_pressure>`__
     * used in aviation.
     * 
     * The default value is 1013.25mbar.
     * 
     */
    public Integer getAirPressure(){
        return airPressure;
    }

    public void setAirPressure(Integer airPressure){
        this.airPressure = airPressure;
    }

    /**
     * 
     * Sets the different averaging parameters. It is possible to set
     * the length of a normal averaging for the temperature and pressure,
     * as well as an additional length of a 
     * `moving average <https://en.wikipedia.org/wiki/Moving_average>`__
     * for the pressure. The moving average is calculated from the normal 
     * averages.  There is no moving average for the temperature.
     * 
     * The maximum length for the pressure average is 10, for the
     * temperature average is 255 and for the moving average is 25.
     * 
     * Setting the all three parameters to 0 will turn the averaging
     * completely off. If the averaging is off, there is lots of noise
     * on the data, but the data is without delay. Thus we recommend
     * to turn the averaging off if the Barometer Bricklet data is
     * to be used for sensor fusion with other sensors.
     * 
     * The default values are 10 for the normal averages and 25 for the
     * moving average.
     * 
     */
    public Short getMovingAveragePressure(){
        return movingAveragePressure;
    }

    public void setMovingAveragePressure(Short movingAveragePressure){
        this.movingAveragePressure = movingAveragePressure;
    }

    /**
     * 
     * Sets the different averaging parameters. It is possible to set
     * the length of a normal averaging for the temperature and pressure,
     * as well as an additional length of a 
     * `moving average <https://en.wikipedia.org/wiki/Moving_average>`__
     * for the pressure. The moving average is calculated from the normal 
     * averages.  There is no moving average for the temperature.
     * 
     * The maximum length for the pressure average is 10, for the
     * temperature average is 255 and for the moving average is 25.
     * 
     * Setting the all three parameters to 0 will turn the averaging
     * completely off. If the averaging is off, there is lots of noise
     * on the data, but the data is without delay. Thus we recommend
     * to turn the averaging off if the Barometer Bricklet data is
     * to be used for sensor fusion with other sensors.
     * 
     * The default values are 10 for the normal averages and 25 for the
     * moving average.
     * 
     */
    public Short getAveragePressure(){
        return averagePressure;
    }

    public void setAveragePressure(Short averagePressure){
        this.averagePressure = averagePressure;
    }

    /**
     * 
     * Sets the different averaging parameters. It is possible to set
     * the length of a normal averaging for the temperature and pressure,
     * as well as an additional length of a 
     * `moving average <https://en.wikipedia.org/wiki/Moving_average>`__
     * for the pressure. The moving average is calculated from the normal 
     * averages.  There is no moving average for the temperature.
     * 
     * The maximum length for the pressure average is 10, for the
     * temperature average is 255 and for the moving average is 25.
     * 
     * Setting the all three parameters to 0 will turn the averaging
     * completely off. If the averaging is off, there is lots of noise
     * on the data, but the data is without delay. Thus we recommend
     * to turn the averaging off if the Barometer Bricklet data is
     * to be used for sensor fusion with other sensors.
     * 
     * The default values are 10 for the normal averages and 25 for the
     * moving average.
     * 
     */
    public Short getAverageTemperature(){
        return averageTemperature;
    }

    public void setAverageTemperature(Short averageTemperature){
        this.averageTemperature = averageTemperature;
    }



}
