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

import org.apache.camel.Consumer;
import org.apache.camel.Endpoint;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.atomspace.camel.component.tinkerforge.TinkerforgeComponent;
import org.atomspace.camel.component.tinkerforge.TinkerforgeEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tinkerforge.BrickletMultiTouch;

/**
 * Capacitive touch sensor for 12 electrodes
 */
public class MultiTouchEndpoint extends TinkerforgeEndpoint<MultiTouchConsumer, MultiTouchProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(MultiTouchEndpoint.class);
    
    private Integer enabledElectrodes;
    private Short sensitivity;

        
    public MultiTouchEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new MultiTouchProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new MultiTouchConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletMultiTouch device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletMultiTouch device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "getTouchState":
                response = device.getTouchState();
                break;

            case "recalibrate":
                device.recalibrate();
                break;

            case "setElectrodeConfig":
                device.setElectrodeConfig(
                        getValue(int.class, "enabledElectrodes", m, getEnabledElectrodes())
                    );
                break;

            case "getElectrodeConfig":
                response = device.getElectrodeConfig();
                break;

            case "setElectrodeSensitivity":
                device.setElectrodeSensitivity(
                        getValue(short.class, "sensitivity", m, getSensitivity())
                    );
                break;

            case "getElectrodeSensitivity":
                response = device.getElectrodeSensitivity();
                break;

            case "getIdentity":
                response = device.getIdentity();
                break;


            default: throw new Exception("unknown function '"+function+"'");
            
        }
        
        return response;
    }
    
    
    /**
     * 
     * Enables/disables electrodes with a bitfield (see :func:`GetTouchState`).
     * 
     * *True* enables the electrode, *false* disables the electrode. A
     * disabled electrode will always return *false* as its state. If you
     * don't need all electrodes you can disable the electrodes that are
     * not needed.
     * 
     * It is recommended that you disable the proximity bit (bit 12) if
     * the proximity feature is not needed. This will reduce the amount of
     * traffic that is produced by the :func:`TouchState` callback.
     * 
     * Disabling electrodes will also reduce power consumption.
     * 
     * Default: 8191 = 0x1FFF = 0b1111111111111 (all electrodes enabled)
     * 
     */
    public Integer getEnabledElectrodes(){
        return enabledElectrodes;
    }

    public void setEnabledElectrodes(Integer enabledElectrodes){
        this.enabledElectrodes = enabledElectrodes;
    }

    /**
     * 
     * Sets the sensitivity of the electrodes. An electrode with a high sensitivity
     * will register a touch earlier then an electrode with a low sensitivity.
     * 
     * If you build a big electrode you might need to decrease the sensitivity, since
     * the area that can be charged will get bigger. If you want to be able to
     * activate an electrode from further away you need to increase the sensitivity.
     * 
     * After a new sensitivity is set, you likely want to call :func:`Recalibrate`
     * to calibrate the electrodes with the newly defined sensitivity.
     * 
     * The valid sensitivity value range is 5-201.
     * 
     * The default sensitivity value is 181.
     * 
     */
    public Short getSensitivity(){
        return sensitivity;
    }

    public void setSensitivity(Short sensitivity){
        this.sensitivity = sensitivity;
    }



}
