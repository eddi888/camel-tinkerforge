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
import com.tinkerforge.BrickletNFCRFID;

public class NFCRFIDProducer extends TinkerforgeProducer<NFCRFIDEndpoint, BrickletNFCRFID> {

    private static final Logger LOG = LoggerFactory.getLogger(NFCRFIDProducer.class);
    
    public NFCRFIDProducer(NFCRFIDEndpoint endpoint) throws UnknownHostException, AlreadyConnectedException, IOException {
        super(endpoint);
        device = new BrickletNFCRFID(endpoint.getUid(),endpoint.getSharedConnection().getConnection());
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        LOG.trace("process(Exchange exchange='"+exchange+"')");

        // TODO IMPL
        
    }

}