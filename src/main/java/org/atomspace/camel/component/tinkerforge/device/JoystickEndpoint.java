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

import com.tinkerforge.BrickletJoystick;

public class JoystickEndpoint extends TinkerforgeEndpoint<JoystickConsumer, JoystickProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(JoystickEndpoint.class);
    
    private Long period;
    private Character option;
    private Short minX;
    private Short maxX;
    private Short minY;
    private Short maxY;
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
                        (long) getValue("period", m, e)
                    );
                break;

            case "getPositionCallbackPeriod":
                response = device.getPositionCallbackPeriod();
                break;

            case "setAnalogValueCallbackPeriod":
                device.setAnalogValueCallbackPeriod(
                        (long) getValue("period", m, e)
                    );
                break;

            case "getAnalogValueCallbackPeriod":
                response = device.getAnalogValueCallbackPeriod();
                break;

            case "setPositionCallbackThreshold":
                device.setPositionCallbackThreshold(
                        (char) getValue("option", m, e),
                        (short) getValue("minX", m, e),
                        (short) getValue("maxX", m, e),
                        (short) getValue("minY", m, e),
                        (short) getValue("maxY", m, e)
                    );
                break;

            case "getPositionCallbackThreshold":
                response = device.getPositionCallbackThreshold();
                break;

            case "setAnalogValueCallbackThreshold":
                device.setAnalogValueCallbackThreshold(
                        (char) getValue("option", m, e),
                        (int) getValue("minX", m, e),
                        (int) getValue("maxX", m, e),
                        (int) getValue("minY", m, e),
                        (int) getValue("maxY", m, e)
                    );
                break;

            case "getAnalogValueCallbackThreshold":
                response = device.getAnalogValueCallbackThreshold();
                break;

            case "setDebouncePeriod":
                device.setDebouncePeriod(
                        (long) getValue("debounce", m, e)
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
    
    
    public Long getPeriod(){
        return period;
    }

    public void setPeriod(Long period){
        this.period = period;
    }

    public Character getOption(){
        return option;
    }

    public void setOption(Character option){
        this.option = option;
    }

    public Short getMinX(){
        return minX;
    }

    public void setMinX(Short minX){
        this.minX = minX;
    }

    public Short getMaxX(){
        return maxX;
    }

    public void setMaxX(Short maxX){
        this.maxX = maxX;
    }

    public Short getMinY(){
        return minY;
    }

    public void setMinY(Short minY){
        this.minY = minY;
    }

    public Short getMaxY(){
        return maxY;
    }

    public void setMaxY(Short maxY){
        this.maxY = maxY;
    }

    public Long getDebounce(){
        return debounce;
    }

    public void setDebounce(Long debounce){
        this.debounce = debounce;
    }



}
