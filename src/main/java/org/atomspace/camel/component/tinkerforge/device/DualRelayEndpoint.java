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
    private Short relay3;
    private Short relay4;
    private Boolean state2;

        
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
                        getValue(boolean.class, "relay1", m, getRelay1()),
                        getValue(boolean.class, "relay2", m, getRelay2())
                    );
                break;

            case "getState":
                response = device.getState();
                break;

            case "setMonoflop":
                device.setMonoflop(
                        getValue(short.class, "relay", m, getRelay()),
                        getValue(boolean.class, "state", m, getState()),
                        getValue(long.class, "time", m, getTime())
                    );
                break;

            case "getMonoflop":
                response = device.getMonoflop(
                        getValue(short.class, "relay3", m, getRelay3())
                    );
                break;

            case "setSelectedState":
                device.setSelectedState(
                        getValue(short.class, "relay4", m, getRelay4()),
                        getValue(boolean.class, "state2", m, getState2())
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

    public Short getRelay3(){
        return relay3;
    }

    public void setRelay3(Short relay3){
        this.relay3 = relay3;
    }

    public Short getRelay4(){
        return relay4;
    }

    public void setRelay4(Short relay4){
        this.relay4 = relay4;
    }

    public Boolean getState2(){
        return state2;
    }

    public void setState2(Boolean state2){
        this.state2 = state2;
    }



}
