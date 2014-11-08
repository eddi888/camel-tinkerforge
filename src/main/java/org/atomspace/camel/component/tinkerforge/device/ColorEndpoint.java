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
    private Long period2;
    private Long period3;

        
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
                        getValue(long.class, "period", m, getPeriod())
                    );
                break;

            case "getColorCallbackPeriod":
                response = device.getColorCallbackPeriod();
                break;

            case "setColorCallbackThreshold":
                device.setColorCallbackThreshold(
                        getValue(char.class, "option", m, getOption()),
                        getValue(int.class, "minR", m, getMinR()),
                        getValue(int.class, "maxR", m, getMaxR()),
                        getValue(int.class, "minG", m, getMinG()),
                        getValue(int.class, "maxG", m, getMaxG()),
                        getValue(int.class, "minB", m, getMinB()),
                        getValue(int.class, "maxB", m, getMaxB()),
                        getValue(int.class, "minC", m, getMinC()),
                        getValue(int.class, "maxC", m, getMaxC())
                    );
                break;

            case "getColorCallbackThreshold":
                response = device.getColorCallbackThreshold();
                break;

            case "setDebouncePeriod":
                device.setDebouncePeriod(
                        getValue(long.class, "debounce", m, getDebounce())
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
                        getValue(short.class, "gain", m, getGain()),
                        getValue(short.class, "integrationTime", m, getIntegrationTime())
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
                        getValue(long.class, "period2", m, getPeriod2())
                    );
                break;

            case "getIlluminanceCallbackPeriod":
                response = device.getIlluminanceCallbackPeriod();
                break;

            case "setColorTemperatureCallbackPeriod":
                device.setColorTemperatureCallbackPeriod(
                        getValue(long.class, "period3", m, getPeriod3())
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

    public Long getPeriod2(){
        return period2;
    }

    public void setPeriod2(Long period2){
        this.period2 = period2;
    }

    public Long getPeriod3(){
        return period3;
    }

    public void setPeriod3(Long period3){
        this.period3 = period3;
    }



}
