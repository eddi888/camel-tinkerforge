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

import com.tinkerforge.BrickDC;

public class DCEndpoint extends TinkerforgeEndpoint<DCConsumer, DCProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(DCEndpoint.class);
    
    private Short velocity;
    private Integer acceleration;
    private Integer frequency;
    private Integer voltage;
    private Short mode;
    private Integer period;
    private Character port;

        
    public DCEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new DCProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new DCConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickDC device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickDC device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "setVelocity":
                device.setVelocity(
                        (short) getValue("velocity", m, e)
                    );
                break;

            case "getVelocity":
                response = device.getVelocity();
                break;

            case "getCurrentVelocity":
                response = device.getCurrentVelocity();
                break;

            case "setAcceleration":
                device.setAcceleration(
                        (int) getValue("acceleration", m, e)
                    );
                break;

            case "getAcceleration":
                response = device.getAcceleration();
                break;

            case "fullBrake":
                device.fullBrake();
                break;

            case "getStackInputVoltage":
                response = device.getStackInputVoltage();
                break;

            case "getExternalInputVoltage":
                response = device.getExternalInputVoltage();
                break;

            case "getCurrentConsumption":
                response = device.getCurrentConsumption();
                break;

            case "enable":
                device.enable();
                break;

            case "disable":
                device.disable();
                break;

            case "isEnabled":
                response = device.isEnabled();
                break;

            case "setMinimumVoltage":
                device.setMinimumVoltage(
                        (int) getValue("voltage", m, e)
                    );
                break;

            case "getMinimumVoltage":
                response = device.getMinimumVoltage();
                break;

            case "setDriveMode":
                device.setDriveMode(
                        (short) getValue("mode", m, e)
                    );
                break;

            case "getDriveMode":
                response = device.getDriveMode();
                break;

            case "setCurrentVelocityPeriod":
                device.setCurrentVelocityPeriod(
                        (int) getValue("period", m, e)
                    );
                break;

            case "getCurrentVelocityPeriod":
                response = device.getCurrentVelocityPeriod();
                break;

            case "getProtocol1BrickletName":
                response = device.getProtocol1BrickletName(
                        (char) getValue("port", m, e)
                    );
                break;

            case "getChipTemperature":
                response = device.getChipTemperature();
                break;

            case "reset":
                device.reset();
                break;

            case "getIdentity":
                response = device.getIdentity();
                break;


            default: throw new Exception("unknown function '"+function+"'");
            
        }
        
        return response;
    }
    
    
    public Short getVelocity(){
        return velocity;
    }

    public void setVelocity(Short velocity){
        this.velocity = velocity;
    }

    public Integer getAcceleration(){
        return acceleration;
    }

    public void setAcceleration(Integer acceleration){
        this.acceleration = acceleration;
    }

    public Integer getFrequency(){
        return frequency;
    }

    public void setFrequency(Integer frequency){
        this.frequency = frequency;
    }

    public Integer getVoltage(){
        return voltage;
    }

    public void setVoltage(Integer voltage){
        this.voltage = voltage;
    }

    public Short getMode(){
        return mode;
    }

    public void setMode(Short mode){
        this.mode = mode;
    }

    public Integer getPeriod(){
        return period;
    }

    public void setPeriod(Integer period){
        this.period = period;
    }

    public Character getPort(){
        return port;
    }

    public void setPort(Character port){
        this.port = port;
    }



}
