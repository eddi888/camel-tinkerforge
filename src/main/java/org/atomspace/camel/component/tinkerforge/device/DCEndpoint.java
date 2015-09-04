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

import com.tinkerforge.BrickDC;

/**
 * Drives one brushed DC motor with up to 28V and 5A (peak)
 */
@UriEndpoint(scheme = "tinkerforgegen", syntax = "tinkerforgegen:[host[:port]/]dc", consumerClass = DCConsumer.class, label = "iot", title = "Tinkerforge")
public class DCEndpoint extends TinkerforgeEndpoint<DCConsumer, DCProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(DCEndpoint.class);

    public static final String VELOCITY="velocity";
    public static final String ACCELERATION="acceleration";
    public static final String FREQUENCY="frequency";
    public static final String VOLTAGE="voltage";
    public static final String MODE="mode";
    public static final String PERIOD="period";
    public static final String PORT="port";

    
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
                        getValue(short.class, "velocity", m, getVelocity())
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
                        getValue(int.class, "acceleration", m, getAcceleration())
                    );
                break;

            case "getAcceleration":
                response = device.getAcceleration();
                break;

            case "setPwmFrequency":
                device.setPWMFrequency(
                        getValue(int.class, "frequency", m, getFrequency())
                    );
                break;

            case "getPwmFrequency":
                response = device.getPWMFrequency();
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
                        getValue(int.class, "voltage", m, getVoltage())
                    );
                break;

            case "getMinimumVoltage":
                response = device.getMinimumVoltage();
                break;

            case "setDriveMode":
                device.setDriveMode(
                        getValue(short.class, "mode", m, getMode())
                    );
                break;

            case "getDriveMode":
                response = device.getDriveMode();
                break;

            case "setCurrentVelocityPeriod":
                device.setCurrentVelocityPeriod(
                        getValue(int.class, "period", m, getPeriod())
                    );
                break;

            case "getCurrentVelocityPeriod":
                response = device.getCurrentVelocityPeriod();
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
     * Sets the velocity of the motor. Whereas -32767 is full speed backward,
     * 0 is stop and 32767 is full speed forward. Depending on the
     * acceleration (see :func:`SetAcceleration`), the motor is not immediately
     * brought to the velocity but smoothly accelerated.
     * 
     * The velocity describes the duty cycle of the PWM with which the motor is
     * controlled, e.g. a velocity of 3277 sets a PWM with a 10% duty cycle.
     * You can not only control the duty cycle of the PWM but also the frequency,
     * see :func:`SetPWMFrequency`.
     * 
     * The default velocity is 0.
     * 
     */
    public Short getVelocity(){
        return velocity;
    }

    public void setVelocity(Short velocity){
        this.velocity = velocity;
    }

    /**
     * 
     * Sets the acceleration of the motor. It is given in *velocity/s*. An
     * acceleration of 10000 means, that every second the velocity is increased
     * by 10000 (or about 30% duty cycle).
     * 
     * For example: If the current velocity is 0 and you want to accelerate to a
     * velocity of 16000 (about 50% duty cycle) in 10 seconds, you should set
     * an acceleration of 1600.
     * 
     * If acceleration is set to 0, there is no speed ramping, i.e. a new velocity
     * is immediately given to the motor.
     * 
     * The default acceleration is 10000.
     * 
     */
    public Integer getAcceleration(){
        return acceleration;
    }

    public void setAcceleration(Integer acceleration){
        this.acceleration = acceleration;
    }

    /**
     * 
     * Sets the frequency (in Hz) of the PWM with which the motor is driven.
     * The possible range of the frequency is 1-20000Hz. Often a high frequency
     * is less noisy and the motor runs smoother. However, with a low frequency
     * there are less switches and therefore fewer switching losses. Also with
     * most motors lower frequencies enable higher torque.
     * 
     * If you have no idea what all this means, just ignore this function and use
     * the default frequency, it will very likely work fine.
     * 
     * The default frequency is 15 kHz.
     * 
     */
    public Integer getFrequency(){
        return frequency;
    }

    public void setFrequency(Integer frequency){
        this.frequency = frequency;
    }

    /**
     * 
     * Sets the minimum voltage in mV, below which the :func:`UnderVoltage` callback
     * is triggered. The minimum possible value that works with the DC Brick is 6V.
     * You can use this function to detect the discharge of a battery that is used
     * to drive the motor. If you have a fixed power supply, you likely do not need
     * this functionality.
     * 
     * The default value is 6V.
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
     * Sets the drive mode. Possible modes are:
     * 
     * * 0 = Drive/Brake
     * * 1 = Drive/Coast
     * 
     * These modes are different kinds of motor controls.
     * 
     * In Drive/Brake mode, the motor is always either driving or braking. There
     * is no freewheeling. Advantages are: A more linear correlation between
     * PWM and velocity, more exact accelerations and the possibility to drive
     * with slower velocities.
     * 
     * In Drive/Coast mode, the motor is always either driving or freewheeling.
     * Advantages are: Less current consumption and less demands on the motor and
     * driver chip.
     * 
     * The default value is 0 = Drive/Brake.
     * 
     */
    public Short getMode(){
        return mode;
    }

    public void setMode(Short mode){
        this.mode = mode;
    }

    /**
     * 
     * Sets a period in ms with which the :func:`CurrentVelocity` callback is triggered.
     * A period of 0 turns the callback off.
     * 
     * The default value is 0.
     * 
     */
    public Integer getPeriod(){
        return period;
    }

    public void setPeriod(Integer period){
        this.period = period;
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
