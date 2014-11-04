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

import com.tinkerforge.BrickletTemperatureIR;

public class TemperatureIREndpoint extends TinkerforgeEndpoint<TemperatureIRConsumer, TemperatureIRProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(TemperatureIREndpoint.class);
    
    private Integer emissivity;
    private Long period;
    private Character option;
    private Short min;
    private Short max;
    private Long debounce;

        
    public TemperatureIREndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new TemperatureIRProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new TemperatureIRConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletTemperatureIR device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletTemperatureIR device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "getAmbientTemperature":
                response = device.getAmbientTemperature();
                break;

            case "getObjectTemperature":
                response = device.getObjectTemperature();
                break;

            case "setEmissivity":
                device.setEmissivity(
                        (int) getValue("emissivity", m, e)
                    );
                break;

            case "getEmissivity":
                response = device.getEmissivity();
                break;

            case "setAmbientTemperatureCallbackPeriod":
                device.setAmbientTemperatureCallbackPeriod(
                        (long) getValue("period", m, e)
                    );
                break;

            case "getAmbientTemperatureCallbackPeriod":
                response = device.getAmbientTemperatureCallbackPeriod();
                break;

            case "setObjectTemperatureCallbackPeriod":
                device.setObjectTemperatureCallbackPeriod(
                        (long) getValue("period", m, e)
                    );
                break;

            case "getObjectTemperatureCallbackPeriod":
                response = device.getObjectTemperatureCallbackPeriod();
                break;

            case "setAmbientTemperatureCallbackThreshold":
                device.setAmbientTemperatureCallbackThreshold(
                        (char) getValue("option", m, e),
                        (short) getValue("min", m, e),
                        (short) getValue("max", m, e)
                    );
                break;

            case "getAmbientTemperatureCallbackThreshold":
                response = device.getAmbientTemperatureCallbackThreshold();
                break;

            case "setObjectTemperatureCallbackThreshold":
                device.setObjectTemperatureCallbackThreshold(
                        (char) getValue("option", m, e),
                        (short) getValue("min", m, e),
                        (short) getValue("max", m, e)
                    );
                break;

            case "getObjectTemperatureCallbackThreshold":
                response = device.getObjectTemperatureCallbackThreshold();
                break;

            case "setDebouncePeriod":
                device.setDebouncePeriod(
                        (long) getValue("debounce", m, e)
                    );
                break;

            case "getDebouncePeriod":
                response = device.getDebouncePeriod();
                break;

            case "getIdentity":
                response = device.getIdentity();
                break;


            default: throw new Exception("unknown function '"+function+"'");
            
        }
        
        return response;
    }
    
    
    public Integer getEmissivity(){
        return emissivity;
    }

    public void setEmissivity(Integer emissivity){
        this.emissivity = emissivity;
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

    public Short getMin(){
        return min;
    }

    public void setMin(Short min){
        this.min = min;
    }

    public Short getMax(){
        return max;
    }

    public void setMax(Short max){
        this.max = max;
    }

    public Long getDebounce(){
        return debounce;
    }

    public void setDebounce(Long debounce){
        this.debounce = debounce;
    }



}
