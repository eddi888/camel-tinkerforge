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

import com.tinkerforge.BrickletLine;

import com.tinkerforge.BrickletLine.ReflectivityListener;
import com.tinkerforge.BrickletLine.ReflectivityReachedListener;;

public class LineConsumer extends TinkerforgeConsumer<LineEndpoint, BrickletLine> implements ReflectivityListener, ReflectivityReachedListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(LineConsumer.class);
    
    public LineConsumer(LineEndpoint endpoint, Processor processor) throws Exception {
        super(endpoint, processor);

        device = new BrickletLine(endpoint.getUid(),endpoint.getSharedConnection().getConnection());
        endpoint.init(device);

        if(endpoint.getCallback()==null || endpoint.getCallback().equals("")){
            device.addReflectivityListener(this);
            device.addReflectivityReachedListener(this);
            
        }else{
            String[] callbacks = endpoint.getCallback().split(",");
            for (String callback : callbacks) {
                if(callback.equals("ReflectivityListener")) device.addReflectivityListener(this);
                if(callback.equals("ReflectivityReachedListener")) device.addReflectivityReachedListener(this);
                
            }
        }
    }
    
    
    @Override
    public void reflectivity(int reflectivity) {
        LOG.trace("reflectivity()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickletLine.CALLBACK_REFLECTIVITY);
            exchange.getIn().setHeader("reflectivity", reflectivity);
            
            
            // ADD BODY
            exchange.getIn().setBody("reflectivity");;
            
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
    public void reflectivityReached(int reflectivity) {
        LOG.trace("reflectivityReached()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickletLine.CALLBACK_REFLECTIVITY_REACHED);
            exchange.getIn().setHeader("reflectivity", reflectivity);
            
            
            // ADD BODY
            exchange.getIn().setBody("reflectivity_reached");;
            
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