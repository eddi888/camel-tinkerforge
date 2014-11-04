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

import com.tinkerforge.BrickletDualRelay;

public class DualRelayEndpoint extends TinkerforgeEndpoint<DualRelayConsumer, DualRelayProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(DualRelayEndpoint.class);
    
    private Boolean relay1;
    private Boolean relay2;
    private Short relay;
    private Boolean state;
    private Long time;

        
    public DualRelayEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new DualRelayProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new DualRelayConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletDualRelay device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletDualRelay device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "setState":
                device.setState(
                        (boolean) getValue("relay1", m, e),
                        (boolean) getValue("relay2", m, e)
                    );
                break;

            case "getState":
                response = device.getState();
                break;

            case "setMonoflop":
                device.setMonoflop(
                        (short) getValue("relay", m, e),
                        (boolean) getValue("state", m, e),
                        (long) getValue("time", m, e)
                    );
                break;

            case "getMonoflop":
                response = device.getMonoflop(
                        (short) getValue("relay", m, e)
                    );
                break;

            case "setSelectedState":
                device.setSelectedState(
                        (short) getValue("relay", m, e),
                        (boolean) getValue("state", m, e)
                    );
                break;

            case "getIdentity":
                response = device.getIdentity();
                break;


            default: throw new Exception("unknown function '"+function+"'");
            
        }
        
        return response;
    }
    
    
    public Boolean getRelay1(){
        return relay1;
    }

    public void setRelay1(Boolean relay1){
        this.relay1 = relay1;
    }

    public Boolean getRelay2(){
        return relay2;
    }

    public void setRelay2(Boolean relay2){
        this.relay2 = relay2;
    }

    public Short getRelay(){
        return relay;
    }

    public void setRelay(Short relay){
        this.relay = relay;
    }

    public Boolean getState(){
        return state;
    }

    public void setState(Boolean state){
        this.state = state;
    }

    public Long getTime(){
        return time;
    }

    public void setTime(Long time){
        this.time = time;
    }



}
