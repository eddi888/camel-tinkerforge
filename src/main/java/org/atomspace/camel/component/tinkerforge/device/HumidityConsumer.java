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

import com.tinkerforge.BrickletHumidity;

import com.tinkerforge.BrickletHumidity.HumidityListener;
import com.tinkerforge.BrickletHumidity.AnalogValueListener;
import com.tinkerforge.BrickletHumidity.HumidityReachedListener;
import com.tinkerforge.BrickletHumidity.AnalogValueReachedListener;;

public class HumidityConsumer extends TinkerforgeConsumer<HumidityEndpoint, BrickletHumidity> implements HumidityListener, AnalogValueListener, HumidityReachedListener, AnalogValueReachedListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(HumidityConsumer.class);
    
    public HumidityConsumer(HumidityEndpoint endpoint, Processor processor) throws Exception {
        super(endpoint, processor);

        device = new BrickletHumidity(endpoint.getUid(),endpoint.getSharedConnection().getConnection());
        endpoint.init(device);

        if(endpoint.getCallback()==null || endpoint.getCallback().equals("")){
            device.addHumidityListener(this);
            device.addAnalogValueListener(this);
            device.addHumidityReachedListener(this);
            device.addAnalogValueReachedListener(this);
            
        }else{
            String[] callbacks = endpoint.getCallback().split(",");
            for (String callback : callbacks) {
                if(callback.equals("HumidityListener")) device.addHumidityListener(this);
                if(callback.equals("AnalogValueListener")) device.addAnalogValueListener(this);
                if(callback.equals("HumidityReachedListener")) device.addHumidityReachedListener(this);
                if(callback.equals("AnalogValueReachedListener")) device.addAnalogValueReachedListener(this);
                
            }
        }
    }
    
    
    @Override
    public void humidity(int humidity) {
        LOG.trace("humidity()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickletHumidity.CALLBACK_HUMIDITY);
            exchange.getIn().setHeader("humidity", humidity);
            
            
            // ADD BODY
            exchange.getIn().setBody("humidity");;
            
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
            exchange.getIn().setHeader("fireBy", BrickletHumidity.CALLBACK_ANALOG_VALUE);
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
    public void humidityReached(int humidity) {
        LOG.trace("humidityReached()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickletHumidity.CALLBACK_HUMIDITY_REACHED);
            exchange.getIn().setHeader("humidity", humidity);
            
            
            // ADD BODY
            exchange.getIn().setBody("humidity_reached");;
            
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
            exchange.getIn().setHeader("fireBy", BrickletHumidity.CALLBACK_ANALOG_VALUE_REACHED);
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