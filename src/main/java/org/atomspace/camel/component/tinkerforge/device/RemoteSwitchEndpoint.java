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
    private Short houseCode2;
    private Short receiverCode2;
    private Short switchTo2;
    private Long address;
    private Short unit;
    private Short switchTo3;
    private Long address2;
    private Short unit2;
    private Short dimValue;
    private Character systemCode;
    private Short deviceCode;
    private Short switchTo4;

        
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
                        getValue(short.class, "houseCode", m, getHouseCode()),
                        getValue(short.class, "receiverCode", m, getReceiverCode()),
                        getValue(short.class, "switchTo", m, getSwitchTo())
                    );
                break;

            case "getSwitchingState":
                response = device.getSwitchingState();
                break;

            case "setRepeats":
                device.setRepeats(
                        getValue(short.class, "repeats", m, getRepeats())
                    );
                break;

            case "getRepeats":
                response = device.getRepeats();
                break;

            case "switchSocketA":
                device.switchSocketA(
                        getValue(short.class, "houseCode2", m, getHouseCode2()),
                        getValue(short.class, "receiverCode2", m, getReceiverCode2()),
                        getValue(short.class, "switchTo2", m, getSwitchTo2())
                    );
                break;

            case "switchSocketB":
                device.switchSocketB(
                        getValue(long.class, "address", m, getAddress()),
                        getValue(short.class, "unit", m, getUnit()),
                        getValue(short.class, "switchTo3", m, getSwitchTo3())
                    );
                break;

            case "dimSocketB":
                device.dimSocketB(
                        getValue(long.class, "address2", m, getAddress2()),
                        getValue(short.class, "unit2", m, getUnit2()),
                        getValue(short.class, "dimValue", m, getDimValue())
                    );
                break;

            case "switchSocketC":
                device.switchSocketC(
                        getValue(char.class, "systemCode", m, getSystemCode()),
                        getValue(short.class, "deviceCode", m, getDeviceCode()),
                        getValue(short.class, "switchTo4", m, getSwitchTo4())
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

    public Short getHouseCode2(){
        return houseCode2;
    }

    public void setHouseCode2(Short houseCode2){
        this.houseCode2 = houseCode2;
    }

    public Short getReceiverCode2(){
        return receiverCode2;
    }

    public void setReceiverCode2(Short receiverCode2){
        this.receiverCode2 = receiverCode2;
    }

    public Short getSwitchTo2(){
        return switchTo2;
    }

    public void setSwitchTo2(Short switchTo2){
        this.switchTo2 = switchTo2;
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

    public Short getSwitchTo3(){
        return switchTo3;
    }

    public void setSwitchTo3(Short switchTo3){
        this.switchTo3 = switchTo3;
    }

    public Long getAddress2(){
        return address2;
    }

    public void setAddress2(Long address2){
        this.address2 = address2;
    }

    public Short getUnit2(){
        return unit2;
    }

    public void setUnit2(Short unit2){
        this.unit2 = unit2;
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

    public Short getSwitchTo4(){
        return switchTo4;
    }

    public void setSwitchTo4(Short switchTo4){
        this.switchTo4 = switchTo4;
    }



}
