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

import com.tinkerforge.BrickletIndustrialQuadRelay;

/**
 * 4 galvanically isolated solid state relays
 */
@UriEndpoint(scheme = "tinkerforgegen", syntax = "tinkerforgegen:[host[:port]/]industrialquadrelay", consumerClass = IndustrialQuadRelayConsumer.class, label = "iot", title = "Tinkerforge")
public class IndustrialQuadRelayEndpoint extends TinkerforgeEndpoint<IndustrialQuadRelayConsumer, IndustrialQuadRelayProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(IndustrialQuadRelayEndpoint.class);

    public static final String VALUEMASK="valueMask";
    public static final String SELECTIONMASK="selectionMask";
    public static final String VALUEMASK2="valueMask2";
    public static final String TIME="time";
    public static final String PIN="pin";
    public static final String GROUP="group";
    public static final String SELECTIONMASK2="selectionMask2";
    public static final String VALUEMASK3="valueMask3";

    
    private Integer valueMask;
    private Integer selectionMask;
    private Integer valueMask2;
    private Long time;
    private Short pin;
    private char[] group;
    private Integer selectionMask2;
    private Integer valueMask3;

        
    public IndustrialQuadRelayEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new IndustrialQuadRelayProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new IndustrialQuadRelayConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletIndustrialQuadRelay device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletIndustrialQuadRelay device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "setValue":
                device.setValue(
                        getValue(int.class, "valueMask", m, getValueMask())
                    );
                break;

            case "getValue":
                response = device.getValue();
                break;

            case "setMonoflop":
                device.setMonoflop(
                        getValue(int.class, "selectionMask", m, getSelectionMask()),
                        getValue(int.class, "valueMask2", m, getValueMask2()),
                        getValue(long.class, "time", m, getTime())
                    );
                break;

            case "getMonoflop":
                response = device.getMonoflop(
                        getValue(short.class, "pin", m, getPin())
                    );
                break;

            case "setGroup":
                device.setGroup(
                        getValue(char[].class, "group", m, getGroup())
                    );
                break;

            case "getGroup":
                response = device.getGroup();
                break;

            case "getAvailableForGroup":
                response = device.getAvailableForGroup();
                break;

            case "setSelectedValues":
                device.setSelectedValues(
                        getValue(int.class, "selectionMask2", m, getSelectionMask2()),
                        getValue(int.class, "valueMask3", m, getValueMask3())
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
     * Sets the output value with a bitmask (16bit). A 1 in the bitmask means relay
     * closed and a 0 means relay open.
     * 
     * For example: The value 3 or 0b0011 will close the relay of pins 0-1 and open
     * the other pins.
     * 
     * If no groups are used (see :func:`SetGroup`), the pins correspond to the
     * markings on the Quad Relay Bricklet.
     * 
     * If groups are used, the pins correspond to the element in the group.
     * Element 1 in the group will get pins 0-3, element 2 pins 4-7, element 3
     * pins 8-11 and element 4 pins 12-15.
     * 
     */
    public Integer getValueMask(){
        return valueMask;
    }

    public void setValueMask(Integer valueMask){
        this.valueMask = valueMask;
    }

    /**
     * 
     * Configures a monoflop of the pins specified by the first parameter
     * bitmask.
     * 
     * The second parameter is a bitmask with the desired value of the specified
     * pins. A 1 in the bitmask means relay closed and a 0 means relay open.
     * 
     * The third parameter indicates the time (in ms) that the pins should hold
     * the value.
     * 
     * If this function is called with the parameters (9, 1, 1500) or
     * (0b1001, 0b0001, 1500): Pin 0 will close and pin 3 will open. In 1.5s pin 0
     * will open and pin 3 will close again.
     * 
     * A monoflop can be used as a fail-safe mechanism. For example: Lets assume you
     * have a RS485 bus and a Quad Relay Bricklet connected to one of the slave
     * stacks. You can now call this function every second, with a time parameter
     * of two seconds and pin 0 closed. Pin 0 will be closed all the time. If now
     * the RS485 connection is lost, then pin 0 will be opened in at most two seconds.
     * 
     */
    public Integer getSelectionMask(){
        return selectionMask;
    }

    public void setSelectionMask(Integer selectionMask){
        this.selectionMask = selectionMask;
    }

    /**
     * 
     * Configures a monoflop of the pins specified by the first parameter
     * bitmask.
     * 
     * The second parameter is a bitmask with the desired value of the specified
     * pins. A 1 in the bitmask means relay closed and a 0 means relay open.
     * 
     * The third parameter indicates the time (in ms) that the pins should hold
     * the value.
     * 
     * If this function is called with the parameters (9, 1, 1500) or
     * (0b1001, 0b0001, 1500): Pin 0 will close and pin 3 will open. In 1.5s pin 0
     * will open and pin 3 will close again.
     * 
     * A monoflop can be used as a fail-safe mechanism. For example: Lets assume you
     * have a RS485 bus and a Quad Relay Bricklet connected to one of the slave
     * stacks. You can now call this function every second, with a time parameter
     * of two seconds and pin 0 closed. Pin 0 will be closed all the time. If now
     * the RS485 connection is lost, then pin 0 will be opened in at most two seconds.
     * 
     */
    public Integer getValueMask2(){
        return valueMask2;
    }

    public void setValueMask2(Integer valueMask2){
        this.valueMask2 = valueMask2;
    }

    /**
     * 
     * Configures a monoflop of the pins specified by the first parameter
     * bitmask.
     * 
     * The second parameter is a bitmask with the desired value of the specified
     * pins. A 1 in the bitmask means relay closed and a 0 means relay open.
     * 
     * The third parameter indicates the time (in ms) that the pins should hold
     * the value.
     * 
     * If this function is called with the parameters (9, 1, 1500) or
     * (0b1001, 0b0001, 1500): Pin 0 will close and pin 3 will open. In 1.5s pin 0
     * will open and pin 3 will close again.
     * 
     * A monoflop can be used as a fail-safe mechanism. For example: Lets assume you
     * have a RS485 bus and a Quad Relay Bricklet connected to one of the slave
     * stacks. You can now call this function every second, with a time parameter
     * of two seconds and pin 0 closed. Pin 0 will be closed all the time. If now
     * the RS485 connection is lost, then pin 0 will be opened in at most two seconds.
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
     * Sets a group of Quad Relay Bricklets that should work together. You can
     * find Bricklets that can be grouped together with :func:`GetAvailableForGroup`.
     * 
     * The group consists of 4 elements. Element 1 in the group will get pins 0-3,
     * element 2 pins 4-7, element 3 pins 8-11 and element 4 pins 12-15.
     * 
     * Each element can either be one of the ports ('a' to 'd') or 'n' if it should
     * not be used.
     * 
     * For example: If you have two Quad Relay Bricklets connected to port A and
     * port B respectively, you could call with |abnn|.
     * 
     * Now the pins on the Quad Relay on port A are assigned to 0-3 and the
     * pins on the Quad Relay on port B are assigned to 4-7. It is now possible
     * to call :func:`SetValue` and control two Bricklets at the same time.
     * 
     */
    public char[] getGroup(){
        return group;
    }

    public void setGroup(char[] group){
        this.group = group;
    }

    /**
     * 
     * Sets the output value with a bitmask, according to the selection mask. 
     * The bitmask is 16 bit long, *true* refers to a closed relay and 
     * *false* refers to an open relay.
     * 
     * For example: The values (3, 1) or (0b0011, 0b0001) will close the relay of
     * pin 0, open the relay of pin 1 and leave the others untouched.
     * 
     * If no groups are used (see :func:`SetGroup`), the pins correspond to the
     * markings on the Quad Relay Bricklet.
     * 
     * If groups are used, the pins correspond to the element in the group.
     * Element 1 in the group will get pins 0-3, element 2 pins 4-7, element 3
     * pins 8-11 and element 4 pins 12-15.
     * 
     */
    public Integer getSelectionMask2(){
        return selectionMask2;
    }

    public void setSelectionMask2(Integer selectionMask2){
        this.selectionMask2 = selectionMask2;
    }

    /**
     * 
     * Sets the output value with a bitmask, according to the selection mask. 
     * The bitmask is 16 bit long, *true* refers to a closed relay and 
     * *false* refers to an open relay.
     * 
     * For example: The values (3, 1) or (0b0011, 0b0001) will close the relay of
     * pin 0, open the relay of pin 1 and leave the others untouched.
     * 
     * If no groups are used (see :func:`SetGroup`), the pins correspond to the
     * markings on the Quad Relay Bricklet.
     * 
     * If groups are used, the pins correspond to the element in the group.
     * Element 1 in the group will get pins 0-3, element 2 pins 4-7, element 3
     * pins 8-11 and element 4 pins 12-15.
     * 
     */
    public Integer getValueMask3(){
        return valueMask3;
    }

    public void setValueMask3(Integer valueMask3){
        this.valueMask3 = valueMask3;
    }



}
