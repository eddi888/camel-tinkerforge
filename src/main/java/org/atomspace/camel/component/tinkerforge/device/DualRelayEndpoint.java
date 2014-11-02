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
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.atomspace.camel.component.tinkerforge.TinkerforgeComponent;
import org.atomspace.camel.component.tinkerforge.TinkerforgeEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DualRelayEndpoint extends TinkerforgeEndpoint<DualRelayConsumer, DualRelayProducer> {
    
    private static final Logger LOG = LoggerFactory.getLogger(DualRelayProducer.class);
    
    private Short houseCode;
    private Short relay;
    private Boolean state;
    private Long time;
    private Boolean relay1;
    private Boolean relay2;
        
    public DualRelayEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new DualRelayProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new DualRelayConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public Short getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(Short houseCode) {
        this.houseCode = houseCode;
    }

    public Short getRelay() {
        return relay;
    }

    public void setRelay(Short relay) {
        this.relay = relay;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Boolean getRelay1() {
        return relay1;
    }

    public void setRelay1(Boolean relay1) {
        this.relay1 = relay1;
    }

    public Boolean getRelay2() {
        return relay2;
    }

    public void setRelay2(Boolean relay2) {
        this.relay2 = relay2;
    }
    
    

    
}
