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

import com.tinkerforge.BrickletDualButton;

public class DualButtonEndpoint extends TinkerforgeEndpoint<DualButtonConsumer, DualButtonProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(DualButtonEndpoint.class);
    
    private Short ledL;
    private Short ledR;
    private Short led;
    private Short state;

        
    public DualButtonEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new DualButtonProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new DualButtonConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletDualButton device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletDualButton device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "setLedState":
                device.setLEDState(
                        getValue(short.class, "ledL", m, getLedL()),
                        getValue(short.class, "ledR", m, getLedR())
                    );
                break;

            case "getLedState":
                response = device.getLEDState();
                break;

            case "getButtonState":
                response = device.getButtonState();
                break;

            case "setSelectedLedState":
                device.setSelectedLEDState(
                        getValue(short.class, "led", m, getLed()),
                        getValue(short.class, "state", m, getState())
                    );
                break;

            case "getIdentity":
                response = device.getIdentity();
                break;


            default: throw new Exception("unknown function '"+function+"'");
            
        }
        
        return response;
    }
    
    
    public Short getLedL(){
        return ledL;
    }

    public void setLedL(Short ledL){
        this.ledL = ledL;
    }

    public Short getLedR(){
        return ledR;
    }

    public void setLedR(Short ledR){
        this.ledR = ledR;
    }

    public Short getLed(){
        return led;
    }

    public void setLed(Short led){
        this.led = led;
    }

    public Short getState(){
        return state;
    }

    public void setState(Short state){
        this.state = state;
    }



}
