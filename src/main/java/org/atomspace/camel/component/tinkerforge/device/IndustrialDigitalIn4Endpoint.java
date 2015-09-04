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

import com.tinkerforge.BrickletIndustrialDigitalIn4;

/**
 * 4 galvanically isolated digital inputs
 */
@UriEndpoint(scheme = "tinkerforgegen", syntax = "tinkerforgegen:[host[:port]/]industrialdigitalin4", consumerClass = IndustrialDigitalIn4Consumer.class, label = "iot", title = "Tinkerforge")
public class IndustrialDigitalIn4Endpoint extends TinkerforgeEndpoint<IndustrialDigitalIn4Consumer, IndustrialDigitalIn4Producer> {

    private static final Logger LOG = LoggerFactory.getLogger(IndustrialDigitalIn4Endpoint.class);

    public static final String GROUP="group";
    public static final String DEBOUNCE="debounce";
    public static final String INTERRUPTMASK="interruptMask";
    public static final String PIN="pin";
    public static final String RESETCOUNTER="resetCounter";
    public static final String SELECTIONMASK="selectionMask";
    public static final String EDGETYPE="edgeType";
    public static final String DEBOUNCE2="debounce2";
    public static final String PIN2="pin2";

    
    private char[] group;
    private Long debounce;
    private Integer interruptMask;
    private Short pin;
    private Boolean resetCounter;
    private Integer selectionMask;
    private Short edgeType;
    private Short debounce2;
    private Short pin2;

        
    public IndustrialDigitalIn4Endpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new IndustrialDigitalIn4Producer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new IndustrialDigitalIn4Consumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletIndustrialDigitalIn4 device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletIndustrialDigitalIn4 device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "getValue":
                response = device.getValue();
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
                        getValue(int.class, "interruptMask", m, getInterruptMask())
                    );
                break;

            case "getInterrupt":
                response = device.getInterrupt();
                break;

            case "getEdgeCount":
                response = device.getEdgeCount(
                        getValue(short.class, "pin", m, getPin()),
                        getValue(boolean.class, "resetCounter", m, getResetCounter())
                    );
                break;

            case "setEdgeCountConfig":
                device.setEdgeCountConfig(
                        getValue(int.class, "selectionMask", m, getSelectionMask()),
                        getValue(short.class, "edgeType", m, getEdgeType()),
                        getValue(short.class, "debounce2", m, getDebounce2())
                    );
                break;

            case "getEdgeCountConfig":
                response = device.getEdgeCountConfig(
                        getValue(short.class, "pin2", m, getPin2())
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
     * Sets a group of Digital In 4 Bricklets that should work together. You can
     * find Bricklets that can be grouped together with :func:`GetAvailableForGroup`.
     * 
     * The group consists of 4 elements. Element 1 in the group will get pins 0-3,
     * element 2 pins 4-7, element 3 pins 8-11 and element 4 pins 12-15.
     * 
     * Each element can either be one of the ports ('a' to 'd') or 'n' if it should
     * not be used.
     * 
     * For example: If you have two Digital In 4 Bricklets connected to port A and
     * port B respectively, you could call with |abnn|.
     * 
     * Now the pins on the Digital In 4 on port A are assigned to 0-3 and the
     * pins on the Digital In 4 on port B are assigned to 4-7. It is now possible
     * to call :func:`GetValue` and read out two Bricklets at the same time.
     * 
     * Changing the group configuration resets all edge counter configurations
     * and values.
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
     * Sets the debounce period of the :func:`Interrupt` callback in ms.
     * 
     * For example: If you set this value to 100, you will get the interrupt
     * maximal every 100ms. This is necessary if something that bounces is
     * connected to the Digital In 4 Bricklet, such as a button.
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
     * For example: An interrupt bitmask of 9 or 0b1001 will enable the interrupt for
     * pins 0 and 3.
     * 
     * The interrupts use the grouping as set by :func:`SetGroup`.
     * 
     * The interrupt is delivered with the callback :func:`Interrupt`.
     * 
     */
    public Integer getInterruptMask(){
        return interruptMask;
    }

    public void setInterruptMask(Integer interruptMask){
        this.interruptMask = interruptMask;
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
    public Short getPin(){
        return pin;
    }

    public void setPin(Short pin){
        this.pin = pin;
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
     * Configures the edge counter for the selected pins. A bitmask of 9 or 0b1001 will
     * enable the edge counter for pins 0 and 3.
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
    public Integer getSelectionMask(){
        return selectionMask;
    }

    public void setSelectionMask(Integer selectionMask){
        this.selectionMask = selectionMask;
    }

    /**
     * 
     * Configures the edge counter for the selected pins. A bitmask of 9 or 0b1001 will
     * enable the edge counter for pins 0 and 3.
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
     * Configures the edge counter for the selected pins. A bitmask of 9 or 0b1001 will
     * enable the edge counter for pins 0 and 3.
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
    public Short getPin2(){
        return pin2;
    }

    public void setPin2(Short pin2){
        this.pin2 = pin2;
    }



}
