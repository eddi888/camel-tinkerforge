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
import org.apache.camel.Processor;
import org.atomspace.camel.component.tinkerforge.TinkerforgeConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tinkerforge.AlreadyConnectedException;
import com.tinkerforge.BrickletIndustrialDual020mA;

import com.tinkerforge.BrickletIndustrialDual020mA.CurrentListener;
import com.tinkerforge.BrickletIndustrialDual020mA.CurrentReachedListener;;

public class IndustrialDual020mAConsumer extends TinkerforgeConsumer<IndustrialDual020mAEndpoint, BrickletIndustrialDual020mA> implements CurrentListener, CurrentReachedListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(IndustrialDual020mAConsumer.class);
    
    public IndustrialDual020mAConsumer(IndustrialDual020mAEndpoint endpoint, Processor processor) throws UnknownHostException, AlreadyConnectedException, IOException {
        super(endpoint, processor);
        device = new BrickletIndustrialDual020mA(endpoint.getUid(),endpoint.getSharedConnection().getConnection());

        if(endpoint.getCallback()==null || endpoint.getCallback().equals("")){
            device.addCurrentListener(this);
            device.addCurrentReachedListener(this);
            
        }else{
            String[] callbacks = endpoint.getCallback().split(",");
            for (String callback : callbacks) {
                if(callback.equals("CurrentListener")) device.addCurrentListener(this);
                if(callback.equals("CurrentReachedListener")) device.addCurrentReachedListener(this);
                
            }
        }
    }
    
    
	@Override
    public void current(short sensor, int current) {
        LOG.trace("current()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("CALLBACK", BrickletIndustrialDual020mA.CALLBACK_CURRENT);
            exchange.getIn().setHeader("sensor", sensor);
            exchange.getIn().setHeader("current", current);
            
            
            // ADD BODY
            exchange.getIn().setBody("current");;
            
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
    public void currentReached(short sensor, int current) {
        LOG.trace("currentReached()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("CALLBACK", BrickletIndustrialDual020mA.CALLBACK_CURRENT_REACHED);
            exchange.getIn().setHeader("sensor", sensor);
            exchange.getIn().setHeader("current", current);
            
            
            // ADD BODY
            exchange.getIn().setBody("current_reached");;
            
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