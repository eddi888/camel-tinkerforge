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

import com.tinkerforge.BrickIMUV2;

/**
 * Full fledged AHRS with 9 degrees of freedom
 */
@UriEndpoint(scheme = "tinkerforgegen", syntax = "tinkerforgegen:[host[:port]/]imuv2", consumerClass = IMUV2Consumer.class, label = "iot", title = "Tinkerforge")
public class IMUV2Endpoint extends TinkerforgeEndpoint<IMUV2Consumer, IMUV2Producer> {

    private static final Logger LOG = LoggerFactory.getLogger(IMUV2Endpoint.class);

    public static final String PERIOD="period";
    public static final String PERIOD2="period2";
    public static final String PERIOD3="period3";
    public static final String PERIOD4="period4";
    public static final String PERIOD5="period5";
    public static final String PERIOD6="period6";
    public static final String PERIOD7="period7";
    public static final String PERIOD8="period8";
    public static final String PERIOD9="period9";
    public static final String PORT="port";

    
    private Long period;
    private Long period2;
    private Long period3;
    private Long period4;
    private Long period5;
    private Long period6;
    private Long period7;
    private Long period8;
    private Long period9;
    private Character port;

        
    public IMUV2Endpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new IMUV2Producer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new IMUV2Consumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickIMUV2 device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickIMUV2 device, String function, Message m, Endpoint e) throws Exception{
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

            case "getTemperature":
                response = device.getTemperature();
                break;

            case "getOrientation":
                response = device.getOrientation();
                break;

            case "getLinearAcceleration":
                response = device.getLinearAcceleration();
                break;

            case "getGravityVector":
                response = device.getGravityVector();
                break;

            case "getQuaternion":
                response = device.getQuaternion();
                break;

            case "getAllData":
                response = device.getAllData();
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

            case "saveCalibration":
                response = device.saveCalibration();
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

            case "setTemperaturePeriod":
                device.setTemperaturePeriod(
                        getValue(long.class, "period4", m, getPeriod4())
                    );
                break;

            case "getTemperaturePeriod":
                response = device.getTemperaturePeriod();
                break;

            case "setOrientationPeriod":
                device.setOrientationPeriod(
                        getValue(long.class, "period5", m, getPeriod5())
                    );
                break;

            case "getOrientationPeriod":
                response = device.getOrientationPeriod();
                break;

            case "setLinearAccelerationPeriod":
                device.setLinearAccelerationPeriod(
                        getValue(long.class, "period6", m, getPeriod6())
                    );
                break;

            case "getLinearAccelerationPeriod":
                response = device.getLinearAccelerationPeriod();
                break;

            case "setGravityVectorPeriod":
                device.setGravityVectorPeriod(
                        getValue(long.class, "period7", m, getPeriod7())
                    );
                break;

            case "getGravityVectorPeriod":
                response = device.getGravityVectorPeriod();
                break;

            case "setQuaternionPeriod":
                device.setQuaternionPeriod(
                        getValue(long.class, "period8", m, getPeriod8())
                    );
                break;

            case "getQuaternionPeriod":
                response = device.getQuaternionPeriod();
                break;

            case "setAllDataPeriod":
                device.setAllDataPeriod(
                        getValue(long.class, "period9", m, getPeriod9())
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
    
    
    /**
     * 
     * Sets the period in ms with which the :func:`Acceleration` callback is triggered
     * periodically. A value of 0 turns the callback off.
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
     * Sets the period in ms with which the :func:`MagneticField` callback is triggered
     * periodically. A value of 0 turns the callback off.
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
     * Sets the period in ms with which the :func:`AngularVelocity` callback is triggered
     * periodically. A value of 0 turns the callback off.
     * 
     */
    public Long getPeriod3(){
        return period3;
    }

    public void setPeriod3(Long period3){
        this.period3 = period3;
    }

    /**
     * 
     * Sets the period in ms with which the :func:`Temperature` callback is triggered
     * periodically. A value of 0 turns the callback off.
     * 
     */
    public Long getPeriod4(){
        return period4;
    }

    public void setPeriod4(Long period4){
        this.period4 = period4;
    }

    /**
     * 
     * Sets the period in ms with which the :func:`Orientation` callback is triggered
     * periodically. A value of 0 turns the callback off.
     * 
     */
    public Long getPeriod5(){
        return period5;
    }

    public void setPeriod5(Long period5){
        this.period5 = period5;
    }

    /**
     * 
     * Sets the period in ms with which the :func:`LinearAcceleration` callback is triggered
     * periodically. A value of 0 turns the callback off.
     * 
     */
    public Long getPeriod6(){
        return period6;
    }

    public void setPeriod6(Long period6){
        this.period6 = period6;
    }

    /**
     * 
     * Sets the period in ms with which the :func:`GravityVector` callback is triggered
     * periodically. A value of 0 turns the callback off.
     * 
     */
    public Long getPeriod7(){
        return period7;
    }

    public void setPeriod7(Long period7){
        this.period7 = period7;
    }

    /**
     * 
     * Sets the period in ms with which the :func:`Quaternion` callback is triggered
     * periodically. A value of 0 turns the callback off.
     * 
     */
    public Long getPeriod8(){
        return period8;
    }

    public void setPeriod8(Long period8){
        this.period8 = period8;
    }

    /**
     * 
     * Sets the period in ms with which the :func:`AllData` callback is triggered
     * periodically. A value of 0 turns the callback off.
     * 
     */
    public Long getPeriod9(){
        return period9;
    }

    public void setPeriod9(Long period9){
        this.period9 = period9;
    }

    /**
     * 
     * Returns the firmware and protocol version and the name of the Bricklet for a
     * given port.
     * 
     * This functions sole purpose is to allow automatic flashing of v1.x.y Bricklet
     * plugins.
     * 
     */
    public Character getPort(){
        return port;
    }

    public void setPort(Character port){
        this.port = port;
    }



}
