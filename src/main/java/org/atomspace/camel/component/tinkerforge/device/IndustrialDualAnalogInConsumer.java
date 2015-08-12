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

import com.tinkerforge.BrickletIndustrialDualAnalogIn;

import com.tinkerforge.BrickletIndustrialDualAnalogIn.VoltageListener;
import com.tinkerforge.BrickletIndustrialDualAnalogIn.VoltageReachedListener;;

public class IndustrialDualAnalogInConsumer extends TinkerforgeConsumer<IndustrialDualAnalogInEndpoint, BrickletIndustrialDualAnalogIn> implements VoltageListener, VoltageReachedListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(IndustrialDualAnalogInConsumer.class);
    
    public IndustrialDualAnalogInConsumer(IndustrialDualAnalogInEndpoint endpoint, Processor processor) throws Exception {
        super(endpoint, processor);

        device = new BrickletIndustrialDualAnalogIn(endpoint.getUid(),endpoint.getSharedConnection().getConnection());
        endpoint.init(device);

        if(endpoint.getCallback()==null || endpoint.getCallback().equals("")){
            device.addVoltageListener(this);
            device.addVoltageReachedListener(this);
            
        }else{
            String[] callbacks = endpoint.getCallback().split(",");
            for (String callback : callbacks) {
                if(callback.equals("VoltageListener")) device.addVoltageListener(this);
                if(callback.equals("VoltageReachedListener")) device.addVoltageReachedListener(this);
                
            }
        }
    }
    
    
    @Override
    public void voltage(short channel, int voltage) {
        LOG.trace("voltage()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickletIndustrialDualAnalogIn.CALLBACK_VOLTAGE);
            exchange.getIn().setHeader("channel", channel);
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
    public void voltageReached(short channel, int voltage) {
        LOG.trace("voltageReached()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickletIndustrialDualAnalogIn.CALLBACK_VOLTAGE_REACHED);
            exchange.getIn().setHeader("channel", channel);
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
    
    

}