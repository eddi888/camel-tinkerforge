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

import com.tinkerforge.BrickletColor;

import com.tinkerforge.BrickletColor.ColorListener;
import com.tinkerforge.BrickletColor.ColorReachedListener;
import com.tinkerforge.BrickletColor.IlluminanceListener;
import com.tinkerforge.BrickletColor.ColorTemperatureListener;;

public class ColorConsumer extends TinkerforgeConsumer<ColorEndpoint, BrickletColor> implements ColorListener, ColorReachedListener, IlluminanceListener, ColorTemperatureListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(ColorConsumer.class);
    
    public ColorConsumer(ColorEndpoint endpoint, Processor processor) throws Exception {
        super(endpoint, processor);

        device = new BrickletColor(endpoint.getUid(),endpoint.getSharedConnection().getConnection());
        endpoint.init(device);

        if(endpoint.getCallback()==null || endpoint.getCallback().equals("")){
            device.addColorListener(this);
            device.addColorReachedListener(this);
            device.addIlluminanceListener(this);
            device.addColorTemperatureListener(this);
            
        }else{
            String[] callbacks = endpoint.getCallback().split(",");
            for (String callback : callbacks) {
                if(callback.equals("ColorListener")) device.addColorListener(this);
                if(callback.equals("ColorReachedListener")) device.addColorReachedListener(this);
                if(callback.equals("IlluminanceListener")) device.addIlluminanceListener(this);
                if(callback.equals("ColorTemperatureListener")) device.addColorTemperatureListener(this);
                
            }
        }
    }
    
    
    @Override
    public void color(int r, int g, int b, int c) {
        LOG.trace("color()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickletColor.CALLBACK_COLOR);
            exchange.getIn().setHeader("r", r);
            exchange.getIn().setHeader("g", g);
            exchange.getIn().setHeader("b", b);
            exchange.getIn().setHeader("c", c);
            
            
            // ADD BODY
            exchange.getIn().setBody("color");;
            
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
    public void colorReached(int r, int g, int b, int c) {
        LOG.trace("colorReached()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickletColor.CALLBACK_COLOR_REACHED);
            exchange.getIn().setHeader("r", r);
            exchange.getIn().setHeader("g", g);
            exchange.getIn().setHeader("b", b);
            exchange.getIn().setHeader("c", c);
            
            
            // ADD BODY
            exchange.getIn().setBody("color_reached");;
            
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
    public void illuminance(long illuminance) {
        LOG.trace("illuminance()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickletColor.CALLBACK_ILLUMINANCE);
            exchange.getIn().setHeader("illuminance", illuminance);
            
            
            // ADD BODY
            exchange.getIn().setBody("illuminance");;
            
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
    public void colorTemperature(int color_temperature) {
        LOG.trace("colorTemperature()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickletColor.CALLBACK_COLOR_TEMPERATURE);
            exchange.getIn().setHeader("color_temperature", color_temperature);
            
            
            // ADD BODY
            exchange.getIn().setBody("color_temperature");;
            
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