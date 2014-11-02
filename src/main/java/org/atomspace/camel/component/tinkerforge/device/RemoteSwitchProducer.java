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
import com.tinkerforge.BrickletRemoteSwitch;

public class RemoteSwitchProducer extends TinkerforgeProducer<RemoteSwitchEndpoint, BrickletRemoteSwitch> {

    private static final Logger LOG = LoggerFactory.getLogger(RemoteSwitchProducer.class);
    
    public RemoteSwitchProducer(RemoteSwitchEndpoint endpoint) throws UnknownHostException, AlreadyConnectedException, IOException {
        super(endpoint);
        device = new BrickletRemoteSwitch(endpoint.getUid(),endpoint.getSharedConnection().getConnection());
    }
    
    @Override
    public void process(Exchange exchange) throws Exception {
        LOG.trace("process(Exchange exchange='"+exchange+"')");
        Message m = exchange.getIn();
        Endpoint e = endpoint;
        Object body = null;
        
        String function = getValue("function", m, e).toString();
        
        switch (function) {
            case "switchSocket":
                device.switchSocket(
                        (short) getValue("houseCode", m, e), 
                        (short) getValue("receiverCode", m, e), 
                        (short) getValue("switchTo", m, e)
                    );
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
                        (short) getValue("address", m, e), 
                        (short) getValue("unit", m, e), 
                        (short) getValue("switchTo", m, e)
                    );    
                break;
                
            case "switchSocketC":
                device.switchSocketC(
                        (char) getValue("systemCode", m, e), 
                        (short) getValue("deviceCode", m, e), 
                        (short) getValue("switchTo", m, e)
                    );
                break;
                
            case "getRepeats":
                body = device.getRepeats();    
                break;
                
            case "setRepeats":
                device.setRepeats((short) getValue("repeats", m, e));    
                break;
                
            case "dimSocketB":
                device.setRepeats((short) getValue("repeats", m, e));
                device.switchSocketC(
                        (char) getValue("address", m, e), 
                        (short) getValue("unit", m, e), 
                        (short) getValue("dimValue", m, e)
                    );    
                break;
                
            default: throw new Exception("unknown Function '"+function+"'");
                
        }

        exchange.getOut().setBody(body);

    }

}