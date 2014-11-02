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
import com.tinkerforge.BrickletTemperature;

import com.tinkerforge.BrickletTemperature.TemperatureListener;
import com.tinkerforge.BrickletTemperature.TemperatureReachedListener;;

public class TemperatureConsumer extends TinkerforgeConsumer<TemperatureEndpoint, BrickletTemperature> implements TemperatureListener, TemperatureReachedListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(TemperatureConsumer.class);
    
    public TemperatureConsumer(TemperatureEndpoint endpoint, Processor processor) throws UnknownHostException, AlreadyConnectedException, IOException {
        super(endpoint, processor);
        device = new BrickletTemperature(endpoint.getUid(),endpoint.getSharedConnection().getConnection());

        if(endpoint.getCallback()==null || endpoint.getCallback().equals("")){
            device.addTemperatureListener(this);
            device.addTemperatureReachedListener(this);
            
        }else{
            String[] callbacks = endpoint.getCallback().split(",");
            for (String callback : callbacks) {
                if(callback.equals("TemperatureListener")) device.addTemperatureListener(this);
                if(callback.equals("TemperatureReachedListener")) device.addTemperatureReachedListener(this);
                
            }
        }
    }
    
    
	@Override
    public void temperature(short temperature) {
        LOG.trace("temperature()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("CALLBACK", BrickletTemperature.CALLBACK_TEMPERATURE);
            exchange.getIn().setHeader("temperature", temperature);
            
            
            // ADD BODY
            exchange.getIn().setBody("temperature");;
            
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
    public void temperatureReached(short temperature) {
        LOG.trace("temperatureReached()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("CALLBACK", BrickletTemperature.CALLBACK_TEMPERATURE_REACHED);
            exchange.getIn().setHeader("temperature", temperature);
            
            
            // ADD BODY
            exchange.getIn().setBody("temperature_reached");;
            
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