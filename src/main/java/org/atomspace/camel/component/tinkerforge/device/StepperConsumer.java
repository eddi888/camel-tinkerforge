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

import com.tinkerforge.BrickStepper;

import com.tinkerforge.BrickStepper.UnderVoltageListener;
import com.tinkerforge.BrickStepper.PositionReachedListener;
import com.tinkerforge.BrickStepper.AllDataListener;
import com.tinkerforge.BrickStepper.NewStateListener;;

public class StepperConsumer extends TinkerforgeConsumer<StepperEndpoint, BrickStepper> implements UnderVoltageListener, PositionReachedListener, AllDataListener, NewStateListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(StepperConsumer.class);
    
    public StepperConsumer(StepperEndpoint endpoint, Processor processor) throws Exception {
        super(endpoint, processor);

        device = new BrickStepper(endpoint.getUid(),endpoint.getSharedConnection().getConnection());
        endpoint.init(device);

        if(endpoint.getCallback()==null || endpoint.getCallback().equals("")){
            device.addUnderVoltageListener(this);
            device.addPositionReachedListener(this);
            device.addAllDataListener(this);
            device.addNewStateListener(this);
            
        }else{
            String[] callbacks = endpoint.getCallback().split(",");
            for (String callback : callbacks) {
                if(callback.equals("UnderVoltageListener")) device.addUnderVoltageListener(this);
                if(callback.equals("PositionReachedListener")) device.addPositionReachedListener(this);
                if(callback.equals("AllDataListener")) device.addAllDataListener(this);
                if(callback.equals("NewStateListener")) device.addNewStateListener(this);
                
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
            exchange.getIn().setHeader("listener", BrickStepper.CALLBACK_UNDER_VOLTAGE);
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
    public void positionReached(int position) {
        LOG.trace("positionReached()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("listener", BrickStepper.CALLBACK_POSITION_REACHED);
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
    public void allData(int current_velocity, int current_position, int remaining_steps, int stack_voltage, int external_voltage, int current_consumption) {
        LOG.trace("allData()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("listener", BrickStepper.CALLBACK_ALL_DATA);
            exchange.getIn().setHeader("current_velocity", current_velocity);
            exchange.getIn().setHeader("current_position", current_position);
            exchange.getIn().setHeader("remaining_steps", remaining_steps);
            exchange.getIn().setHeader("stack_voltage", stack_voltage);
            exchange.getIn().setHeader("external_voltage", external_voltage);
            exchange.getIn().setHeader("current_consumption", current_consumption);
            
            
            // ADD BODY
            exchange.getIn().setBody("all_data");;
            
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
    public void newState(short state_new, short state_previous) {
        LOG.trace("newState()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("listener", BrickStepper.CALLBACK_NEW_STATE);
            exchange.getIn().setHeader("state_new", state_new);
            exchange.getIn().setHeader("state_previous", state_previous);
            
            
            // ADD BODY
            exchange.getIn().setBody("new_state");;
            
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