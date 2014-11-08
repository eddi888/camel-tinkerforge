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

import com.tinkerforge.BrickletIO4;

public class IO4Endpoint extends TinkerforgeEndpoint<IO4Consumer, IO4Producer> {

    private static final Logger LOG = LoggerFactory.getLogger(IO4Endpoint.class);
    
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
    
    
    public Short getValueMask(){
        return valueMask;
    }

    public void setValueMask(Short valueMask){
        this.valueMask = valueMask;
    }

    public Short getSelectionMask(){
        return selectionMask;
    }

    public void setSelectionMask(Short selectionMask){
        this.selectionMask = selectionMask;
    }

    public Character getDirection(){
        return direction;
    }

    public void setDirection(Character direction){
        this.direction = direction;
    }

    public Boolean getValue(){
        return value;
    }

    public void setValue(Boolean value){
        this.value = value;
    }

    public Long getDebounce(){
        return debounce;
    }

    public void setDebounce(Long debounce){
        this.debounce = debounce;
    }

    public Short getInterruptMask(){
        return interruptMask;
    }

    public void setInterruptMask(Short interruptMask){
        this.interruptMask = interruptMask;
    }

    public Short getSelectionMask2(){
        return selectionMask2;
    }

    public void setSelectionMask2(Short selectionMask2){
        this.selectionMask2 = selectionMask2;
    }

    public Short getValueMask2(){
        return valueMask2;
    }

    public void setValueMask2(Short valueMask2){
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

    public Short getSelectionMask3(){
        return selectionMask3;
    }

    public void setSelectionMask3(Short selectionMask3){
        this.selectionMask3 = selectionMask3;
    }

    public Short getValueMask3(){
        return valueMask3;
    }

    public void setValueMask3(Short valueMask3){
        this.valueMask3 = valueMask3;
    }

    public Short getPin2(){
        return pin2;
    }

    public void setPin2(Short pin2){
        this.pin2 = pin2;
    }

    public Boolean getResetCounter(){
        return resetCounter;
    }

    public void setResetCounter(Boolean resetCounter){
        this.resetCounter = resetCounter;
    }

    public Short getSelectionMask4(){
        return selectionMask4;
    }

    public void setSelectionMask4(Short selectionMask4){
        this.selectionMask4 = selectionMask4;
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

    public Short getPin3(){
        return pin3;
    }

    public void setPin3(Short pin3){
        this.pin3 = pin3;
    }



}
