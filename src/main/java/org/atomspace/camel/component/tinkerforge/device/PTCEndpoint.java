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

import com.tinkerforge.BrickletPTC;

public class PTCEndpoint extends TinkerforgeEndpoint<PTCConsumer, PTCProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(PTCEndpoint.class);
    
    private Long period;
    private Character option;
    private Integer min;
    private Integer max;
    private Long debounce;
    private Short filter;
    private Short mode;

        
    public PTCEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new PTCProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new PTCConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletPTC device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletPTC device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "getTemperature":
                response = device.getTemperature();
                break;

            case "getResistance":
                response = device.getResistance();
                break;

            case "setTemperatureCallbackPeriod":
                device.setTemperatureCallbackPeriod(
                        (long) getValue("period", m, e)
                    );
                break;

            case "getTemperatureCallbackPeriod":
                response = device.getTemperatureCallbackPeriod();
                break;

            case "setResistanceCallbackPeriod":
                device.setResistanceCallbackPeriod(
                        (long) getValue("period", m, e)
                    );
                break;

            case "getResistanceCallbackPeriod":
                response = device.getResistanceCallbackPeriod();
                break;

            case "setTemperatureCallbackThreshold":
                device.setTemperatureCallbackThreshold(
                        (char) getValue("option", m, e),
                        (int) getValue("min", m, e),
                        (int) getValue("max", m, e)
                    );
                break;

            case "getTemperatureCallbackThreshold":
                response = device.getTemperatureCallbackThreshold();
                break;

            case "setResistanceCallbackThreshold":
                device.setResistanceCallbackThreshold(
                        (char) getValue("option", m, e),
                        (int) getValue("min", m, e),
                        (int) getValue("max", m, e)
                    );
                break;

            case "getResistanceCallbackThreshold":
                response = device.getResistanceCallbackThreshold();
                break;

            case "setDebouncePeriod":
                device.setDebouncePeriod(
                        (long) getValue("debounce", m, e)
                    );
                break;

            case "getDebouncePeriod":
                response = device.getDebouncePeriod();
                break;

            case "setNoiseRejectionFilter":
                device.setNoiseRejectionFilter(
                        (short) getValue("filter", m, e)
                    );
                break;

            case "getNoiseRejectionFilter":
                response = device.getNoiseRejectionFilter();
                break;

            case "isSensorConnected":
                response = device.isSensorConnected();
                break;

            case "setWireMode":
                device.setWireMode(
                        (short) getValue("mode", m, e)
                    );
                break;

            case "getWireMode":
                response = device.getWireMode();
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

    public Integer getMin(){
        return min;
    }

    public void setMin(Integer min){
        this.min = min;
    }

    public Integer getMax(){
        return max;
    }

    public void setMax(Integer max){
        this.max = max;
    }

    public Long getDebounce(){
        return debounce;
    }

    public void setDebounce(Long debounce){
        this.debounce = debounce;
    }

    public Short getFilter(){
        return filter;
    }

    public void setFilter(Short filter){
        this.filter = filter;
    }

    public Short getMode(){
        return mode;
    }

    public void setMode(Short mode){
        this.mode = mode;
    }



}
