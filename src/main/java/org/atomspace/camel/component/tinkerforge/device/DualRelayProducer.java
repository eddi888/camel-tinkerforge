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

import java.io.IOException;
import java.net.UnknownHostException;

import org.apache.camel.Exchange;
import org.atomspace.camel.component.tinkerforge.TinkerforgeProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tinkerforge.AlreadyConnectedException;
import com.tinkerforge.BrickletDualRelay;

public class DualRelayProducer extends TinkerforgeProducer<DualRelayEndpoint, BrickletDualRelay> {

    private static final Logger LOG = LoggerFactory.getLogger(DualRelayProducer.class);
    
    public DualRelayProducer(DualRelayEndpoint endpoint) throws UnknownHostException, AlreadyConnectedException, IOException {
        super(endpoint);
        device = new BrickletDualRelay(endpoint.getUid(), endpoint.getSharedConnection().getConnection());
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        LOG.trace("process(Exchange exchange='"+exchange+"')");
        

        // TODO IMPL BETTER!!!
        if(endpoint.getMonoflop()!=null){
            
            //DEFAULT
            short relay=1;
            boolean state=true;
            long time=10000;
            
            String[] keyValues = endpoint.getMonoflop().split(",");
            for (String keyValue : keyValues) {
                keyValue = keyValue.replaceAll("\\{", "");
                keyValue = keyValue.replaceAll("\\}", "");
                String key = keyValue.split(":")[0];
                String value = keyValue.split(":")[1];
                if(key.equals("relay")){
                    relay=Short.valueOf(value);
                }else if(key.equals("state")){
                    state=Boolean.valueOf(value);
                }else if(key.equals("time")){
                    time=Long.valueOf(value);
                }else {
                    LOG.error("ERROR PARSING key:"+key);
                }
            }
            LOG.trace("device.setMonoflop(relay='"+relay+"', state='"+state+"', time='"+time+"')");
            device.setMonoflop(relay, state, time);
            
            
        }
        
        // TODO IMPL BETTER!!!
        if(endpoint.getSelectedState()!=null){
            //DEFAULT
            short relay=1;
            boolean state=true;
            
            String[] keyValues = endpoint.getSelectedState().split(",");
            for (String keyValue : keyValues) {
                keyValue = keyValue.replaceAll("\\{", "");
                keyValue = keyValue.replaceAll("\\}", "");
                String key = keyValue.split(":")[0];
                String value = keyValue.split(":")[1];
                if(key.equals("relay")){
                    relay=Short.valueOf(value);
                }else if(key.equals("state")){
                    state=Boolean.valueOf(value);
                }else {
                    LOG.error("ERROR PARSING key:"+key);
                }
            }
            LOG.trace("device.setSelectedState(relay='"+relay+"', state='"+state+"')");
            device.setSelectedState(relay, state);
        }
        
        

        
        // TODO IMPL
        //device.setState(true, true);

                
        // TODO IMPL
        //device.getState();
        
        // TODO IMPL
        //device.getMonoflop(relay);
        
    }

}
