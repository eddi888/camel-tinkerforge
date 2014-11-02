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
import com.tinkerforge.BrickMaster;

import com.tinkerforge.BrickMaster.StackCurrentListener;
import com.tinkerforge.BrickMaster.StackVoltageListener;
import com.tinkerforge.BrickMaster.USBVoltageListener;
import com.tinkerforge.BrickMaster.StackCurrentReachedListener;
import com.tinkerforge.BrickMaster.StackVoltageReachedListener;
import com.tinkerforge.BrickMaster.USBVoltageReachedListener;;

public class MasterConsumer extends TinkerforgeConsumer<MasterEndpoint, BrickMaster> implements StackCurrentListener, StackVoltageListener, USBVoltageListener, StackCurrentReachedListener, StackVoltageReachedListener, USBVoltageReachedListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(MasterConsumer.class);
    
    public MasterConsumer(MasterEndpoint endpoint, Processor processor) throws UnknownHostException, AlreadyConnectedException, IOException {
        super(endpoint, processor);
        device = new BrickMaster(endpoint.getUid(),endpoint.getSharedConnection().getConnection());

        if(endpoint.getCallback()==null || endpoint.getCallback().equals("")){
            device.addStackCurrentListener(this);
            device.addStackVoltageListener(this);
            device.addUSBVoltageListener(this);
            device.addStackCurrentReachedListener(this);
            device.addStackVoltageReachedListener(this);
            device.addUSBVoltageReachedListener(this);
            
        }else{
            String[] callbacks = endpoint.getCallback().split(",");
            for (String callback : callbacks) {
                if(callback.equals("StackCurrentListener")) device.addStackCurrentListener(this);
                if(callback.equals("StackVoltageListener")) device.addStackVoltageListener(this);
                if(callback.equals("USBVoltageListener")) device.addUSBVoltageListener(this);
                if(callback.equals("StackCurrentReachedListener")) device.addStackCurrentReachedListener(this);
                if(callback.equals("StackVoltageReachedListener")) device.addStackVoltageReachedListener(this);
                if(callback.equals("USBVoltageReachedListener")) device.addUSBVoltageReachedListener(this);
                
            }
        }
    }
    
    
	@Override
    public void stackCurrent(int current) {
        LOG.trace("stackCurrent()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("CALLBACK", BrickMaster.CALLBACK_STACK_CURRENT);
            exchange.getIn().setHeader("current", current);
            
            
            // ADD BODY
            exchange.getIn().setBody("stack_current");;
            
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
    public void stackVoltage(int voltage) {
        LOG.trace("stackVoltage()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("CALLBACK", BrickMaster.CALLBACK_STACK_VOLTAGE);
            exchange.getIn().setHeader("voltage", voltage);
            
            
            // ADD BODY
            exchange.getIn().setBody("stack_voltage");;
            
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
    public void usbVoltage(int voltage) {
        LOG.trace("USBVoltage()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("CALLBACK", BrickMaster.CALLBACK_USB_VOLTAGE);
            exchange.getIn().setHeader("voltage", voltage);
            
            
            // ADD BODY
            exchange.getIn().setBody("usb_voltage");;
            
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
    public void stackCurrentReached(int current) {
        LOG.trace("stackCurrentReached()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("CALLBACK", BrickMaster.CALLBACK_STACK_CURRENT_REACHED);
            exchange.getIn().setHeader("current", current);
            
            
            // ADD BODY
            exchange.getIn().setBody("stack_current_reached");;
            
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
    public void stackVoltageReached(int voltage) {
        LOG.trace("stackVoltageReached()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("CALLBACK", BrickMaster.CALLBACK_STACK_VOLTAGE_REACHED);
            exchange.getIn().setHeader("voltage", voltage);
            
            
            // ADD BODY
            exchange.getIn().setBody("stack_voltage_reached");;
            
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
    public void usbVoltageReached(int voltage) {
        LOG.trace("USBVoltageReached()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("CALLBACK", BrickMaster.CALLBACK_USB_VOLTAGE_REACHED);
            exchange.getIn().setHeader("voltage", voltage);
            
            
            // ADD BODY
            exchange.getIn().setBody("usb_voltage_reached");;
            
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