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

import com.tinkerforge.BrickletAmbientLightV2;

import com.tinkerforge.BrickletAmbientLightV2.IlluminanceListener;
import com.tinkerforge.BrickletAmbientLightV2.IlluminanceReachedListener;;

public class AmbientLightV2Consumer extends TinkerforgeConsumer<AmbientLightV2Endpoint, BrickletAmbientLightV2> implements IlluminanceListener, IlluminanceReachedListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(AmbientLightV2Consumer.class);
    
    public AmbientLightV2Consumer(AmbientLightV2Endpoint endpoint, Processor processor) throws Exception {
        super(endpoint, processor);

        device = new BrickletAmbientLightV2(endpoint.getUid(),endpoint.getSharedConnection().getConnection());
        endpoint.init(device);

        if(endpoint.getCallback()==null || endpoint.getCallback().equals("")){
            device.addIlluminanceListener(this);
            device.addIlluminanceReachedListener(this);
            
        }else{
            String[] callbacks = endpoint.getCallback().split(",");
            for (String callback : callbacks) {
                if(callback.equals("IlluminanceListener")) device.addIlluminanceListener(this);
                if(callback.equals("IlluminanceReachedListener")) device.addIlluminanceReachedListener(this);
                
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
            exchange.getIn().setHeader("fireBy", BrickletAmbientLightV2.CALLBACK_ILLUMINANCE);
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
    public void illuminanceReached(long illuminance) {
        LOG.trace("illuminanceReached()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickletAmbientLightV2.CALLBACK_ILLUMINANCE_REACHED);
            exchange.getIn().setHeader("illuminance", illuminance);
            
            
            // ADD BODY
            exchange.getIn().setBody("illuminance_reached");;
            
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