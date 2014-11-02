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
import com.tinkerforge.BrickIMU;

import com.tinkerforge.BrickIMU.AccelerationListener;
import com.tinkerforge.BrickIMU.MagneticFieldListener;
import com.tinkerforge.BrickIMU.AngularVelocityListener;
import com.tinkerforge.BrickIMU.AllDataListener;
import com.tinkerforge.BrickIMU.OrientationListener;
import com.tinkerforge.BrickIMU.QuaternionListener;;

public class IMUConsumer extends TinkerforgeConsumer<IMUEndpoint, BrickIMU> implements AccelerationListener, MagneticFieldListener, AngularVelocityListener, AllDataListener, OrientationListener, QuaternionListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(IMUConsumer.class);
    
    public IMUConsumer(IMUEndpoint endpoint, Processor processor) throws UnknownHostException, AlreadyConnectedException, IOException {
        super(endpoint, processor);
        device = new BrickIMU(endpoint.getUid(),endpoint.getSharedConnection().getConnection());

        if(endpoint.getCallback()==null || endpoint.getCallback().equals("")){
            device.addAccelerationListener(this);
            device.addMagneticFieldListener(this);
            device.addAngularVelocityListener(this);
            device.addAllDataListener(this);
            device.addOrientationListener(this);
            device.addQuaternionListener(this);
            
        }else{
            String[] callbacks = endpoint.getCallback().split(",");
            for (String callback : callbacks) {
                if(callback.equals("AccelerationListener")) device.addAccelerationListener(this);
                if(callback.equals("MagneticFieldListener")) device.addMagneticFieldListener(this);
                if(callback.equals("AngularVelocityListener")) device.addAngularVelocityListener(this);
                if(callback.equals("AllDataListener")) device.addAllDataListener(this);
                if(callback.equals("OrientationListener")) device.addOrientationListener(this);
                if(callback.equals("QuaternionListener")) device.addQuaternionListener(this);
                
            }
        }
    }
    
    
	@Override
    public void acceleration(short x, short y, short z) {
        LOG.trace("acceleration()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("CALLBACK", BrickIMU.CALLBACK_ACCELERATION);
            exchange.getIn().setHeader("x", x);
            exchange.getIn().setHeader("y", y);
            exchange.getIn().setHeader("z", z);
            
            
            // ADD BODY
            exchange.getIn().setBody("acceleration");;
            
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
    public void magneticField(short x, short y, short z) {
        LOG.trace("magneticField()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("CALLBACK", BrickIMU.CALLBACK_MAGNETIC_FIELD);
            exchange.getIn().setHeader("x", x);
            exchange.getIn().setHeader("y", y);
            exchange.getIn().setHeader("z", z);
            
            
            // ADD BODY
            exchange.getIn().setBody("magnetic_field");;
            
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
    public void angularVelocity(short x, short y, short z) {
        LOG.trace("angularVelocity()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("CALLBACK", BrickIMU.CALLBACK_ANGULAR_VELOCITY);
            exchange.getIn().setHeader("x", x);
            exchange.getIn().setHeader("y", y);
            exchange.getIn().setHeader("z", z);
            
            
            // ADD BODY
            exchange.getIn().setBody("angular_velocity");;
            
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
    public void allData(short acc_x, short acc_y, short acc_z, short mag_x, short mag_y, short mag_z, short ang_x, short ang_y, short ang_z, short temperature) {
        LOG.trace("allData()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("CALLBACK", BrickIMU.CALLBACK_ALL_DATA);
            exchange.getIn().setHeader("acc_x", acc_x);
            exchange.getIn().setHeader("acc_y", acc_y);
            exchange.getIn().setHeader("acc_z", acc_z);
            exchange.getIn().setHeader("mag_x", mag_x);
            exchange.getIn().setHeader("mag_y", mag_y);
            exchange.getIn().setHeader("mag_z", mag_z);
            exchange.getIn().setHeader("ang_x", ang_x);
            exchange.getIn().setHeader("ang_y", ang_y);
            exchange.getIn().setHeader("ang_z", ang_z);
            exchange.getIn().setHeader("temperature", temperature);
            
            
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
    public void orientation(short roll, short pitch, short yaw) {
        LOG.trace("orientation()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("CALLBACK", BrickIMU.CALLBACK_ORIENTATION);
            exchange.getIn().setHeader("roll", roll);
            exchange.getIn().setHeader("pitch", pitch);
            exchange.getIn().setHeader("yaw", yaw);
            
            
            // ADD BODY
            exchange.getIn().setBody("orientation");;
            
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
    public void quaternion(float x, float y, float z, float w) {
        LOG.trace("quaternion()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("CALLBACK", BrickIMU.CALLBACK_QUATERNION);
            exchange.getIn().setHeader("x", x);
            exchange.getIn().setHeader("y", y);
            exchange.getIn().setHeader("z", z);
            exchange.getIn().setHeader("w", w);
            
            
            // ADD BODY
            exchange.getIn().setBody("quaternion");;
            
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