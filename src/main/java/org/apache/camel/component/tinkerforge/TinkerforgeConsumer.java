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
package org.apache.camel.component.tinkerforge;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.impl.DefaultConsumer;

import com.tinkerforge.Device;
import com.tinkerforge.Device.Identity;
import com.tinkerforge.NotConnectedException;
import com.tinkerforge.TimeoutException;

/**
 * The Tinkerforge consumer.
 */
public class TinkerforgeConsumer<EndpointType extends TinkerforgeEndpoint, BrickletType extends Device> extends DefaultConsumer {
    
    protected final TinkerforgeEndpoint endpoint;
    
    protected BrickletType bricklet;
    
    protected Identity identity = null;
    
    public TinkerforgeConsumer(TinkerforgeEndpoint endpoint, Processor processor) {
        super(endpoint, processor);
        this.endpoint = endpoint;
    }
    
    protected Exchange createExchange(org.apache.camel.Endpoint endpoint, Object messageBody) throws TimeoutException, NotConnectedException {
        Identity info = getIdentity();
        Exchange exchange = endpoint.createExchange(ExchangePattern.InOut);
        Message message = exchange.getIn();
        message.setHeader("com.tinkerforge.bricklet.uid", info.uid);
        message.setHeader("com.tinkerforge.bricklet.connectedUid", info.connectedUid);
        message.setHeader("com.tinkerforge.bricklet.deviceIdentifier", info.deviceIdentifier);
        message.setHeader("com.tinkerforge.bricklet.position", info.position);
        
        message.setBody(messageBody);
        return exchange;
    }
    
    
    protected Identity getIdentity() throws TimeoutException, NotConnectedException {
        if(identity==null){
            identity = bricklet.getIdentity();
        }
        return identity;
    }
    
}
