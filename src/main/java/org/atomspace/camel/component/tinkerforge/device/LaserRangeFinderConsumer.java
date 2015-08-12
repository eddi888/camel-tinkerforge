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

import com.tinkerforge.BrickletLaserRangeFinder;

import com.tinkerforge.BrickletLaserRangeFinder.DistanceListener;
import com.tinkerforge.BrickletLaserRangeFinder.VelocityListener;
import com.tinkerforge.BrickletLaserRangeFinder.DistanceReachedListener;
import com.tinkerforge.BrickletLaserRangeFinder.VelocityReachedListener;;

public class LaserRangeFinderConsumer extends TinkerforgeConsumer<LaserRangeFinderEndpoint, BrickletLaserRangeFinder> implements DistanceListener, VelocityListener, DistanceReachedListener, VelocityReachedListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(LaserRangeFinderConsumer.class);
    
    public LaserRangeFinderConsumer(LaserRangeFinderEndpoint endpoint, Processor processor) throws Exception {
        super(endpoint, processor);

        device = new BrickletLaserRangeFinder(endpoint.getUid(),endpoint.getSharedConnection().getConnection());
        endpoint.init(device);

        if(endpoint.getCallback()==null || endpoint.getCallback().equals("")){
            device.addDistanceListener(this);
            device.addVelocityListener(this);
            device.addDistanceReachedListener(this);
            device.addVelocityReachedListener(this);
            
        }else{
            String[] callbacks = endpoint.getCallback().split(",");
            for (String callback : callbacks) {
                if(callback.equals("DistanceListener")) device.addDistanceListener(this);
                if(callback.equals("VelocityListener")) device.addVelocityListener(this);
                if(callback.equals("DistanceReachedListener")) device.addDistanceReachedListener(this);
                if(callback.equals("VelocityReachedListener")) device.addVelocityReachedListener(this);
                
            }
        }
    }
    
    
    @Override
    public void distance(int distance) {
        LOG.trace("distance()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickletLaserRangeFinder.CALLBACK_DISTANCE);
            exchange.getIn().setHeader("distance", distance);
            
            
            // ADD BODY
            exchange.getIn().setBody("distance");;
            
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
    public void velocity(short velocity) {
        LOG.trace("velocity()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickletLaserRangeFinder.CALLBACK_VELOCITY);
            exchange.getIn().setHeader("velocity", velocity);
            
            
            // ADD BODY
            exchange.getIn().setBody("velocity");;
            
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
    public void distanceReached(int distance) {
        LOG.trace("distanceReached()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickletLaserRangeFinder.CALLBACK_DISTANCE_REACHED);
            exchange.getIn().setHeader("distance", distance);
            
            
            // ADD BODY
            exchange.getIn().setBody("distance_reached");;
            
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
    public void velocityReached(short velocity) {
        LOG.trace("velocityReached()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickletLaserRangeFinder.CALLBACK_VELOCITY_REACHED);
            exchange.getIn().setHeader("velocity", velocity);
            
            
            // ADD BODY
            exchange.getIn().setBody("velocity_reached");;
            
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