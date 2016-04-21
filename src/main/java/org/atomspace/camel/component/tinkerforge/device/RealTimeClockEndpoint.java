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

import com.tinkerforge.BrickletRealTimeClock;

/**
 * Battery-backed real-time clock
 */
@UriEndpoint(scheme = "tinkerforgegen", syntax = "tinkerforgegen:[host[:port]/]realtimeclock", consumerClass = RealTimeClockConsumer.class, label = "iot", title = "Tinkerforge")
public class RealTimeClockEndpoint extends TinkerforgeEndpoint<RealTimeClockConsumer, RealTimeClockProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(RealTimeClockEndpoint.class);

    public static final String YEAR="year";
    public static final String MONTH="month";
    public static final String DAY="day";
    public static final String HOUR="hour";
    public static final String MINUTE="minute";
    public static final String SECOND="second";
    public static final String CENTISECOND="centisecond";
    public static final String WEEKDAY="weekday";
    public static final String OFFSET="offset";

    
    private Integer year;
    private Short month;
    private Short day;
    private Short hour;
    private Short minute;
    private Short second;
    private Short centisecond;
    private Short weekday;
    private Byte offset;

        
    public RealTimeClockEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new RealTimeClockProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new RealTimeClockConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletRealTimeClock device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletRealTimeClock device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "setDateTime":
                device.setDateTime(
                        getValue(int.class, "year", m, getYear()),
                        getValue(short.class, "month", m, getMonth()),
                        getValue(short.class, "day", m, getDay()),
                        getValue(short.class, "hour", m, getHour()),
                        getValue(short.class, "minute", m, getMinute()),
                        getValue(short.class, "second", m, getSecond()),
                        getValue(short.class, "centisecond", m, getCentisecond()),
                        getValue(short.class, "weekday", m, getWeekday())
                    );
                break;

            case "getDateTime":
                response = device.getDateTime();
                break;

            case "getTimestamp":
                response = device.getTimestamp();
                break;

            case "setOffset":
                device.setOffset(
                        getValue(byte.class, "offset", m, getOffset())
                    );
                break;

            case "getOffset":
                response = device.getOffset();
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
     * Sets the current date (including weekday) and the current time with hundredths
     * of a second resolution.
     * 
     * Possible value ranges:
     * 
     * * Year: 2000 to 2099
     * * Month: 1 to 12 (January to December)
     * * Day: 1 to 31
     * * Hour: 0 to 23
     * * Minute: 0 to 59
     * * Second: 0 to 59
     * * Centisecond: 0 to 99
     * * Weekday: 1 to 7 (Monday to Sunday)
     * 
     * If the backup battery is installed then the real-time clock keeps date and
     * time even if the Bricklet is not powered by a Brick.
     * 
     * The real-time clock handles leap year and inserts the 29th of February
     * accordingly. But leap seconds, time zones and daylight saving time are not
     * handled.
     * 
     */
    public Integer getYear(){
        return year;
    }

    public void setYear(Integer year){
        this.year = year;
    }

    /**
     * 
     * Sets the current date (including weekday) and the current time with hundredths
     * of a second resolution.
     * 
     * Possible value ranges:
     * 
     * * Year: 2000 to 2099
     * * Month: 1 to 12 (January to December)
     * * Day: 1 to 31
     * * Hour: 0 to 23
     * * Minute: 0 to 59
     * * Second: 0 to 59
     * * Centisecond: 0 to 99
     * * Weekday: 1 to 7 (Monday to Sunday)
     * 
     * If the backup battery is installed then the real-time clock keeps date and
     * time even if the Bricklet is not powered by a Brick.
     * 
     * The real-time clock handles leap year and inserts the 29th of February
     * accordingly. But leap seconds, time zones and daylight saving time are not
     * handled.
     * 
     */
    public Short getMonth(){
        return month;
    }

    public void setMonth(Short month){
        this.month = month;
    }

    /**
     * 
     * Sets the current date (including weekday) and the current time with hundredths
     * of a second resolution.
     * 
     * Possible value ranges:
     * 
     * * Year: 2000 to 2099
     * * Month: 1 to 12 (January to December)
     * * Day: 1 to 31
     * * Hour: 0 to 23
     * * Minute: 0 to 59
     * * Second: 0 to 59
     * * Centisecond: 0 to 99
     * * Weekday: 1 to 7 (Monday to Sunday)
     * 
     * If the backup battery is installed then the real-time clock keeps date and
     * time even if the Bricklet is not powered by a Brick.
     * 
     * The real-time clock handles leap year and inserts the 29th of February
     * accordingly. But leap seconds, time zones and daylight saving time are not
     * handled.
     * 
     */
    public Short getDay(){
        return day;
    }

    public void setDay(Short day){
        this.day = day;
    }

    /**
     * 
     * Sets the current date (including weekday) and the current time with hundredths
     * of a second resolution.
     * 
     * Possible value ranges:
     * 
     * * Year: 2000 to 2099
     * * Month: 1 to 12 (January to December)
     * * Day: 1 to 31
     * * Hour: 0 to 23
     * * Minute: 0 to 59
     * * Second: 0 to 59
     * * Centisecond: 0 to 99
     * * Weekday: 1 to 7 (Monday to Sunday)
     * 
     * If the backup battery is installed then the real-time clock keeps date and
     * time even if the Bricklet is not powered by a Brick.
     * 
     * The real-time clock handles leap year and inserts the 29th of February
     * accordingly. But leap seconds, time zones and daylight saving time are not
     * handled.
     * 
     */
    public Short getHour(){
        return hour;
    }

    public void setHour(Short hour){
        this.hour = hour;
    }

    /**
     * 
     * Sets the current date (including weekday) and the current time with hundredths
     * of a second resolution.
     * 
     * Possible value ranges:
     * 
     * * Year: 2000 to 2099
     * * Month: 1 to 12 (January to December)
     * * Day: 1 to 31
     * * Hour: 0 to 23
     * * Minute: 0 to 59
     * * Second: 0 to 59
     * * Centisecond: 0 to 99
     * * Weekday: 1 to 7 (Monday to Sunday)
     * 
     * If the backup battery is installed then the real-time clock keeps date and
     * time even if the Bricklet is not powered by a Brick.
     * 
     * The real-time clock handles leap year and inserts the 29th of February
     * accordingly. But leap seconds, time zones and daylight saving time are not
     * handled.
     * 
     */
    public Short getMinute(){
        return minute;
    }

    public void setMinute(Short minute){
        this.minute = minute;
    }

    /**
     * 
     * Sets the current date (including weekday) and the current time with hundredths
     * of a second resolution.
     * 
     * Possible value ranges:
     * 
     * * Year: 2000 to 2099
     * * Month: 1 to 12 (January to December)
     * * Day: 1 to 31
     * * Hour: 0 to 23
     * * Minute: 0 to 59
     * * Second: 0 to 59
     * * Centisecond: 0 to 99
     * * Weekday: 1 to 7 (Monday to Sunday)
     * 
     * If the backup battery is installed then the real-time clock keeps date and
     * time even if the Bricklet is not powered by a Brick.
     * 
     * The real-time clock handles leap year and inserts the 29th of February
     * accordingly. But leap seconds, time zones and daylight saving time are not
     * handled.
     * 
     */
    public Short getSecond(){
        return second;
    }

    public void setSecond(Short second){
        this.second = second;
    }

    /**
     * 
     * Sets the current date (including weekday) and the current time with hundredths
     * of a second resolution.
     * 
     * Possible value ranges:
     * 
     * * Year: 2000 to 2099
     * * Month: 1 to 12 (January to December)
     * * Day: 1 to 31
     * * Hour: 0 to 23
     * * Minute: 0 to 59
     * * Second: 0 to 59
     * * Centisecond: 0 to 99
     * * Weekday: 1 to 7 (Monday to Sunday)
     * 
     * If the backup battery is installed then the real-time clock keeps date and
     * time even if the Bricklet is not powered by a Brick.
     * 
     * The real-time clock handles leap year and inserts the 29th of February
     * accordingly. But leap seconds, time zones and daylight saving time are not
     * handled.
     * 
     */
    public Short getCentisecond(){
        return centisecond;
    }

    public void setCentisecond(Short centisecond){
        this.centisecond = centisecond;
    }

    /**
     * 
     * Sets the current date (including weekday) and the current time with hundredths
     * of a second resolution.
     * 
     * Possible value ranges:
     * 
     * * Year: 2000 to 2099
     * * Month: 1 to 12 (January to December)
     * * Day: 1 to 31
     * * Hour: 0 to 23
     * * Minute: 0 to 59
     * * Second: 0 to 59
     * * Centisecond: 0 to 99
     * * Weekday: 1 to 7 (Monday to Sunday)
     * 
     * If the backup battery is installed then the real-time clock keeps date and
     * time even if the Bricklet is not powered by a Brick.
     * 
     * The real-time clock handles leap year and inserts the 29th of February
     * accordingly. But leap seconds, time zones and daylight saving time are not
     * handled.
     * 
     */
    public Short getWeekday(){
        return weekday;
    }

    public void setWeekday(Short weekday){
        this.weekday = weekday;
    }

    /**
     * 
     * Sets the offset the real-time clock should compensate for in 2.17 ppm steps
     * between -277.76 ppm (-128) and +275.59 ppm (127).
     * 
     * The real-time clock time can deviate from the actual time due to the frequency
     * deviation of its 32.768 kHz crystal. Even without compensation (factory
     * default) the resulting time deviation should be at most ±20 ppm (±52.6
     * seconds per month).
     * 
     * This deviation can be calculated by comparing the same duration measured by the
     * real-time clock (``rtc_duration``) an accurate reference clock
     * (``ref_duration``).
     * 
     * For best results the configured offset should be set to 0 ppm first and then a
     * duration of at least 6 hours should be measured.
     * 
     * The new offset (``new_offset``) can be calculated from the currently configured
     * offset (``current_offset``) and the measured durations as follow::
     * 
     *   new_offset = current_offset - round(1000000 * (rtc_duration - ref_duration) / rtc_duration / 2.17)
     * 
     * If you want to calculate the offset, then we recommend using the calibration
     * dialog in Brick Viewer, instead of doing it manually.
     * 
     * The offset is saved in the EEPROM of the Bricklet and only needs to be
     * configured once.
     * 
     */
    public Byte getOffset(){
        return offset;
    }

    public void setOffset(Byte offset){
        this.offset = offset;
    }



}
