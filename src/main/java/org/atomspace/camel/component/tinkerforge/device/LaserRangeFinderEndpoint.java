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

import com.tinkerforge.BrickletLaserRangeFinder;

/**
 * Measures distance up to 40m with laser light
 */
public class LaserRangeFinderEndpoint extends TinkerforgeEndpoint<LaserRangeFinderConsumer, LaserRangeFinderProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(LaserRangeFinderEndpoint.class);
    
    private Long period;
    private Long period2;
    private Character option;
    private Integer min;
    private Integer max;
    private Character option2;
    private Short min2;
    private Short max2;
    private Long debounce;
    private Short distanceAverageLength;
    private Short velocityAverageLength;
    private Short mode;

        
    public LaserRangeFinderEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new LaserRangeFinderProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new LaserRangeFinderConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletLaserRangeFinder device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletLaserRangeFinder device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "getDistance":
                response = device.getDistance();
                break;

            case "getVelocity":
                response = device.getVelocity();
                break;

            case "setDistanceCallbackPeriod":
                device.setDistanceCallbackPeriod(
                        getValue(long.class, "period", m, getPeriod())
                    );
                break;

            case "getDistanceCallbackPeriod":
                response = device.getDistanceCallbackPeriod();
                break;

            case "setVelocityCallbackPeriod":
                device.setVelocityCallbackPeriod(
                        getValue(long.class, "period2", m, getPeriod2())
                    );
                break;

            case "getVelocityCallbackPeriod":
                response = device.getVelocityCallbackPeriod();
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

            case "setVelocityCallbackThreshold":
                device.setVelocityCallbackThreshold(
                        getValue(char.class, "option2", m, getOption2()),
                        getValue(short.class, "min2", m, getMin2()),
                        getValue(short.class, "max2", m, getMax2())
                    );
                break;

            case "getVelocityCallbackThreshold":
                response = device.getVelocityCallbackThreshold();
                break;

            case "setDebouncePeriod":
                device.setDebouncePeriod(
                        getValue(long.class, "debounce", m, getDebounce())
                    );
                break;

            case "getDebouncePeriod":
                response = device.getDebouncePeriod();
                break;

            case "setMovingAverage":
                device.setMovingAverage(
                        getValue(short.class, "distanceAverageLength", m, getDistanceAverageLength()),
                        getValue(short.class, "velocityAverageLength", m, getVelocityAverageLength())
                    );
                break;

            case "getMovingAverage":
                response = device.getMovingAverage();
                break;

            case "setMode":
                device.setMode(
                        getValue(short.class, "mode", m, getMode())
                    );
                break;

            case "getMode":
                response = device.getMode();
                break;

            case "enableLaser":
                device.enableLaser();
                break;

            case "disableLaser":
                device.disableLaser();
                break;

            case "isLaserEnabled":
                response = device.isLaserEnabled();
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
     * Sets the period in ms with which the :func:`Distance` callback is triggered
     * periodically. A value of 0 turns the callback off.
     * 
     * :func:`Distance` is only triggered if the distance value has changed since the
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
     * Sets the period in ms with which the :func:`Velocity` callback is triggered
     * periodically. A value of 0 turns the callback off.
     * 
     * :func:`Velocity` is only triggered if the velocity value has changed since the
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
     *  "'o'",    "Callback is triggered when the distance value is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the distance value is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the distance value is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the distance value is greater than the min value (max is ignored)"
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
     *  "'o'",    "Callback is triggered when the distance value is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the distance value is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the distance value is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the distance value is greater than the min value (max is ignored)"
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
     *  "'o'",    "Callback is triggered when the distance value is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the distance value is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the distance value is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the distance value is greater than the min value (max is ignored)"
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
     * Sets the thresholds for the :func:`VelocityReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the velocity is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the velocity is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the velocity is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the velocity is greater than the min value (max is ignored)"
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
     * Sets the thresholds for the :func:`VelocityReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the velocity is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the velocity is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the velocity is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the velocity is greater than the min value (max is ignored)"
     * 
     * The default value is ('x', 0, 0).
     * 
     */
    public Short getMin2(){
        return min2;
    }

    public void setMin2(Short min2){
        this.min2 = min2;
    }

    /**
     * 
     * Sets the thresholds for the :func:`VelocityReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the velocity is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the velocity is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the velocity is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the velocity is greater than the min value (max is ignored)"
     * 
     * The default value is ('x', 0, 0).
     * 
     */
    public Short getMax2(){
        return max2;
    }

    public void setMax2(Short max2){
        this.max2 = max2;
    }

    /**
     * 
     * Sets the period in ms with which the threshold callbacks
     * 
     * * :func:`DistanceReached`,
     * * :func:`VelocityReached`,
     * 
     * are triggered, if the thresholds
     * 
     * * :func:`SetDistanceCallbackThreshold`,
     * * :func:`SetVelocityCallbackThreshold`,
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
     * Sets the length of a `moving averaging <https://en.wikipedia.org/wiki/Moving_average>`__
     * for the distance and velocity.
     * 
     * Setting the length to 0 will turn the averaging completely off. With less
     * averaging, there is more noise on the data.
     * 
     * The range for the averaging is 0-30.
     * 
     * The default value is 10.
     * 
     */
    public Short getDistanceAverageLength(){
        return distanceAverageLength;
    }

    public void setDistanceAverageLength(Short distanceAverageLength){
        this.distanceAverageLength = distanceAverageLength;
    }

    /**
     * 
     * Sets the length of a `moving averaging <https://en.wikipedia.org/wiki/Moving_average>`__
     * for the distance and velocity.
     * 
     * Setting the length to 0 will turn the averaging completely off. With less
     * averaging, there is more noise on the data.
     * 
     * The range for the averaging is 0-30.
     * 
     * The default value is 10.
     * 
     */
    public Short getVelocityAverageLength(){
        return velocityAverageLength;
    }

    public void setVelocityAverageLength(Short velocityAverageLength){
        this.velocityAverageLength = velocityAverageLength;
    }

    /**
     * 
     * The LIDAR has five different modes. One mode is for distance
     * measurements and four modes are for velocity measurements with
     * different ranges.
     * 
     * The following modes are available:
     * 
     * * 0: Distance is measured with resolution 1.0 cm and range 0-400 cm
     * * 1: Velocity is measured with resolution 0.1 m/s and range is 0-12.7 m/s
     * * 2: Velocity is measured with resolution 0.25 m/s and range is 0-31.75 m/s
     * * 3: Velocity is measured with resolution 0.5 m/s and range is 0-63.5 m/s
     * * 4: Velocity is measured with resolution 1.0 m/s and range is 0-127 m/s
     * 
     * The default mode is 0 (distance is measured).
     * 
     */
    public Short getMode(){
        return mode;
    }

    public void setMode(Short mode){
        this.mode = mode;
    }



}
