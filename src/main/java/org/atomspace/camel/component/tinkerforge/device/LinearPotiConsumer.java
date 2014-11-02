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
import com.tinkerforge.BrickletLinearPoti;

import com.tinkerforge.BrickletLinearPoti.PositionListener;
import com.tinkerforge.BrickletLinearPoti.AnalogValueListener;
import com.tinkerforge.BrickletLinearPoti.PositionReachedListener;
import com.tinkerforge.BrickletLinearPoti.AnalogValueReachedListener;;

public class LinearPotiConsumer extends TinkerforgeConsumer<LinearPotiEndpoint, BrickletLinearPoti> implements PositionListener, AnalogValueListener, PositionReachedListener, AnalogValueReachedListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(LinearPotiConsumer.class);
    
    public LinearPotiConsumer(LinearPotiEndpoint endpoint, Processor processor) throws UnknownHostException, AlreadyConnectedException, IOException {
        super(endpoint, processor);
        device = new BrickletLinearPoti(endpoint.getUid(),endpoint.getSharedConnection().getConnection());

        if(endpoint.getCallback()==null || endpoint.getCallback().equals("")){
            device.addPositionListener(this);
            device.addAnalogValueListener(this);
            device.addPositionReachedListener(this);
            device.addAnalogValueReachedListener(this);
            
        }else{
            String[] callbacks = endpoint.getCallback().split(",");
            for (String callback : callbacks) {
                if(callback.equals("PositionListener")) device.addPositionListener(this);
                if(callback.equals("AnalogValueListener")) device.addAnalogValueListener(this);
                if(callback.equals("PositionReachedListener")) device.addPositionReachedListener(this);
                if(callback.equals("AnalogValueReachedListener")) device.addAnalogValueReachedListener(this);
                
            }
        }
    }
    
    
	@Override
    public void position(int position) {
        LOG.trace("position()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("CALLBACK", BrickletLinearPoti.CALLBACK_POSITION);
            exchange.getIn().setHeader("position", position);
            
            
            // ADD BODY
            exchange.getIn().setBody("position");;
            
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
            exchange.getIn().setHeader("CALLBACK", BrickletLinearPoti.CALLBACK_ANALOG_VALUE);
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
    public void positionReached(int position) {
        LOG.trace("positionReached()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("CALLBACK", BrickletLinearPoti.CALLBACK_POSITION_REACHED);
            exchange.getIn().setHeader("position", position);
            
            
            // ADD BODY
            exchange.getIn().setBody("position_reached");;
            
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
            exchange.getIn().setHeader("CALLBACK", BrickletLinearPoti.CALLBACK_ANALOG_VALUE_REACHED);
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
    
    

}