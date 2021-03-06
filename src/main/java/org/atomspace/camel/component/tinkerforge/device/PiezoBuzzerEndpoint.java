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
import org.apache.camel.spi.UriEndpoint;
import org.atomspace.camel.component.tinkerforge.TinkerforgeComponent;
import org.atomspace.camel.component.tinkerforge.TinkerforgeEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tinkerforge.BrickletPiezoBuzzer;

/**
 * Creates 1kHz beep
 */
@UriEndpoint(scheme = "tinkerforgegen", syntax = "tinkerforgegen:[host[:port]/]piezobuzzer", consumerClass = PiezoBuzzerConsumer.class, label = "iot", title = "Tinkerforge")
public class PiezoBuzzerEndpoint extends TinkerforgeEndpoint<PiezoBuzzerConsumer, PiezoBuzzerProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(PiezoBuzzerEndpoint.class);

    public static final String DURATION="duration";
    public static final String MORSE="morse";

    
    private Long duration;
    private String morse;

        
    public PiezoBuzzerEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new PiezoBuzzerProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new PiezoBuzzerConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletPiezoBuzzer device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletPiezoBuzzer device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "beep":
                device.beep(
                        getValue(long.class, "duration", m, getDuration())
                    );
                break;

            case "morseCode":
                device.morseCode(
                        getValue(String.class, "morse", m, getMorse())
                    );
                break;

            case "getIdentity":
                response = device.getIdentity();
                break;


            default: throw new Exception("unknown function '"+function+"'");
            
        }
        
        return response;
    }
    
    
    /**
     * 
     * Beeps with the duration in ms. For example: If you set a value of 1000,
     * the piezo buzzer will beep for one second.
     * 
     */
    public Long getDuration(){
        return duration;
    }

    public void setDuration(Long duration){
        this.duration = duration;
    }

    /**
     * 
     * Sets morse code that will be played by the piezo buzzer. The morse code
     * is given as a string consisting of "." (dot), "-" (minus) and " " (space)
     * for *dits*, *dahs* and *pauses*. Every other character is ignored.
     * 
     * For example: If you set the string "...---...", the piezo buzzer will beep
     * nine times with the durations "short short short long long long short 
     * short short".
     * 
     * The maximum string size is 60.
     * 
     */
    public String getMorse(){
        return morse;
    }

    public void setMorse(String morse){
        this.morse = morse;
    }



}
