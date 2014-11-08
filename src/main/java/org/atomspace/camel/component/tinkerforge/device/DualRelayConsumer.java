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

import com.tinkerforge.BrickletDualRelay;

import com.tinkerforge.BrickletDualRelay.MonoflopDoneListener;;

public class DualRelayConsumer extends TinkerforgeConsumer<DualRelayEndpoint, BrickletDualRelay> implements MonoflopDoneListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(DualRelayConsumer.class);
    
    public DualRelayConsumer(DualRelayEndpoint endpoint, Processor processor) throws Exception {
        super(endpoint, processor);

        device = new BrickletDualRelay(endpoint.getUid(),endpoint.getSharedConnection().getConnection());
        endpoint.init(device);

        if(endpoint.getCallback()==null || endpoint.getCallback().equals("")){
            device.addMonoflopDoneListener(this);
            
        }else{
            String[] callbacks = endpoint.getCallback().split(",");
            for (String callback : callbacks) {
                if(callback.equals("MonoflopDoneListener")) device.addMonoflopDoneListener(this);
                
            }
        }
    }
    
    
    @Override
    public void monoflopDone(short relay, boolean state) {
        LOG.trace("monoflopDone()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickletDualRelay.CALLBACK_MONOFLOP_DONE);
            exchange.getIn().setHeader("relay", relay);
            exchange.getIn().setHeader("state", state);
            
            
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