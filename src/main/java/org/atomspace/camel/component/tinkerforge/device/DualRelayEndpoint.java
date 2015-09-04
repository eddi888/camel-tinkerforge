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

import com.tinkerforge.BrickletDualRelay;

/**
 * Two relays to switch AC/DC devices
 */
@UriEndpoint(scheme = "tinkerforgegen", syntax = "tinkerforgegen:[host[:port]/]dualrelay", consumerClass = DualRelayConsumer.class, label = "iot", title = "Tinkerforge")
public class DualRelayEndpoint extends TinkerforgeEndpoint<DualRelayConsumer, DualRelayProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(DualRelayEndpoint.class);

    public static final String RELAY1="relay1";
    public static final String RELAY2="relay2";
    public static final String RELAY="relay";
    public static final String STATE="state";
    public static final String TIME="time";
    public static final String RELAY3="relay3";
    public static final String RELAY4="relay4";
    public static final String STATE2="state2";

    
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
    
    
    /**
     * 
     * Sets the state of the relays, *true* means on and *false* means off. 
     * For example: (true, false) turns relay 1 on and relay 2 off.
     * 
     * If you just want to set one of the relays and don't know the current state
     * of the other relay, you can get the state with :func:`GetState` or you
     * can use :func:`SetSelectedState`.
     * 
     * Running monoflop timers will be overwritten if this function is called.
     * 
     * The default value is (*false*, *false*).
     * 
     */
    public Boolean getRelay1(){
        return relay1;
    }

    public void setRelay1(Boolean relay1){
        this.relay1 = relay1;
    }

    /**
     * 
     * Sets the state of the relays, *true* means on and *false* means off. 
     * For example: (true, false) turns relay 1 on and relay 2 off.
     * 
     * If you just want to set one of the relays and don't know the current state
     * of the other relay, you can get the state with :func:`GetState` or you
     * can use :func:`SetSelectedState`.
     * 
     * Running monoflop timers will be overwritten if this function is called.
     * 
     * The default value is (*false*, *false*).
     * 
     */
    public Boolean getRelay2(){
        return relay2;
    }

    public void setRelay2(Boolean relay2){
        this.relay2 = relay2;
    }

    /**
     * 
     * The first parameter can be 1 or 2 (relay 1 or relay 2). The second parameter 
     * is the desired state of the relay (*true* means on and *false* means off).
     * The third parameter indicates the time (in ms) that the relay should hold 
     * the state.
     * 
     * If this function is called with the parameters (1, true, 1500):
     * Relay 1 will turn on and in 1.5s it will turn off again.
     * 
     * A monoflop can be used as a failsafe mechanism. For example: Lets assume you 
     * have a RS485 bus and a Dual Relay Bricklet connected to one of the slave 
     * stacks. You can now call this function every second, with a time parameter
     * of two seconds. The relay will be on all the time. If now the RS485 
     * connection is lost, the relay will turn off in at most two seconds.
     * 
     */
    public Short getRelay(){
        return relay;
    }

    public void setRelay(Short relay){
        this.relay = relay;
    }

    /**
     * 
     * The first parameter can be 1 or 2 (relay 1 or relay 2). The second parameter 
     * is the desired state of the relay (*true* means on and *false* means off).
     * The third parameter indicates the time (in ms) that the relay should hold 
     * the state.
     * 
     * If this function is called with the parameters (1, true, 1500):
     * Relay 1 will turn on and in 1.5s it will turn off again.
     * 
     * A monoflop can be used as a failsafe mechanism. For example: Lets assume you 
     * have a RS485 bus and a Dual Relay Bricklet connected to one of the slave 
     * stacks. You can now call this function every second, with a time parameter
     * of two seconds. The relay will be on all the time. If now the RS485 
     * connection is lost, the relay will turn off in at most two seconds.
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
     * The first parameter can be 1 or 2 (relay 1 or relay 2). The second parameter 
     * is the desired state of the relay (*true* means on and *false* means off).
     * The third parameter indicates the time (in ms) that the relay should hold 
     * the state.
     * 
     * If this function is called with the parameters (1, true, 1500):
     * Relay 1 will turn on and in 1.5s it will turn off again.
     * 
     * A monoflop can be used as a failsafe mechanism. For example: Lets assume you 
     * have a RS485 bus and a Dual Relay Bricklet connected to one of the slave 
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

    /**
     * 
     * Returns (for the given relay) the current state and the time as set by 
     * :func:`SetMonoflop` as well as the remaining time until the state flips.
     * 
     * If the timer is not running currently, the remaining time will be returned
     * as 0.
     * 
     */
    public Short getRelay3(){
        return relay3;
    }

    public void setRelay3(Short relay3){
        this.relay3 = relay3;
    }

    /**
     * 
     * Sets the state of the selected relay (1 or 2), *true* means on and *false* means off. 
     * 
     * The other relay remains untouched.
     * 
     */
    public Short getRelay4(){
        return relay4;
    }

    public void setRelay4(Short relay4){
        this.relay4 = relay4;
    }

    /**
     * 
     * Sets the state of the selected relay (1 or 2), *true* means on and *false* means off. 
     * 
     * The other relay remains untouched.
     * 
     */
    public Boolean getState2(){
        return state2;
    }

    public void setState2(Boolean state2){
        this.state2 = state2;
    }



}
