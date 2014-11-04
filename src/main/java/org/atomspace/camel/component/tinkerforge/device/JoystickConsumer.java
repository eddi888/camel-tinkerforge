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

import com.tinkerforge.BrickletJoystick;

import com.tinkerforge.BrickletJoystick.PositionListener;
import com.tinkerforge.BrickletJoystick.AnalogValueListener;
import com.tinkerforge.BrickletJoystick.PositionReachedListener;
import com.tinkerforge.BrickletJoystick.AnalogValueReachedListener;
import com.tinkerforge.BrickletJoystick.PressedListener;
import com.tinkerforge.BrickletJoystick.ReleasedListener;;

public class JoystickConsumer extends TinkerforgeConsumer<JoystickEndpoint, BrickletJoystick> implements PositionListener, AnalogValueListener, PositionReachedListener, AnalogValueReachedListener, PressedListener, ReleasedListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(JoystickConsumer.class);
    
    public JoystickConsumer(JoystickEndpoint endpoint, Processor processor) throws Exception {
        super(endpoint, processor);

        device = new BrickletJoystick(endpoint.getUid(),endpoint.getSharedConnection().getConnection());
        endpoint.init(device);

        if(endpoint.getCallback()==null || endpoint.getCallback().equals("")){
            device.addPositionListener(this);
            device.addAnalogValueListener(this);
            device.addPositionReachedListener(this);
            device.addAnalogValueReachedListener(this);
            device.addPressedListener(this);
            device.addReleasedListener(this);
            
        }else{
            String[] callbacks = endpoint.getCallback().split(",");
            for (String callback : callbacks) {
                if(callback.equals("PositionListener")) device.addPositionListener(this);
                if(callback.equals("AnalogValueListener")) device.addAnalogValueListener(this);
                if(callback.equals("PositionReachedListener")) device.addPositionReachedListener(this);
                if(callback.equals("AnalogValueReachedListener")) device.addAnalogValueReachedListener(this);
                if(callback.equals("PressedListener")) device.addPressedListener(this);
                if(callback.equals("ReleasedListener")) device.addReleasedListener(this);
                
            }
        }
    }
    
    
    @Override
    public void position(short x, short y) {
        LOG.trace("position()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("listener", BrickletJoystick.CALLBACK_POSITION);
            exchange.getIn().setHeader("x", x);
            exchange.getIn().setHeader("y", y);
            
            
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
    public void analogValue(int x, int y) {
        LOG.trace("analogValue()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("listener", BrickletJoystick.CALLBACK_ANALOG_VALUE);
            exchange.getIn().setHeader("x", x);
            exchange.getIn().setHeader("y", y);
            
            
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
    public void positionReached(short x, short y) {
        LOG.trace("positionReached()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("listener", BrickletJoystick.CALLBACK_POSITION_REACHED);
            exchange.getIn().setHeader("x", x);
            exchange.getIn().setHeader("y", y);
            
            
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
    public void analogValueReached(int x, int y) {
        LOG.trace("analogValueReached()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("listener", BrickletJoystick.CALLBACK_ANALOG_VALUE_REACHED);
            exchange.getIn().setHeader("x", x);
            exchange.getIn().setHeader("y", y);
            
            
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
    public void pressed() {
        LOG.trace("pressed()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("listener", BrickletJoystick.CALLBACK_PRESSED);
            
            
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
            exchange.getIn().setHeader("listener", BrickletJoystick.CALLBACK_RELEASED);
            
            
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