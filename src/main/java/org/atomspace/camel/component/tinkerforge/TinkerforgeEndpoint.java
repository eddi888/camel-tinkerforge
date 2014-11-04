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

import org.apache.camel.Endpoint;
import org.apache.camel.Message;
import org.apache.camel.impl.DefaultConsumer;
import org.apache.camel.impl.DefaultEndpoint;
import org.apache.camel.impl.DefaultProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tinkerforge.Device;

/**
 * Represents a Tinkerforge endpoint.
 */
public abstract class TinkerforgeEndpoint<ConsumerType extends DefaultConsumer, ProducerType extends DefaultProducer> extends DefaultEndpoint {

    private static final Logger LOG = LoggerFactory.getLogger(TinkerforgeEndpoint.class);
    
    private String secret;
    private boolean autoReconnect = true;
    private int timeout = 2500;
    protected SharedConnection sharedConnection;
    protected String uid;
    
    protected ConsumerType consumer;
    protected ProducerType producer;
    
    private String callback;
    private String init;
    private String function;
    
    public TinkerforgeEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
        LOG.trace("TinkerforgeEndpoint(String uri='"+uri+"', TinkerforgeComponent tinkerforgeComponent='"+tinkerforgeComponent+"')");
        this.createExchange();
    }
    
    /**
     * Get Header-Parameter or alternative Configured Endpoint Parameter
     */
    public Object getValue(String parameter, Message message, Endpoint endpoint){
        if(message==null && endpoint!=null){
            return endpoint.getEndpointConfiguration().getParameter("parameter");
        }
        Object value = message.getHeader(parameter);
        if(value==null){
            value = endpoint.getEndpointConfiguration().getParameter("parameter");
        }
        return value;
    }
    
    /**
     * Get Configured Endpoint Parameter
     */
    public Object getValue(String parameter, Endpoint endpoint){
        return endpoint.getEndpointConfiguration().getParameter("parameter");
        
    }
    
    /**
     * Get Header-Parameter
     */
    public Object getValue(String parameter, Message message){
        return message.getHeader(parameter);
        
    }
    
    
    /**
     * Extract values out from Functions
     * 
     * init=functionName1('parameter':'value','parameter2':'value2'),functionName1('parameter':'value','parameter2':'value2')
     * 
     */
    public Object getFunctionsValue(String parameter, String function){
        
        Object value=null;
        return value;
    }
    
    
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public SharedConnection getSharedConnection() {
        LOG.trace("getSharedConnection()");
        return sharedConnection;
    }

    public void setSharedConnection(SharedConnection sharedConnection) {
        LOG.trace("setSharedConnection(SharedConnection sharedConnection='"+sharedConnection+"')");
        this.sharedConnection = sharedConnection;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        LOG.trace("setSecret(String secret='"+secret+"')");
        this.secret = secret;
    }

    public boolean isAutoReconnect() {
        return autoReconnect;
    }

    public void setAutoReconnect(boolean autoReconnect) {
        LOG.trace("setAutoReconnect(boolean autoReconnect='"+autoReconnect+"')");
        this.autoReconnect = autoReconnect;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        LOG.trace("setTimeout(int timeout='"+timeout+"')");
        this.timeout = timeout;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getInit() {
        return init;
    }

    public void setInit(String init) {
        this.init = init;
    }

    
    
}
