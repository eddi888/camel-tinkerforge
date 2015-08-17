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

import com.tinkerforge.BrickletSolidStateRelay;

/**
 * Controls AC and DC Solid State Relays
 */
public class SolidStateRelayEndpoint extends TinkerforgeEndpoint<SolidStateRelayConsumer, SolidStateRelayProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(SolidStateRelayEndpoint.class);
    
    private Boolean state;
    private Boolean state2;
    private Long time;

        
    public SolidStateRelayEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new SolidStateRelayProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new SolidStateRelayConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletSolidStateRelay device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletSolidStateRelay device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "setState":
                device.setState(
                        getValue(boolean.class, "state", m, getState())
                    );
                break;

            case "getState":
                response = device.getState();
                break;

            case "setMonoflop":
                device.setMonoflop(
                        getValue(boolean.class, "state2", m, getState2()),
                        getValue(long.class, "time", m, getTime())
                    );
                break;

            case "getMonoflop":
                response = device.getMonoflop();
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
     * Sets the state of the relays *true* means on and *false* means off. 
     * 
     * Running monoflop timers will be overwritten if this function is called.
     * 
     * The default value is *false*.
     * 
     */
    public Boolean getState(){
        return state;
    }

    public void setState(Boolean state){
        this.state = state;
    }

    /**
     * 
     * The first parameter  is the desired state of the relay (*true* means on 
     * and *false* means off). The second parameter indicates the time (in ms) that 
     * the relay should hold the state.
     * 
     * If this function is called with the parameters (true, 1500):
     * The relay will turn on and in 1.5s it will turn off again.
     * 
     * A monoflop can be used as a failsafe mechanism. For example: Lets assume you 
     * have a RS485 bus and a Solid State Relay Bricklet connected to one of the slave 
     * stacks. You can now call this function every second, with a time parameter
     * of two seconds. The relay will be on all the time. If now the RS485 
     * connection is lost, the relay will turn off in at most two seconds.
     * 
     */
    public Boolean getState2(){
        return state2;
    }

    public void setState2(Boolean state2){
        this.state2 = state2;
    }

    /**
     * 
     * The first parameter  is the desired state of the relay (*true* means on 
     * and *false* means off). The second parameter indicates the time (in ms) that 
     * the relay should hold the state.
     * 
     * If this function is called with the parameters (true, 1500):
     * The relay will turn on and in 1.5s it will turn off again.
     * 
     * A monoflop can be used as a failsafe mechanism. For example: Lets assume you 
     * have a RS485 bus and a Solid State Relay Bricklet connected to one of the slave 
     * stacks. You can now call this function every second, with a time parameter
     * of two seconds. The relay will be on all the time. If now the RS485 
     * connection is lost, the relay will turn off in at most two seconds.
     * 
     */
    public Long getTime(){
        return time;
    }

    public void setTime(Long time){
        this.time = time;
    }



}
