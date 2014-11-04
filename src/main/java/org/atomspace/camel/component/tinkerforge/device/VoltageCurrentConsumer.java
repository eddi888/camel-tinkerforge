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

import com.tinkerforge.BrickletVoltageCurrent;

import com.tinkerforge.BrickletVoltageCurrent.CurrentListener;
import com.tinkerforge.BrickletVoltageCurrent.VoltageListener;
import com.tinkerforge.BrickletVoltageCurrent.PowerListener;
import com.tinkerforge.BrickletVoltageCurrent.CurrentReachedListener;
import com.tinkerforge.BrickletVoltageCurrent.VoltageReachedListener;
import com.tinkerforge.BrickletVoltageCurrent.PowerReachedListener;;

public class VoltageCurrentConsumer extends TinkerforgeConsumer<VoltageCurrentEndpoint, BrickletVoltageCurrent> implements CurrentListener, VoltageListener, PowerListener, CurrentReachedListener, VoltageReachedListener, PowerReachedListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(VoltageCurrentConsumer.class);
    
    public VoltageCurrentConsumer(VoltageCurrentEndpoint endpoint, Processor processor) throws Exception {
        super(endpoint, processor);

        device = new BrickletVoltageCurrent(endpoint.getUid(),endpoint.getSharedConnection().getConnection());
        endpoint.init(device);

        if(endpoint.getCallback()==null || endpoint.getCallback().equals("")){
            device.addCurrentListener(this);
            device.addVoltageListener(this);
            device.addPowerListener(this);
            device.addCurrentReachedListener(this);
            device.addVoltageReachedListener(this);
            device.addPowerReachedListener(this);
            
        }else{
            String[] callbacks = endpoint.getCallback().split(",");
            for (String callback : callbacks) {
                if(callback.equals("CurrentListener")) device.addCurrentListener(this);
                if(callback.equals("VoltageListener")) device.addVoltageListener(this);
                if(callback.equals("PowerListener")) device.addPowerListener(this);
                if(callback.equals("CurrentReachedListener")) device.addCurrentReachedListener(this);
                if(callback.equals("VoltageReachedListener")) device.addVoltageReachedListener(this);
                if(callback.equals("PowerReachedListener")) device.addPowerReachedListener(this);
                
            }
        }
    }
    
    
    @Override
    public void current(int current) {
        LOG.trace("current()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("listener", BrickletVoltageCurrent.CALLBACK_CURRENT);
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
    public void voltage(int voltage) {
        LOG.trace("voltage()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("listener", BrickletVoltageCurrent.CALLBACK_VOLTAGE);
            exchange.getIn().setHeader("voltage", voltage);
            
            
            // ADD BODY
            exchange.getIn().setBody("voltage");;
            
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
    public void power(int power) {
        LOG.trace("power()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("listener", BrickletVoltageCurrent.CALLBACK_POWER);
            exchange.getIn().setHeader("power", power);
            
            
            // ADD BODY
            exchange.getIn().setBody("power");;
            
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
    public void currentReached(int current) {
        LOG.trace("currentReached()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("listener", BrickletVoltageCurrent.CALLBACK_CURRENT_REACHED);
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
    public void voltageReached(int voltage) {
        LOG.trace("voltageReached()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("listener", BrickletVoltageCurrent.CALLBACK_VOLTAGE_REACHED);
            exchange.getIn().setHeader("voltage", voltage);
            
            
            // ADD BODY
            exchange.getIn().setBody("voltage_reached");;
            
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
    public void powerReached(int power) {
        LOG.trace("powerReached()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("listener", BrickletVoltageCurrent.CALLBACK_POWER_REACHED);
            exchange.getIn().setHeader("power", power);
            
            
            // ADD BODY
            exchange.getIn().setBody("power_reached");;
            
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