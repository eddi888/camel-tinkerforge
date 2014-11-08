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

import com.tinkerforge.BrickletPiezoSpeaker;

public class PiezoSpeakerEndpoint extends TinkerforgeEndpoint<PiezoSpeakerConsumer, PiezoSpeakerProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(PiezoSpeakerEndpoint.class);
    
    private Long duration;
    private Integer frequency;
    private String morse;
    private Integer frequency2;

        
    public PiezoSpeakerEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new PiezoSpeakerProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new PiezoSpeakerConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletPiezoSpeaker device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletPiezoSpeaker device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "beep":
                device.beep(
                        getValue(long.class, "duration", m, getDuration()),
                        getValue(int.class, "frequency", m, getFrequency())
                    );
                break;

            case "morseCode":
                device.morseCode(
                        getValue(String.class, "morse", m, getMorse()),
                        getValue(int.class, "frequency2", m, getFrequency2())
                    );
                break;

            case "calibrate":
                response = device.calibrate();
                break;

            case "getIdentity":
                response = device.getIdentity();
                break;


            default: throw new Exception("unknown function '"+function+"'");
            
        }
        
        return response;
    }
    
    
    public Long getDuration(){
        return duration;
    }

    public void setDuration(Long duration){
        this.duration = duration;
    }

    public Integer getFrequency(){
        return frequency;
    }

    public void setFrequency(Integer frequency){
        this.frequency = frequency;
    }

    public String getMorse(){
        return morse;
    }

    public void setMorse(String morse){
        this.morse = morse;
    }

    public Integer getFrequency2(){
        return frequency2;
    }

    public void setFrequency2(Integer frequency2){
        this.frequency2 = frequency2;
    }



}