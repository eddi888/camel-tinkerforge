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

import org.apache.camel.Exchange;
import org.atomspace.camel.component.tinkerforge.TinkerforgeProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tinkerforge.Device;
import com.tinkerforge.IPConnection;
import com.tinkerforge.NotConnectedException;
import com.tinkerforge.TimeoutException;

public class MockProducer extends TinkerforgeProducer<MockEndpoint, Device> {

    private static final Logger LOG = LoggerFactory.getLogger(MockProducer.class);
    
    public MockProducer(final MockEndpoint endpoint) {
        super(endpoint);
        device = new Device(endpoint.getUid(), new IPConnection()) {
            
            @Override
            public Identity getIdentity() throws TimeoutException, NotConnectedException {
                LOG.trace("getIdentity()");
                
                Identity identity = new Identity();
                identity.uid = endpoint.getUid();
                identity.connectedUid = "XYZ";
                identity.position = 'a';
                identity.hardwareVersion[0] = 1;
                identity.hardwareVersion[1] = 2;
                identity.hardwareVersion[2] = 3;
                identity.firmwareVersion[0] = 4;
                identity.firmwareVersion[1] = 5;
                identity.firmwareVersion[2] = 6;
                identity.deviceIdentifier = 233;
                return identity;
            }
            
        };
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        LOG.trace("process(Exchange exchange='"+exchange+"')");
        
    }
    
    
    
}
