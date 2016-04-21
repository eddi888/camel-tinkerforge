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

import com.tinkerforge.BrickletDustDetector;

import com.tinkerforge.BrickletDustDetector.DustDensityListener;
import com.tinkerforge.BrickletDustDetector.DustDensityReachedListener;;

public class DustDetectorConsumer extends TinkerforgeConsumer<DustDetectorEndpoint, BrickletDustDetector> implements DustDensityListener, DustDensityReachedListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(DustDetectorConsumer.class);
    
    public DustDetectorConsumer(DustDetectorEndpoint endpoint, Processor processor) throws Exception {
        super(endpoint, processor);

        device = new BrickletDustDetector(endpoint.getUid(),endpoint.getSharedConnection().getConnection());
        endpoint.init(device);

        if(endpoint.getCallback()==null || endpoint.getCallback().equals("")){
            device.addDustDensityListener(this);
            device.addDustDensityReachedListener(this);
            
        }else{
            String[] callbacks = endpoint.getCallback().split(",");
            for (String callback : callbacks) {
                if(callback.equals("DustDensityListener")) device.addDustDensityListener(this);
                if(callback.equals("DustDensityReachedListener")) device.addDustDensityReachedListener(this);
                
            }
        }
    }
    
    
    @Override
    public void dustDensity(int dustDensity) {
        LOG.trace("dustDensity()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickletDustDetector.CALLBACK_DUST_DENSITY);
            exchange.getIn().setHeader("dustDensity", dustDensity);
            
            
            // ADD BODY
            exchange.getIn().setBody("dust_density");;
            
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
    public void dustDensityReached(int dustDensity) {
        LOG.trace("dustDensityReached()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickletDustDetector.CALLBACK_DUST_DENSITY_REACHED);
            exchange.getIn().setHeader("dustDensity", dustDensity);
            
            
            // ADD BODY
            exchange.getIn().setBody("dust_density_reached");;
            
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