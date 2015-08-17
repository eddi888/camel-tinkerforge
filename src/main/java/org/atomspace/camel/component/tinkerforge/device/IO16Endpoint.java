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

import com.tinkerforge.BrickletIO16;

/**
 * 16-channel digital input/output
 */
public class IO16Endpoint extends TinkerforgeEndpoint<IO16Consumer, IO16Producer> {

    private static final Logger LOG = LoggerFactory.getLogger(IO16Endpoint.class);
    
    private Character port;
    private Short valueMask;
    private Character port2;
    private Character port3;
    private Short selectionMask;
    private Character direction;
    private Boolean value;
    private Character port4;
    private Long debounce;
    private Character port5;
    private Short interruptMask;
    private Character port6;
    private Character port7;
    private Short selectionMask2;
    private Short valueMask2;
    private Long time;
    private Character port8;
    private Short pin;
    private Character port9;
    private Short selectionMask3;
    private Short valueMask3;
    private Short pin2;
    private Boolean resetCounter;
    private Short pin3;
    private Short edgeType;
    private Short debounce2;
    private Short pin4;

        
    public IO16Endpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new IO16Producer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new IO16Consumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletIO16 device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletIO16 device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "setPort":
                device.setPort(
                        getValue(char.class, "port", m, getPort()),
                        getValue(short.class, "valueMask", m, getValueMask())
                    );
                break;

            case "getPort":
                response = device.getPort(
                        getValue(char.class, "port2", m, getPort2())
                    );
                break;

            case "setPortConfiguration":
                device.setPortConfiguration(
                        getValue(char.class, "port3", m, getPort3()),
                        getValue(short.class, "selectionMask", m, getSelectionMask()),
                        getValue(char.class, "direction", m, getDirection()),
                        getValue(boolean.class, "value", m, getValue())
                    );
                break;

            case "getPortConfiguration":
                response = device.getPortConfiguration(
                        getValue(char.class, "port4", m, getPort4())
                    );
                break;

            case "setDebouncePeriod":
                device.setDebouncePeriod(
                        getValue(long.class, "debounce", m, getDebounce())
                    );
                break;

            case "getDebouncePeriod":
                response = device.getDebouncePeriod();
                break;

            case "setPortInterrupt":
                device.setPortInterrupt(
                        getValue(char.class, "port5", m, getPort5()),
                        getValue(short.class, "interruptMask", m, getInterruptMask())
                    );
                break;

            case "getPortInterrupt":
                response = device.getPortInterrupt(
                        getValue(char.class, "port6", m, getPort6())
                    );
                break;

            case "setPortMonoflop":
                device.setPortMonoflop(
                        getValue(char.class, "port7", m, getPort7()),
                        getValue(short.class, "selectionMask2", m, getSelectionMask2()),
                        getValue(short.class, "valueMask2", m, getValueMask2()),
                        getValue(long.class, "time", m, getTime())
                    );
                break;

            case "getPortMonoflop":
                response = device.getPortMonoflop(
                        getValue(char.class, "port8", m, getPort8()),
                        getValue(short.class, "pin", m, getPin())
                    );
                break;

            case "setSelectedValues":
                device.setSelectedValues(
                        getValue(char.class, "port9", m, getPort9()),
                        getValue(short.class, "selectionMask3", m, getSelectionMask3()),
                        getValue(short.class, "valueMask3", m, getValueMask3())
                    );
                break;

            case "getEdgeCount":
                response = device.getEdgeCount(
                        getValue(short.class, "pin2", m, getPin2()),
                        getValue(boolean.class, "resetCounter", m, getResetCounter())
                    );
                break;

            case "setEdgeCountConfig":
                device.setEdgeCountConfig(
                        getValue(short.class, "pin3", m, getPin3()),
                        getValue(short.class, "edgeType", m, getEdgeType()),
                        getValue(short.class, "debounce2", m, getDebounce2())
                    );
                break;

            case "getEdgeCountConfig":
                response = device.getEdgeCountConfig(
                        getValue(short.class, "pin4", m, getPin4())
                    );
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
     * Sets the output value (high or low) for a port ("a" or "b") with a bitmask
     * (8bit). A 1 in the bitmask means high and a 0 in the bitmask means low.
     * 
     * For example: The value 15 or 0b00001111 will turn the pins 0-3 high and the
     * pins 4-7 low for the specified port.
     * 
     * .. note::
     *  This function does nothing for pins that are configured as input.
     *  Pull-up resistors can be switched on with :func:`SetPortConfiguration`.
     * 
     */
    public Character getPort(){
        return port;
    }

    public void setPort(Character port){
        this.port = port;
    }

    /**
     * 
     * Sets the output value (high or low) for a port ("a" or "b") with a bitmask
     * (8bit). A 1 in the bitmask means high and a 0 in the bitmask means low.
     * 
     * For example: The value 15 or 0b00001111 will turn the pins 0-3 high and the
     * pins 4-7 low for the specified port.
     * 
     * .. note::
     *  This function does nothing for pins that are configured as input.
     *  Pull-up resistors can be switched on with :func:`SetPortConfiguration`.
     * 
     */
    public Short getValueMask(){
        return valueMask;
    }

    public void setValueMask(Short valueMask){
        this.valueMask = valueMask;
    }

    /**
     * 
     * Returns a bitmask of the values that are currently measured on the
     * specified port. This function works if the pin is configured to input
     * as well as if it is configured to output.
     * 
     */
    public Character getPort2(){
        return port2;
    }

    public void setPort2(Character port2){
        this.port2 = port2;
    }

    /**
     * 
     * Configures the value and direction of a specified port. Possible directions
     * are 'i' and 'o' for input and output.
     * 
     * If the direction is configured as output, the value is either high or low
     * (set as *true* or *false*).
     * 
     * If the direction is configured as input, the value is either pull-up or
     * default (set as *true* or *false*).
     * 
     * For example:
     * 
     * * ('a', 255, 'i', true) or ('a', 0b11111111, 'i', true) will set all pins of port A as input pull-up.
     * * ('a', 128, 'i', false) or ('a', 0b10000000, 'i', false) will set pin 7 of port A as input default (floating if nothing is connected).
     * * ('b', 3, 'o', false) or ('b', 0b00000011, 'o', false) will set pins 0 and 1 of port B as output low.
     * * ('b', 4, 'o', true) or ('b', 0b00000100, 'o', true) will set pin 2 of port B as output high.
     * 
     * The default configuration is input with pull-up.
     * 
     */
    public Character getPort3(){
        return port3;
    }

    public void setPort3(Character port3){
        this.port3 = port3;
    }

    /**
     * 
     * Configures the value and direction of a specified port. Possible directions
     * are 'i' and 'o' for input and output.
     * 
     * If the direction is configured as output, the value is either high or low
     * (set as *true* or *false*).
     * 
     * If the direction is configured as input, the value is either pull-up or
     * default (set as *true* or *false*).
     * 
     * For example:
     * 
     * * ('a', 255, 'i', true) or ('a', 0b11111111, 'i', true) will set all pins of port A as input pull-up.
     * * ('a', 128, 'i', false) or ('a', 0b10000000, 'i', false) will set pin 7 of port A as input default (floating if nothing is connected).
     * * ('b', 3, 'o', false) or ('b', 0b00000011, 'o', false) will set pins 0 and 1 of port B as output low.
     * * ('b', 4, 'o', true) or ('b', 0b00000100, 'o', true) will set pin 2 of port B as output high.
     * 
     * The default configuration is input with pull-up.
     * 
     */
    public Short getSelectionMask(){
        return selectionMask;
    }

    public void setSelectionMask(Short selectionMask){
        this.selectionMask = selectionMask;
    }

    /**
     * 
     * Configures the value and direction of a specified port. Possible directions
     * are 'i' and 'o' for input and output.
     * 
     * If the direction is configured as output, the value is either high or low
     * (set as *true* or *false*).
     * 
     * If the direction is configured as input, the value is either pull-up or
     * default (set as *true* or *false*).
     * 
     * For example:
     * 
     * * ('a', 255, 'i', true) or ('a', 0b11111111, 'i', true) will set all pins of port A as input pull-up.
     * * ('a', 128, 'i', false) or ('a', 0b10000000, 'i', false) will set pin 7 of port A as input default (floating if nothing is connected).
     * * ('b', 3, 'o', false) or ('b', 0b00000011, 'o', false) will set pins 0 and 1 of port B as output low.
     * * ('b', 4, 'o', true) or ('b', 0b00000100, 'o', true) will set pin 2 of port B as output high.
     * 
     * The default configuration is input with pull-up.
     * 
     */
    public Character getDirection(){
        return direction;
    }

    public void setDirection(Character direction){
        this.direction = direction;
    }

    /**
     * 
     * Configures the value and direction of a specified port. Possible directions
     * are 'i' and 'o' for input and output.
     * 
     * If the direction is configured as output, the value is either high or low
     * (set as *true* or *false*).
     * 
     * If the direction is configured as input, the value is either pull-up or
     * default (set as *true* or *false*).
     * 
     * For example:
     * 
     * * ('a', 255, 'i', true) or ('a', 0b11111111, 'i', true) will set all pins of port A as input pull-up.
     * * ('a', 128, 'i', false) or ('a', 0b10000000, 'i', false) will set pin 7 of port A as input default (floating if nothing is connected).
     * * ('b', 3, 'o', false) or ('b', 0b00000011, 'o', false) will set pins 0 and 1 of port B as output low.
     * * ('b', 4, 'o', true) or ('b', 0b00000100, 'o', true) will set pin 2 of port B as output high.
     * 
     * The default configuration is input with pull-up.
     * 
     */
    public Boolean getValue(){
        return value;
    }

    public void setValue(Boolean value){
        this.value = value;
    }

    /**
     * 
     * Returns a direction bitmask and a value bitmask for the specified port. A 1 in
     * the direction bitmask means input and a 0 in the bitmask means output.
     * 
     * For example: A return value of (15, 51) or (0b00001111, 0b00110011) for
     * direction and value means that:
     * 
     * * pins 0 and 1 are configured as input pull-up,
     * * pins 2 and 3 are configured as input default,
     * * pins 4 and 5 are configured as output high
     * * and pins 6 and 7 are configured as output low.
     * 
     */
    public Character getPort4(){
        return port4;
    }

    public void setPort4(Character port4){
        this.port4 = port4;
    }

    /**
     * 
     * Sets the debounce period of the :func:`Interrupt` callback in ms.
     * 
     * For example: If you set this value to 100, you will get the interrupt
     * maximal every 100ms. This is necessary if something that bounces is
     * connected to the IO-16 Bricklet, such as a button.
     * 
     * The default value is 100.
     * 
     */
    public Long getDebounce(){
        return debounce;
    }

    public void setDebounce(Long debounce){
        this.debounce = debounce;
    }

    /**
     * 
     * Sets the pins on which an interrupt is activated with a bitmask.
     * Interrupts are triggered on changes of the voltage level of the pin,
     * i.e. changes from high to low and low to high.
     * 
     * For example: ('a', 129) or ('a', 0b10000001) will enable the interrupt for
     * pins 0 and 7 of port a.
     * 
     * The interrupt is delivered with the callback :func:`Interrupt`.
     * 
     */
    public Character getPort5(){
        return port5;
    }

    public void setPort5(Character port5){
        this.port5 = port5;
    }

    /**
     * 
     * Sets the pins on which an interrupt is activated with a bitmask.
     * Interrupts are triggered on changes of the voltage level of the pin,
     * i.e. changes from high to low and low to high.
     * 
     * For example: ('a', 129) or ('a', 0b10000001) will enable the interrupt for
     * pins 0 and 7 of port a.
     * 
     * The interrupt is delivered with the callback :func:`Interrupt`.
     * 
     */
    public Short getInterruptMask(){
        return interruptMask;
    }

    public void setInterruptMask(Short interruptMask){
        this.interruptMask = interruptMask;
    }

    /**
     * 
     * Returns the interrupt bitmask for the specified port as set by
     * :func:`SetPortInterrupt`.
     * 
     */
    public Character getPort6(){
        return port6;
    }

    public void setPort6(Character port6){
        this.port6 = port6;
    }

    /**
     * 
     * Configures a monoflop of the pins specified by the second parameter as 8 bit
     * long bitmask. The specified pins must be configured for output. Non-output
     * pins will be ignored.
     * 
     * The third parameter is a bitmask with the desired value of the specified
     * output pins. A 1 in the bitmask means high and a 0 in the bitmask means low.
     * 
     * The forth parameter indicates the time (in ms) that the pins should hold
     * the value.
     * 
     * If this function is called with the parameters ('a', 9, 1, 1500) or
     * ('a', 0b00001001, 0b00000001, 1500): Pin 0 will get high and pin 3 will get
     * low on port 'a'. In 1.5s pin 0 will get low and pin 3 will get high again.
     * 
     * A monoflop can be used as a fail-safe mechanism. For example: Lets assume you
     * have a RS485 bus and an IO-16 Bricklet connected to one of the slave
     * stacks. You can now call this function every second, with a time parameter
     * of two seconds and pin 0 set to high. Pin 0 will be high all the time. If now
     * the RS485 connection is lost, then pin 0 will get low in at most two seconds.
     * 
     */
    public Character getPort7(){
        return port7;
    }

    public void setPort7(Character port7){
        this.port7 = port7;
    }

    /**
     * 
     * Configures a monoflop of the pins specified by the second parameter as 8 bit
     * long bitmask. The specified pins must be configured for output. Non-output
     * pins will be ignored.
     * 
     * The third parameter is a bitmask with the desired value of the specified
     * output pins. A 1 in the bitmask means high and a 0 in the bitmask means low.
     * 
     * The forth parameter indicates the time (in ms) that the pins should hold
     * the value.
     * 
     * If this function is called with the parameters ('a', 9, 1, 1500) or
     * ('a', 0b00001001, 0b00000001, 1500): Pin 0 will get high and pin 3 will get
     * low on port 'a'. In 1.5s pin 0 will get low and pin 3 will get high again.
     * 
     * A monoflop can be used as a fail-safe mechanism. For example: Lets assume you
     * have a RS485 bus and an IO-16 Bricklet connected to one of the slave
     * stacks. You can now call this function every second, with a time parameter
     * of two seconds and pin 0 set to high. Pin 0 will be high all the time. If now
     * the RS485 connection is lost, then pin 0 will get low in at most two seconds.
     * 
     */
    public Short getSelectionMask2(){
        return selectionMask2;
    }

    public void setSelectionMask2(Short selectionMask2){
        this.selectionMask2 = selectionMask2;
    }

    /**
     * 
     * Configures a monoflop of the pins specified by the second parameter as 8 bit
     * long bitmask. The specified pins must be configured for output. Non-output
     * pins will be ignored.
     * 
     * The third parameter is a bitmask with the desired value of the specified
     * output pins. A 1 in the bitmask means high and a 0 in the bitmask means low.
     * 
     * The forth parameter indicates the time (in ms) that the pins should hold
     * the value.
     * 
     * If this function is called with the parameters ('a', 9, 1, 1500) or
     * ('a', 0b00001001, 0b00000001, 1500): Pin 0 will get high and pin 3 will get
     * low on port 'a'. In 1.5s pin 0 will get low and pin 3 will get high again.
     * 
     * A monoflop can be used as a fail-safe mechanism. For example: Lets assume you
     * have a RS485 bus and an IO-16 Bricklet connected to one of the slave
     * stacks. You can now call this function every second, with a time parameter
     * of two seconds and pin 0 set to high. Pin 0 will be high all the time. If now
     * the RS485 connection is lost, then pin 0 will get low in at most two seconds.
     * 
     */
    public Short getValueMask2(){
        return valueMask2;
    }

    public void setValueMask2(Short valueMask2){
        this.valueMask2 = valueMask2;
    }

    /**
     * 
     * Configures a monoflop of the pins specified by the second parameter as 8 bit
     * long bitmask. The specified pins must be configured for output. Non-output
     * pins will be ignored.
     * 
     * The third parameter is a bitmask with the desired value of the specified
     * output pins. A 1 in the bitmask means high and a 0 in the bitmask means low.
     * 
     * The forth parameter indicates the time (in ms) that the pins should hold
     * the value.
     * 
     * If this function is called with the parameters ('a', 9, 1, 1500) or
     * ('a', 0b00001001, 0b00000001, 1500): Pin 0 will get high and pin 3 will get
     * low on port 'a'. In 1.5s pin 0 will get low and pin 3 will get high again.
     * 
     * A monoflop can be used as a fail-safe mechanism. For example: Lets assume you
     * have a RS485 bus and an IO-16 Bricklet connected to one of the slave
     * stacks. You can now call this function every second, with a time parameter
     * of two seconds and pin 0 set to high. Pin 0 will be high all the time. If now
     * the RS485 connection is lost, then pin 0 will get low in at most two seconds.
     * 
     */
    public Long getTime(){
        return time;
    }

    public void setTime(Long time){
        this.time = time;
    }

    /**
     * 
     * Returns (for the given pin) the current value and the time as set by
     * :func:`SetPortMonoflop` as well as the remaining time until the value flips.
     * 
     * If the timer is not running currently, the remaining time will be returned
     * as 0.
     * 
     */
    public Character getPort8(){
        return port8;
    }

    public void setPort8(Character port8){
        this.port8 = port8;
    }

    /**
     * 
     * Returns (for the given pin) the current value and the time as set by
     * :func:`SetPortMonoflop` as well as the remaining time until the value flips.
     * 
     * If the timer is not running currently, the remaining time will be returned
     * as 0.
     * 
     */
    public Short getPin(){
        return pin;
    }

    public void setPin(Short pin){
        this.pin = pin;
    }

    /**
     * 
     * Sets the output value (high or low) for a port ("a" or "b" with a bitmask, 
     * according to the selection mask. The bitmask is 8 bit long and a 1 in the
     * bitmask means high and a 0 in the bitmask means low.
     * 
     * For example: The parameters ('a', 192, 128) or ('a', 0b11000000, 0b10000000)
     * will turn pin 7 high and pin 6 low on port A, pins 0-6 will remain untouched.
     * 
     * .. note::
     *  This function does nothing for pins that are configured as input.
     *  Pull-up resistors can be switched on with :func:`SetConfiguration`.
     * 
     */
    public Character getPort9(){
        return port9;
    }

    public void setPort9(Character port9){
        this.port9 = port9;
    }

    /**
     * 
     * Sets the output value (high or low) for a port ("a" or "b" with a bitmask, 
     * according to the selection mask. The bitmask is 8 bit long and a 1 in the
     * bitmask means high and a 0 in the bitmask means low.
     * 
     * For example: The parameters ('a', 192, 128) or ('a', 0b11000000, 0b10000000)
     * will turn pin 7 high and pin 6 low on port A, pins 0-6 will remain untouched.
     * 
     * .. note::
     *  This function does nothing for pins that are configured as input.
     *  Pull-up resistors can be switched on with :func:`SetConfiguration`.
     * 
     */
    public Short getSelectionMask3(){
        return selectionMask3;
    }

    public void setSelectionMask3(Short selectionMask3){
        this.selectionMask3 = selectionMask3;
    }

    /**
     * 
     * Sets the output value (high or low) for a port ("a" or "b" with a bitmask, 
     * according to the selection mask. The bitmask is 8 bit long and a 1 in the
     * bitmask means high and a 0 in the bitmask means low.
     * 
     * For example: The parameters ('a', 192, 128) or ('a', 0b11000000, 0b10000000)
     * will turn pin 7 high and pin 6 low on port A, pins 0-6 will remain untouched.
     * 
     * .. note::
     *  This function does nothing for pins that are configured as input.
     *  Pull-up resistors can be switched on with :func:`SetConfiguration`.
     * 
     */
    public Short getValueMask3(){
        return valueMask3;
    }

    public void setValueMask3(Short valueMask3){
        this.valueMask3 = valueMask3;
    }

    /**
     * 
     * Returns the current value of the edge counter for the selected pin on port A.
     * You can configure the edges that are counted with :func:`SetEdgeCountConfig`.
     * 
     * If you set the reset counter to *true*, the count is set back to 0
     * directly after it is read.
     * 
     */
    public Short getPin2(){
        return pin2;
    }

    public void setPin2(Short pin2){
        this.pin2 = pin2;
    }

    /**
     * 
     * Returns the current value of the edge counter for the selected pin on port A.
     * You can configure the edges that are counted with :func:`SetEdgeCountConfig`.
     * 
     * If you set the reset counter to *true*, the count is set back to 0
     * directly after it is read.
     * 
     */
    public Boolean getResetCounter(){
        return resetCounter;
    }

    public void setResetCounter(Boolean resetCounter){
        this.resetCounter = resetCounter;
    }

    /**
     * 
     * Configures the edge counter for the selected pin of port A. Pins 0 and 1
     * are available for edge counting.
     * 
     * The edge type parameter configures if rising edges, falling edges or
     * both are counted if the pin is configured for input. Possible edge types are:
     * 
     * * 0 = rising (default)
     * * 1 = falling
     * * 2 = both
     * 
     * The debounce time is given in ms.
     * 
     * Configuring an edge counter resets its value to 0.
     * 
     * If you don't know what any of this means, just leave it at default. The
     * default configuration is very likely OK for you.
     * 
     * Default values: 0 (edge type) and 100ms (debounce time)
     * 
     */
    public Short getPin3(){
        return pin3;
    }

    public void setPin3(Short pin3){
        this.pin3 = pin3;
    }

    /**
     * 
     * Configures the edge counter for the selected pin of port A. Pins 0 and 1
     * are available for edge counting.
     * 
     * The edge type parameter configures if rising edges, falling edges or
     * both are counted if the pin is configured for input. Possible edge types are:
     * 
     * * 0 = rising (default)
     * * 1 = falling
     * * 2 = both
     * 
     * The debounce time is given in ms.
     * 
     * Configuring an edge counter resets its value to 0.
     * 
     * If you don't know what any of this means, just leave it at default. The
     * default configuration is very likely OK for you.
     * 
     * Default values: 0 (edge type) and 100ms (debounce time)
     * 
     */
    public Short getEdgeType(){
        return edgeType;
    }

    public void setEdgeType(Short edgeType){
        this.edgeType = edgeType;
    }

    /**
     * 
     * Configures the edge counter for the selected pin of port A. Pins 0 and 1
     * are available for edge counting.
     * 
     * The edge type parameter configures if rising edges, falling edges or
     * both are counted if the pin is configured for input. Possible edge types are:
     * 
     * * 0 = rising (default)
     * * 1 = falling
     * * 2 = both
     * 
     * The debounce time is given in ms.
     * 
     * Configuring an edge counter resets its value to 0.
     * 
     * If you don't know what any of this means, just leave it at default. The
     * default configuration is very likely OK for you.
     * 
     * Default values: 0 (edge type) and 100ms (debounce time)
     * 
     */
    public Short getDebounce2(){
        return debounce2;
    }

    public void setDebounce2(Short debounce2){
        this.debounce2 = debounce2;
    }

    /**
     * 
     * Returns the edge type and debounce time for the selected pin of port A as set by
     * :func:`SetEdgeCountConfig`.
     * 
     */
    public Short getPin4(){
        return pin4;
    }

    public void setPin4(Short pin4){
        this.pin4 = pin4;
    }



}
