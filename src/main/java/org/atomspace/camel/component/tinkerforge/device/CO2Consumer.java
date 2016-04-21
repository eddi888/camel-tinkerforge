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

import com.tinkerforge.BrickletCO2;

import com.tinkerforge.BrickletCO2.CO2ConcentrationListener;
import com.tinkerforge.BrickletCO2.CO2ConcentrationReachedListener;;

public class CO2Consumer extends TinkerforgeConsumer<CO2Endpoint, BrickletCO2> implements CO2ConcentrationListener, CO2ConcentrationReachedListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(CO2Consumer.class);
    
    public CO2Consumer(CO2Endpoint endpoint, Processor processor) throws Exception {
        super(endpoint, processor);

        device = new BrickletCO2(endpoint.getUid(),endpoint.getSharedConnection().getConnection());
        endpoint.init(device);

        if(endpoint.getCallback()==null || endpoint.getCallback().equals("")){
            device.addCO2ConcentrationListener(this);
            device.addCO2ConcentrationReachedListener(this);
            
        }else{
            String[] callbacks = endpoint.getCallback().split(",");
            for (String callback : callbacks) {
                if(callback.equals("CO2ConcentrationListener")) device.addCO2ConcentrationListener(this);
                if(callback.equals("CO2ConcentrationReachedListener")) device.addCO2ConcentrationReachedListener(this);
                
            }
        }
    }
    
    
    @Override
    public void co2Concentration(int co2Concentration) {
        LOG.trace("co2Concentration()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickletCO2.CALLBACK_CO2_CONCENTRATION);
            exchange.getIn().setHeader("co2Concentration", co2Concentration);
            
            
            // ADD BODY
            exchange.getIn().setBody("co2_concentration");;
            
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
    public void co2ConcentrationReached(int co2Concentration) {
        LOG.trace("co2ConcentrationReached()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickletCO2.CALLBACK_CO2_CONCENTRATION_REACHED);
            exchange.getIn().setHeader("co2Concentration", co2Concentration);
            
            
            // ADD BODY
            exchange.getIn().setBody("co2_concentration_reached");;
            
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