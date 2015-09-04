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

import com.tinkerforge.BrickletPiezoSpeaker;

/**
 * Creates beep with configurable frequency
 */
@UriEndpoint(scheme = "tinkerforgegen", syntax = "tinkerforgegen:[host[:port]/]piezospeaker", consumerClass = PiezoSpeakerConsumer.class, label = "iot", title = "Tinkerforge")
public class PiezoSpeakerEndpoint extends TinkerforgeEndpoint<PiezoSpeakerConsumer, PiezoSpeakerProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(PiezoSpeakerEndpoint.class);

    public static final String DURATION="duration";
    public static final String FREQUENCY="frequency";
    public static final String MORSE="morse";
    public static final String FREQUENCY2="frequency2";

    
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
    
    
    /**
     * 
     * Beeps with the given frequency for the duration in ms. For example: 
     * If you set a duration of 1000, with a frequency value of 2000
     * the piezo buzzer will beep for one second with a frequency of
     * approximately 2 kHz.
     * 
     * .. versionchanged:: 2.0.2~(Plugin)
     *    A duration of 0 stops the current beep if any, the frequency parameter is
     *    ignored. A duration of 4294967295 results in an infinite beep.
     * 
     * The *frequency* parameter can be set between 585 and 7100.
     * 
     * The Piezo Speaker Bricklet can only approximate the frequency, it will play
     * the best possible match by applying the calibration (see :func:`Calibrate`).
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
     * Beeps with the given frequency for the duration in ms. For example: 
     * If you set a duration of 1000, with a frequency value of 2000
     * the piezo buzzer will beep for one second with a frequency of
     * approximately 2 kHz.
     * 
     * .. versionchanged:: 2.0.2~(Plugin)
     *    A duration of 0 stops the current beep if any, the frequency parameter is
     *    ignored. A duration of 4294967295 results in an infinite beep.
     * 
     * The *frequency* parameter can be set between 585 and 7100.
     * 
     * The Piezo Speaker Bricklet can only approximate the frequency, it will play
     * the best possible match by applying the calibration (see :func:`Calibrate`).
     * 
     */
    public Integer getFrequency(){
        return frequency;
    }

    public void setFrequency(Integer frequency){
        this.frequency = frequency;
    }

    /**
     * 
     * Sets morse code that will be played by the piezo buzzer. The morse code
     * is given as a string consisting of "." (dot), "-" (minus) and " " (space)
     * for *dits*, *dahs* and *pauses*. Every other character is ignored.
     * The second parameter is the frequency (see :func:`Beep`).
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

    /**
     * 
     * Sets morse code that will be played by the piezo buzzer. The morse code
     * is given as a string consisting of "." (dot), "-" (minus) and " " (space)
     * for *dits*, *dahs* and *pauses*. Every other character is ignored.
     * The second parameter is the frequency (see :func:`Beep`).
     * 
     * For example: If you set the string "...---...", the piezo buzzer will beep
     * nine times with the durations "short short short long long long short 
     * short short".
     * 
     * The maximum string size is 60.
     * 
     */
    public Integer getFrequency2(){
        return frequency2;
    }

    public void setFrequency2(Integer frequency2){
        this.frequency2 = frequency2;
    }



}
