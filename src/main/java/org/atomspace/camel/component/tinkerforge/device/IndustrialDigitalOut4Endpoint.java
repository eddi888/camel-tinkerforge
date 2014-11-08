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

import com.tinkerforge.BrickletIndustrialDigitalOut4;

public class IndustrialDigitalOut4Endpoint extends TinkerforgeEndpoint<IndustrialDigitalOut4Consumer, IndustrialDigitalOut4Producer> {

    private static final Logger LOG = LoggerFactory.getLogger(IndustrialDigitalOut4Endpoint.class);
    
    private Integer valueMask;
    private Integer selectionMask;
    private Integer valueMask2;
    private Long time;
    private Short pin;
    private char[] group;
    private Integer selectionMask2;
    private Integer valueMask3;

        
    public IndustrialDigitalOut4Endpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new IndustrialDigitalOut4Producer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new IndustrialDigitalOut4Consumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletIndustrialDigitalOut4 device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletIndustrialDigitalOut4 device, String function, Message m, Endpoint e) throws Exception{
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
    
    
    public Integer getValueMask(){
        return valueMask;
    }

    public void setValueMask(Integer valueMask){
        this.valueMask = valueMask;
    }

    public Integer getSelectionMask(){
        return selectionMask;
    }

    public void setSelectionMask(Integer selectionMask){
        this.selectionMask = selectionMask;
    }

    public Integer getValueMask2(){
        return valueMask2;
    }

    public void setValueMask2(Integer valueMask2){
        this.valueMask2 = valueMask2;
    }

    public Long getTime(){
        return time;
    }

    public void setTime(Long time){
        this.time = time;
    }

    public Short getPin(){
        return pin;
    }

    public void setPin(Short pin){
        this.pin = pin;
    }

    public char[] getGroup(){
        return group;
    }

    public void setGroup(char[] group){
        this.group = group;
    }

    public Integer getSelectionMask2(){
        return selectionMask2;
    }

    public void setSelectionMask2(Integer selectionMask2){
        this.selectionMask2 = selectionMask2;
    }

    public Integer getValueMask3(){
        return valueMask3;
    }

    public void setValueMask3(Integer valueMask3){
        this.valueMask3 = valueMask3;
    }



}
