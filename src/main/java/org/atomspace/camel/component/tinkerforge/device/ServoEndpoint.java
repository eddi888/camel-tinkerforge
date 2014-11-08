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
    private Short servoNum2;
    private Short servoNum3;
    private Short servoNum4;
    private Short position;
    private Short servoNum5;
    private Short servoNum6;
    private Short servoNum7;
    private Integer velocity;
    private Short servoNum8;
    private Short servoNum9;
    private Short servoNum10;
    private Integer acceleration;
    private Short servoNum11;
    private Integer voltage;
    private Short servoNum12;
    private Integer min;
    private Integer max;
    private Short servoNum13;
    private Short servoNum14;
    private Short min2;
    private Short max2;
    private Short servoNum15;
    private Short servoNum16;
    private Integer period;
    private Short servoNum17;
    private Short servoNum18;
    private Integer voltage2;
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
                        getValue(short.class, "servoNum", m, getServoNum())
                    );
                break;

            case "disable":
                device.disable(
                        getValue(short.class, "servoNum2", m, getServoNum2())
                    );
                break;

            case "isEnabled":
                response = device.isEnabled(
                        getValue(short.class, "servoNum3", m, getServoNum3())
                    );
                break;

            case "setPosition":
                device.setPosition(
                        getValue(short.class, "servoNum4", m, getServoNum4()),
                        getValue(short.class, "position", m, getPosition())
                    );
                break;

            case "getPosition":
                response = device.getPosition(
                        getValue(short.class, "servoNum5", m, getServoNum5())
                    );
                break;

            case "getCurrentPosition":
                response = device.getCurrentPosition(
                        getValue(short.class, "servoNum6", m, getServoNum6())
                    );
                break;

            case "setVelocity":
                device.setVelocity(
                        getValue(short.class, "servoNum7", m, getServoNum7()),
                        getValue(int.class, "velocity", m, getVelocity())
                    );
                break;

            case "getVelocity":
                response = device.getVelocity(
                        getValue(short.class, "servoNum8", m, getServoNum8())
                    );
                break;

            case "getCurrentVelocity":
                response = device.getCurrentVelocity(
                        getValue(short.class, "servoNum9", m, getServoNum9())
                    );
                break;

            case "setAcceleration":
                device.setAcceleration(
                        getValue(short.class, "servoNum10", m, getServoNum10()),
                        getValue(int.class, "acceleration", m, getAcceleration())
                    );
                break;

            case "getAcceleration":
                response = device.getAcceleration(
                        getValue(short.class, "servoNum11", m, getServoNum11())
                    );
                break;

            case "setOutputVoltage":
                device.setOutputVoltage(
                        getValue(int.class, "voltage", m, getVoltage())
                    );
                break;

            case "getOutputVoltage":
                response = device.getOutputVoltage();
                break;

            case "setPulseWidth":
                device.setPulseWidth(
                        getValue(short.class, "servoNum12", m, getServoNum12()),
                        getValue(int.class, "min", m, getMin()),
                        getValue(int.class, "max", m, getMax())
                    );
                break;

            case "getPulseWidth":
                response = device.getPulseWidth(
                        getValue(short.class, "servoNum13", m, getServoNum13())
                    );
                break;

            case "setDegree":
                device.setDegree(
                        getValue(short.class, "servoNum14", m, getServoNum14()),
                        getValue(short.class, "min2", m, getMin2()),
                        getValue(short.class, "max2", m, getMax2())
                    );
                break;

            case "getDegree":
                response = device.getDegree(
                        getValue(short.class, "servoNum15", m, getServoNum15())
                    );
                break;

            case "setPeriod":
                device.setPeriod(
                        getValue(short.class, "servoNum16", m, getServoNum16()),
                        getValue(int.class, "period", m, getPeriod())
                    );
                break;

            case "getPeriod":
                response = device.getPeriod(
                        getValue(short.class, "servoNum17", m, getServoNum17())
                    );
                break;

            case "getServoCurrent":
                response = device.getServoCurrent(
                        getValue(short.class, "servoNum18", m, getServoNum18())
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
                        getValue(int.class, "voltage2", m, getVoltage2())
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
    
    
    public Short getServoNum(){
        return servoNum;
    }

    public void setServoNum(Short servoNum){
        this.servoNum = servoNum;
    }

    public Short getServoNum2(){
        return servoNum2;
    }

    public void setServoNum2(Short servoNum2){
        this.servoNum2 = servoNum2;
    }

    public Short getServoNum3(){
        return servoNum3;
    }

    public void setServoNum3(Short servoNum3){
        this.servoNum3 = servoNum3;
    }

    public Short getServoNum4(){
        return servoNum4;
    }

    public void setServoNum4(Short servoNum4){
        this.servoNum4 = servoNum4;
    }

    public Short getPosition(){
        return position;
    }

    public void setPosition(Short position){
        this.position = position;
    }

    public Short getServoNum5(){
        return servoNum5;
    }

    public void setServoNum5(Short servoNum5){
        this.servoNum5 = servoNum5;
    }

    public Short getServoNum6(){
        return servoNum6;
    }

    public void setServoNum6(Short servoNum6){
        this.servoNum6 = servoNum6;
    }

    public Short getServoNum7(){
        return servoNum7;
    }

    public void setServoNum7(Short servoNum7){
        this.servoNum7 = servoNum7;
    }

    public Integer getVelocity(){
        return velocity;
    }

    public void setVelocity(Integer velocity){
        this.velocity = velocity;
    }

    public Short getServoNum8(){
        return servoNum8;
    }

    public void setServoNum8(Short servoNum8){
        this.servoNum8 = servoNum8;
    }

    public Short getServoNum9(){
        return servoNum9;
    }

    public void setServoNum9(Short servoNum9){
        this.servoNum9 = servoNum9;
    }

    public Short getServoNum10(){
        return servoNum10;
    }

    public void setServoNum10(Short servoNum10){
        this.servoNum10 = servoNum10;
    }

    public Integer getAcceleration(){
        return acceleration;
    }

    public void setAcceleration(Integer acceleration){
        this.acceleration = acceleration;
    }

    public Short getServoNum11(){
        return servoNum11;
    }

    public void setServoNum11(Short servoNum11){
        this.servoNum11 = servoNum11;
    }

    public Integer getVoltage(){
        return voltage;
    }

    public void setVoltage(Integer voltage){
        this.voltage = voltage;
    }

    public Short getServoNum12(){
        return servoNum12;
    }

    public void setServoNum12(Short servoNum12){
        this.servoNum12 = servoNum12;
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

    public Short getServoNum13(){
        return servoNum13;
    }

    public void setServoNum13(Short servoNum13){
        this.servoNum13 = servoNum13;
    }

    public Short getServoNum14(){
        return servoNum14;
    }

    public void setServoNum14(Short servoNum14){
        this.servoNum14 = servoNum14;
    }

    public Short getMin2(){
        return min2;
    }

    public void setMin2(Short min2){
        this.min2 = min2;
    }

    public Short getMax2(){
        return max2;
    }

    public void setMax2(Short max2){
        this.max2 = max2;
    }

    public Short getServoNum15(){
        return servoNum15;
    }

    public void setServoNum15(Short servoNum15){
        this.servoNum15 = servoNum15;
    }

    public Short getServoNum16(){
        return servoNum16;
    }

    public void setServoNum16(Short servoNum16){
        this.servoNum16 = servoNum16;
    }

    public Integer getPeriod(){
        return period;
    }

    public void setPeriod(Integer period){
        this.period = period;
    }

    public Short getServoNum17(){
        return servoNum17;
    }

    public void setServoNum17(Short servoNum17){
        this.servoNum17 = servoNum17;
    }

    public Short getServoNum18(){
        return servoNum18;
    }

    public void setServoNum18(Short servoNum18){
        this.servoNum18 = servoNum18;
    }

    public Integer getVoltage2(){
        return voltage2;
    }

    public void setVoltage2(Integer voltage2){
        this.voltage2 = voltage2;
    }

    public Character getPort(){
        return port;
    }

    public void setPort(Character port){
        this.port = port;
    }



}
