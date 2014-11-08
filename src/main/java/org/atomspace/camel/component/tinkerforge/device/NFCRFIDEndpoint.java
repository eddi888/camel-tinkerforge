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

import com.tinkerforge.BrickletNFCRFID;

public class NFCRFIDEndpoint extends TinkerforgeEndpoint<NFCRFIDConsumer, NFCRFIDProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(NFCRFIDEndpoint.class);
    
    private Short tagType;
    private Integer page;
    private Short keyNumber;
    private short[] key;
    private Integer page2;
    private short[] data;
    private Integer page3;

        
    public NFCRFIDEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new NFCRFIDProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new NFCRFIDConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletNFCRFID device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletNFCRFID device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "requestTagId":
                device.requestTagID(
                        getValue(short.class, "tagType", m, getTagType())
                    );
                break;

            case "getTagId":
                response = device.getTagID();
                break;

            case "getState":
                response = device.getState();
                break;

            case "authenticateMifareClassicPage":
                device.authenticateMifareClassicPage(
                        getValue(int.class, "page", m, getPage()),
                        getValue(short.class, "keyNumber", m, getKeyNumber()),
                        getValue(short[].class, "key", m, getKey())
                    );
                break;

            case "writePage":
                device.writePage(
                        getValue(int.class, "page2", m, getPage2()),
                        getValue(short[].class, "data", m, getData())
                    );
                break;

            case "requestPage":
                device.requestPage(
                        getValue(int.class, "page3", m, getPage3())
                    );
                break;

            case "getPage":
                response = device.getPage();
                break;

            case "getIdentity":
                response = device.getIdentity();
                break;


            default: throw new Exception("unknown function '"+function+"'");
            
        }
        
        return response;
    }
    
    
    public Short getTagType(){
        return tagType;
    }

    public void setTagType(Short tagType){
        this.tagType = tagType;
    }

    public Integer getPage(){
        return page;
    }

    public void setPage(Integer page){
        this.page = page;
    }

    public Short getKeyNumber(){
        return keyNumber;
    }

    public void setKeyNumber(Short keyNumber){
        this.keyNumber = keyNumber;
    }

    public short[] getKey(){
        return key;
    }

    public void setKey(short[] key){
        this.key = key;
    }

    public Integer getPage2(){
        return page2;
    }

    public void setPage2(Integer page2){
        this.page2 = page2;
    }

    public short[] getData(){
        return data;
    }

    public void setData(short[] data){
        this.data = data;
    }

    public Integer getPage3(){
        return page3;
    }

    public void setPage3(Integer page3){
        this.page3 = page3;
    }



}
