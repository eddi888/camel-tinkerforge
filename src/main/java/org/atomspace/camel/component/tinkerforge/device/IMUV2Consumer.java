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

import com.tinkerforge.BrickIMUV2;

import com.tinkerforge.BrickIMUV2.AccelerationListener;
import com.tinkerforge.BrickIMUV2.MagneticFieldListener;
import com.tinkerforge.BrickIMUV2.AngularVelocityListener;
import com.tinkerforge.BrickIMUV2.TemperatureListener;
import com.tinkerforge.BrickIMUV2.LinearAccelerationListener;
import com.tinkerforge.BrickIMUV2.GravityVectorListener;
import com.tinkerforge.BrickIMUV2.OrientationListener;
import com.tinkerforge.BrickIMUV2.QuaternionListener;
import com.tinkerforge.BrickIMUV2.AllDataListener;;

public class IMUV2Consumer extends TinkerforgeConsumer<IMUV2Endpoint, BrickIMUV2> implements AccelerationListener, MagneticFieldListener, AngularVelocityListener, TemperatureListener, LinearAccelerationListener, GravityVectorListener, OrientationListener, QuaternionListener, AllDataListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(IMUV2Consumer.class);
    
    public IMUV2Consumer(IMUV2Endpoint endpoint, Processor processor) throws Exception {
        super(endpoint, processor);

        device = new BrickIMUV2(endpoint.getUid(),endpoint.getSharedConnection().getConnection());
        endpoint.init(device);

        if(endpoint.getCallback()==null || endpoint.getCallback().equals("")){
            device.addAccelerationListener(this);
            device.addMagneticFieldListener(this);
            device.addAngularVelocityListener(this);
            device.addTemperatureListener(this);
            device.addLinearAccelerationListener(this);
            device.addGravityVectorListener(this);
            device.addOrientationListener(this);
            device.addQuaternionListener(this);
            device.addAllDataListener(this);
            
        }else{
            String[] callbacks = endpoint.getCallback().split(",");
            for (String callback : callbacks) {
                if(callback.equals("AccelerationListener")) device.addAccelerationListener(this);
                if(callback.equals("MagneticFieldListener")) device.addMagneticFieldListener(this);
                if(callback.equals("AngularVelocityListener")) device.addAngularVelocityListener(this);
                if(callback.equals("TemperatureListener")) device.addTemperatureListener(this);
                if(callback.equals("LinearAccelerationListener")) device.addLinearAccelerationListener(this);
                if(callback.equals("GravityVectorListener")) device.addGravityVectorListener(this);
                if(callback.equals("OrientationListener")) device.addOrientationListener(this);
                if(callback.equals("QuaternionListener")) device.addQuaternionListener(this);
                if(callback.equals("AllDataListener")) device.addAllDataListener(this);
                
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
            exchange.getIn().setHeader("fireBy", BrickIMUV2.CALLBACK_ACCELERATION);
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
            exchange.getIn().setHeader("fireBy", BrickIMUV2.CALLBACK_MAGNETIC_FIELD);
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
            exchange.getIn().setHeader("fireBy", BrickIMUV2.CALLBACK_ANGULAR_VELOCITY);
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
    public void temperature(byte temperature) {
        LOG.trace("temperature()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickIMUV2.CALLBACK_TEMPERATURE);
            exchange.getIn().setHeader("temperature", temperature);
            
            
            // ADD BODY
            exchange.getIn().setBody("temperature");;
            
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
    public void linearAcceleration(short x, short y, short z) {
        LOG.trace("linearAcceleration()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickIMUV2.CALLBACK_LINEAR_ACCELERATION);
            exchange.getIn().setHeader("x", x);
            exchange.getIn().setHeader("y", y);
            exchange.getIn().setHeader("z", z);
            
            
            // ADD BODY
            exchange.getIn().setBody("linear_acceleration");;
            
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
    public void gravityVector(short x, short y, short z) {
        LOG.trace("gravityVector()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickIMUV2.CALLBACK_GRAVITY_VECTOR);
            exchange.getIn().setHeader("x", x);
            exchange.getIn().setHeader("y", y);
            exchange.getIn().setHeader("z", z);
            
            
            // ADD BODY
            exchange.getIn().setBody("gravity_vector");;
            
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
    public void orientation(short heading, short roll, short pitch) {
        LOG.trace("orientation()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickIMUV2.CALLBACK_ORIENTATION);
            exchange.getIn().setHeader("heading", heading);
            exchange.getIn().setHeader("roll", roll);
            exchange.getIn().setHeader("pitch", pitch);
            
            
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
    public void quaternion(short w, short x, short y, short z) {
        LOG.trace("quaternion()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickIMUV2.CALLBACK_QUATERNION);
            exchange.getIn().setHeader("w", w);
            exchange.getIn().setHeader("x", x);
            exchange.getIn().setHeader("y", y);
            exchange.getIn().setHeader("z", z);
            
            
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
    
    @Override
    public void allData(short[] acceleration, short[] magnetic_field, short[] angular_velocity, short[] euler_angle, short[] quaternion, short[] linear_acceleration, short[] gravity_vector, byte temperature, short calibration_status) {
        LOG.trace("allData()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickIMUV2.CALLBACK_ALL_DATA);
            exchange.getIn().setHeader("acceleration", acceleration);
            exchange.getIn().setHeader("magnetic_field", magnetic_field);
            exchange.getIn().setHeader("angular_velocity", angular_velocity);
            exchange.getIn().setHeader("euler_angle", euler_angle);
            exchange.getIn().setHeader("quaternion", quaternion);
            exchange.getIn().setHeader("linear_acceleration", linear_acceleration);
            exchange.getIn().setHeader("gravity_vector", gravity_vector);
            exchange.getIn().setHeader("temperature", temperature);
            exchange.getIn().setHeader("calibration_status", calibration_status);
            
            
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
    
    

}