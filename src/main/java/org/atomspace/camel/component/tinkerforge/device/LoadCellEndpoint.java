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

import com.tinkerforge.BrickletLoadCell;

public class LoadCellEndpoint extends TinkerforgeEndpoint<LoadCellConsumer, LoadCellProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(LoadCellEndpoint.class);
    
    private Long period;
    private Character option;
    private Integer min;
    private Integer max;
    private Long debounce;
    private Short average;
    private Long weight;
    private Short rate;
    private Short gain;

        
    public LoadCellEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new LoadCellProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new LoadCellConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletLoadCell device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletLoadCell device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "getWeight":
                response = device.getWeight();
                break;

            case "setWeightCallbackPeriod":
                device.setWeightCallbackPeriod(
                        getValue(long.class, "period", m, getPeriod())
                    );
                break;

            case "getWeightCallbackPeriod":
                response = device.getWeightCallbackPeriod();
                break;

            case "setWeightCallbackThreshold":
                device.setWeightCallbackThreshold(
                        getValue(char.class, "option", m, getOption()),
                        getValue(int.class, "min", m, getMin()),
                        getValue(int.class, "max", m, getMax())
                    );
                break;

            case "getWeightCallbackThreshold":
                response = device.getWeightCallbackThreshold();
                break;

            case "setDebouncePeriod":
                device.setDebouncePeriod(
                        getValue(long.class, "debounce", m, getDebounce())
                    );
                break;

            case "getDebouncePeriod":
                response = device.getDebouncePeriod();
                break;

            case "setMovingAverage":
                device.setMovingAverage(
                        getValue(short.class, "average", m, getAverage())
                    );
                break;

            case "getMovingAverage":
                response = device.getMovingAverage();
                break;

            case "ledOn":
                device.ledOn();
                break;

            case "ledOff":
                device.ledOff();;
                break;

            case "isLedOn":
                response = device.isLEDOn();
                break;

            case "calibrate":
                device.calibrate(
                        getValue(long.class, "weight", m, getWeight())
                    );
                break;

            case "tare":
                device.tare();
                break;

            case "setConfiguration":
                device.setConfiguration(
                        getValue(short.class, "rate", m, getRate()),
                        getValue(short.class, "gain", m, getGain())
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

    public Short getAverage(){
        return average;
    }

    public void setAverage(Short average){
        this.average = average;
    }

    public Long getWeight(){
        return weight;
    }

    public void setWeight(Long weight){
        this.weight = weight;
    }

    public Short getRate(){
        return rate;
    }

    public void setRate(Short rate){
        this.rate = rate;
    }

    public Short getGain(){
        return gain;
    }

    public void setGain(Short gain){
        this.gain = gain;
    }



}
