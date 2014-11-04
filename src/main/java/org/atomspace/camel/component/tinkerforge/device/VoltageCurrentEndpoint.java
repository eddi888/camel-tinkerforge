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

import com.tinkerforge.BrickletVoltageCurrent;

public class VoltageCurrentEndpoint extends TinkerforgeEndpoint<VoltageCurrentConsumer, VoltageCurrentProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(VoltageCurrentEndpoint.class);
    
    private Short averaging;
    private Short voltageConversionTime;
    private Short currentConversionTime;
    private Integer gainMultiplier;
    private Integer gainDivisor;
    private Long period;
    private Character option;
    private Integer min;
    private Integer max;
    private Long debounce;

        
    public VoltageCurrentEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new VoltageCurrentProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new VoltageCurrentConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletVoltageCurrent device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletVoltageCurrent device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "getCurrent":
                response = device.getCurrent();
                break;

            case "getVoltage":
                response = device.getVoltage();
                break;

            case "getPower":
                response = device.getPower();
                break;

            case "setConfiguration":
                device.setConfiguration(
                        (short) getValue("averaging", m, e),
                        (short) getValue("voltageConversionTime", m, e),
                        (short) getValue("currentConversionTime", m, e)
                    );
                break;

            case "getConfiguration":
                response = device.getConfiguration();
                break;

            case "setCalibration":
                device.setCalibration(
                        (int) getValue("gainMultiplier", m, e),
                        (int) getValue("gainDivisor", m, e)
                    );
                break;

            case "getCalibration":
                response = device.getCalibration();
                break;

            case "setCurrentCallbackPeriod":
                device.setCurrentCallbackPeriod(
                        (long) getValue("period", m, e)
                    );
                break;

            case "getCurrentCallbackPeriod":
                response = device.getCurrentCallbackPeriod();
                break;

            case "setVoltageCallbackPeriod":
                device.setVoltageCallbackPeriod(
                        (long) getValue("period", m, e)
                    );
                break;

            case "getVoltageCallbackPeriod":
                response = device.getVoltageCallbackPeriod();
                break;

            case "setPowerCallbackPeriod":
                device.setPowerCallbackPeriod(
                        (long) getValue("period", m, e)
                    );
                break;

            case "getPowerCallbackPeriod":
                response = device.getPowerCallbackPeriod();
                break;

            case "setCurrentCallbackThreshold":
                device.setCurrentCallbackThreshold(
                        (char) getValue("option", m, e),
                        (int) getValue("min", m, e),
                        (int) getValue("max", m, e)
                    );
                break;

            case "getCurrentCallbackThreshold":
                response = device.getCurrentCallbackThreshold();
                break;

            case "setVoltageCallbackThreshold":
                device.setVoltageCallbackThreshold(
                        (char) getValue("option", m, e),
                        (int) getValue("min", m, e),
                        (int) getValue("max", m, e)
                    );
                break;

            case "getVoltageCallbackThreshold":
                response = device.getVoltageCallbackThreshold();
                break;

            case "setPowerCallbackThreshold":
                device.setPowerCallbackThreshold(
                        (char) getValue("option", m, e),
                        (int) getValue("min", m, e),
                        (int) getValue("max", m, e)
                    );
                break;

            case "getPowerCallbackThreshold":
                response = device.getPowerCallbackThreshold();
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
    
    
    public Short getAveraging(){
        return averaging;
    }

    public void setAveraging(Short averaging){
        this.averaging = averaging;
    }

    public Short getVoltageConversionTime(){
        return voltageConversionTime;
    }

    public void setVoltageConversionTime(Short voltageConversionTime){
        this.voltageConversionTime = voltageConversionTime;
    }

    public Short getCurrentConversionTime(){
        return currentConversionTime;
    }

    public void setCurrentConversionTime(Short currentConversionTime){
        this.currentConversionTime = currentConversionTime;
    }

    public Integer getGainMultiplier(){
        return gainMultiplier;
    }

    public void setGainMultiplier(Integer gainMultiplier){
        this.gainMultiplier = gainMultiplier;
    }

    public Integer getGainDivisor(){
        return gainDivisor;
    }

    public void setGainDivisor(Integer gainDivisor){
        this.gainDivisor = gainDivisor;
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



}
