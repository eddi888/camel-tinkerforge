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

/**
 * Drives one bipolar stepper motor with up to 38V and 2.5A per phase
 */
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
    
    
    /**
     * 
     * Sets the maximum velocity of the stepper motor in steps per second.
     * This function does *not* start the motor, it merely sets the maximum
     * velocity the stepper motor is accelerated to. To get the motor running use
     * either :func:`SetTargetPosition`, :func:`SetSteps`, :func:`DriveForward` or
     * :func:`DriveBackward`.
     * 
     */
    public Integer getVelocity(){
        return velocity;
    }

    public void setVelocity(Integer velocity){
        this.velocity = velocity;
    }

    /**
     * 
     * Sets the acceleration and deacceleration of the stepper motor. The values
     * are given in *steps/s²*. An acceleration of 1000 means, that
     * every second the velocity is increased by 1000 *steps/s*.
     * 
     * For example: If the current velocity is 0 and you want to accelerate to a
     * velocity of 8000 *steps/s* in 10 seconds, you should set an acceleration
     * of 800 *steps/s²*.
     * 
     * An acceleration/deacceleration of 0 means instantaneous
     * acceleration/deacceleration (not recommended)
     * 
     * The default value is 1000 for both
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
     * Sets the acceleration and deacceleration of the stepper motor. The values
     * are given in *steps/s²*. An acceleration of 1000 means, that
     * every second the velocity is increased by 1000 *steps/s*.
     * 
     * For example: If the current velocity is 0 and you want to accelerate to a
     * velocity of 8000 *steps/s* in 10 seconds, you should set an acceleration
     * of 800 *steps/s²*.
     * 
     * An acceleration/deacceleration of 0 means instantaneous
     * acceleration/deacceleration (not recommended)
     * 
     * The default value is 1000 for both
     * 
     */
    public Integer getDeacceleration(){
        return deacceleration;
    }

    public void setDeacceleration(Integer deacceleration){
        this.deacceleration = deacceleration;
    }

    /**
     * 
     * Sets the current steps of the internal step counter. This can be used to
     * set the current position to 0 when some kind of starting position
     * is reached (e.g. when a CNC machine reaches a corner).
     * 
     */
    public Integer getPosition(){
        return position;
    }

    public void setPosition(Integer position){
        this.position = position;
    }

    /**
     * 
     * Sets the target position of the stepper motor in steps. For example,
     * if the current position of the motor is 500 and :func:`SetTargetPosition` is
     * called with 1000, the stepper motor will drive 500 steps forward. It will
     * use the velocity, acceleration and deacceleration as set by
     * :func:`SetMaxVelocity` and :func:`SetSpeedRamping`.
     * 
     * A call of :func:`SetTargetPosition` with the parameter *x* is equivalent to
     * a call of :func:`SetSteps` with the parameter 
     * (*x* - :func:`GetCurrentPosition`).
     * 
     */
    public Integer getPosition2(){
        return position2;
    }

    public void setPosition2(Integer position2){
        this.position2 = position2;
    }

    /**
     * 
     * Sets the number of steps the stepper motor should run. Positive values
     * will drive the motor forward and negative values backward. 
     * The velocity, acceleration and deacceleration as set by
     * :func:`SetMaxVelocity` and :func:`SetSpeedRamping` will be used.
     * 
     */
    public Integer getSteps(){
        return steps;
    }

    public void setSteps(Integer steps){
        this.steps = steps;
    }

    /**
     * 
     * Sets the step mode of the stepper motor. Possible values are:
     * 
     * * Full Step = 1
     * * Half Step = 2
     * * Quarter Step = 4
     * * Eighth Step = 8
     * 
     * A higher value will increase the resolution and
     * decrease the torque of the stepper motor.
     * 
     * The default value is 8 (Eighth Step).
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
     * Sets the current in mA with which the motor will be driven.
     * The minimum value is 100mA, the maximum value 2291mA and the 
     * default value is 800mA.
     * 
     * .. warning::
     *  Do not set this value above the specifications of your stepper motor.
     *  Otherwise it may damage your motor.
     * 
     */
    public Integer getCurrent(){
        return current;
    }

    public void setCurrent(Integer current){
        this.current = current;
    }

    /**
     * 
     * Sets the decay mode of the stepper motor. The possible value range is
     * between 0 and 65535. A value of 0 sets the fast decay mode, a value of
     * 65535 sets the slow decay mode and a value in between sets the mixed
     * decay mode.
     * 
     * Changing the decay mode is only possible if synchronous rectification
     * is enabled (see :func:`SetSyncRect`).
     * 
     * For a good explanation of the different decay modes see 
     * `this <http://ebldc.com/?p=86/>`__ blog post by Avayan.
     * 
     * A good decay mode is unfortunately different for every motor. The best
     * way to work out a good decay mode for your stepper motor, if you can't
     * measure the current with an oscilloscope, is to listen to the sound of
     * the motor. If the value is too low, you often hear a high pitched 
     * sound and if it is too high you can often hear a humming sound.
     * 
     * Generally, fast decay mode (small value) will be noisier but also
     * allow higher motor speeds.
     * 
     * The default value is 10000.
     * 
     * .. note::
     *  There is unfortunately no formula to calculate a perfect decay
     *  mode for a given stepper motor. If you have problems with loud noises
     *  or the maximum motor speed is too slow, you should try to tinker with
     *  the decay value
     * 
     */
    public Integer getDecay(){
        return decay;
    }

    public void setDecay(Integer decay){
        this.decay = decay;
    }

    /**
     * 
     * Sets the minimum voltage in mV, below which the :func:`UnderVoltage` callback
     * is triggered. The minimum possible value that works with the Stepper Brick is 8V.
     * You can use this function to detect the discharge of a battery that is used
     * to drive the stepper motor. If you have a fixed power supply, you likely do 
     * not need this functionality.
     * 
     * The default value is 8V.
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
     * Turns synchronous rectification on or off (*true* or *false*).
     * 
     * With synchronous rectification on, the decay can be changed
     * (see :func:`SetDecay`). Without synchronous rectification fast
     * decay is used.
     * 
     * For an explanation of synchronous rectification see 
     * `here <https://en.wikipedia.org/wiki/Active_rectification>`__.
     * 
     * .. warning::
     *  If you want to use high speeds (> 10000 steps/s) for a large 
     *  stepper motor with a large inductivity we strongly
     *  suggest that you disable synchronous rectification. Otherwise the
     *  Brick may not be able to cope with the load and overheat.
     * 
     * The default value is *false*.
     * 
     */
    public Boolean getSyncRect(){
        return syncRect;
    }

    public void setSyncRect(Boolean syncRect){
        this.syncRect = syncRect;
    }

    /**
     * 
     * Sets the time base of the velocity and the acceleration of the stepper brick
     * (in seconds).
     * 
     * For example, if you want to make one step every 1.5 seconds, you can set 
     * the time base to 15 and the velocity to 10. Now the velocity is 
     * 10steps/15s = 1steps/1.5s.
     * 
     * The default value is 1.
     * 
     */
    public Long getTimeBase(){
        return timeBase;
    }

    public void setTimeBase(Long timeBase){
        this.timeBase = timeBase;
    }

    /**
     * 
     * Sets the period in ms with which the :func:`AllData` callback is triggered
     * periodically. A value of 0 turns the callback off.
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
