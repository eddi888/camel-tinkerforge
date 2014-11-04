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

import com.tinkerforge.BrickletBarometer;

public class BarometerEndpoint extends TinkerforgeEndpoint<BarometerConsumer, BarometerProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(BarometerEndpoint.class);
    
    private Long period;
    private Character option;
    private Integer min;
    private Integer max;
    private Long debounce;
    private Integer airPressure;
    private Short movingAveragePressure;
    private Short averagePressure;
    private Short averageTemperature;

        
    public BarometerEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new BarometerProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new BarometerConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletBarometer device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletBarometer device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "getAirPressure":
                response = device.getAirPressure();
                break;

            case "getAltitude":
                response = device.getAltitude();
                break;

            case "setAirPressureCallbackPeriod":
                device.setAirPressureCallbackPeriod(
                        (long) getValue("period", m, e)
                    );
                break;

            case "getAirPressureCallbackPeriod":
                response = device.getAirPressureCallbackPeriod();
                break;

            case "setAltitudeCallbackPeriod":
                device.setAltitudeCallbackPeriod(
                        (long) getValue("period", m, e)
                    );
                break;

            case "getAltitudeCallbackPeriod":
                response = device.getAltitudeCallbackPeriod();
                break;

            case "setAirPressureCallbackThreshold":
                device.setAirPressureCallbackThreshold(
                        (char) getValue("option", m, e),
                        (int) getValue("min", m, e),
                        (int) getValue("max", m, e)
                    );
                break;

            case "getAirPressureCallbackThreshold":
                response = device.getAirPressureCallbackThreshold();
                break;

            case "setAltitudeCallbackThreshold":
                device.setAltitudeCallbackThreshold(
                        (char) getValue("option", m, e),
                        (int) getValue("min", m, e),
                        (int) getValue("max", m, e)
                    );
                break;

            case "getAltitudeCallbackThreshold":
                response = device.getAltitudeCallbackThreshold();
                break;

            case "setDebouncePeriod":
                device.setDebouncePeriod(
                        (long) getValue("debounce", m, e)
                    );
                break;

            case "getDebouncePeriod":
                response = device.getDebouncePeriod();
                break;

            case "setReferenceAirPressure":
                device.setReferenceAirPressure(
                        (int) getValue("airPressure", m, e)
                    );
                break;

            case "getChipTemperature":
                response = device.getChipTemperature();
                break;

            case "getReferenceAirPressure":
                response = device.getReferenceAirPressure();
                break;

            case "setAveraging":
                device.setAveraging(
                        (short) getValue("movingAveragePressure", m, e),
                        (short) getValue("averagePressure", m, e),
                        (short) getValue("averageTemperature", m, e)
                    );
                break;

            case "getAveraging":
                response = device.getAveraging();
                break;

            case "getIdentity":
                response = device.getIdentity();
                break;


            default: throw new Exception("unknown function '"+function+"'");
            
        }
        
        return response;
    }
    
    
    public Long getPeriod(){
        return period;
    }

    public void setPeriod(Long period){
        this.period = period;
    }

    public Character getOption(){
        return option;
    }

    public void setOption(Character option){
        this.option = option;
    }

    public Integer getMin(){
        return min;
    }

    public void setMin(Integer min){
        this.min = min;
    }

    public Integer getMax(){
        return max;
    }

    public void setMax(Integer max){
        this.max = max;
    }

    public Long getDebounce(){
        return debounce;
    }

    public void setDebounce(Long debounce){
        this.debounce = debounce;
    }

    public Integer getAirPressure(){
        return airPressure;
    }

    public void setAirPressure(Integer airPressure){
        this.airPressure = airPressure;
    }

    public Short getMovingAveragePressure(){
        return movingAveragePressure;
    }

    public void setMovingAveragePressure(Short movingAveragePressure){
        this.movingAveragePressure = movingAveragePressure;
    }

    public Short getAveragePressure(){
        return averagePressure;
    }

    public void setAveragePressure(Short averagePressure){
        this.averagePressure = averagePressure;
    }

    public Short getAverageTemperature(){
        return averageTemperature;
    }

    public void setAverageTemperature(Short averageTemperature){
        this.averageTemperature = averageTemperature;
    }



}
