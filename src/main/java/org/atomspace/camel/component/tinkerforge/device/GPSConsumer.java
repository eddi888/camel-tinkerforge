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
import com.tinkerforge.BrickletGPS;

import com.tinkerforge.BrickletGPS.CoordinatesListener;
import com.tinkerforge.BrickletGPS.StatusListener;
import com.tinkerforge.BrickletGPS.AltitudeListener;
import com.tinkerforge.BrickletGPS.MotionListener;
import com.tinkerforge.BrickletGPS.DateTimeListener;;

public class GPSConsumer extends TinkerforgeConsumer<GPSEndpoint, BrickletGPS> implements CoordinatesListener, StatusListener, AltitudeListener, MotionListener, DateTimeListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(GPSConsumer.class);
    
    public GPSConsumer(GPSEndpoint endpoint, Processor processor) throws UnknownHostException, AlreadyConnectedException, IOException {
        super(endpoint, processor);
        device = new BrickletGPS(endpoint.getUid(),endpoint.getSharedConnection().getConnection());

        if(endpoint.getCallback()==null || endpoint.getCallback().equals("")){
            device.addCoordinatesListener(this);
            device.addStatusListener(this);
            device.addAltitudeListener(this);
            device.addMotionListener(this);
            device.addDateTimeListener(this);
            
        }else{
            String[] callbacks = endpoint.getCallback().split(",");
            for (String callback : callbacks) {
                if(callback.equals("CoordinatesListener")) device.addCoordinatesListener(this);
                if(callback.equals("StatusListener")) device.addStatusListener(this);
                if(callback.equals("AltitudeListener")) device.addAltitudeListener(this);
                if(callback.equals("MotionListener")) device.addMotionListener(this);
                if(callback.equals("DateTimeListener")) device.addDateTimeListener(this);
                
            }
        }
    }
    
    
	@Override
    public void coordinates(long latitude, char ns, long longitude, char ew, int pdop, int hdop, int vdop, int epe) {
        LOG.trace("coordinates()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("CALLBACK", BrickletGPS.CALLBACK_COORDINATES);
            exchange.getIn().setHeader("latitude", latitude);
            exchange.getIn().setHeader("ns", ns);
            exchange.getIn().setHeader("longitude", longitude);
            exchange.getIn().setHeader("ew", ew);
            exchange.getIn().setHeader("pdop", pdop);
            exchange.getIn().setHeader("hdop", hdop);
            exchange.getIn().setHeader("vdop", vdop);
            exchange.getIn().setHeader("epe", epe);
            
            
            // ADD BODY
            exchange.getIn().setBody("coordinates");;
            
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
    public void status(short fix, short satellites_view, short satellites_used) {
        LOG.trace("status()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("CALLBACK", BrickletGPS.CALLBACK_STATUS);
            exchange.getIn().setHeader("fix", fix);
            exchange.getIn().setHeader("satellites_view", satellites_view);
            exchange.getIn().setHeader("satellites_used", satellites_used);
            
            
            // ADD BODY
            exchange.getIn().setBody("status");;
            
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
    public void altitude(long altitude, long geoidal_separation) {
        LOG.trace("altitude()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("CALLBACK", BrickletGPS.CALLBACK_ALTITUDE);
            exchange.getIn().setHeader("altitude", altitude);
            exchange.getIn().setHeader("geoidal_separation", geoidal_separation);
            
            
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
    public void motion(long course, long speed) {
        LOG.trace("motion()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("CALLBACK", BrickletGPS.CALLBACK_MOTION);
            exchange.getIn().setHeader("course", course);
            exchange.getIn().setHeader("speed", speed);
            
            
            // ADD BODY
            exchange.getIn().setBody("motion");;
            
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
    public void dateTime(long date, long time) {
        LOG.trace("dateTime()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("CALLBACK", BrickletGPS.CALLBACK_DATE_TIME);
            exchange.getIn().setHeader("date", date);
            exchange.getIn().setHeader("time", time);
            
            
            // ADD BODY
            exchange.getIn().setBody("date_time");;
            
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