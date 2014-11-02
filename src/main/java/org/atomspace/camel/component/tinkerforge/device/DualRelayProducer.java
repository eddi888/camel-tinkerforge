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

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
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
        Message m = exchange.getIn();
        Endpoint e = endpoint;
        Object body = null;
        
        String function = getValue("function", m, e).toString();

        switch (function) {
            case "getMonoflop":
                body = device.getMonoflop(
                        (short) getValue("houseCode", m, e)
                    );
                break;
                
            case "getState":
                body = device.getState();    
                break;
                
            case "setMonoflop":
                device.setMonoflop(
                        (short) getValue("relay", m, e), 
                        (boolean) getValue("state", m, e), 
                        (long) getValue("time", m, e)
                    );    
                break;
                
            case "setSelectedState":
                device.setSelectedState(
                        (short) getValue("relay", m, e), 
                        (boolean) getValue("state", m, e)
                    );
                break;
                
            case "setState":
                device.setState(
                        (boolean) getValue("relay1", m, e), 
                        (boolean) getValue("relay2", m, e)
                    );
                break;
                
            default: throw new Exception("unknown Function '"+function+"'");
                
        }
    
        exchange.getOut().setBody(body);
    
    }

}
