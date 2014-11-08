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

import com.tinkerforge.BrickletCurrent12;

import com.tinkerforge.BrickletCurrent12.CurrentListener;
import com.tinkerforge.BrickletCurrent12.AnalogValueListener;
import com.tinkerforge.BrickletCurrent12.CurrentReachedListener;
import com.tinkerforge.BrickletCurrent12.AnalogValueReachedListener;
import com.tinkerforge.BrickletCurrent12.OverCurrentListener;;

public class Current12Consumer extends TinkerforgeConsumer<Current12Endpoint, BrickletCurrent12> implements CurrentListener, AnalogValueListener, CurrentReachedListener, AnalogValueReachedListener, OverCurrentListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(Current12Consumer.class);
    
    public Current12Consumer(Current12Endpoint endpoint, Processor processor) throws Exception {
        super(endpoint, processor);

        device = new BrickletCurrent12(endpoint.getUid(),endpoint.getSharedConnection().getConnection());
        endpoint.init(device);

        if(endpoint.getCallback()==null || endpoint.getCallback().equals("")){
            device.addCurrentListener(this);
            device.addAnalogValueListener(this);
            device.addCurrentReachedListener(this);
            device.addAnalogValueReachedListener(this);
            device.addOverCurrentListener(this);
            
        }else{
            String[] callbacks = endpoint.getCallback().split(",");
            for (String callback : callbacks) {
                if(callback.equals("CurrentListener")) device.addCurrentListener(this);
                if(callback.equals("AnalogValueListener")) device.addAnalogValueListener(this);
                if(callback.equals("CurrentReachedListener")) device.addCurrentReachedListener(this);
                if(callback.equals("AnalogValueReachedListener")) device.addAnalogValueReachedListener(this);
                if(callback.equals("OverCurrentListener")) device.addOverCurrentListener(this);
                
            }
        }
    }
    
    
    @Override
    public void current(short current) {
        LOG.trace("current()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickletCurrent12.CALLBACK_CURRENT);
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
    public void analogValue(int value) {
        LOG.trace("analogValue()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickletCurrent12.CALLBACK_ANALOG_VALUE);
            exchange.getIn().setHeader("value", value);
            
            
            // ADD BODY
            exchange.getIn().setBody("analog_value");;
            
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
    public void currentReached(short current) {
        LOG.trace("currentReached()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickletCurrent12.CALLBACK_CURRENT_REACHED);
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
    
    @Override
    public void analogValueReached(int value) {
        LOG.trace("analogValueReached()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickletCurrent12.CALLBACK_ANALOG_VALUE_REACHED);
            exchange.getIn().setHeader("value", value);
            
            
            // ADD BODY
            exchange.getIn().setBody("analog_value_reached");;
            
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
    public void overCurrent() {
        LOG.trace("overCurrent()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickletCurrent12.CALLBACK_OVER_CURRENT);
            
            
            // ADD BODY
            exchange.getIn().setBody("over_current");;
            
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