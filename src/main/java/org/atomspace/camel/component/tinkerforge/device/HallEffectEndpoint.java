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

import com.tinkerforge.BrickletHallEffect;

public class HallEffectEndpoint extends TinkerforgeEndpoint<HallEffectConsumer, HallEffectProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(HallEffectEndpoint.class);
    
    private Boolean resetCounter;
    private Short edgeType;
    private Short debounce;
    private Long edges;
    private Long period;

        
    public HallEffectEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new HallEffectProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new HallEffectConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletHallEffect device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletHallEffect device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "getValue":
                response = device.getValue();
                break;

            case "getEdgeCount":
                response = device.getEdgeCount(
                        getValue(boolean.class, "resetCounter", m, getResetCounter())
                    );
                break;

            case "setEdgeCountConfig":
                device.setEdgeCountConfig(
                        getValue(short.class, "edgeType", m, getEdgeType()),
                        getValue(short.class, "debounce", m, getDebounce())
                    );
                break;

            case "getEdgeCountConfig":
                response = device.getEdgeCountConfig();
                break;

            case "setEdgeInterrupt":
                device.setEdgeInterrupt(
                        getValue(long.class, "edges", m, getEdges())
                    );
                break;

            case "getEdgeInterrupt":
                response = device.getEdgeInterrupt();
                break;

            case "setEdgeCountCallbackPeriod":
                device.setEdgeCountCallbackPeriod(
                        getValue(long.class, "period", m, getPeriod())
                    );
                break;

            case "getEdgeCountCallbackPeriod":
                response = device.getEdgeCountCallbackPeriod();
                break;

            case "edgeInterrupt":
                response = device.edgeInterrupt();
                break;

            case "getIdentity":
                response = device.getIdentity();
                break;


            default: throw new Exception("unknown function '"+function+"'");
            
        }
        
        return response;
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

    public Short getDebounce(){
        return debounce;
    }

    public void setDebounce(Short debounce){
        this.debounce = debounce;
    }

    public Long getEdges(){
        return edges;
    }

    public void setEdges(Long edges){
        this.edges = edges;
    }

    public Long getPeriod(){
        return period;
    }

    public void setPeriod(Long period){
        this.period = period;
    }



}
