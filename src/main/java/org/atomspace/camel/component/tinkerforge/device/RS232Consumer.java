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
import org.apache.camel.Processor;
import org.atomspace.camel.component.tinkerforge.TinkerforgeConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tinkerforge.BrickletRS232;

import com.tinkerforge.BrickletRS232.ReadCallbackListener;
import com.tinkerforge.BrickletRS232.ErrorCallbackListener;;

public class RS232Consumer extends TinkerforgeConsumer<RS232Endpoint, BrickletRS232> implements ReadCallbackListener, ErrorCallbackListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(RS232Consumer.class);
    
    public RS232Consumer(RS232Endpoint endpoint, Processor processor) throws Exception {
        super(endpoint, processor);

        device = new BrickletRS232(endpoint.getUid(),endpoint.getSharedConnection().getConnection());
        endpoint.init(device);

        if(endpoint.getCallback()==null || endpoint.getCallback().equals("")){
            device.addReadCallbackListener(this);
            device.addErrorCallbackListener(this);
            
        }else{
            String[] callbacks = endpoint.getCallback().split(",");
            for (String callback : callbacks) {
                if(callback.equals("ReadCallbackListener")) device.addReadCallbackListener(this);
                if(callback.equals("ErrorCallbackListener")) device.addErrorCallbackListener(this);
                
            }
        }
    }
    
    
    @Override
    public void readCallback(char[] message, short length) {
        LOG.trace("readCallback()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickletRS232.CALLBACK_READ_CALLBACK);
            exchange.getIn().setHeader("message", message);
            exchange.getIn().setHeader("length", length);
            
            
            // ADD BODY
            exchange.getIn().setBody("read_callback");;
            
            getProcessor().process(exchange);
        } catch (Exception e) {
            getExceptionHandler().handleException("Error processing exchange", exchange, e);
        } finally {
            if (exchange != null && exchange.getException() != null) {
                getExceptionHandler().handleException("Error processing exchange", exchange, exchange.getException());
            }
        }
    }
    
    @Override
    public void errorCallback(short error) {
        LOG.trace("errorCallback()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickletRS232.CALLBACK_ERROR_CALLBACK);
            exchange.getIn().setHeader("error", error);
            
            
            // ADD BODY
            exchange.getIn().setBody("error_callback");;
            
            getProcessor().process(exchange);
        } catch (Exception e) {
            getExceptionHandler().handleException("Error processing exchange", exchange, e);
        } finally {
            if (exchange != null && exchange.getException() != null) {
                getExceptionHandler().handleException("Error processing exchange", exchange, exchange.getException());
            }
        }
    }
    
    

}