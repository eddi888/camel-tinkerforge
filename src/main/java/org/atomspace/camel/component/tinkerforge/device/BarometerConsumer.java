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
import com.tinkerforge.BrickletBarometer;

import com.tinkerforge.BrickletBarometer.AirPressureListener;
import com.tinkerforge.BrickletBarometer.AltitudeListener;
import com.tinkerforge.BrickletBarometer.AirPressureReachedListener;
import com.tinkerforge.BrickletBarometer.AltitudeReachedListener;;

public class BarometerConsumer extends TinkerforgeConsumer<BarometerEndpoint, BrickletBarometer> implements AirPressureListener, AltitudeListener, AirPressureReachedListener, AltitudeReachedListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(BarometerConsumer.class);
    
    public BarometerConsumer(BarometerEndpoint endpoint, Processor processor) throws UnknownHostException, AlreadyConnectedException, IOException {
        super(endpoint, processor);
        device = new BrickletBarometer(endpoint.getUid(),endpoint.getSharedConnection().getConnection());

        if(endpoint.getCallback()==null || endpoint.getCallback().equals("")){
            device.addAirPressureListener(this);
            device.addAltitudeListener(this);
            device.addAirPressureReachedListener(this);
            device.addAltitudeReachedListener(this);
            
        }else{
            String[] callbacks = endpoint.getCallback().split(",");
            for (String callback : callbacks) {
                if(callback.equals("AirPressureListener")) device.addAirPressureListener(this);
                if(callback.equals("AltitudeListener")) device.addAltitudeListener(this);
                if(callback.equals("AirPressureReachedListener")) device.addAirPressureReachedListener(this);
                if(callback.equals("AltitudeReachedListener")) device.addAltitudeReachedListener(this);
                
            }
        }
    }
    
    
	@Override
    public void airPressure(int air_pressure) {
        LOG.trace("airPressure()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("CALLBACK", BrickletBarometer.CALLBACK_AIR_PRESSURE);
            exchange.getIn().setHeader("air_pressure", air_pressure);
            
            
            // ADD BODY
            exchange.getIn().setBody("air_pressure");;
            
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
    public void altitude(int altitude) {
        LOG.trace("altitude()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("CALLBACK", BrickletBarometer.CALLBACK_ALTITUDE);
            exchange.getIn().setHeader("altitude", altitude);
            
            
            // ADD BODY
            exchange.getIn().setBody("altitude");;
            
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
    public void airPressureReached(int air_pressure) {
        LOG.trace("airPressureReached()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("CALLBACK", BrickletBarometer.CALLBACK_AIR_PRESSURE_REACHED);
            exchange.getIn().setHeader("air_pressure", air_pressure);
            
            
            // ADD BODY
            exchange.getIn().setBody("air_pressure_reached");;
            
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
    public void altitudeReached(int altitude) {
        LOG.trace("altitudeReached()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("CALLBACK", BrickletBarometer.CALLBACK_ALTITUDE_REACHED);
            exchange.getIn().setHeader("altitude", altitude);
            
            
            // ADD BODY
            exchange.getIn().setBody("altitude_reached");;
            
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