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

import com.tinkerforge.BrickletIndustrialDigitalIn4;

public class IndustrialDigitalIn4Endpoint extends TinkerforgeEndpoint<IndustrialDigitalIn4Consumer, IndustrialDigitalIn4Producer> {

    private static final Logger LOG = LoggerFactory.getLogger(IndustrialDigitalIn4Endpoint.class);
    
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
    
    
    public char[] getGroup(){
        return group;
    }

    public void setGroup(char[] group){
        this.group = group;
    }

    public Long getDebounce(){
        return debounce;
    }

    public void setDebounce(Long debounce){
        this.debounce = debounce;
    }

    public Integer getInterruptMask(){
        return interruptMask;
    }

    public void setInterruptMask(Integer interruptMask){
        this.interruptMask = interruptMask;
    }

    public Short getPin(){
        return pin;
    }

    public void setPin(Short pin){
        this.pin = pin;
    }

    public Boolean getResetCounter(){
        return resetCounter;
    }

    public void setResetCounter(Boolean resetCounter){
        this.resetCounter = resetCounter;
    }

    public Integer getSelectionMask(){
        return selectionMask;
    }

    public void setSelectionMask(Integer selectionMask){
        this.selectionMask = selectionMask;
    }

    public Short getEdgeType(){
        return edgeType;
    }

    public void setEdgeType(Short edgeType){
        this.edgeType = edgeType;
    }

    public Short getDebounce2(){
        return debounce2;
    }

    public void setDebounce2(Short debounce2){
        this.debounce2 = debounce2;
    }

    public Short getPin2(){
        return pin2;
    }

    public void setPin2(Short pin2){
        this.pin2 = pin2;
    }



}