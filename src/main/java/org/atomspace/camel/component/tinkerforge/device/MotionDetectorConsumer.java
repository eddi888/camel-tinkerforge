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

import com.tinkerforge.BrickletMotionDetector;

import com.tinkerforge.BrickletMotionDetector.MotionDetectedListener;
import com.tinkerforge.BrickletMotionDetector.DetectionCycleEndedListener;;

public class MotionDetectorConsumer extends TinkerforgeConsumer<MotionDetectorEndpoint, BrickletMotionDetector> implements MotionDetectedListener, DetectionCycleEndedListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(MotionDetectorConsumer.class);
    
    public MotionDetectorConsumer(MotionDetectorEndpoint endpoint, Processor processor) throws Exception {
        super(endpoint, processor);

        device = new BrickletMotionDetector(endpoint.getUid(),endpoint.getSharedConnection().getConnection());
        endpoint.init(device);

        if(endpoint.getCallback()==null || endpoint.getCallback().equals("")){
            device.addMotionDetectedListener(this);
            device.addDetectionCycleEndedListener(this);
            
        }else{
            String[] callbacks = endpoint.getCallback().split(",");
            for (String callback : callbacks) {
                if(callback.equals("MotionDetectedListener")) device.addMotionDetectedListener(this);
                if(callback.equals("DetectionCycleEndedListener")) device.addDetectionCycleEndedListener(this);
                
            }
        }
    }
    
    
    @Override
    public void motionDetected() {
        LOG.trace("motionDetected()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickletMotionDetector.CALLBACK_MOTION_DETECTED);
            
            
            // ADD BODY
            exchange.getIn().setBody("motion_detected");;
            
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
    public void detectionCycleEnded() {
        LOG.trace("detectionCycleEnded()");
        
        Exchange exchange = null;
        try {
            exchange = createExchange();
            
            // ADD HEADER
            exchange.getIn().setHeader("fireBy", BrickletMotionDetector.CALLBACK_DETECTION_CYCLE_ENDED);
            
            
            // ADD BODY
            exchange.getIn().setBody("detection_cycle_ended");;
            
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