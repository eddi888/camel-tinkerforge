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

import com.tinkerforge.BrickServo;

public class ServoEndpoint extends TinkerforgeEndpoint<ServoConsumer, ServoProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(ServoEndpoint.class);
    
    private Short servoNum;
    private Short position;
    private Integer velocity;
    private Integer acceleration;
    private Integer voltage;
    private Integer min;
    private Integer max;
    private Integer period;
    private Character port;

        
    public ServoEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new ServoProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new ServoConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickServo device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickServo device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "enable":
                device.enable(
                        (short) getValue("servoNum", m, e)
                    );
                break;

            case "disable":
                device.disable(
                        (short) getValue("servoNum", m, e)
                    );
                break;

            case "isEnabled":
                response = device.isEnabled(
                        (short) getValue("servoNum", m, e)
                    );
                break;

            case "setPosition":
                device.setPosition(
                        (short) getValue("servoNum", m, e),
                        (short) getValue("position", m, e)
                    );
                break;

            case "getPosition":
                response = device.getPosition(
                        (short) getValue("servoNum", m, e)
                    );
                break;

            case "getCurrentPosition":
                response = device.getCurrentPosition(
                        (short) getValue("servoNum", m, e)
                    );
                break;

            case "setVelocity":
                device.setVelocity(
                        (short) getValue("servoNum", m, e),
                        (int) getValue("velocity", m, e)
                    );
                break;

            case "getVelocity":
                response = device.getVelocity(
                        (short) getValue("servoNum", m, e)
                    );
                break;

            case "getCurrentVelocity":
                response = device.getCurrentVelocity(
                        (short) getValue("servoNum", m, e)
                    );
                break;

            case "setAcceleration":
                device.setAcceleration(
                        (short) getValue("servoNum", m, e),
                        (int) getValue("acceleration", m, e)
                    );
                break;

            case "getAcceleration":
                response = device.getAcceleration(
                        (short) getValue("servoNum", m, e)
                    );
                break;

            case "setOutputVoltage":
                device.setOutputVoltage(
                        (int) getValue("voltage", m, e)
                    );
                break;

            case "getOutputVoltage":
                response = device.getOutputVoltage();
                break;

            case "setPulseWidth":
                device.setPulseWidth(
                        (short) getValue("servoNum", m, e),
                        (int) getValue("min", m, e),
                        (int) getValue("max", m, e)
                    );
                break;

            case "getPulseWidth":
                response = device.getPulseWidth(
                        (short) getValue("servoNum", m, e)
                    );
                break;

            case "setDegree":
                device.setDegree(
                        (short) getValue("servoNum", m, e),
                        (short) getValue("min", m, e),
                        (short) getValue("max", m, e)
                    );
                break;

            case "getDegree":
                response = device.getDegree(
                        (short) getValue("servoNum", m, e)
                    );
                break;

            case "setPeriod":
                device.setPeriod(
                        (short) getValue("servoNum", m, e),
                        (int) getValue("period", m, e)
                    );
                break;

            case "getPeriod":
                response = device.getPeriod(
                        (short) getValue("servoNum", m, e)
                    );
                break;

            case "getServoCurrent":
                response = device.getServoCurrent(
                        (short) getValue("servoNum", m, e)
                    );
                break;

            case "getOverallCurrent":
                response = device.getOverallCurrent();
                break;

            case "getStackInputVoltage":
                response = device.getStackInputVoltage();
                break;

            case "getExternalInputVoltage":
                response = device.getExternalInputVoltage();
                break;

            case "setMinimumVoltage":
                device.setMinimumVoltage(
                        (int) getValue("voltage", m, e)
                    );
                break;

            case "getMinimumVoltage":
                response = device.getMinimumVoltage();
                break;

            case "enablePositionReachedCallback":
                device.enablePositionReachedCallback();
                break;

            case "disablePositionReachedCallback":
                device.disablePositionReachedCallback();
                break;

            case "isPositionReachedCallbackEnabled":
                response = device.isPositionReachedCallbackEnabled();
                break;

            case "enableVelocityReachedCallback":
                device.enableVelocityReachedCallback();
                break;

            case "disableVelocityReachedCallback":
                device.disableVelocityReachedCallback();
                break;

            case "isVelocityReachedCallbackEnabled":
                response = device.isVelocityReachedCallbackEnabled();
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
    
    
    public Short getServoNum(){
        return servoNum;
    }

    public void setServoNum(Short servoNum){
        this.servoNum = servoNum;
    }

    public Short getPosition(){
        return position;
    }

    public void setPosition(Short position){
        this.position = position;
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

    public Integer getVoltage(){
        return voltage;
    }

    public void setVoltage(Integer voltage){
        this.voltage = voltage;
    }

    public Integer getMin(){
        return min;
    }

    public void setMin(Integer min){
        this.min = min;
    }

    public Integer getMax(){
        return max;
    }

    public void setMax(Integer max){
        this.max = max;
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
