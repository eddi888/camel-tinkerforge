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
package org.apache.camel.component.tinkerforge.device;

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.component.tinkerforge.TinkerforgeComponent;
import org.apache.camel.component.tinkerforge.TinkerforgeEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DualRelayEndpoint extends TinkerforgeEndpoint<DualRelayConsumer, DualRelayProducer> {
    
    private static final Logger LOG = LoggerFactory.getLogger(DualRelayProducer.class);
    
    private String state;
    private String selectedState;
    private String monoflop;
        
    public DualRelayEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        if(producer==null){
            producer = new DualRelayProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        if(consumer==null){
            consumer = new DualRelayConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        LOG.trace("setState(String state='"+state+"')");
        this.state = state;
    }

    public String getSelectedState() {
        return selectedState;
    }

    public void setSelectedState(String selectedState) {
        LOG.trace("setSelectedState(String selectedState='"+selectedState+"')");
        this.selectedState = selectedState;
    }

    public String getMonoflop() {
        return monoflop;
    }

    public void setMonoflop(String monoflop) {
        this.monoflop = monoflop;
    }

   



        
}
