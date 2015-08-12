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
    private Short range2;
    private Integer speed;
    private Short typ;
    private short[] data;
    private Short typ2;
    private Long period;
    private Long period2;
    private Long period3;
    private Long period4;
    private Long period5;
    private Long period6;
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

            case "getImuTemperature":
                response = device.getIMUTemperature();
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
                        getValue(short.class, "range", m, getRange())
                    );
                break;

            case "getAccelerationRange":
                response = device.getAccelerationRange();
                break;

            case "setMagnetometerRange":
                device.setMagnetometerRange(
                        getValue(short.class, "range2", m, getRange2())
                    );
                break;

            case "getMagnetometerRange":
                response = device.getMagnetometerRange();
                break;

            case "setConvergenceSpeed":
                device.setConvergenceSpeed(
                        getValue(int.class, "speed", m, getSpeed())
                    );
                break;

            case "getConvergenceSpeed":
                response = device.getConvergenceSpeed();
                break;

            case "setCalibration":
                device.setCalibration(
                        getValue(short.class, "typ", m, getTyp()),
                        getValue(short[].class, "data", m, getData())
                    );
                break;

            case "getCalibration":
                response = device.getCalibration(
                        getValue(short.class, "typ2", m, getTyp2())
                    );
                break;

            case "setAccelerationPeriod":
                device.setAccelerationPeriod(
                        getValue(long.class, "period", m, getPeriod())
                    );
                break;

            case "getAccelerationPeriod":
                response = device.getAccelerationPeriod();
                break;

            case "setMagneticFieldPeriod":
                device.setMagneticFieldPeriod(
                        getValue(long.class, "period2", m, getPeriod2())
                    );
                break;

            case "getMagneticFieldPeriod":
                response = device.getMagneticFieldPeriod();
                break;

            case "setAngularVelocityPeriod":
                device.setAngularVelocityPeriod(
                        getValue(long.class, "period3", m, getPeriod3())
                    );
                break;

            case "getAngularVelocityPeriod":
                response = device.getAngularVelocityPeriod();
                break;

            case "setAllDataPeriod":
                device.setAllDataPeriod(
                        getValue(long.class, "period4", m, getPeriod4())
                    );
                break;

            case "getAllDataPeriod":
                response = device.getAllDataPeriod();
                break;

            case "setOrientationPeriod":
                device.setOrientationPeriod(
                        getValue(long.class, "period5", m, getPeriod5())
                    );
                break;

            case "getOrientationPeriod":
                response = device.getOrientationPeriod();
                break;

            case "setQuaternionPeriod":
                device.setQuaternionPeriod(
                        getValue(long.class, "period6", m, getPeriod6())
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
    
    
    public Short getRange(){
        return range;
    }

    public void setRange(Short range){
        this.range = range;
    }

    public Short getRange2(){
        return range2;
    }

    public void setRange2(Short range2){
        this.range2 = range2;
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

    public short[] getData(){
        return data;
    }

    public void setData(short[] data){
        this.data = data;
    }

    public Short getTyp2(){
        return typ2;
    }

    public void setTyp2(Short typ2){
        this.typ2 = typ2;
    }

    public Long getPeriod(){
        return period;
    }

    public void setPeriod(Long period){
        this.period = period;
    }

    public Long getPeriod2(){
        return period2;
    }

    public void setPeriod2(Long period2){
        this.period2 = period2;
    }

    public Long getPeriod3(){
        return period3;
    }

    public void setPeriod3(Long period3){
        this.period3 = period3;
    }

    public Long getPeriod4(){
        return period4;
    }

    public void setPeriod4(Long period4){
        this.period4 = period4;
    }

    public Long getPeriod5(){
        return period5;
    }

    public void setPeriod5(Long period5){
        this.period5 = period5;
    }

    public Long getPeriod6(){
        return period6;
    }

    public void setPeriod6(Long period6){
        this.period6 = period6;
    }

    public Character getPort(){
        return port;
    }

    public void setPort(Character port){
        this.port = port;
    }



}
