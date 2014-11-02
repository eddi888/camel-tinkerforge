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
import com.tinkerforge.BrickletDualButton;

import com.tinkerforge.BrickletDualButton.StateChangedListener;;

public class DualButtonConsumer extends TinkerforgeConsumer<DualButtonEndpoint, BrickletDualButton> implements StateChangedListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(DualButtonConsumer.class);
    
    public DualButtonConsumer(DualButtonEndpoint endpoint, Processor processor) throws UnknownHostException, AlreadyConnectedException, IOException {
        super(endpoint, processor);
        device = new BrickletDualButton(endpoint.getUid(),endpoint.getSharedConnection().getConnection());

        if(endpoint.getCallback()==null || endpoint.getCallback().equals("")){
            device.addStateChangedListener(this);
            
        }else{
            String[] callbacks = endpoint.getCallback().split(",");
            for (String callback : callbacks) {
                if(callback.equals("StateChangedListener")) device.addStateChangedListener(this);
                
            }
        }
    }
    
    
	@Override
    public void stateChanged(short button_l, short button_r, short led_l, short led_r) {
        LOG.trace("stateChanged()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("CALLBACK", BrickletDualButton.CALLBACK_STATE_CHANGED);
            exchange.getIn().setHeader("button_l", button_l);
            exchange.getIn().setHeader("button_r", button_r);
            exchange.getIn().setHeader("led_l", led_l);
            exchange.getIn().setHeader("led_r", led_r);
            
            
            // ADD BODY
            exchange.getIn().setBody("state_changed");;
            
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