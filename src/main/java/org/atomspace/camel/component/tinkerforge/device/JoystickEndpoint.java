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

import com.tinkerforge.BrickletJoystick;

/**
 * 2-axis joystick with push-button
 */
@UriEndpoint(scheme = "tinkerforgegen", syntax = "tinkerforgegen:[host[:port]/]joystick", consumerClass = JoystickConsumer.class, label = "iot", title = "Tinkerforge")
public class JoystickEndpoint extends TinkerforgeEndpoint<JoystickConsumer, JoystickProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(JoystickEndpoint.class);

    public static final String PERIOD="period";
    public static final String PERIOD2="period2";
    public static final String OPTION="option";
    public static final String MINX="minX";
    public static final String MAXX="maxX";
    public static final String MINY="minY";
    public static final String MAXY="maxY";
    public static final String OPTION2="option2";
    public static final String MINX2="minX2";
    public static final String MAXX2="maxX2";
    public static final String MINY2="minY2";
    public static final String MAXY2="maxY2";
    public static final String DEBOUNCE="debounce";

    
    private Long period;
    private Long period2;
    private Character option;
    private Short minX;
    private Short maxX;
    private Short minY;
    private Short maxY;
    private Character option2;
    private Integer minX2;
    private Integer maxX2;
    private Integer minY2;
    private Integer maxY2;
    private Long debounce;

        
    public JoystickEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new JoystickProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new JoystickConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletJoystick device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletJoystick device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "getPosition":
                response = device.getPosition();
                break;

            case "isPressed":
                response = device.isPressed();
                break;

            case "getAnalogValue":
                response = device.getAnalogValue();
                break;

            case "calibrate":
                device.calibrate();
                break;

            case "setPositionCallbackPeriod":
                device.setPositionCallbackPeriod(
                        getValue(long.class, "period", m, getPeriod())
                    );
                break;

            case "getPositionCallbackPeriod":
                response = device.getPositionCallbackPeriod();
                break;

            case "setAnalogValueCallbackPeriod":
                device.setAnalogValueCallbackPeriod(
                        getValue(long.class, "period2", m, getPeriod2())
                    );
                break;

            case "getAnalogValueCallbackPeriod":
                response = device.getAnalogValueCallbackPeriod();
                break;

            case "setPositionCallbackThreshold":
                device.setPositionCallbackThreshold(
                        getValue(char.class, "option", m, getOption()),
                        getValue(short.class, "minX", m, getMinX()),
                        getValue(short.class, "maxX", m, getMaxX()),
                        getValue(short.class, "minY", m, getMinY()),
                        getValue(short.class, "maxY", m, getMaxY())
                    );
                break;

            case "getPositionCallbackThreshold":
                response = device.getPositionCallbackThreshold();
                break;

            case "setAnalogValueCallbackThreshold":
                device.setAnalogValueCallbackThreshold(
                        getValue(char.class, "option2", m, getOption2()),
                        getValue(int.class, "minX2", m, getMinX2()),
                        getValue(int.class, "maxX2", m, getMaxX2()),
                        getValue(int.class, "minY2", m, getMinY2()),
                        getValue(int.class, "maxY2", m, getMaxY2())
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
     * Sets the period in ms with which the :func:`Position` callback is triggered
     * periodically. A value of 0 turns the callback off.
     * 
     * :func:`Position` is only triggered if the position has changed since the
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
     * :func:`AnalogValue` is only triggered if the analog values have changed since the
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
     * Sets the thresholds for the :func:`PositionReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the position is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the position is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the position is smaller than the min values (max is ignored)"
     *  "'>'",    "Callback is triggered when the position is greater than the min values (max is ignored)"
     * 
     * The default value is ('x', 0, 0, 0, 0).
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
     * Sets the thresholds for the :func:`PositionReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the position is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the position is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the position is smaller than the min values (max is ignored)"
     *  "'>'",    "Callback is triggered when the position is greater than the min values (max is ignored)"
     * 
     * The default value is ('x', 0, 0, 0, 0).
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
     * Sets the thresholds for the :func:`PositionReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the position is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the position is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the position is smaller than the min values (max is ignored)"
     *  "'>'",    "Callback is triggered when the position is greater than the min values (max is ignored)"
     * 
     * The default value is ('x', 0, 0, 0, 0).
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
     * Sets the thresholds for the :func:`PositionReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the position is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the position is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the position is smaller than the min values (max is ignored)"
     *  "'>'",    "Callback is triggered when the position is greater than the min values (max is ignored)"
     * 
     * The default value is ('x', 0, 0, 0, 0).
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
     * Sets the thresholds for the :func:`PositionReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the position is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the position is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the position is smaller than the min values (max is ignored)"
     *  "'>'",    "Callback is triggered when the position is greater than the min values (max is ignored)"
     * 
     * The default value is ('x', 0, 0, 0, 0).
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
     * Sets the thresholds for the :func:`AnalogValueReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the analog values are *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the analog values are *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the analog values are smaller than the min values (max is ignored)"
     *  "'>'",    "Callback is triggered when the analog values are greater than the min values (max is ignored)"
     * 
     * The default value is ('x', 0, 0, 0, 0).
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
     *  "'o'",    "Callback is triggered when the analog values are *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the analog values are *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the analog values are smaller than the min values (max is ignored)"
     *  "'>'",    "Callback is triggered when the analog values are greater than the min values (max is ignored)"
     * 
     * The default value is ('x', 0, 0, 0, 0).
     * 
     */
    public Integer getMinX2(){
        return minX2;
    }

    public void setMinX2(Integer minX2){
        this.minX2 = minX2;
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
     *  "'o'",    "Callback is triggered when the analog values are *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the analog values are *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the analog values are smaller than the min values (max is ignored)"
     *  "'>'",    "Callback is triggered when the analog values are greater than the min values (max is ignored)"
     * 
     * The default value is ('x', 0, 0, 0, 0).
     * 
     */
    public Integer getMaxX2(){
        return maxX2;
    }

    public void setMaxX2(Integer maxX2){
        this.maxX2 = maxX2;
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
     *  "'o'",    "Callback is triggered when the analog values are *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the analog values are *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the analog values are smaller than the min values (max is ignored)"
     *  "'>'",    "Callback is triggered when the analog values are greater than the min values (max is ignored)"
     * 
     * The default value is ('x', 0, 0, 0, 0).
     * 
     */
    public Integer getMinY2(){
        return minY2;
    }

    public void setMinY2(Integer minY2){
        this.minY2 = minY2;
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
     *  "'o'",    "Callback is triggered when the analog values are *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the analog values are *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the analog values are smaller than the min values (max is ignored)"
     *  "'>'",    "Callback is triggered when the analog values are greater than the min values (max is ignored)"
     * 
     * The default value is ('x', 0, 0, 0, 0).
     * 
     */
    public Integer getMaxY2(){
        return maxY2;
    }

    public void setMaxY2(Integer maxY2){
        this.maxY2 = maxY2;
    }

    /**
     * 
     * Sets the period in ms with which the threshold callbacks
     * 
     * * :func:`PositionReached`,
     * * :func:`AnalogValueReached`
     * 
     * are triggered, if the thresholds
     * 
     * * :func:`SetPositionCallbackThreshold`,
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
