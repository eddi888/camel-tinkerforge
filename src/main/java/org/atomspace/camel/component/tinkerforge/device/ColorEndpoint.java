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

import com.tinkerforge.BrickletColor;

public class ColorEndpoint extends TinkerforgeEndpoint<ColorConsumer, ColorProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(ColorEndpoint.class);
    
    private Long period;
    private Character option;
    private Integer minR;
    private Integer maxR;
    private Integer minG;
    private Integer maxG;
    private Integer minB;
    private Integer maxB;
    private Integer minC;
    private Integer maxC;
    private Long debounce;
    private Short gain;
    private Short integrationTime;

        
    public ColorEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new ColorProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new ColorConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletColor device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletColor device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "getColor":
                response = device.getColor();
                break;

            case "setColorCallbackPeriod":
                device.setColorCallbackPeriod(
                        (long) getValue("period", m, e)
                    );
                break;

            case "getColorCallbackPeriod":
                response = device.getColorCallbackPeriod();
                break;

            case "setColorCallbackThreshold":
                device.setColorCallbackThreshold(
                        (char) getValue("option", m, e),
                        (int) getValue("minR", m, e),
                        (int) getValue("maxR", m, e),
                        (int) getValue("minG", m, e),
                        (int) getValue("maxG", m, e),
                        (int) getValue("minB", m, e),
                        (int) getValue("maxB", m, e),
                        (int) getValue("minC", m, e),
                        (int) getValue("maxC", m, e)
                    );
                break;

            case "getColorCallbackThreshold":
                response = device.getColorCallbackThreshold();
                break;

            case "setDebouncePeriod":
                device.setDebouncePeriod(
                        (long) getValue("debounce", m, e)
                    );
                break;

            case "getDebouncePeriod":
                response = device.getDebouncePeriod();
                break;

            case "lightOn":
                device.lightOn();
                break;

            case "lightOff":
                device.lightOff();
                break;

            case "isLightOn":
                response = device.isLightOn();
                break;

            case "setConfig":
                device.setConfig(
                        (short) getValue("gain", m, e),
                        (short) getValue("integrationTime", m, e)
                    );
                break;

            case "getConfig":
                response = device.getConfig();
                break;

            case "getIlluminance":
                response = device.getIlluminance();
                break;

            case "getColorTemperature":
                response = device.getColorTemperature();
                break;

            case "setIlluminanceCallbackPeriod":
                device.setIlluminanceCallbackPeriod(
                        (long) getValue("period", m, e)
                    );
                break;

            case "getIlluminanceCallbackPeriod":
                response = device.getIlluminanceCallbackPeriod();
                break;

            case "setColorTemperatureCallbackPeriod":
                device.setColorTemperatureCallbackPeriod(
                        (long) getValue("period", m, e)
                    );
                break;

            case "getColorTemperatureCallbackPeriod":
                response = device.getColorTemperatureCallbackPeriod();
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

    public Integer getMinR(){
        return minR;
    }

    public void setMinR(Integer minR){
        this.minR = minR;
    }

    public Integer getMaxR(){
        return maxR;
    }

    public void setMaxR(Integer maxR){
        this.maxR = maxR;
    }

    public Integer getMinG(){
        return minG;
    }

    public void setMinG(Integer minG){
        this.minG = minG;
    }

    public Integer getMaxG(){
        return maxG;
    }

    public void setMaxG(Integer maxG){
        this.maxG = maxG;
    }

    public Integer getMinB(){
        return minB;
    }

    public void setMinB(Integer minB){
        this.minB = minB;
    }

    public Integer getMaxB(){
        return maxB;
    }

    public void setMaxB(Integer maxB){
        this.maxB = maxB;
    }

    public Integer getMinC(){
        return minC;
    }

    public void setMinC(Integer minC){
        this.minC = minC;
    }

    public Integer getMaxC(){
        return maxC;
    }

    public void setMaxC(Integer maxC){
        this.maxC = maxC;
    }

    public Long getDebounce(){
        return debounce;
    }

    public void setDebounce(Long debounce){
        this.debounce = debounce;
    }

    public Short getGain(){
        return gain;
    }

    public void setGain(Short gain){
        this.gain = gain;
    }

    public Short getIntegrationTime(){
        return integrationTime;
    }

    public void setIntegrationTime(Short integrationTime){
        this.integrationTime = integrationTime;
    }



}
