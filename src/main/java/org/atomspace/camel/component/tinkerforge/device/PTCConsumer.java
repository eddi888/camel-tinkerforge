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

import com.tinkerforge.BrickletPTC;

import com.tinkerforge.BrickletPTC.TemperatureListener;
import com.tinkerforge.BrickletPTC.TemperatureReachedListener;
import com.tinkerforge.BrickletPTC.ResistanceListener;
import com.tinkerforge.BrickletPTC.ResistanceReachedListener;;

public class PTCConsumer extends TinkerforgeConsumer<PTCEndpoint, BrickletPTC> implements TemperatureListener, TemperatureReachedListener, ResistanceListener, ResistanceReachedListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(PTCConsumer.class);
    
    public PTCConsumer(PTCEndpoint endpoint, Processor processor) throws Exception {
        super(endpoint, processor);

        device = new BrickletPTC(endpoint.getUid(),endpoint.getSharedConnection().getConnection());
        endpoint.init(device);

        if(endpoint.getCallback()==null || endpoint.getCallback().equals("")){
            device.addTemperatureListener(this);
            device.addTemperatureReachedListener(this);
            device.addResistanceListener(this);
            device.addResistanceReachedListener(this);
            
        }else{
            String[] callbacks = endpoint.getCallback().split(",");
            for (String callback : callbacks) {
                if(callback.equals("TemperatureListener")) device.addTemperatureListener(this);
                if(callback.equals("TemperatureReachedListener")) device.addTemperatureReachedListener(this);
                if(callback.equals("ResistanceListener")) device.addResistanceListener(this);
                if(callback.equals("ResistanceReachedListener")) device.addResistanceReachedListener(this);
                
            }
        }
    }
    
    
    @Override
    public void temperature(int temperature) {
        LOG.trace("temperature()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickletPTC.CALLBACK_TEMPERATURE);
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
    public void temperatureReached(int temperature) {
        LOG.trace("temperatureReached()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickletPTC.CALLBACK_TEMPERATURE_REACHED);
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
    
    @Override
    public void resistance(int resistance) {
        LOG.trace("resistance()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickletPTC.CALLBACK_RESISTANCE);
            exchange.getIn().setHeader("resistance", resistance);
            
            
            // ADD BODY
            exchange.getIn().setBody("resistance");;
            
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
    public void resistanceReached(int resistance) {
        LOG.trace("resistanceReached()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickletPTC.CALLBACK_RESISTANCE_REACHED);
            exchange.getIn().setHeader("resistance", resistance);
            
            
            // ADD BODY
            exchange.getIn().setBody("resistance_reached");;
            
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