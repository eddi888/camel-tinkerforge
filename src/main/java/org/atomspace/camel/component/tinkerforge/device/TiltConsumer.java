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

import com.tinkerforge.BrickletTilt;

import com.tinkerforge.BrickletTilt.TiltStateListener;;

public class TiltConsumer extends TinkerforgeConsumer<TiltEndpoint, BrickletTilt> implements TiltStateListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(TiltConsumer.class);
    
    public TiltConsumer(TiltEndpoint endpoint, Processor processor) throws Exception {
        super(endpoint, processor);

        device = new BrickletTilt(endpoint.getUid(),endpoint.getSharedConnection().getConnection());
        endpoint.init(device);

        if(endpoint.getCallback()==null || endpoint.getCallback().equals("")){
            device.addTiltStateListener(this);
            
        }else{
            String[] callbacks = endpoint.getCallback().split(",");
            for (String callback : callbacks) {
                if(callback.equals("TiltStateListener")) device.addTiltStateListener(this);
                
            }
        }
    }
    
    
    @Override
    public void tiltState(short state) {
        LOG.trace("tiltState()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickletTilt.CALLBACK_TILT_STATE);
            exchange.getIn().setHeader("state", state);
            
            
            // ADD BODY
            exchange.getIn().setBody("tilt_state");;
            
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