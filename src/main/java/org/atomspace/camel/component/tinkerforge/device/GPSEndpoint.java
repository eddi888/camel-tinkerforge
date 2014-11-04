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

import com.tinkerforge.BrickletGPS;

public class GPSEndpoint extends TinkerforgeEndpoint<GPSConsumer, GPSProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(GPSEndpoint.class);
    
    private Short restartType;
    private Long period;

        
    public GPSEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new GPSProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new GPSConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletGPS device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletGPS device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "getCoordinates":
                response = device.getCoordinates();
                break;

            case "getStatus":
                response = device.getStatus();
                break;

            case "getAltitude":
                response = device.getAltitude();
                break;

            case "getMotion":
                response = device.getMotion();
                break;

            case "getDateTime":
                response = device.getDateTime();
                break;

            case "restart":
                device.restart(
                        (short) getValue("restartType", m, e)
                    );
                break;

            case "setCoordinatesCallbackPeriod":
                device.setCoordinatesCallbackPeriod(
                        (long) getValue("period", m, e)
                    );
                break;

            case "getCoordinatesCallbackPeriod":
                response = device.getCoordinatesCallbackPeriod();
                break;

            case "setStatusCallbackPeriod":
                device.setStatusCallbackPeriod(
                        (long) getValue("period", m, e)
                    );
                break;

            case "getStatusCallbackPeriod":
                response = device.getStatusCallbackPeriod();
                break;

            case "setAltitudeCallbackPeriod":
                device.setAltitudeCallbackPeriod(
                        (long) getValue("period", m, e)
                    );
                break;

            case "getAltitudeCallbackPeriod":
                response = device.getAltitudeCallbackPeriod();
                break;

            case "setMotionCallbackPeriod":
                device.setMotionCallbackPeriod(
                        (long) getValue("period", m, e)
                    );
                break;

            case "getMotionCallbackPeriod":
                response = device.getMotionCallbackPeriod();
                break;

            case "setDateTimeCallbackPeriod":
                device.setDateTimeCallbackPeriod(
                        (long) getValue("period", m, e)
                    );
                break;

            case "getDateTimeCallbackPeriod":
                response = device.getDateTimeCallbackPeriod();
                break;

            case "getIdentity":
                response = device.getIdentity();
                break;


            default: throw new Exception("unknown function '"+function+"'");
            
        }
        
        return response;
    }
    
    
    public Short getRestartType(){
        return restartType;
    }

    public void setRestartType(Short restartType){
        this.restartType = restartType;
    }

    public Long getPeriod(){
        return period;
    }

    public void setPeriod(Long period){
        this.period = period;
    }



}
