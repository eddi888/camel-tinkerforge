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
    
    
    public Character getPort(){
        return port;
    }

    public void setPort(Character port){
        this.port = port;
    }

    public Short getValueMask(){
        return valueMask;
    }

    public void setValueMask(Short valueMask){
        this.valueMask = valueMask;
    }

    public Character getPort2(){
        return port2;
    }

    public void setPort2(Character port2){
        this.port2 = port2;
    }

    public Character getPort3(){
        return port3;
    }

    public void setPort3(Character port3){
        this.port3 = port3;
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

    public Character getPort4(){
        return port4;
    }

    public void setPort4(Character port4){
        this.port4 = port4;
    }

    public Long getDebounce(){
        return debounce;
    }

    public void setDebounce(Long debounce){
        this.debounce = debounce;
    }

    public Character getPort5(){
        return port5;
    }

    public void setPort5(Character port5){
        this.port5 = port5;
    }

    public Short getInterruptMask(){
        return interruptMask;
    }

    public void setInterruptMask(Short interruptMask){
        this.interruptMask = interruptMask;
    }

    public Character getPort6(){
        return port6;
    }

    public void setPort6(Character port6){
        this.port6 = port6;
    }

    public Character getPort7(){
        return port7;
    }

    public void setPort7(Character port7){
        this.port7 = port7;
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

    public Character getPort8(){
        return port8;
    }

    public void setPort8(Character port8){
        this.port8 = port8;
    }

    public Short getPin(){
        return pin;
    }

    public void setPin(Short pin){
        this.pin = pin;
    }

    public Character getPort9(){
        return port9;
    }

    public void setPort9(Character port9){
        this.port9 = port9;
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

    public Short getPin3(){
        return pin3;
    }

    public void setPin3(Short pin3){
        this.pin3 = pin3;
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

    public Short getPin4(){
        return pin4;
    }

    public void setPin4(Short pin4){
        this.pin4 = pin4;
    }



}
