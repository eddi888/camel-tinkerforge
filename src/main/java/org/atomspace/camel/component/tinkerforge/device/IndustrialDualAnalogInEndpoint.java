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

import com.tinkerforge.BrickletIndustrialDualAnalogIn;

public class IndustrialDualAnalogInEndpoint extends TinkerforgeEndpoint<IndustrialDualAnalogInConsumer, IndustrialDualAnalogInProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(IndustrialDualAnalogInEndpoint.class);
    
    private Short channel;
    private Short channel2;
    private Long period;
    private Short channel3;
    private Short channel4;
    private Character option;
    private Integer min;
    private Integer max;
    private Short channel5;
    private Long debounce;
    private Short rate;
    private int[] offset;
    private int[] gain;

        
    public IndustrialDualAnalogInEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new IndustrialDualAnalogInProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new IndustrialDualAnalogInConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletIndustrialDualAnalogIn device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletIndustrialDualAnalogIn device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "getVoltage":
                response = device.getVoltage(
                        getValue(short.class, "channel", m, getChannel())
                    );
                break;

            case "setVoltageCallbackPeriod":
                device.setVoltageCallbackPeriod(
                        getValue(short.class, "channel2", m, getChannel2()),
                        getValue(long.class, "period", m, getPeriod())
                    );
                break;

            case "getVoltageCallbackPeriod":
                response = device.getVoltageCallbackPeriod(
                        getValue(short.class, "channel3", m, getChannel3())
                    );
                break;

            case "setVoltageCallbackThreshold":
                device.setVoltageCallbackThreshold(
                        getValue(short.class, "channel4", m, getChannel4()),
                        getValue(char.class, "option", m, getOption()),
                        getValue(int.class, "min", m, getMin()),
                        getValue(int.class, "max", m, getMax())
                    );
                break;

            case "getVoltageCallbackThreshold":
                response = device.getVoltageCallbackThreshold(
                        getValue(short.class, "channel5", m, getChannel5())
                    );
                break;

            case "setDebouncePeriod":
                device.setDebouncePeriod(
                        getValue(long.class, "debounce", m, getDebounce())
                    );
                break;

            case "getDebouncePeriod":
                response = device.getDebouncePeriod();
                break;

            case "setSampleRate":
                device.setSampleRate(
                        getValue(short.class, "rate", m, getRate())
                    );
                break;

            case "getSampleRate":
                response = device.getSampleRate();
                break;

            case "setCalibration":
                device.setCalibration(
                        getValue(int[].class, "offset", m, getOffset()),
                        getValue(int[].class, "gain", m, getGain())
                    );
                break;

            case "getCalibration":
                response = device.getCalibration();
                break;

            case "getAdcValues":
                response = device.getADCValues();
                break;

            case "getIdentity":
                response = device.getIdentity();
                break;


            default: throw new Exception("unknown function '"+function+"'");
            
        }
        
        return response;
    }
    
    
    public Short getChannel(){
        return channel;
    }

    public void setChannel(Short channel){
        this.channel = channel;
    }

    public Short getChannel2(){
        return channel2;
    }

    public void setChannel2(Short channel2){
        this.channel2 = channel2;
    }

    public Long getPeriod(){
        return period;
    }

    public void setPeriod(Long period){
        this.period = period;
    }

    public Short getChannel3(){
        return channel3;
    }

    public void setChannel3(Short channel3){
        this.channel3 = channel3;
    }

    public Short getChannel4(){
        return channel4;
    }

    public void setChannel4(Short channel4){
        this.channel4 = channel4;
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

    public Short getChannel5(){
        return channel5;
    }

    public void setChannel5(Short channel5){
        this.channel5 = channel5;
    }

    public Long getDebounce(){
        return debounce;
    }

    public void setDebounce(Long debounce){
        this.debounce = debounce;
    }

    public Short getRate(){
        return rate;
    }

    public void setRate(Short rate){
        this.rate = rate;
    }

    public int[] getOffset(){
        return offset;
    }

    public void setOffset(int[] offset){
        this.offset = offset;
    }

    public int[] getGain(){
        return gain;
    }

    public void setGain(int[] gain){
        this.gain = gain;
    }



}
