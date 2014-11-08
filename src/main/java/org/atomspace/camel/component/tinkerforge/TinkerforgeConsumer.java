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
package org.atomspace.camel.component.tinkerforge;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.impl.DefaultConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tinkerforge.Device;
import com.tinkerforge.Device.Identity;
import com.tinkerforge.NotConnectedException;
import com.tinkerforge.TimeoutException;

/**
 * The Tinkerforge consumer.
 */
public abstract class TinkerforgeConsumer<EndpointType extends TinkerforgeEndpoint<?,?>, DeviceType extends Device> extends DefaultConsumer {
    
    private static final Logger LOG = LoggerFactory.getLogger(TinkerforgeConsumer.class);
    
    protected EndpointType endpoint;
    
    protected DeviceType device;
    
    protected Identity identity = null;
    
    public TinkerforgeConsumer(EndpointType endpoint, Processor processor) {
        super(endpoint, processor);
        this.endpoint = endpoint;
    }
    
    protected Exchange createExchange() throws TimeoutException, NotConnectedException {
        LOG.trace("createExchange()");
        Identity info = getIdentity();
        Exchange exchange = endpoint.createExchange();
        
        ExchangePattern exchangePattern = exchange.getPattern();
        LOG.trace("exchangePattern="+ exchangePattern);
        
        //TODO CHECK InOnly. InOut, OutOnly
        
        // SET DEFAULT HEADER
        Message message = exchange.getIn();
        message.setHeader("uid", info.uid);
        message.setHeader("connectedUid", info.connectedUid);
        message.setHeader("deviceIdentifier", info.deviceIdentifier);
        message.setHeader("position", info.position);
        
        return exchange;
    }
    
    
    protected Identity getIdentity() throws TimeoutException, NotConnectedException {
        if(identity==null){
            identity = device.getIdentity();
        }
        return identity;
    }
    
}
