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

import com.tinkerforge.BrickletRemoteSwitch;

public class RemoteSwitchEndpoint extends TinkerforgeEndpoint<RemoteSwitchConsumer, RemoteSwitchProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(RemoteSwitchEndpoint.class);
    
    private Short houseCode;
    private Short receiverCode;
    private Short switchTo;
    private Short repeats;
    private Long address;
    private Short unit;
    private Short dimValue;
    private Character systemCode;
    private Short deviceCode;

        
    public RemoteSwitchEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new RemoteSwitchProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new RemoteSwitchConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletRemoteSwitch device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletRemoteSwitch device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "switchSocket":
                device.switchSocket(
                        (short) getValue("houseCode", m, e),
                        (short) getValue("receiverCode", m, e),
                        (short) getValue("switchTo", m, e)
                    );
                break;

            case "getSwitchingState":
                response = device.getSwitchingState();
                break;

            case "setRepeats":
                device.setRepeats(
                        (short) getValue("repeats", m, e)
                    );
                break;

            case "getRepeats":
                response = device.getRepeats();
                break;

            case "switchSocketA":
                device.switchSocketA(
                        (short) getValue("houseCode", m, e),
                        (short) getValue("receiverCode", m, e),
                        (short) getValue("switchTo", m, e)
                    );
                break;

            case "switchSocketB":
                device.switchSocketB(
                        (long) getValue("address", m, e),
                        (short) getValue("unit", m, e),
                        (short) getValue("switchTo", m, e)
                    );
                break;

            case "dimSocketB":
                device.dimSocketB(
                        (long) getValue("address", m, e),
                        (short) getValue("unit", m, e),
                        (short) getValue("dimValue", m, e)
                    );
                break;

            case "switchSocketC":
                device.switchSocketC(
                        (char) getValue("systemCode", m, e),
                        (short) getValue("deviceCode", m, e),
                        (short) getValue("switchTo", m, e)
                    );
                break;

            case "getIdentity":
                response = device.getIdentity();
                break;


            default: throw new Exception("unknown function '"+function+"'");
            
        }
        
        return response;
    }
    
    
    public Short getHouseCode(){
        return houseCode;
    }

    public void setHouseCode(Short houseCode){
        this.houseCode = houseCode;
    }

    public Short getReceiverCode(){
        return receiverCode;
    }

    public void setReceiverCode(Short receiverCode){
        this.receiverCode = receiverCode;
    }

    public Short getSwitchTo(){
        return switchTo;
    }

    public void setSwitchTo(Short switchTo){
        this.switchTo = switchTo;
    }

    public Short getRepeats(){
        return repeats;
    }

    public void setRepeats(Short repeats){
        this.repeats = repeats;
    }

    public Long getAddress(){
        return address;
    }

    public void setAddress(Long address){
        this.address = address;
    }

    public Short getUnit(){
        return unit;
    }

    public void setUnit(Short unit){
        this.unit = unit;
    }

    public Short getDimValue(){
        return dimValue;
    }

    public void setDimValue(Short dimValue){
        this.dimValue = dimValue;
    }

    public Character getSystemCode(){
        return systemCode;
    }

    public void setSystemCode(Character systemCode){
        this.systemCode = systemCode;
    }

    public Short getDeviceCode(){
        return deviceCode;
    }

    public void setDeviceCode(Short deviceCode){
        this.deviceCode = deviceCode;
    }



}
