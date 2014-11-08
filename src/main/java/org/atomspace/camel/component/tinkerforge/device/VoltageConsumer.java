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

import com.tinkerforge.BrickletVoltage;

import com.tinkerforge.BrickletVoltage.VoltageListener;
import com.tinkerforge.BrickletVoltage.AnalogValueListener;
import com.tinkerforge.BrickletVoltage.VoltageReachedListener;
import com.tinkerforge.BrickletVoltage.AnalogValueReachedListener;;

public class VoltageConsumer extends TinkerforgeConsumer<VoltageEndpoint, BrickletVoltage> implements VoltageListener, AnalogValueListener, VoltageReachedListener, AnalogValueReachedListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(VoltageConsumer.class);
    
    public VoltageConsumer(VoltageEndpoint endpoint, Processor processor) throws Exception {
        super(endpoint, processor);

        device = new BrickletVoltage(endpoint.getUid(),endpoint.getSharedConnection().getConnection());
        endpoint.init(device);

        if(endpoint.getCallback()==null || endpoint.getCallback().equals("")){
            device.addVoltageListener(this);
            device.addAnalogValueListener(this);
            device.addVoltageReachedListener(this);
            device.addAnalogValueReachedListener(this);
            
        }else{
            String[] callbacks = endpoint.getCallback().split(",");
            for (String callback : callbacks) {
                if(callback.equals("VoltageListener")) device.addVoltageListener(this);
                if(callback.equals("AnalogValueListener")) device.addAnalogValueListener(this);
                if(callback.equals("VoltageReachedListener")) device.addVoltageReachedListener(this);
                if(callback.equals("AnalogValueReachedListener")) device.addAnalogValueReachedListener(this);
                
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
            exchange.getIn().setHeader("fireBy", BrickletVoltage.CALLBACK_VOLTAGE);
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
    public void analogValue(int value) {
        LOG.trace("analogValue()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickletVoltage.CALLBACK_ANALOG_VALUE);
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
    public void voltageReached(int voltage) {
        LOG.trace("voltageReached()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickletVoltage.CALLBACK_VOLTAGE_REACHED);
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
    public void analogValueReached(int value) {
        LOG.trace("analogValueReached()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickletVoltage.CALLBACK_ANALOG_VALUE_REACHED);
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