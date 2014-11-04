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

import com.tinkerforge.BrickletRotaryEncoder;

import com.tinkerforge.BrickletRotaryEncoder.CountListener;
import com.tinkerforge.BrickletRotaryEncoder.CountReachedListener;
import com.tinkerforge.BrickletRotaryEncoder.PressedListener;
import com.tinkerforge.BrickletRotaryEncoder.ReleasedListener;;

public class RotaryEncoderConsumer extends TinkerforgeConsumer<RotaryEncoderEndpoint, BrickletRotaryEncoder> implements CountListener, CountReachedListener, PressedListener, ReleasedListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(RotaryEncoderConsumer.class);
    
    public RotaryEncoderConsumer(RotaryEncoderEndpoint endpoint, Processor processor) throws Exception {
        super(endpoint, processor);

        device = new BrickletRotaryEncoder(endpoint.getUid(),endpoint.getSharedConnection().getConnection());
        endpoint.init(device);

        if(endpoint.getCallback()==null || endpoint.getCallback().equals("")){
            device.addCountListener(this);
            device.addCountReachedListener(this);
            device.addPressedListener(this);
            device.addReleasedListener(this);
            
        }else{
            String[] callbacks = endpoint.getCallback().split(",");
            for (String callback : callbacks) {
                if(callback.equals("CountListener")) device.addCountListener(this);
                if(callback.equals("CountReachedListener")) device.addCountReachedListener(this);
                if(callback.equals("PressedListener")) device.addPressedListener(this);
                if(callback.equals("ReleasedListener")) device.addReleasedListener(this);
                
            }
        }
    }
    
    
    @Override
    public void count(int count) {
        LOG.trace("count()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("listener", BrickletRotaryEncoder.CALLBACK_COUNT);
            exchange.getIn().setHeader("count", count);
            
            
            // ADD BODY
            exchange.getIn().setBody("count");;
            
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
    public void countReached(int count) {
        LOG.trace("countReached()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("listener", BrickletRotaryEncoder.CALLBACK_COUNT_REACHED);
            exchange.getIn().setHeader("count", count);
            
            
            // ADD BODY
            exchange.getIn().setBody("count_reached");;
            
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
    public void pressed() {
        LOG.trace("pressed()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("listener", BrickletRotaryEncoder.CALLBACK_PRESSED);
            
            
            // ADD BODY
            exchange.getIn().setBody("pressed");;
            
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
    public void released() {
        LOG.trace("released()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("listener", BrickletRotaryEncoder.CALLBACK_RELEASED);
            
            
            // ADD BODY
            exchange.getIn().setBody("released");;
            
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