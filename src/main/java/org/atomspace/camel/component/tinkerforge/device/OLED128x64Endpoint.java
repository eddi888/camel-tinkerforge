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

import com.tinkerforge.BrickletOLED128x64;

/**
 * 3.3cm (1.3") OLED with 128x64 pixels
 */
@UriEndpoint(scheme = "tinkerforgegen", syntax = "tinkerforgegen:[host[:port]/]oled128x64", consumerClass = OLED128x64Consumer.class, label = "iot", title = "Tinkerforge")
public class OLED128x64Endpoint extends TinkerforgeEndpoint<OLED128x64Consumer, OLED128x64Producer> {

    private static final Logger LOG = LoggerFactory.getLogger(OLED128x64Endpoint.class);

    public static final String DATA="data";
    public static final String COLUMNFROM="columnFrom";
    public static final String COLUMNTO="columnTo";
    public static final String ROWFROM="rowFrom";
    public static final String ROWTO="rowTo";
    public static final String CONTRAST="contrast";
    public static final String INVERT="invert";
    public static final String LINE="line";
    public static final String POSITION="position";
    public static final String TEXT="text";

    
    private short[] data;
    private Short columnFrom;
    private Short columnTo;
    private Short rowFrom;
    private Short rowTo;
    private Short contrast;
    private Boolean invert;
    private Short line;
    private Short position;
    private String text;

        
    public OLED128x64Endpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new OLED128x64Producer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new OLED128x64Consumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletOLED128x64 device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletOLED128x64 device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "write":
                device.write(
                        getValue(short[].class, "data", m, getData())
                    );
                break;

            case "newWindow":
                device.newWindow(
                        getValue(short.class, "columnFrom", m, getColumnFrom()),
                        getValue(short.class, "columnTo", m, getColumnTo()),
                        getValue(short.class, "rowFrom", m, getRowFrom()),
                        getValue(short.class, "rowTo", m, getRowTo())
                    );
                break;

            case "clearDisplay":
                device.clearDisplay();
                break;

            case "setDisplayConfiguration":
                device.setDisplayConfiguration(
                        getValue(short.class, "contrast", m, getContrast()),
                        getValue(boolean.class, "invert", m, getInvert())
                    );
                break;

            case "getDisplayConfiguration":
                response = device.getDisplayConfiguration();
                break;

            case "writeLine":
                device.writeLine(
                        getValue(short.class, "line", m, getLine()),
                        getValue(short.class, "position", m, getPosition()),
                        getValue(String.class, "text", m, getText())
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
     * Appends 64 byte of data to the window as set by :func:`NewWindow`.
     * 
     * Each row has a height of 8 pixels which corresponds to one byte of data.
     * 
     * Example: if you call :func:`NewWindow` with column from 0 to 127 and row
     * from 0 to 7 (the whole display) each call of :func:`Write` (red arrow) will
     * write half of a row.
     * 
     * .. image:: /Images/Bricklets/bricklet_oled_128x64_display.png
     *    :scale: 100 %
     *    :alt: Display pixel order
     *    :align: center
     *    :target: ../../_images/Bricklets/bricklet_oled_128x64_display.png
     * 
     * The LSB (D0) of each data byte is at the top and the MSB (D7) is at the 
     * bottom of the row.
     * 
     * The next call of :func:`Write` will write the second half of the row
     * and the next two the second row and so on. To fill the whole display 
     * you need to call :func:`Write` 16 times.
     * 
     */
    public short[] getData(){
        return data;
    }

    public void setData(short[] data){
        this.data = data;
    }

    /**
     * 
     * Sets the window in which you can write with :func:`Write`. One row
     * has a height of 8 pixels.
     * 
     * The columns have a range of 0 to 127 and the rows have a range of 0 to 7.
     * 
     */
    public Short getColumnFrom(){
        return columnFrom;
    }

    public void setColumnFrom(Short columnFrom){
        this.columnFrom = columnFrom;
    }

    /**
     * 
     * Sets the window in which you can write with :func:`Write`. One row
     * has a height of 8 pixels.
     * 
     * The columns have a range of 0 to 127 and the rows have a range of 0 to 7.
     * 
     */
    public Short getColumnTo(){
        return columnTo;
    }

    public void setColumnTo(Short columnTo){
        this.columnTo = columnTo;
    }

    /**
     * 
     * Sets the window in which you can write with :func:`Write`. One row
     * has a height of 8 pixels.
     * 
     * The columns have a range of 0 to 127 and the rows have a range of 0 to 7.
     * 
     */
    public Short getRowFrom(){
        return rowFrom;
    }

    public void setRowFrom(Short rowFrom){
        this.rowFrom = rowFrom;
    }

    /**
     * 
     * Sets the window in which you can write with :func:`Write`. One row
     * has a height of 8 pixels.
     * 
     * The columns have a range of 0 to 127 and the rows have a range of 0 to 7.
     * 
     */
    public Short getRowTo(){
        return rowTo;
    }

    public void setRowTo(Short rowTo){
        this.rowTo = rowTo;
    }

    /**
     * 
     * Sets the configuration of the display.
     * 
     * You can set a contrast value from 0 to 255 and you can invert the color
     * (black/white) of the display.
     * 
     * The default values are contrast 143 and inverting off.
     * 
     */
    public Short getContrast(){
        return contrast;
    }

    public void setContrast(Short contrast){
        this.contrast = contrast;
    }

    /**
     * 
     * Sets the configuration of the display.
     * 
     * You can set a contrast value from 0 to 255 and you can invert the color
     * (black/white) of the display.
     * 
     * The default values are contrast 143 and inverting off.
     * 
     */
    public Boolean getInvert(){
        return invert;
    }

    public void setInvert(Boolean invert){
        this.invert = invert;
    }

    /**
     * 
     * Writes text to a specific line (0 to 7) with a specific position 
     * (0 to 25). The text can have a maximum of 26 characters.
     * 
     * For example: (1, 10, "Hello") will write *Hello* in the middle of the
     * second line of the display.
     * 
     * You can draw to the display with :func:`Write` and then add text to it
     * afterwards.
     * 
     * The display uses a special 5x7 pixel charset. You can view the characters 
     * of the charset in Brick Viewer.
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
     * Writes text to a specific line (0 to 7) with a specific position 
     * (0 to 25). The text can have a maximum of 26 characters.
     * 
     * For example: (1, 10, "Hello") will write *Hello* in the middle of the
     * second line of the display.
     * 
     * You can draw to the display with :func:`Write` and then add text to it
     * afterwards.
     * 
     * The display uses a special 5x7 pixel charset. You can view the characters 
     * of the charset in Brick Viewer.
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
     * Writes text to a specific line (0 to 7) with a specific position 
     * (0 to 25). The text can have a maximum of 26 characters.
     * 
     * For example: (1, 10, "Hello") will write *Hello* in the middle of the
     * second line of the display.
     * 
     * You can draw to the display with :func:`Write` and then add text to it
     * afterwards.
     * 
     * The display uses a special 5x7 pixel charset. You can view the characters 
     * of the charset in Brick Viewer.
     * 
     */
    public String getText(){
        return text;
    }

    public void setText(String text){
        this.text = text;
    }



}
