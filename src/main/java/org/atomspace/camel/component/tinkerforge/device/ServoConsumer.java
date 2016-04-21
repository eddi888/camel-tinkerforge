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

import com.tinkerforge.BrickServo;

import com.tinkerforge.BrickServo.UnderVoltageListener;
import com.tinkerforge.BrickServo.PositionReachedListener;
import com.tinkerforge.BrickServo.VelocityReachedListener;;

public class ServoConsumer extends TinkerforgeConsumer<ServoEndpoint, BrickServo> implements UnderVoltageListener, PositionReachedListener, VelocityReachedListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(ServoConsumer.class);
    
    public ServoConsumer(ServoEndpoint endpoint, Processor processor) throws Exception {
        super(endpoint, processor);

        device = new BrickServo(endpoint.getUid(),endpoint.getSharedConnection().getConnection());
        endpoint.init(device);

        if(endpoint.getCallback()==null || endpoint.getCallback().equals("")){
            device.addUnderVoltageListener(this);
            device.addPositionReachedListener(this);
            device.addVelocityReachedListener(this);
            
        }else{
            String[] callbacks = endpoint.getCallback().split(",");
            for (String callback : callbacks) {
                if(callback.equals("UnderVoltageListener")) device.addUnderVoltageListener(this);
                if(callback.equals("PositionReachedListener")) device.addPositionReachedListener(this);
                if(callback.equals("VelocityReachedListener")) device.addVelocityReachedListener(this);
                
            }
        }
    }
    
    
    @Override
    public void underVoltage(int voltage) {
        LOG.trace("underVoltage()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickServo.CALLBACK_UNDER_VOLTAGE);
            exchange.getIn().setHeader("voltage", voltage);
            
            
            // ADD BODY
            exchange.getIn().setBody("under_voltage");;
            
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
    public void positionReached(short servoNum, short position) {
        LOG.trace("positionReached()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickServo.CALLBACK_POSITION_REACHED);
            exchange.getIn().setHeader("servoNum", servoNum);
            exchange.getIn().setHeader("position", position);
            
            
            // ADD BODY
            exchange.getIn().setBody("position_reached");;
            
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
    public void velocityReached(short servoNum, short velocity) {
        LOG.trace("velocityReached()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickServo.CALLBACK_VELOCITY_REACHED);
            exchange.getIn().setHeader("servoNum", servoNum);
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