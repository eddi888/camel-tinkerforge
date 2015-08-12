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

import com.tinkerforge.BrickletAccelerometer;

public class AccelerometerEndpoint extends TinkerforgeEndpoint<AccelerometerConsumer, AccelerometerProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(AccelerometerEndpoint.class);
    
    private Long period;
    private Character option;
    private Short minX;
    private Short maxX;
    private Short minY;
    private Short maxY;
    private Short minZ;
    private Short maxZ;
    private Long debounce;
    private Short dataRate;
    private Short fullScale;
    private Short filterBandwidth;

        
    public AccelerometerEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new AccelerometerProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new AccelerometerConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletAccelerometer device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletAccelerometer device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "getAcceleration":
                response = device.getAcceleration();
                break;

            case "setAccelerationCallbackPeriod":
                device.setAccelerationCallbackPeriod(
                        getValue(long.class, "period", m, getPeriod())
                    );
                break;

            case "getAccelerationCallbackPeriod":
                response = device.getAccelerationCallbackPeriod();
                break;

            case "setAccelerationCallbackThreshold":
                device.setAccelerationCallbackThreshold(
                        getValue(char.class, "option", m, getOption()),
                        getValue(short.class, "minX", m, getMinX()),
                        getValue(short.class, "maxX", m, getMaxX()),
                        getValue(short.class, "minY", m, getMinY()),
                        getValue(short.class, "maxY", m, getMaxY()),
                        getValue(short.class, "minZ", m, getMinZ()),
                        getValue(short.class, "maxZ", m, getMaxZ())
                    );
                break;

            case "getAccelerationCallbackThreshold":
                response = device.getAccelerationCallbackThreshold();
                break;

            case "setDebouncePeriod":
                device.setDebouncePeriod(
                        getValue(long.class, "debounce", m, getDebounce())
                    );
                break;

            case "getDebouncePeriod":
                response = device.getDebouncePeriod();
                break;

            case "getTemperature":
                response = device.getTemperature();
                break;

            case "setConfiguration":
                device.setConfiguration(
                        getValue(short.class, "dataRate", m, getDataRate()),
                        getValue(short.class, "fullScale", m, getFullScale()),
                        getValue(short.class, "filterBandwidth", m, getFilterBandwidth())
                    );
                break;

            case "getConfiguration":
                response = device.getConfiguration();
                break;

            case "ledOn":
                device.ledOn();
                break;

            case "ledOff":
                device.ledOff();
                break;

            case "isLedOn":
                response = device.isLEDOn();
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

    public Short getMinX(){
        return minX;
    }

    public void setMinX(Short minX){
        this.minX = minX;
    }

    public Short getMaxX(){
        return maxX;
    }

    public void setMaxX(Short maxX){
        this.maxX = maxX;
    }

    public Short getMinY(){
        return minY;
    }

    public void setMinY(Short minY){
        this.minY = minY;
    }

    public Short getMaxY(){
        return maxY;
    }

    public void setMaxY(Short maxY){
        this.maxY = maxY;
    }

    public Short getMinZ(){
        return minZ;
    }

    public void setMinZ(Short minZ){
        this.minZ = minZ;
    }

    public Short getMaxZ(){
        return maxZ;
    }

    public void setMaxZ(Short maxZ){
        this.maxZ = maxZ;
    }

    public Long getDebounce(){
        return debounce;
    }

    public void setDebounce(Long debounce){
        this.debounce = debounce;
    }

    public Short getDataRate(){
        return dataRate;
    }

    public void setDataRate(Short dataRate){
        this.dataRate = dataRate;
    }

    public Short getFullScale(){
        return fullScale;
    }

    public void setFullScale(Short fullScale){
        this.fullScale = fullScale;
    }

    public Short getFilterBandwidth(){
        return filterBandwidth;
    }

    public void setFilterBandwidth(Short filterBandwidth){
        this.filterBandwidth = filterBandwidth;
    }



}
