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

import com.tinkerforge.BrickletAnalogOut;

/**
 * Generates configurable DC voltage between 0V and 5V
 */
public class AnalogOutEndpoint extends TinkerforgeEndpoint<AnalogOutConsumer, AnalogOutProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(AnalogOutEndpoint.class);
    
    private Integer voltage;
    private Short mode;

        
    public AnalogOutEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new AnalogOutProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new AnalogOutConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletAnalogOut device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletAnalogOut device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "setVoltage":
                device.setVoltage(
                        getValue(int.class, "voltage", m, getVoltage())
                    );
                break;

            case "getVoltage":
                response = device.getVoltage();
                break;

            case "setMode":
                device.setMode(
                        getValue(short.class, "mode", m, getMode())
                    );
                break;

            case "getMode":
                response = device.getMode();
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
     * Sets the voltage in mV. The possible range is 0V to 5V (0-5000).
     * Calling this function will set the mode to 0 (see :func:`SetMode`).
     * 
     * The default value is 0 (with mode 1).
     * 
     */
    public Integer getVoltage(){
        return voltage;
    }

    public void setVoltage(Integer voltage){
        this.voltage = voltage;
    }

    /**
     * 
     * Sets the mode of the analog value. Possible modes:
     * 
     * * 0: Normal Mode (Analog value as set by :func:`SetVoltage` is applied)
     * * 1: 1k Ohm resistor to ground
     * * 2: 100k Ohm resistor to ground
     * * 3: 500k Ohm resistor to ground
     * 
     * Setting the mode to 0 will result in an output voltage of 0. You can jump
     * to a higher output voltage directly by calling :func:`SetVoltage`.
     * 
     * The default mode is 1.
     * 
     */
    public Short getMode(){
        return mode;
    }

    public void setMode(Short mode){
        this.mode = mode;
    }



}
