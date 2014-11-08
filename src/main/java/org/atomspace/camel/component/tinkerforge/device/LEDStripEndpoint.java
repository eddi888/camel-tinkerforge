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
    private short[] r;
    private short[] g;
    private short[] b;
    private Integer index2;
    private Short length2;
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
                
            case "setRgbValues":
                device.setRGBValues(
                        getValue(int.class, "index", m, getIndex()),
                        getValue(short.class, "length", m, getLength()),
                        getValue(short[].class, "r", m, getR()),
                        getValue(short[].class, "g", m, getG()),
                        getValue(short[].class, "b", m, getB())
                    );
                break;

            case "getRgbValues":
                response = device.getRGBValues(
                        getValue(int.class, "index2", m, getIndex2()),
                        getValue(short.class, "length2", m, getLength2())
                    );
                break;

            case "setFrameDuration":
                device.setFrameDuration(
                        getValue(int.class, "duration", m, getDuration())
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
                        getValue(long.class, "frequency", m, getFrequency())
                    );
                break;

            case "getClockFrequency":
                response = device.getClockFrequency();
                break;

            case "setChipType":
                device.setChipType(
                        getValue(int.class, "chip", m, getChip())
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

    public short[] getR(){
        return r;
    }

    public void setR(short[] r){
        this.r = r;
    }

    public short[] getG(){
        return g;
    }

    public void setG(short[] g){
        this.g = g;
    }

    public short[] getB(){
        return b;
    }

    public void setB(short[] b){
        this.b = b;
    }

    public Integer getIndex2(){
        return index2;
    }

    public void setIndex2(Integer index2){
        this.index2 = index2;
    }

    public Short getLength2(){
        return length2;
    }

    public void setLength2(Short length2){
        this.length2 = length2;
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
