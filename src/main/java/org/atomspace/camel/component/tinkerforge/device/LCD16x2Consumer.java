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

import com.tinkerforge.BrickletLCD16x2;

import com.tinkerforge.BrickletLCD16x2.ButtonPressedListener;
import com.tinkerforge.BrickletLCD16x2.ButtonReleasedListener;;

public class LCD16x2Consumer extends TinkerforgeConsumer<LCD16x2Endpoint, BrickletLCD16x2> implements ButtonPressedListener, ButtonReleasedListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(LCD16x2Consumer.class);
    
    public LCD16x2Consumer(LCD16x2Endpoint endpoint, Processor processor) throws Exception {
        super(endpoint, processor);

        device = new BrickletLCD16x2(endpoint.getUid(),endpoint.getSharedConnection().getConnection());
        endpoint.init(device);

        if(endpoint.getCallback()==null || endpoint.getCallback().equals("")){
            device.addButtonPressedListener(this);
            device.addButtonReleasedListener(this);
            
        }else{
            String[] callbacks = endpoint.getCallback().split(",");
            for (String callback : callbacks) {
                if(callback.equals("ButtonPressedListener")) device.addButtonPressedListener(this);
                if(callback.equals("ButtonReleasedListener")) device.addButtonReleasedListener(this);
                
            }
        }
    }
    
    
    @Override
    public void buttonPressed(short button) {
        LOG.trace("buttonPressed()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("listener", BrickletLCD16x2.CALLBACK_BUTTON_PRESSED);
            exchange.getIn().setHeader("button", button);
            
            
            // ADD BODY
            exchange.getIn().setBody("button_pressed");;
            
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
    public void buttonReleased(short button) {
        LOG.trace("buttonReleased()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("listener", BrickletLCD16x2.CALLBACK_BUTTON_RELEASED);
            exchange.getIn().setHeader("button", button);
            
            
            // ADD BODY
            exchange.getIn().setBody("button_released");;
            
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