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

import com.tinkerforge.BrickletIO4;

import com.tinkerforge.BrickletIO4.InterruptListener;
import com.tinkerforge.BrickletIO4.MonoflopDoneListener;;

public class IO4Consumer extends TinkerforgeConsumer<IO4Endpoint, BrickletIO4> implements InterruptListener, MonoflopDoneListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(IO4Consumer.class);
    
    public IO4Consumer(IO4Endpoint endpoint, Processor processor) throws Exception {
        super(endpoint, processor);

        device = new BrickletIO4(endpoint.getUid(),endpoint.getSharedConnection().getConnection());
        endpoint.init(device);

        if(endpoint.getCallback()==null || endpoint.getCallback().equals("")){
            device.addInterruptListener(this);
            device.addMonoflopDoneListener(this);
            
        }else{
            String[] callbacks = endpoint.getCallback().split(",");
            for (String callback : callbacks) {
                if(callback.equals("InterruptListener")) device.addInterruptListener(this);
                if(callback.equals("MonoflopDoneListener")) device.addMonoflopDoneListener(this);
                
            }
        }
    }
    
    
    @Override
    public void interrupt(short interrupt_mask, short value_mask) {
        LOG.trace("interrupt()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("listener", BrickletIO4.CALLBACK_INTERRUPT);
            exchange.getIn().setHeader("interrupt_mask", interrupt_mask);
            exchange.getIn().setHeader("value_mask", value_mask);
            
            
            // ADD BODY
            exchange.getIn().setBody("interrupt");;
            
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
    public void monoflopDone(short selection_mask, short value_mask) {
        LOG.trace("monoflopDone()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("listener", BrickletIO4.CALLBACK_MONOFLOP_DONE);
            exchange.getIn().setHeader("selection_mask", selection_mask);
            exchange.getIn().setHeader("value_mask", value_mask);
            
            
            // ADD BODY
            exchange.getIn().setBody("monoflop_done");;
            
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