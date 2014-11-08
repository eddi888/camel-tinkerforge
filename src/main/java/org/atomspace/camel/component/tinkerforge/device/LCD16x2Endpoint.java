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

import com.tinkerforge.BrickletLCD16x2;

public class LCD16x2Endpoint extends TinkerforgeEndpoint<LCD16x2Consumer, LCD16x2Producer> {

    private static final Logger LOG = LoggerFactory.getLogger(LCD16x2Endpoint.class);
    
    private Short line;
    private Short position;
    private String text;
    private Boolean cursor;
    private Boolean blinking;
    private Short button;
    private Short index;
    private short[] character;
    private Short index2;

        
    public LCD16x2Endpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new LCD16x2Producer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new LCD16x2Consumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletLCD16x2 device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletLCD16x2 device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "writeLine":
                device.writeLine(
                        getValue(short.class, "line", m, getLine()),
                        getValue(short.class, "position", m, getPosition()),
                        getValue(String.class, "text", m, getText())
                    );
                break;

            case "clearDisplay":
                device.clearDisplay();
                break;

            case "backlightOn":
                device.backlightOn();
                break;

            case "backlightOff":
                device.backlightOff();
                break;

            case "isBacklightOn":
                response = device.isBacklightOn();
                break;

            case "setConfig":
                device.setConfig(
                        getValue(boolean.class, "cursor", m, getCursor()),
                        getValue(boolean.class, "blinking", m, getBlinking())
                    );
                break;

            case "getConfig":
                response = device.getConfig();
                break;

            case "isButtonPressed":
                response = device.isButtonPressed(
                        getValue(short.class, "button", m, getButton())
                    );
                break;

            case "setCustomCharacter":
                device.setCustomCharacter(
                        getValue(short.class, "index", m, getIndex()),
                        getValue(short[].class, "character", m, getCharacter())
                    );
                break;

            case "getCustomCharacter":
                response = device.getCustomCharacter(
                        getValue(short.class, "index2", m, getIndex2())
                    );
                break;

            case "getIdentity":
                response = device.getIdentity();
                break;


            default: throw new Exception("unknown function '"+function+"'");
            
        }
        
        return response;
    }
    
    
    public Short getLine(){
        return line;
    }

    public void setLine(Short line){
        this.line = line;
    }

    public Short getPosition(){
        return position;
    }

    public void setPosition(Short position){
        this.position = position;
    }

    public String getText(){
        return text;
    }

    public void setText(String text){
        this.text = text;
    }

    public Boolean getCursor(){
        return cursor;
    }

    public void setCursor(Boolean cursor){
        this.cursor = cursor;
    }

    public Boolean getBlinking(){
        return blinking;
    }

    public void setBlinking(Boolean blinking){
        this.blinking = blinking;
    }

    public Short getButton(){
        return button;
    }

    public void setButton(Short button){
        this.button = button;
    }

    public Short getIndex(){
        return index;
    }

    public void setIndex(Short index){
        this.index = index;
    }

    public short[] getCharacter(){
        return character;
    }

    public void setCharacter(short[] character){
        this.character = character;
    }

    public Short getIndex2(){
        return index2;
    }

    public void setIndex2(Short index2){
        this.index2 = index2;
    }



}
