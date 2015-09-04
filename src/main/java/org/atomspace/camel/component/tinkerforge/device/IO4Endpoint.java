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

import com.tinkerforge.BrickletIO4;

/**
 * 4-channel digital input/output
 */
@UriEndpoint(scheme = "tinkerforge", syntax = "tinkerforge:[host[:port]/]io4", consumerClass = IO4Consumer.class, label = "iot", title = "Tinkerforge")
public class IO4Endpoint extends TinkerforgeEndpoint<IO4Consumer, IO4Producer> {

    private static final Logger LOG = LoggerFactory.getLogger(IO4Endpoint.class);

    public static final String VALUEMASK="valueMask";
    public static final String SELECTIONMASK="selectionMask";
    public static final String DIRECTION="direction";
    public static final String VALUE="value";
    public static final String DEBOUNCE="debounce";
    public static final String INTERRUPTMASK="interruptMask";
    public static final String SELECTIONMASK2="selectionMask2";
    public static final String VALUEMASK2="valueMask2";
    public static final String TIME="time";
    public static final String PIN="pin";
    public static final String SELECTIONMASK3="selectionMask3";
    public static final String VALUEMASK3="valueMask3";
    public static final String PIN2="pin2";
    public static final String RESETCOUNTER="resetCounter";
    public static final String SELECTIONMASK4="selectionMask4";
    public static final String EDGETYPE="edgeType";
    public static final String DEBOUNCE2="debounce2";
    public static final String PIN3="pin3";

    
    private Short valueMask;
    private Short selectionMask;
    private Character direction;
    private Boolean value;
    private Long debounce;
    private Short interruptMask;
    private Short selectionMask2;
    private Short valueMask2;
    private Long time;
    private Short pin;
    private Short selectionMask3;
    private Short valueMask3;
    private Short pin2;
    private Boolean resetCounter;
    private Short selectionMask4;
    private Short edgeType;
    private Short debounce2;
    private Short pin3;

        
    public IO4Endpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new IO4Producer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new IO4Consumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletIO4 device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletIO4 device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "setValue":
                device.setValue(
                        getValue(short.class, "valueMask", m, getValueMask())
                    );
                break;

            case "getValue":
                response = device.getValue();
                break;

            case "setConfiguration":
                device.setConfiguration(
                        getValue(short.class, "selectionMask", m, getSelectionMask()),
                        getValue(char.class, "direction", m, getDirection()),
                        getValue(boolean.class, "value", m, getValue())
                    );
                break;

            case "getConfiguration":
                response = device.getConfiguration();
                break;

            case "setDebouncePeriod":
                device.setDebouncePeriod(
                        getValue(long.class, "debounce", m, getDebounce())
                    );
                break;

            case "getDebouncePeriod":
                response = device.getDebouncePeriod();
                break;

            case "setInterrupt":
                device.setInterrupt(
                        getValue(short.class, "interruptMask", m, getInterruptMask())
                    );
                break;

            case "getInterrupt":
                response = device.getInterrupt();
                break;

            case "setMonoflop":
                device.setMonoflop(
                        getValue(short.class, "selectionMask2", m, getSelectionMask2()),
                        getValue(short.class, "valueMask2", m, getValueMask2()),
                        getValue(long.class, "time", m, getTime())
                    );
                break;

            case "getMonoflop":
                response = device.getMonoflop(
                        getValue(short.class, "pin", m, getPin())
                    );
                break;

            case "setSelectedValues":
                device.setSelectedValues(
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
                        getValue(short.class, "selectionMask4", m, getSelectionMask4()),
                        getValue(short.class, "edgeType", m, getEdgeType()),
                        getValue(short.class, "debounce2", m, getDebounce2())
                    );
                break;

            case "getEdgeCountConfig":
                response = device.getEdgeCountConfig(
                        getValue(short.class, "pin3", m, getPin3())
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
     * Sets the output value (high or low) with a bitmask (4bit). A 1 in the bitmask
     * means high and a 0 in the bitmask means low.
     * 
     * For example: The value 3 or 0b0011 will turn the pins 0-1 high and the
     * pins 2-3 low.
     * 
     * .. note::
     *  This function does nothing for pins that are configured as input.
     *  Pull-up resistors can be switched on with :func:`SetConfiguration`.
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
     * Configures the value and direction of the specified pins. Possible directions
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
     * * (15, 'i', true) or (0b1111, 'i', true) will set all pins of as input pull-up.
     * * (8, 'i', false) or (0b1000, 'i', false) will set pin 3 of as input default (floating if nothing is connected).
     * * (3, 'o', false) or (0b0011, 'o', false) will set pins 0 and 1 as output low.
     * * (4, 'o', true) or (0b0100, 'o', true) will set pin 2 of as output high.
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
     * Configures the value and direction of the specified pins. Possible directions
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
     * * (15, 'i', true) or (0b1111, 'i', true) will set all pins of as input pull-up.
     * * (8, 'i', false) or (0b1000, 'i', false) will set pin 3 of as input default (floating if nothing is connected).
     * * (3, 'o', false) or (0b0011, 'o', false) will set pins 0 and 1 as output low.
     * * (4, 'o', true) or (0b0100, 'o', true) will set pin 2 of as output high.
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
     * Configures the value and direction of the specified pins. Possible directions
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
     * * (15, 'i', true) or (0b1111, 'i', true) will set all pins of as input pull-up.
     * * (8, 'i', false) or (0b1000, 'i', false) will set pin 3 of as input default (floating if nothing is connected).
     * * (3, 'o', false) or (0b0011, 'o', false) will set pins 0 and 1 as output low.
     * * (4, 'o', true) or (0b0100, 'o', true) will set pin 2 of as output high.
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
     * Sets the debounce period of the :func:`Interrupt` callback in ms.
     * 
     * For example: If you set this value to 100, you will get the interrupt
     * maximal every 100ms. This is necessary if something that bounces is
     * connected to the IO-4 Bricklet, such as a button.
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
     * For example: An interrupt bitmask of 10 or 0b1010 will enable the interrupt for
     * pins 1 and 3.
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
     * Configures a monoflop of the pins specified by the first parameter as 4 bit
     * long bitmask. The specified pins must be configured for output. Non-output
     * pins will be ignored.
     * 
     * The second parameter is a bitmask with the desired value of the specified
     * output pins. A 1 in the bitmask means high and a 0 in the bitmask means low.
     * 
     * The third parameter indicates the time (in ms) that the pins should hold
     * the value.
     * 
     * If this function is called with the parameters (9, 1, 1500) or
     * (0b1001, 0b0001, 1500): Pin 0 will get high and pin 3 will get low. In 1.5s pin
     * 0 will get low and pin 3 will get high again.
     * 
     * A monoflop can be used as a fail-safe mechanism. For example: Lets assume you
     * have a RS485 bus and an IO-4 Bricklet connected to one of the slave
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
     * Configures a monoflop of the pins specified by the first parameter as 4 bit
     * long bitmask. The specified pins must be configured for output. Non-output
     * pins will be ignored.
     * 
     * The second parameter is a bitmask with the desired value of the specified
     * output pins. A 1 in the bitmask means high and a 0 in the bitmask means low.
     * 
     * The third parameter indicates the time (in ms) that the pins should hold
     * the value.
     * 
     * If this function is called with the parameters (9, 1, 1500) or
     * (0b1001, 0b0001, 1500): Pin 0 will get high and pin 3 will get low. In 1.5s pin
     * 0 will get low and pin 3 will get high again.
     * 
     * A monoflop can be used as a fail-safe mechanism. For example: Lets assume you
     * have a RS485 bus and an IO-4 Bricklet connected to one of the slave
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
     * Configures a monoflop of the pins specified by the first parameter as 4 bit
     * long bitmask. The specified pins must be configured for output. Non-output
     * pins will be ignored.
     * 
     * The second parameter is a bitmask with the desired value of the specified
     * output pins. A 1 in the bitmask means high and a 0 in the bitmask means low.
     * 
     * The third parameter indicates the time (in ms) that the pins should hold
     * the value.
     * 
     * If this function is called with the parameters (9, 1, 1500) or
     * (0b1001, 0b0001, 1500): Pin 0 will get high and pin 3 will get low. In 1.5s pin
     * 0 will get low and pin 3 will get high again.
     * 
     * A monoflop can be used as a fail-safe mechanism. For example: Lets assume you
     * have a RS485 bus and an IO-4 Bricklet connected to one of the slave
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
     * :func:`SetMonoflop` as well as the remaining time until the value flips.
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
     * Sets the output value (high or low) with a bitmask, according to
     * the selection mask. The bitmask is 4 bit long, *true* refers to high 
     * and *false* refers to low.
     * 
     * For example: The parameters (9, 4) or (0b0110, 0b0100) will turn
     * pin 1 low and pin 2 high, pin 0 and 3 will remain untouched.
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
     * Sets the output value (high or low) with a bitmask, according to
     * the selection mask. The bitmask is 4 bit long, *true* refers to high 
     * and *false* refers to low.
     * 
     * For example: The parameters (9, 4) or (0b0110, 0b0100) will turn
     * pin 1 low and pin 2 high, pin 0 and 3 will remain untouched.
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
     * Returns the current value of the edge counter for the selected pin. You can
     * configure the edges that are counted with :func:`SetEdgeCountConfig`.
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
     * Returns the current value of the edge counter for the selected pin. You can
     * configure the edges that are counted with :func:`SetEdgeCountConfig`.
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
     * Configures the edge counter for the selected pins.
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
    public Short getSelectionMask4(){
        return selectionMask4;
    }

    public void setSelectionMask4(Short selectionMask4){
        this.selectionMask4 = selectionMask4;
    }

    /**
     * 
     * Configures the edge counter for the selected pins.
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
     * Configures the edge counter for the selected pins.
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
     * Returns the edge type and debounce time for the selected pin as set by
     * :func:`SetEdgeCountConfig`.
     * 
     */
    public Short getPin3(){
        return pin3;
    }

    public void setPin3(Short pin3){
        this.pin3 = pin3;
    }



}
