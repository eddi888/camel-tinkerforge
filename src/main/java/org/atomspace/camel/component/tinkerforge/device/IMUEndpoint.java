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

import com.tinkerforge.BrickIMU;

public class IMUEndpoint extends TinkerforgeEndpoint<IMUConsumer, IMUProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(IMUEndpoint.class);
    
    private Short range;
    private Integer speed;
    private Short typ;
    private Short data;
    private Long period;
    private Character port;

        
    public IMUEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new IMUProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new IMUConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickIMU device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickIMU device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "getAcceleration":
                response = device.getAcceleration();
                break;

            case "getMagneticField":
                response = device.getMagneticField();
                break;

            case "getAngularVelocity":
                response = device.getAngularVelocity();
                break;

            case "getAllData":
                response = device.getAllData();
                break;

            case "getOrientation":
                response = device.getOrientation();
                break;

            case "getQuaternion":
                response = device.getQuaternion();
                break;

            case "ledsOn":
                device.ledsOn();
                break;

            case "ledsOff":
                device.ledsOff();
                break;

            case "areLedsOn":
                response = device.areLedsOn();
                break;

            case "setAccelerationRange":
                device.setAccelerationRange(
                        (short) getValue("range", m, e)
                    );
                break;

            case "getAccelerationRange":
                response = device.getAccelerationRange();
                break;

            case "setMagnetometerRange":
                device.setMagnetometerRange(
                        (short) getValue("range", m, e)
                    );
                break;

            case "getMagnetometerRange":
                response = device.getMagnetometerRange();
                break;

            case "setConvergenceSpeed":
                device.setConvergenceSpeed(
                        (int) getValue("speed", m, e)
                    );
                break;

            case "getConvergenceSpeed":
                response = device.getConvergenceSpeed();
                break;

            case "getCalibration":
                response = device.getCalibration(
                        (short) getValue("typ", m, e)
                    );
                break;

            case "setAccelerationPeriod":
                device.setAccelerationPeriod(
                        (long) getValue("period", m, e)
                    );
                break;

            case "getAccelerationPeriod":
                response = device.getAccelerationPeriod();
                break;

            case "setMagneticFieldPeriod":
                device.setMagneticFieldPeriod(
                        (long) getValue("period", m, e)
                    );
                break;

            case "getMagneticFieldPeriod":
                response = device.getMagneticFieldPeriod();
                break;

            case "setAngularVelocityPeriod":
                device.setAngularVelocityPeriod(
                        (long) getValue("period", m, e)
                    );
                break;

            case "getAngularVelocityPeriod":
                response = device.getAngularVelocityPeriod();
                break;

            case "setAllDataPeriod":
                device.setAllDataPeriod(
                        (long) getValue("period", m, e)
                    );
                break;

            case "getAllDataPeriod":
                response = device.getAllDataPeriod();
                break;

            case "setOrientationPeriod":
                device.setOrientationPeriod(
                        (long) getValue("period", m, e)
                    );
                break;

            case "getOrientationPeriod":
                response = device.getOrientationPeriod();
                break;

            case "setQuaternionPeriod":
                device.setQuaternionPeriod(
                        (long) getValue("period", m, e)
                    );
                break;

            case "getQuaternionPeriod":
                response = device.getQuaternionPeriod();
                break;

            case "orientationCalculationOn":
                device.orientationCalculationOn();
                break;

            case "orientationCalculationOff":
                device.orientationCalculationOff();
                break;

            case "isOrientationCalculationOn":
                response = device.isOrientationCalculationOn();
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
    
    
    public Short getRange(){
        return range;
    }

    public void setRange(Short range){
        this.range = range;
    }

    public Integer getSpeed(){
        return speed;
    }

    public void setSpeed(Integer speed){
        this.speed = speed;
    }

    public Short getTyp(){
        return typ;
    }

    public void setTyp(Short typ){
        this.typ = typ;
    }

    public Short getData(){
        return data;
    }

    public void setData(Short data){
        this.data = data;
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
