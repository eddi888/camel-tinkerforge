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

import com.tinkerforge.BrickletLEDStrip;

public class LEDStripEndpoint extends TinkerforgeEndpoint<LEDStripConsumer, LEDStripProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(LEDStripEndpoint.class);
    
    private Integer index;
    private Short length;
    private Short r;
    private Short g;
    private Short b;
    private Integer duration;
    private Long frequency;
    private Integer chip;

        
    public LEDStripEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new LEDStripProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new LEDStripConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletLEDStrip device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletLEDStrip device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "setFrameDuration":
                device.setFrameDuration(
                        (int) getValue("duration", m, e)
                    );
                break;

            case "getFrameDuration":
                response = device.getFrameDuration();
                break;

            case "getSupplyVoltage":
                response = device.getSupplyVoltage();
                break;

            case "setClockFrequency":
                device.setClockFrequency(
                        (long) getValue("frequency", m, e)
                    );
                break;

            case "getClockFrequency":
                response = device.getClockFrequency();
                break;

            case "setChipType":
                device.setChipType(
                        (int) getValue("chip", m, e)
                    );
                break;

            case "getChipType":
                response = device.getChipType();
                break;

            case "getIdentity":
                response = device.getIdentity();
                break;


            default: throw new Exception("unknown function '"+function+"'");
            
        }
        
        return response;
    }
    
    
    public Integer getIndex(){
        return index;
    }

    public void setIndex(Integer index){
        this.index = index;
    }

    public Short getLength(){
        return length;
    }

    public void setLength(Short length){
        this.length = length;
    }

    public Short getR(){
        return r;
    }

    public void setR(Short r){
        this.r = r;
    }

    public Short getG(){
        return g;
    }

    public void setG(Short g){
        this.g = g;
    }

    public Short getB(){
        return b;
    }

    public void setB(Short b){
        this.b = b;
    }

    public Integer getDuration(){
        return duration;
    }

    public void setDuration(Integer duration){
        this.duration = duration;
    }

    public Long getFrequency(){
        return frequency;
    }

    public void setFrequency(Long frequency){
        this.frequency = frequency;
    }

    public Integer getChip(){
        return chip;
    }

    public void setChip(Integer chip){
        this.chip = chip;
    }



}
