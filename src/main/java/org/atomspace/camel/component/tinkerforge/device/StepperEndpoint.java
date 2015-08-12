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

import com.tinkerforge.BrickStepper;

public class StepperEndpoint extends TinkerforgeEndpoint<StepperConsumer, StepperProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(StepperEndpoint.class);
    
    private Integer velocity;
    private Integer acceleration;
    private Integer deacceleration;
    private Integer position;
    private Integer position2;
    private Integer steps;
    private Short mode;
    private Integer current;
    private Integer decay;
    private Integer voltage;
    private Boolean syncRect;
    private Long timeBase;
    private Long period;
    private Character port;

        
    public StepperEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new StepperProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new StepperConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickStepper device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickStepper device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "setMaxVelocity":
                device.setMaxVelocity(
                        getValue(int.class, "velocity", m, getVelocity())
                    );
                break;

            case "getMaxVelocity":
                response = device.getMaxVelocity();
                break;

            case "getCurrentVelocity":
                response = device.getCurrentVelocity();
                break;

            case "setSpeedRamping":
                device.setSpeedRamping(
                        getValue(int.class, "acceleration", m, getAcceleration()),
                        getValue(int.class, "deacceleration", m, getDeacceleration())
                    );
                break;

            case "getSpeedRamping":
                response = device.getSpeedRamping();
                break;

            case "fullBrake":
                device.fullBrake();
                break;

            case "setCurrentPosition":
                device.setCurrentPosition(
                        getValue(int.class, "position", m, getPosition())
                    );
                break;

            case "getCurrentPosition":
                response = device.getCurrentPosition();
                break;

            case "setTargetPosition":
                device.setTargetPosition(
                        getValue(int.class, "position2", m, getPosition2())
                    );
                break;

            case "getTargetPosition":
                response = device.getTargetPosition();
                break;

            case "setSteps":
                device.setSteps(
                        getValue(int.class, "steps", m, getSteps())
                    );
                break;

            case "getSteps":
                response = device.getSteps();
                break;

            case "getRemainingSteps":
                response = device.getRemainingSteps();
                break;

            case "setStepMode":
                device.setStepMode(
                        getValue(short.class, "mode", m, getMode())
                    );
                break;

            case "getStepMode":
                response = device.getStepMode();
                break;

            case "driveForward":
                device.driveForward();
                break;

            case "driveBackward":
                device.driveBackward();
                break;

            case "stop":
                device.stop();
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

            case "setMotorCurrent":
                device.setMotorCurrent(
                        getValue(int.class, "current", m, getCurrent())
                    );
                break;

            case "getMotorCurrent":
                response = device.getMotorCurrent();
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

            case "setDecay":
                device.setDecay(
                        getValue(int.class, "decay", m, getDecay())
                    );
                break;

            case "getDecay":
                response = device.getDecay();
                break;

            case "setMinimumVoltage":
                device.setMinimumVoltage(
                        getValue(int.class, "voltage", m, getVoltage())
                    );
                break;

            case "getMinimumVoltage":
                response = device.getMinimumVoltage();
                break;

            case "setSyncRect":
                device.setSyncRect(
                        getValue(boolean.class, "syncRect", m, getSyncRect())
                    );
                break;

            case "isSyncRect":
                response = device.isSyncRect();
                break;

            case "setTimeBase":
                device.setTimeBase(
                        getValue(long.class, "timeBase", m, getTimeBase())
                    );
                break;

            case "getTimeBase":
                response = device.getTimeBase();
                break;

            case "getAllData":
                response = device.getAllData();
                break;

            case "setAllDataPeriod":
                device.setAllDataPeriod(
                        getValue(long.class, "period", m, getPeriod())
                    );
                break;

            case "getAllDataPeriod":
                response = device.getAllDataPeriod();
                break;

            case "enableStatusLed":
                device.enableStatusLED();
                break;

            case "disableStatusLed":
                device.disableStatusLED();
                break;

            case "isStatusLedEnabled":
                response = device.isStatusLEDEnabled();
                break;

            case "getProtocol1BrickletName":
                response = device.getProtocol1BrickletName(
                        getValue(char.class, "port", m, getPort())
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
    
    
    public Integer getVelocity(){
        return velocity;
    }

    public void setVelocity(Integer velocity){
        this.velocity = velocity;
    }

    public Integer getAcceleration(){
        return acceleration;
    }

    public void setAcceleration(Integer acceleration){
        this.acceleration = acceleration;
    }

    public Integer getDeacceleration(){
        return deacceleration;
    }

    public void setDeacceleration(Integer deacceleration){
        this.deacceleration = deacceleration;
    }

    public Integer getPosition(){
        return position;
    }

    public void setPosition(Integer position){
        this.position = position;
    }

    public Integer getPosition2(){
        return position2;
    }

    public void setPosition2(Integer position2){
        this.position2 = position2;
    }

    public Integer getSteps(){
        return steps;
    }

    public void setSteps(Integer steps){
        this.steps = steps;
    }

    public Short getMode(){
        return mode;
    }

    public void setMode(Short mode){
        this.mode = mode;
    }

    public Integer getCurrent(){
        return current;
    }

    public void setCurrent(Integer current){
        this.current = current;
    }

    public Integer getDecay(){
        return decay;
    }

    public void setDecay(Integer decay){
        this.decay = decay;
    }

    public Integer getVoltage(){
        return voltage;
    }

    public void setVoltage(Integer voltage){
        this.voltage = voltage;
    }

    public Boolean getSyncRect(){
        return syncRect;
    }

    public void setSyncRect(Boolean syncRect){
        this.syncRect = syncRect;
    }

    public Long getTimeBase(){
        return timeBase;
    }

    public void setTimeBase(Long timeBase){
        this.timeBase = timeBase;
    }

    public Long getPeriod(){
        return period;
    }

    public void setPeriod(Long period){
        this.period = period;
    }

    public Character getPort(){
        return port;
    }

    public void setPort(Character port){
        this.port = port;
    }



}
