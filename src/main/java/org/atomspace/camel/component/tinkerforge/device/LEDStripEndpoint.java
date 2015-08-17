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

/**
 * Controls up to 320 RGB LEDs
 */
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
    
    
    /**
     * 
     * Sets the *rgb* values for the LEDs with the given *length* starting 
     * from *index*.
     * 
     * The maximum length is 16, the index goes from 0 to 319 and the rgb values
     * have 8 bits each.
     * 
     * Example: If you set
     * 
     * * index to 5,
     * * length to 3,
     * * r to |r_values|,
     * * g to |g_values| and
     * * b to |b_values|
     * 
     * the LED with index 5 will be red, 6 will be green and 7 will be blue.
     * 
     * .. note:: Depending on the LED circuitry colors can be permuted.
     * 
     * The colors will be transfered to actual LEDs when the next
     * frame duration ends, see :func:`SetFrameDuration`.
     * 
     * Generic approach: 
     * 
     * * Set the frame duration to a value that represents
     *   the number of frames per second you want to achieve. 
     * * Set all of the LED colors for one frame.
     * * Wait for the :func:`FrameRendered` callback.
     * * Set all of the LED colors for next frame.
     * * Wait for the :func:`FrameRendered` callback.
     * * and so on.
     * 
     * This approach ensures that you can change the LED colors with
     * a fixed frame rate.
     * 
     * The actual number of controllable LEDs depends on the number of free
     * Bricklet ports. See :ref:`here <led_strip_bricklet_ram_constraints>` for more
     * information. A call of :func:`SetRGBValues` with index + length above the
     * bounds is ignored completely.
     * 
     */
    public Integer getIndex(){
        return index;
    }

    public void setIndex(Integer index){
        this.index = index;
    }

    /**
     * 
     * Sets the *rgb* values for the LEDs with the given *length* starting 
     * from *index*.
     * 
     * The maximum length is 16, the index goes from 0 to 319 and the rgb values
     * have 8 bits each.
     * 
     * Example: If you set
     * 
     * * index to 5,
     * * length to 3,
     * * r to |r_values|,
     * * g to |g_values| and
     * * b to |b_values|
     * 
     * the LED with index 5 will be red, 6 will be green and 7 will be blue.
     * 
     * .. note:: Depending on the LED circuitry colors can be permuted.
     * 
     * The colors will be transfered to actual LEDs when the next
     * frame duration ends, see :func:`SetFrameDuration`.
     * 
     * Generic approach: 
     * 
     * * Set the frame duration to a value that represents
     *   the number of frames per second you want to achieve. 
     * * Set all of the LED colors for one frame.
     * * Wait for the :func:`FrameRendered` callback.
     * * Set all of the LED colors for next frame.
     * * Wait for the :func:`FrameRendered` callback.
     * * and so on.
     * 
     * This approach ensures that you can change the LED colors with
     * a fixed frame rate.
     * 
     * The actual number of controllable LEDs depends on the number of free
     * Bricklet ports. See :ref:`here <led_strip_bricklet_ram_constraints>` for more
     * information. A call of :func:`SetRGBValues` with index + length above the
     * bounds is ignored completely.
     * 
     */
    public Short getLength(){
        return length;
    }

    public void setLength(Short length){
        this.length = length;
    }

    /**
     * 
     * Sets the *rgb* values for the LEDs with the given *length* starting 
     * from *index*.
     * 
     * The maximum length is 16, the index goes from 0 to 319 and the rgb values
     * have 8 bits each.
     * 
     * Example: If you set
     * 
     * * index to 5,
     * * length to 3,
     * * r to |r_values|,
     * * g to |g_values| and
     * * b to |b_values|
     * 
     * the LED with index 5 will be red, 6 will be green and 7 will be blue.
     * 
     * .. note:: Depending on the LED circuitry colors can be permuted.
     * 
     * The colors will be transfered to actual LEDs when the next
     * frame duration ends, see :func:`SetFrameDuration`.
     * 
     * Generic approach: 
     * 
     * * Set the frame duration to a value that represents
     *   the number of frames per second you want to achieve. 
     * * Set all of the LED colors for one frame.
     * * Wait for the :func:`FrameRendered` callback.
     * * Set all of the LED colors for next frame.
     * * Wait for the :func:`FrameRendered` callback.
     * * and so on.
     * 
     * This approach ensures that you can change the LED colors with
     * a fixed frame rate.
     * 
     * The actual number of controllable LEDs depends on the number of free
     * Bricklet ports. See :ref:`here <led_strip_bricklet_ram_constraints>` for more
     * information. A call of :func:`SetRGBValues` with index + length above the
     * bounds is ignored completely.
     * 
     */
    public short[] getR(){
        return r;
    }

    public void setR(short[] r){
        this.r = r;
    }

    /**
     * 
     * Sets the *rgb* values for the LEDs with the given *length* starting 
     * from *index*.
     * 
     * The maximum length is 16, the index goes from 0 to 319 and the rgb values
     * have 8 bits each.
     * 
     * Example: If you set
     * 
     * * index to 5,
     * * length to 3,
     * * r to |r_values|,
     * * g to |g_values| and
     * * b to |b_values|
     * 
     * the LED with index 5 will be red, 6 will be green and 7 will be blue.
     * 
     * .. note:: Depending on the LED circuitry colors can be permuted.
     * 
     * The colors will be transfered to actual LEDs when the next
     * frame duration ends, see :func:`SetFrameDuration`.
     * 
     * Generic approach: 
     * 
     * * Set the frame duration to a value that represents
     *   the number of frames per second you want to achieve. 
     * * Set all of the LED colors for one frame.
     * * Wait for the :func:`FrameRendered` callback.
     * * Set all of the LED colors for next frame.
     * * Wait for the :func:`FrameRendered` callback.
     * * and so on.
     * 
     * This approach ensures that you can change the LED colors with
     * a fixed frame rate.
     * 
     * The actual number of controllable LEDs depends on the number of free
     * Bricklet ports. See :ref:`here <led_strip_bricklet_ram_constraints>` for more
     * information. A call of :func:`SetRGBValues` with index + length above the
     * bounds is ignored completely.
     * 
     */
    public short[] getG(){
        return g;
    }

    public void setG(short[] g){
        this.g = g;
    }

    /**
     * 
     * Sets the *rgb* values for the LEDs with the given *length* starting 
     * from *index*.
     * 
     * The maximum length is 16, the index goes from 0 to 319 and the rgb values
     * have 8 bits each.
     * 
     * Example: If you set
     * 
     * * index to 5,
     * * length to 3,
     * * r to |r_values|,
     * * g to |g_values| and
     * * b to |b_values|
     * 
     * the LED with index 5 will be red, 6 will be green and 7 will be blue.
     * 
     * .. note:: Depending on the LED circuitry colors can be permuted.
     * 
     * The colors will be transfered to actual LEDs when the next
     * frame duration ends, see :func:`SetFrameDuration`.
     * 
     * Generic approach: 
     * 
     * * Set the frame duration to a value that represents
     *   the number of frames per second you want to achieve. 
     * * Set all of the LED colors for one frame.
     * * Wait for the :func:`FrameRendered` callback.
     * * Set all of the LED colors for next frame.
     * * Wait for the :func:`FrameRendered` callback.
     * * and so on.
     * 
     * This approach ensures that you can change the LED colors with
     * a fixed frame rate.
     * 
     * The actual number of controllable LEDs depends on the number of free
     * Bricklet ports. See :ref:`here <led_strip_bricklet_ram_constraints>` for more
     * information. A call of :func:`SetRGBValues` with index + length above the
     * bounds is ignored completely.
     * 
     */
    public short[] getB(){
        return b;
    }

    public void setB(short[] b){
        this.b = b;
    }

    /**
     * 
     * Returns the rgb with the given *length* starting from the
     * given *index*.
     * 
     * The values are the last values that were set by :func:`SetRGBValues`.
     * 
     */
    public Integer getIndex2(){
        return index2;
    }

    public void setIndex2(Integer index2){
        this.index2 = index2;
    }

    /**
     * 
     * Returns the rgb with the given *length* starting from the
     * given *index*.
     * 
     * The values are the last values that were set by :func:`SetRGBValues`.
     * 
     */
    public Short getLength2(){
        return length2;
    }

    public void setLength2(Short length2){
        this.length2 = length2;
    }

    /**
     * 
     * Sets the frame duration in ms.
     * 
     * Example: If you want to achieve 20 frames per second, you should
     * set the frame duration to 50ms (50ms * 20 = 1 second). 
     * 
     * For an explanation of the general approach see :func:`SetRGBValues`.
     * 
     * Default value: 100ms (10 frames per second).
     * 
     */
    public Integer getDuration(){
        return duration;
    }

    public void setDuration(Integer duration){
        this.duration = duration;
    }

    /**
     * 
     * Sets the frequency of the clock in Hz. The range is 10000Hz (10kHz) up to
     * 2000000Hz (2MHz).
     * 
     * The Bricklet will choose the nearest achievable frequency, which may
     * be off by a few Hz. You can get the exact frequency that is used by
     * calling :func:`GetClockFrequency`.
     * 
     * If you have problems with flickering LEDs, they may be bits flipping. You
     * can fix this by either making the connection between the LEDs and the
     * Bricklet shorter or by reducing the frequency.
     * 
     * With a decreasing frequency your maximum frames per second will decrease
     * too.
     * 
     * The default value is 1.66MHz.
     * 
     * .. note::
     *  The frequency in firmware version 2.0.0 is fixed at 2MHz.
     * 
     */
    public Long getFrequency(){
        return frequency;
    }

    public void setFrequency(Long frequency){
        this.frequency = frequency;
    }

    /**
     * 
     * Sets the type of the led driver chip. We currently support
     * the chips
     * 
     * * WS2801 (``chip`` = 2801),
     * * WS2811 (``chip`` = 2811) and
     * * WS2812 (``chip`` = 2812).
     * 
     * The WS2812 is sometimes also called "NeoPixel", a name coined by
     * Adafruit.
     * 
     * The default value is WS2801 (``chip`` = 2801).
     * 
     */
    public Integer getChip(){
        return chip;
    }

    public void setChip(Integer chip){
        this.chip = chip;
    }



}
