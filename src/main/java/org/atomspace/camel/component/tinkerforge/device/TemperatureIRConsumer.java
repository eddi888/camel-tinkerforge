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

import com.tinkerforge.BrickletTemperatureIR;

import com.tinkerforge.BrickletTemperatureIR.AmbientTemperatureListener;
import com.tinkerforge.BrickletTemperatureIR.ObjectTemperatureListener;
import com.tinkerforge.BrickletTemperatureIR.AmbientTemperatureReachedListener;
import com.tinkerforge.BrickletTemperatureIR.ObjectTemperatureReachedListener;;

public class TemperatureIRConsumer extends TinkerforgeConsumer<TemperatureIREndpoint, BrickletTemperatureIR> implements AmbientTemperatureListener, ObjectTemperatureListener, AmbientTemperatureReachedListener, ObjectTemperatureReachedListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(TemperatureIRConsumer.class);
    
    public TemperatureIRConsumer(TemperatureIREndpoint endpoint, Processor processor) throws Exception {
        super(endpoint, processor);

        device = new BrickletTemperatureIR(endpoint.getUid(),endpoint.getSharedConnection().getConnection());
        endpoint.init(device);

        if(endpoint.getCallback()==null || endpoint.getCallback().equals("")){
            device.addAmbientTemperatureListener(this);
            device.addObjectTemperatureListener(this);
            device.addAmbientTemperatureReachedListener(this);
            device.addObjectTemperatureReachedListener(this);
            
        }else{
            String[] callbacks = endpoint.getCallback().split(",");
            for (String callback : callbacks) {
                if(callback.equals("AmbientTemperatureListener")) device.addAmbientTemperatureListener(this);
                if(callback.equals("ObjectTemperatureListener")) device.addObjectTemperatureListener(this);
                if(callback.equals("AmbientTemperatureReachedListener")) device.addAmbientTemperatureReachedListener(this);
                if(callback.equals("ObjectTemperatureReachedListener")) device.addObjectTemperatureReachedListener(this);
                
            }
        }
    }
    
    
    @Override
    public void ambientTemperature(short temperature) {
        LOG.trace("ambientTemperature()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickletTemperatureIR.CALLBACK_AMBIENT_TEMPERATURE);
            exchange.getIn().setHeader("temperature", temperature);
            
            
            // ADD BODY
            exchange.getIn().setBody("ambient_temperature");;
            
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
    public void objectTemperature(short temperature) {
        LOG.trace("objectTemperature()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickletTemperatureIR.CALLBACK_OBJECT_TEMPERATURE);
            exchange.getIn().setHeader("temperature", temperature);
            
            
            // ADD BODY
            exchange.getIn().setBody("object_temperature");;
            
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
    public void ambientTemperatureReached(short temperature) {
        LOG.trace("ambientTemperatureReached()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickletTemperatureIR.CALLBACK_AMBIENT_TEMPERATURE_REACHED);
            exchange.getIn().setHeader("temperature", temperature);
            
            
            // ADD BODY
            exchange.getIn().setBody("ambient_temperature_reached");;
            
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
    public void objectTemperatureReached(short temperature) {
        LOG.trace("objectTemperatureReached()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickletTemperatureIR.CALLBACK_OBJECT_TEMPERATURE_REACHED);
            exchange.getIn().setHeader("temperature", temperature);
            
            
            // ADD BODY
            exchange.getIn().setBody("object_temperature_reached");;
            
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