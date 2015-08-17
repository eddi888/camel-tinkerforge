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

import com.tinkerforge.BrickletLCD20x4;

/**
 * 20x4 character alphanumeric display with blue backlight
 */
public class LCD20x4Endpoint extends TinkerforgeEndpoint<LCD20x4Consumer, LCD20x4Producer> {

    private static final Logger LOG = LoggerFactory.getLogger(LCD20x4Endpoint.class);
    
    private Short line;
    private Short position;
    private String text;
    private Boolean cursor;
    private Boolean blinking;
    private Short button;
    private Short index;
    private short[] character;
    private Short index2;
    private Short line2;
    private String text2;
    private Short line3;
    private Integer counter;

        
    public LCD20x4Endpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new LCD20x4Producer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new LCD20x4Consumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletLCD20x4 device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletLCD20x4 device, String function, Message m, Endpoint e) throws Exception{
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

            case "setDefaultText":
                device.setDefaultText(
                        getValue(short.class, "line2", m, getLine2()),
                        getValue(String.class, "text2", m, getText2())
                    );
                break;

            case "getDefaultText":
                response = device.getDefaultText(
                        getValue(short.class, "line3", m, getLine3())
                    );
                break;

            case "setDefaultTextCounter":
                device.setDefaultTextCounter(
                        getValue(int.class, "counter", m, getCounter())
                    );
                break;

            case "getDefaultTextCounter":
                response = device.getDefaultTextCounter();
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
     * Writes text to a specific line (0 to 3) with a specific position 
     * (0 to 19). The text can have a maximum of 20 characters.
     * 
     * For example: (0, 7, "Hello") will write *Hello* in the middle of the
     * first line of the display.
     * 
     * The display uses a special charset that includes all ASCII characters except
     * backslash and tilde. The LCD charset also includes several other non-ASCII characters, see
     * the `charset specification <https://github.com/Tinkerforge/lcd-20x4-bricklet/raw/master/datasheets/standard_charset.pdf>`__
     * for details. The Unicode example above shows how to specify non-ASCII characters
     * and how to translate from Unicode to the LCD charset.
     * 
     */
    public Short getLine(){
        return line;
    }

    public void setLine(Short line){
        this.line = line;
    }

    /**
     * 
     * Writes text to a specific line (0 to 3) with a specific position 
     * (0 to 19). The text can have a maximum of 20 characters.
     * 
     * For example: (0, 7, "Hello") will write *Hello* in the middle of the
     * first line of the display.
     * 
     * The display uses a special charset that includes all ASCII characters except
     * backslash and tilde. The LCD charset also includes several other non-ASCII characters, see
     * the `charset specification <https://github.com/Tinkerforge/lcd-20x4-bricklet/raw/master/datasheets/standard_charset.pdf>`__
     * for details. The Unicode example above shows how to specify non-ASCII characters
     * and how to translate from Unicode to the LCD charset.
     * 
     */
    public Short getPosition(){
        return position;
    }

    public void setPosition(Short position){
        this.position = position;
    }

    /**
     * 
     * Writes text to a specific line (0 to 3) with a specific position 
     * (0 to 19). The text can have a maximum of 20 characters.
     * 
     * For example: (0, 7, "Hello") will write *Hello* in the middle of the
     * first line of the display.
     * 
     * The display uses a special charset that includes all ASCII characters except
     * backslash and tilde. The LCD charset also includes several other non-ASCII characters, see
     * the `charset specification <https://github.com/Tinkerforge/lcd-20x4-bricklet/raw/master/datasheets/standard_charset.pdf>`__
     * for details. The Unicode example above shows how to specify non-ASCII characters
     * and how to translate from Unicode to the LCD charset.
     * 
     */
    public String getText(){
        return text;
    }

    public void setText(String text){
        this.text = text;
    }

    /**
     * 
     * Configures if the cursor (shown as "_") should be visible and if it
     * should be blinking (shown as a blinking block). The cursor position
     * is one character behind the the last text written with 
     * :func:`WriteLine`.
     * 
     * The default is (*false*, *false*).
     * 
     */
    public Boolean getCursor(){
        return cursor;
    }

    public void setCursor(Boolean cursor){
        this.cursor = cursor;
    }

    /**
     * 
     * Configures if the cursor (shown as "_") should be visible and if it
     * should be blinking (shown as a blinking block). The cursor position
     * is one character behind the the last text written with 
     * :func:`WriteLine`.
     * 
     * The default is (*false*, *false*).
     * 
     */
    public Boolean getBlinking(){
        return blinking;
    }

    public void setBlinking(Boolean blinking){
        this.blinking = blinking;
    }

    /**
     * 
     * Returns *true* if the button (0 to 2 or 0 to 3 since hardware version 1.2)
     * is pressed. If you want to react
     * on button presses and releases it is recommended to use the
     * :func:`ButtonPressed` and :func:`ButtonReleased` callbacks.
     * 
     */
    public Short getButton(){
        return button;
    }

    public void setButton(Short button){
        this.button = button;
    }

    /**
     * 
     * The LCD 20x4 Bricklet can store up to 8 custom characters. The characters
     * consist of 5x8 pixels and can be addressed with the index 0-7. To describe
     * the pixels, the first 5 bits of 8 bytes are used. For example, to make
     * a custom character "H", you should transfer the following:
     * 
     * * ``character[0] = 0b00010001`` (decimal value 17)
     * * ``character[1] = 0b00010001`` (decimal value 17)
     * * ``character[2] = 0b00010001`` (decimal value 17)
     * * ``character[3] = 0b00011111`` (decimal value 31)
     * * ``character[4] = 0b00010001`` (decimal value 17)
     * * ``character[5] = 0b00010001`` (decimal value 17)
     * * ``character[6] = 0b00010001`` (decimal value 17)
     * * ``character[7] = 0b00000000`` (decimal value 0)
     * 
     * The characters can later be written with :func:`WriteLine` by using the
     * characters with the byte representation 8 ("\x08") to 15 ("\x0F").
     * 
     * You can play around with the custom characters in Brick Viewer version
     * since 2.0.1.
     * 
     * Custom characters are stored by the LCD in RAM, so they have to be set
     * after each startup.
     * 
     */
    public Short getIndex(){
        return index;
    }

    public void setIndex(Short index){
        this.index = index;
    }

    /**
     * 
     * The LCD 20x4 Bricklet can store up to 8 custom characters. The characters
     * consist of 5x8 pixels and can be addressed with the index 0-7. To describe
     * the pixels, the first 5 bits of 8 bytes are used. For example, to make
     * a custom character "H", you should transfer the following:
     * 
     * * ``character[0] = 0b00010001`` (decimal value 17)
     * * ``character[1] = 0b00010001`` (decimal value 17)
     * * ``character[2] = 0b00010001`` (decimal value 17)
     * * ``character[3] = 0b00011111`` (decimal value 31)
     * * ``character[4] = 0b00010001`` (decimal value 17)
     * * ``character[5] = 0b00010001`` (decimal value 17)
     * * ``character[6] = 0b00010001`` (decimal value 17)
     * * ``character[7] = 0b00000000`` (decimal value 0)
     * 
     * The characters can later be written with :func:`WriteLine` by using the
     * characters with the byte representation 8 ("\x08") to 15 ("\x0F").
     * 
     * You can play around with the custom characters in Brick Viewer version
     * since 2.0.1.
     * 
     * Custom characters are stored by the LCD in RAM, so they have to be set
     * after each startup.
     * 
     */
    public short[] getCharacter(){
        return character;
    }

    public void setCharacter(short[] character){
        this.character = character;
    }

    /**
     * 
     * Returns the custom character for a given index, as set with
     * :func:`SetCustomCharacter`.
     * 
     */
    public Short getIndex2(){
        return index2;
    }

    public void setIndex2(Short index2){
        this.index2 = index2;
    }

    /**
     * 
     * Sets the default text for lines 0-3. The max number of characters
     * per line is 20.
     * 
     * The default text is shown on the LCD, if the default text counter
     * expires, see :func:`SetDefaultTextCounter`.
     * 
     */
    public Short getLine2(){
        return line2;
    }

    public void setLine2(Short line2){
        this.line2 = line2;
    }

    /**
     * 
     * Sets the default text for lines 0-3. The max number of characters
     * per line is 20.
     * 
     * The default text is shown on the LCD, if the default text counter
     * expires, see :func:`SetDefaultTextCounter`.
     * 
     */
    public String getText2(){
        return text2;
    }

    public void setText2(String text2){
        this.text2 = text2;
    }

    /**
     * 
     * Returns the default text for a given line (0-3) as set by
     * :func:`SetDefaultText`.
     * 
     */
    public Short getLine3(){
        return line3;
    }

    public void setLine3(Short line3){
        this.line3 = line3;
    }

    /**
     * 
     * Sets the default text counter in ms. This counter is decremented each
     * ms by the LCD firmware. If the counter reaches 0, the default text
     * (see :func:`SetDefaultText`) is shown on the LCD.
     * 
     * This functionality can be used to show a default text if the controlling
     * program crashes or the connection is interrupted.
     * 
     * A possible approach is to call :func:`SetDefaultTextCounter` every
     * minute with the parameter 1000*60*2 (2 minutes). In this case the
     * default text will be shown no later than 2 minutes after the
     * controlling program crashes.
     * 
     * A negative counter turns the default text functionality off.
     * 
     * The default is -1.
     * 
     */
    public Integer getCounter(){
        return counter;
    }

    public void setCounter(Integer counter){
        this.counter = counter;
    }



}
