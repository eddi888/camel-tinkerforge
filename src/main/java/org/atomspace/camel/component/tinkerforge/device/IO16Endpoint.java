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
    private Short selectionMask;
    private Character direction;
    private Boolean value;
    private Long debounce;
    private Short interruptMask;
    private Long time;
    private Short pin;
    private Boolean resetCounter;
    private Short edgeType;

        
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
                        (char) getValue("port", m, e),
                        (short) getValue("valueMask", m, e)
                    );
                break;

            case "getPort":
                response = device.getPort(
                        (char) getValue("port", m, e)
                    );
                break;

            case "setPortConfiguration":
                device.setPortConfiguration(
                        (char) getValue("port", m, e),
                        (short) getValue("selectionMask", m, e),
                        (char) getValue("direction", m, e),
                        (boolean) getValue("value", m, e)
                    );
                break;

            case "getPortConfiguration":
                response = device.getPortConfiguration(
                        (char) getValue("port", m, e)
                    );
                break;

            case "setDebouncePeriod":
                device.setDebouncePeriod(
                        (long) getValue("debounce", m, e)
                    );
                break;

            case "getDebouncePeriod":
                response = device.getDebouncePeriod();
                break;

            case "setPortInterrupt":
                device.setPortInterrupt(
                        (char) getValue("port", m, e),
                        (short) getValue("interruptMask", m, e)
                    );
                break;

            case "getPortInterrupt":
                response = device.getPortInterrupt(
                        (char) getValue("port", m, e)
                    );
                break;

            case "setPortMonoflop":
                device.setPortMonoflop(
                        (char) getValue("port", m, e),
                        (short) getValue("selectionMask", m, e),
                        (short) getValue("valueMask", m, e),
                        (long) getValue("time", m, e)
                    );
                break;

            case "getPortMonoflop":
                response = device.getPortMonoflop(
                        (char) getValue("port", m, e),
                        (short) getValue("pin", m, e)
                    );
                break;

            case "setSelectedValues":
                device.setSelectedValues(
                        (char) getValue("port", m, e),
                        (short) getValue("selectionMask", m, e),
                        (short) getValue("valueMask", m, e)
                    );
                break;

            case "getEdgeCount":
                response = device.getEdgeCount(
                        (short) getValue("pin", m, e),
                        (boolean) getValue("resetCounter", m, e)
                    );
                break;

            case "setEdgeCountConfig":
                device.setEdgeCountConfig(
                        (short) getValue("pin", m, e),
                        (short) getValue("edgeType", m, e),
                        (short) getValue("debounce", m, e)
                    );
                break;

            case "getEdgeCountConfig":
                response = device.getEdgeCountConfig(
                        (short) getValue("pin", m, e)
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

    public Boolean getResetCounter(){
        return resetCounter;
    }

    public void setResetCounter(Boolean resetCounter){
        this.resetCounter = resetCounter;
    }

    public Short getEdgeType(){
        return edgeType;
    }

    public void setEdgeType(Short edgeType){
        this.edgeType = edgeType;
    }



}
