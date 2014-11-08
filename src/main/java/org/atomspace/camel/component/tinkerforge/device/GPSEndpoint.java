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
    private Long period2;
    private Long period3;
    private Long period4;
    private Long period5;

        
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
                        getValue(short.class, "restartType", m, getRestartType())
                    );
                break;

            case "setCoordinatesCallbackPeriod":
                device.setCoordinatesCallbackPeriod(
                        getValue(long.class, "period", m, getPeriod())
                    );
                break;

            case "getCoordinatesCallbackPeriod":
                response = device.getCoordinatesCallbackPeriod();
                break;

            case "setStatusCallbackPeriod":
                device.setStatusCallbackPeriod(
                        getValue(long.class, "period2", m, getPeriod2())
                    );
                break;

            case "getStatusCallbackPeriod":
                response = device.getStatusCallbackPeriod();
                break;

            case "setAltitudeCallbackPeriod":
                device.setAltitudeCallbackPeriod(
                        getValue(long.class, "period3", m, getPeriod3())
                    );
                break;

            case "getAltitudeCallbackPeriod":
                response = device.getAltitudeCallbackPeriod();
                break;

            case "setMotionCallbackPeriod":
                device.setMotionCallbackPeriod(
                        getValue(long.class, "period4", m, getPeriod4())
                    );
                break;

            case "getMotionCallbackPeriod":
                response = device.getMotionCallbackPeriod();
                break;

            case "setDateTimeCallbackPeriod":
                device.setDateTimeCallbackPeriod(
                        getValue(long.class, "period5", m, getPeriod5())
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

    public Long getPeriod2(){
        return period2;
    }

    public void setPeriod2(Long period2){
        this.period2 = period2;
    }

    public Long getPeriod3(){
        return period3;
    }

    public void setPeriod3(Long period3){
        this.period3 = period3;
    }

    public Long getPeriod4(){
        return period4;
    }

    public void setPeriod4(Long period4){
        this.period4 = period4;
    }

    public Long getPeriod5(){
        return period5;
    }

    public void setPeriod5(Long period5){
        this.period5 = period5;
    }



}
