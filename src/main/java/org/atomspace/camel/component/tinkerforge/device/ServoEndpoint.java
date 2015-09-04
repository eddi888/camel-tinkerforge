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

import com.tinkerforge.BrickServo;

/**
 * Drives up to 7 RC Servos with up to 3A
 */
@UriEndpoint(scheme = "tinkerforgegen", syntax = "tinkerforgegen:[host[:port]/]servo", consumerClass = ServoConsumer.class, label = "iot", title = "Tinkerforge")
public class ServoEndpoint extends TinkerforgeEndpoint<ServoConsumer, ServoProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(ServoEndpoint.class);

    public static final String SERVONUM="servoNum";
    public static final String SERVONUM2="servoNum2";
    public static final String SERVONUM3="servoNum3";
    public static final String SERVONUM4="servoNum4";
    public static final String POSITION="position";
    public static final String SERVONUM5="servoNum5";
    public static final String SERVONUM6="servoNum6";
    public static final String SERVONUM7="servoNum7";
    public static final String VELOCITY="velocity";
    public static final String SERVONUM8="servoNum8";
    public static final String SERVONUM9="servoNum9";
    public static final String SERVONUM10="servoNum10";
    public static final String ACCELERATION="acceleration";
    public static final String SERVONUM11="servoNum11";
    public static final String VOLTAGE="voltage";
    public static final String SERVONUM12="servoNum12";
    public static final String MIN="min";
    public static final String MAX="max";
    public static final String SERVONUM13="servoNum13";
    public static final String SERVONUM14="servoNum14";
    public static final String MIN2="min2";
    public static final String MAX2="max2";
    public static final String SERVONUM15="servoNum15";
    public static final String SERVONUM16="servoNum16";
    public static final String PERIOD="period";
    public static final String SERVONUM17="servoNum17";
    public static final String SERVONUM18="servoNum18";
    public static final String VOLTAGE2="voltage2";
    public static final String PORT="port";

    
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
     * Enables a servo (0 to 6). If a servo is enabled, the configured position,
     * velocity, acceleration, etc. are applied immediately.
     * 
     */
    public Short getServoNum(){
        return servoNum;
    }

    public void setServoNum(Short servoNum){
        this.servoNum = servoNum;
    }

    /**
     * 
     * Disables a servo (0 to 6). Disabled servos are not driven at all, i.e. a
     * disabled servo will not hold its position if a load is applied.
     * 
     */
    public Short getServoNum2(){
        return servoNum2;
    }

    public void setServoNum2(Short servoNum2){
        this.servoNum2 = servoNum2;
    }

    /**
     * 
     * Returns *true* if the specified servo is enabled, *false* otherwise.
     * 
     */
    public Short getServoNum3(){
        return servoNum3;
    }

    public void setServoNum3(Short servoNum3){
        this.servoNum3 = servoNum3;
    }

    /**
     * 
     * Sets the position in °/100 for the specified servo. 
     * 
     * The default range of the position is -9000 to 9000, but it can be specified
     * according to your servo with :func:`SetDegree`.
     * 
     * If you want to control a linear servo or RC brushless motor controller or
     * similar with the Servo Brick, you can also define lengths or speeds with
     * :func:`SetDegree`.
     * 
     */
    public Short getServoNum4(){
        return servoNum4;
    }

    public void setServoNum4(Short servoNum4){
        this.servoNum4 = servoNum4;
    }

    /**
     * 
     * Sets the position in °/100 for the specified servo. 
     * 
     * The default range of the position is -9000 to 9000, but it can be specified
     * according to your servo with :func:`SetDegree`.
     * 
     * If you want to control a linear servo or RC brushless motor controller or
     * similar with the Servo Brick, you can also define lengths or speeds with
     * :func:`SetDegree`.
     * 
     */
    public Short getPosition(){
        return position;
    }

    public void setPosition(Short position){
        this.position = position;
    }

    /**
     * 
     * Returns the position of the specified servo as set by :func:`SetPosition`.
     * 
     */
    public Short getServoNum5(){
        return servoNum5;
    }

    public void setServoNum5(Short servoNum5){
        this.servoNum5 = servoNum5;
    }

    /**
     * 
     * Returns the *current* position of the specified servo. This may not be the
     * value of :func:`SetPosition` if the servo is currently approaching a
     * position goal.
     * 
     */
    public Short getServoNum6(){
        return servoNum6;
    }

    public void setServoNum6(Short servoNum6){
        this.servoNum6 = servoNum6;
    }

    /**
     * 
     * Sets the maximum velocity of the specified servo in °/100s. The velocity
     * is accelerated according to the value set by :func:`SetAcceleration`.
     * 
     * The minimum velocity is 0 (no movement) and the maximum velocity is 65535.
     * With a value of 65535 the position will be set immediately (no velocity).
     * 
     * The default value is 65535.
     * 
     */
    public Short getServoNum7(){
        return servoNum7;
    }

    public void setServoNum7(Short servoNum7){
        this.servoNum7 = servoNum7;
    }

    /**
     * 
     * Sets the maximum velocity of the specified servo in °/100s. The velocity
     * is accelerated according to the value set by :func:`SetAcceleration`.
     * 
     * The minimum velocity is 0 (no movement) and the maximum velocity is 65535.
     * With a value of 65535 the position will be set immediately (no velocity).
     * 
     * The default value is 65535.
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
     * Returns the velocity of the specified servo as set by :func:`SetVelocity`.
     * 
     */
    public Short getServoNum8(){
        return servoNum8;
    }

    public void setServoNum8(Short servoNum8){
        this.servoNum8 = servoNum8;
    }

    /**
     * 
     * Returns the *current* velocity of the specified servo. This may not be the
     * value of :func:`SetVelocity` if the servo is currently approaching a
     * velocity goal.
     * 
     */
    public Short getServoNum9(){
        return servoNum9;
    }

    public void setServoNum9(Short servoNum9){
        this.servoNum9 = servoNum9;
    }

    /**
     * 
     * Sets the acceleration of the specified servo in °/100s².
     * 
     * The minimum acceleration is 1 and the maximum acceleration is 65535.
     * With a value of 65535 the velocity will be set immediately (no acceleration).
     * 
     * The default value is 65535.
     * 
     */
    public Short getServoNum10(){
        return servoNum10;
    }

    public void setServoNum10(Short servoNum10){
        this.servoNum10 = servoNum10;
    }

    /**
     * 
     * Sets the acceleration of the specified servo in °/100s².
     * 
     * The minimum acceleration is 1 and the maximum acceleration is 65535.
     * With a value of 65535 the velocity will be set immediately (no acceleration).
     * 
     * The default value is 65535.
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
     * Returns the acceleration for the specified servo as set by 
     * :func:`SetAcceleration`.
     * 
     */
    public Short getServoNum11(){
        return servoNum11;
    }

    public void setServoNum11(Short servoNum11){
        this.servoNum11 = servoNum11;
    }

    /**
     * 
     * Sets the output voltages with which the servos are driven in mV.
     * The minimum output voltage is 2000mV and the maximum output voltage is 
     * 9000mV.
     * 
     * .. note::
     *  We recommend that you set this value to the maximum voltage that is
     *  specified for your servo, most servos achieve their maximum force only
     *  with high voltages.
     * 
     * The default value is 5000.
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
     * Sets the minimum and maximum pulse width of the specified servo in µs.
     * 
     * Usually, servos are controlled with a 
     * `PWM <https://en.wikipedia.org/wiki/Pulse-width_modulation>`__, whereby the
     * length of the pulse controls the position of the servo. Every servo has
     * different minimum and maximum pulse widths, these can be specified with
     * this function.
     * 
     * If you have a datasheet for your servo that specifies the minimum and
     * maximum pulse width, you should set the values accordingly. If your servo
     * comes without any datasheet you have to find the values via trial and error.
     * 
     * Both values have a range from 1 to 65535 (unsigned 16-bit integer). The
     * minimum must be smaller than the maximum.
     * 
     * The default values are 1000µs (1ms) and 2000µs (2ms) for minimum and 
     * maximum pulse width.
     * 
     */
    public Short getServoNum12(){
        return servoNum12;
    }

    public void setServoNum12(Short servoNum12){
        this.servoNum12 = servoNum12;
    }

    /**
     * 
     * Sets the minimum and maximum pulse width of the specified servo in µs.
     * 
     * Usually, servos are controlled with a 
     * `PWM <https://en.wikipedia.org/wiki/Pulse-width_modulation>`__, whereby the
     * length of the pulse controls the position of the servo. Every servo has
     * different minimum and maximum pulse widths, these can be specified with
     * this function.
     * 
     * If you have a datasheet for your servo that specifies the minimum and
     * maximum pulse width, you should set the values accordingly. If your servo
     * comes without any datasheet you have to find the values via trial and error.
     * 
     * Both values have a range from 1 to 65535 (unsigned 16-bit integer). The
     * minimum must be smaller than the maximum.
     * 
     * The default values are 1000µs (1ms) and 2000µs (2ms) for minimum and 
     * maximum pulse width.
     * 
     */
    public Integer getMin(){
        return min;
    }

    public void setMin(Integer min){
        this.min = min;
    }

    /**
     * 
     * Sets the minimum and maximum pulse width of the specified servo in µs.
     * 
     * Usually, servos are controlled with a 
     * `PWM <https://en.wikipedia.org/wiki/Pulse-width_modulation>`__, whereby the
     * length of the pulse controls the position of the servo. Every servo has
     * different minimum and maximum pulse widths, these can be specified with
     * this function.
     * 
     * If you have a datasheet for your servo that specifies the minimum and
     * maximum pulse width, you should set the values accordingly. If your servo
     * comes without any datasheet you have to find the values via trial and error.
     * 
     * Both values have a range from 1 to 65535 (unsigned 16-bit integer). The
     * minimum must be smaller than the maximum.
     * 
     * The default values are 1000µs (1ms) and 2000µs (2ms) for minimum and 
     * maximum pulse width.
     * 
     */
    public Integer getMax(){
        return max;
    }

    public void setMax(Integer max){
        this.max = max;
    }

    /**
     * 
     * Returns the minimum and maximum pulse width for the specified servo as set by
     * :func:`SetPulseWidth`.
     * 
     */
    public Short getServoNum13(){
        return servoNum13;
    }

    public void setServoNum13(Short servoNum13){
        this.servoNum13 = servoNum13;
    }

    /**
     * 
     * Sets the minimum and maximum degree for the specified servo (by default
     * given as °/100).
     * 
     * This only specifies the abstract values between which the minimum and maximum
     * pulse width is scaled. For example: If you specify a pulse width of 1000µs
     * to 2000µs and a degree range of -90° to 90°, a call of :func:`SetPosition`
     * with 0 will result in a pulse width of 1500µs 
     * (-90° = 1000µs, 90° = 2000µs, etc.).
     * 
     * Possible usage:
     * 
     * * The datasheet of your servo specifies a range of 200° with the middle position
     *   at 110°. In this case you can set the minimum to -9000 and the maximum to 11000.
     * * You measure a range of 220° on your servo and you don't have or need a middle
     *   position. In this case you can set the minimum to 0 and the maximum to 22000.
     * * You have a linear servo with a drive length of 20cm, In this case you could
     *   set the minimum to 0 and the maximum to 20000. Now you can set the Position
     *   with :func:`SetPosition` with a resolution of cm/100. Also the velocity will
     *   have a resolution of cm/100s and the acceleration will have a resolution of
     *   cm/100s².
     * * You don't care about units and just want the highest possible resolution. In
     *   this case you should set the minimum to -32767 and the maximum to 32767.
     * * You have a brushless motor with a maximum speed of 10000 rpm and want to
     *   control it with a RC brushless motor controller. In this case you can set the
     *   minimum to 0 and the maximum to 10000. :func:`SetPosition` now controls the rpm.
     * 
     * Both values have a possible range from -32767 to 32767 
     * (signed 16-bit integer). The minimum must be smaller than the maximum.
     * 
     * The default values are -9000 and 9000 for the minimum and maximum degree.
     * 
     */
    public Short getServoNum14(){
        return servoNum14;
    }

    public void setServoNum14(Short servoNum14){
        this.servoNum14 = servoNum14;
    }

    /**
     * 
     * Sets the minimum and maximum degree for the specified servo (by default
     * given as °/100).
     * 
     * This only specifies the abstract values between which the minimum and maximum
     * pulse width is scaled. For example: If you specify a pulse width of 1000µs
     * to 2000µs and a degree range of -90° to 90°, a call of :func:`SetPosition`
     * with 0 will result in a pulse width of 1500µs 
     * (-90° = 1000µs, 90° = 2000µs, etc.).
     * 
     * Possible usage:
     * 
     * * The datasheet of your servo specifies a range of 200° with the middle position
     *   at 110°. In this case you can set the minimum to -9000 and the maximum to 11000.
     * * You measure a range of 220° on your servo and you don't have or need a middle
     *   position. In this case you can set the minimum to 0 and the maximum to 22000.
     * * You have a linear servo with a drive length of 20cm, In this case you could
     *   set the minimum to 0 and the maximum to 20000. Now you can set the Position
     *   with :func:`SetPosition` with a resolution of cm/100. Also the velocity will
     *   have a resolution of cm/100s and the acceleration will have a resolution of
     *   cm/100s².
     * * You don't care about units and just want the highest possible resolution. In
     *   this case you should set the minimum to -32767 and the maximum to 32767.
     * * You have a brushless motor with a maximum speed of 10000 rpm and want to
     *   control it with a RC brushless motor controller. In this case you can set the
     *   minimum to 0 and the maximum to 10000. :func:`SetPosition` now controls the rpm.
     * 
     * Both values have a possible range from -32767 to 32767 
     * (signed 16-bit integer). The minimum must be smaller than the maximum.
     * 
     * The default values are -9000 and 9000 for the minimum and maximum degree.
     * 
     */
    public Short getMin2(){
        return min2;
    }

    public void setMin2(Short min2){
        this.min2 = min2;
    }

    /**
     * 
     * Sets the minimum and maximum degree for the specified servo (by default
     * given as °/100).
     * 
     * This only specifies the abstract values between which the minimum and maximum
     * pulse width is scaled. For example: If you specify a pulse width of 1000µs
     * to 2000µs and a degree range of -90° to 90°, a call of :func:`SetPosition`
     * with 0 will result in a pulse width of 1500µs 
     * (-90° = 1000µs, 90° = 2000µs, etc.).
     * 
     * Possible usage:
     * 
     * * The datasheet of your servo specifies a range of 200° with the middle position
     *   at 110°. In this case you can set the minimum to -9000 and the maximum to 11000.
     * * You measure a range of 220° on your servo and you don't have or need a middle
     *   position. In this case you can set the minimum to 0 and the maximum to 22000.
     * * You have a linear servo with a drive length of 20cm, In this case you could
     *   set the minimum to 0 and the maximum to 20000. Now you can set the Position
     *   with :func:`SetPosition` with a resolution of cm/100. Also the velocity will
     *   have a resolution of cm/100s and the acceleration will have a resolution of
     *   cm/100s².
     * * You don't care about units and just want the highest possible resolution. In
     *   this case you should set the minimum to -32767 and the maximum to 32767.
     * * You have a brushless motor with a maximum speed of 10000 rpm and want to
     *   control it with a RC brushless motor controller. In this case you can set the
     *   minimum to 0 and the maximum to 10000. :func:`SetPosition` now controls the rpm.
     * 
     * Both values have a possible range from -32767 to 32767 
     * (signed 16-bit integer). The minimum must be smaller than the maximum.
     * 
     * The default values are -9000 and 9000 for the minimum and maximum degree.
     * 
     */
    public Short getMax2(){
        return max2;
    }

    public void setMax2(Short max2){
        this.max2 = max2;
    }

    /**
     * 
     * Returns the minimum and maximum degree for the specified servo as set by
     * :func:`SetDegree`.
     * 
     */
    public Short getServoNum15(){
        return servoNum15;
    }

    public void setServoNum15(Short servoNum15){
        this.servoNum15 = servoNum15;
    }

    /**
     * 
     * Sets the period of the specified servo in µs.
     * 
     * Usually, servos are controlled with a 
     * `PWM <https://en.wikipedia.org/wiki/Pulse-width_modulation>`__. Different
     * servos expect PWMs with different periods. Most servos run well with a 
     * period of about 20ms.
     * 
     * If your servo comes with a datasheet that specifies a period, you should
     * set it accordingly. If you don't have a datasheet and you have no idea
     * what the correct period is, the default value (19.5ms) will most likely
     * work fine. 
     * 
     * The minimum possible period is 1µs and the maximum is 65535µs.
     * 
     * The default value is 19.5ms (19500µs).
     * 
     */
    public Short getServoNum16(){
        return servoNum16;
    }

    public void setServoNum16(Short servoNum16){
        this.servoNum16 = servoNum16;
    }

    /**
     * 
     * Sets the period of the specified servo in µs.
     * 
     * Usually, servos are controlled with a 
     * `PWM <https://en.wikipedia.org/wiki/Pulse-width_modulation>`__. Different
     * servos expect PWMs with different periods. Most servos run well with a 
     * period of about 20ms.
     * 
     * If your servo comes with a datasheet that specifies a period, you should
     * set it accordingly. If you don't have a datasheet and you have no idea
     * what the correct period is, the default value (19.5ms) will most likely
     * work fine. 
     * 
     * The minimum possible period is 1µs and the maximum is 65535µs.
     * 
     * The default value is 19.5ms (19500µs).
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
     * Returns the period for the specified servo as set by :func:`SetPeriod`.
     * 
     */
    public Short getServoNum17(){
        return servoNum17;
    }

    public void setServoNum17(Short servoNum17){
        this.servoNum17 = servoNum17;
    }

    /**
     * 
     * Returns the current consumption of the specified servo in mA.
     * 
     */
    public Short getServoNum18(){
        return servoNum18;
    }

    public void setServoNum18(Short servoNum18){
        this.servoNum18 = servoNum18;
    }

    /**
     * 
     * Sets the minimum voltage in mV, below which the :func:`UnderVoltage` callback
     * is triggered. The minimum possible value that works with the Servo Brick is 5V.
     * You can use this function to detect the discharge of a battery that is used
     * to drive the stepper motor. If you have a fixed power supply, you likely do 
     * not need this functionality.
     * 
     * The default value is 5V (5000mV).
     * 
     */
    public Integer getVoltage2(){
        return voltage2;
    }

    public void setVoltage2(Integer voltage2){
        this.voltage2 = voltage2;
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
