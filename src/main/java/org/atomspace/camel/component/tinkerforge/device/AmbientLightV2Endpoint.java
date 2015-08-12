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

import com.tinkerforge.BrickletAmbientLightV2;

public class AmbientLightV2Endpoint extends TinkerforgeEndpoint<AmbientLightV2Consumer, AmbientLightV2Producer> {

    private static final Logger LOG = LoggerFactory.getLogger(AmbientLightV2Endpoint.class);
    
    private Long period;
    private Character option;
    private Long min;
    private Long max;
    private Long debounce;
    private Short illuminanceRange;
    private Short integrationTime;

        
    public AmbientLightV2Endpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new AmbientLightV2Producer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new AmbientLightV2Consumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletAmbientLightV2 device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletAmbientLightV2 device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "getIlluminance":
                response = device.getIlluminance();
                break;

            case "setIlluminanceCallbackPeriod":
                device.setIlluminanceCallbackPeriod(
                        getValue(long.class, "period", m, getPeriod())
                    );
                break;

            case "getIlluminanceCallbackPeriod":
                response = device.getIlluminanceCallbackPeriod();
                break;

            case "setIlluminanceCallbackThreshold":
                device.setIlluminanceCallbackThreshold(
                        getValue(char.class, "option", m, getOption()),
                        getValue(long.class, "min", m, getMin()),
                        getValue(long.class, "max", m, getMax())
                    );
                break;

            case "getIlluminanceCallbackThreshold":
                response = device.getIlluminanceCallbackThreshold();
                break;

            case "setDebouncePeriod":
                device.setDebouncePeriod(
                        getValue(long.class, "debounce", m, getDebounce())
                    );
                break;

            case "getDebouncePeriod":
                response = device.getDebouncePeriod();
                break;

            case "setConfiguration":
                device.setConfiguration(
                        getValue(short.class, "illuminanceRange", m, getIlluminanceRange()),
                        getValue(short.class, "integrationTime", m, getIntegrationTime())
                    );
                break;

            case "getConfiguration":
                response = device.getConfiguration();
                break;

            case "getIdentity":
                response = device.getIdentity();
                break;


            default: throw new Exception("unknown function '"+function+"'");
            
        }
        
        return response;
    }
    
    
    public Long getPeriod(){
        return period;
    }

    public void setPeriod(Long period){
        this.period = period;
    }

    public Character getOption(){
        return option;
    }

    public void setOption(Character option){
        this.option = option;
    }

    public Long getMin(){
        return min;
    }

    public void setMin(Long min){
        this.min = min;
    }

    public Long getMax(){
        return max;
    }

    public void setMax(Long max){
        this.max = max;
    }

    public Long getDebounce(){
        return debounce;
    }

    public void setDebounce(Long debounce){
        this.debounce = debounce;
    }

    public Short getIlluminanceRange(){
        return illuminanceRange;
    }

    public void setIlluminanceRange(Short illuminanceRange){
        this.illuminanceRange = illuminanceRange;
    }

    public Short getIntegrationTime(){
        return integrationTime;
    }

    public void setIntegrationTime(Short integrationTime){
        this.integrationTime = integrationTime;
    }



}
