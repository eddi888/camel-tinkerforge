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
import org.apache.camel.spi.UriEndpoint;
import org.atomspace.camel.component.tinkerforge.TinkerforgeComponent;
import org.atomspace.camel.component.tinkerforge.TinkerforgeEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tinkerforge.BrickletIndustrialAnalogOut;

/**
 * Generates configurable DC voltage and current, 0V to 10V and 4mA to 20mA
 */
@UriEndpoint(scheme = "tinkerforgegen", syntax = "tinkerforgegen:[host[:port]/]industrialanalogout", consumerClass = IndustrialAnalogOutConsumer.class, label = "iot", title = "Tinkerforge")
public class IndustrialAnalogOutEndpoint extends TinkerforgeEndpoint<IndustrialAnalogOutConsumer, IndustrialAnalogOutProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(IndustrialAnalogOutEndpoint.class);

    public static final String VOLTAGE="voltage";
    public static final String CURRENT="current";
    public static final String VOLTAGERANGE="voltageRange";
    public static final String CURRENTRANGE="currentRange";

    
    private Integer voltage;
    private Integer current;
    private Short voltageRange;
    private Short currentRange;

        
    public IndustrialAnalogOutEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new IndustrialAnalogOutProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new IndustrialAnalogOutConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletIndustrialAnalogOut device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletIndustrialAnalogOut device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "enable":
                device.enable();
                break;

            case "disable":
                device.disable();
                break;

            case "isEnabled":
                response = device.isEnabled();
                break;

            case "setVoltage":
                device.setVoltage(
                        getValue(int.class, "voltage", m, getVoltage())
                    );
                break;

            case "getVoltage":
                response = device.getVoltage();
                break;

            case "setCurrent":
                device.setCurrent(
                        getValue(int.class, "current", m, getCurrent())
                    );
                break;

            case "getCurrent":
                response = device.getCurrent();
                break;

            case "setConfiguration":
                device.setConfiguration(
                        getValue(short.class, "voltageRange", m, getVoltageRange()),
                        getValue(short.class, "currentRange", m, getCurrentRange())
                    );
                break;

            case "getConfiguration":
                response = device.getConfiguration();
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
     * Sets the output voltage in mV.
     * 
     * The output voltage and output current are linked. Changing the output voltage
     * also changes the output current.
     * 
     */
    public Integer getVoltage(){
        return voltage;
    }

    public void setVoltage(Integer voltage){
        this.voltage = voltage;
    }

    /**
     * 
     * Sets the output current in µA.
     * 
     * The output current and output voltage are linked. Changing the output current
     * also changes the output voltage.
     * 
     */
    public Integer getCurrent(){
        return current;
    }

    public void setCurrent(Integer current){
        this.current = current;
    }

    /**
     * 
     * Configures the voltage and current range.
     * 
     * Possible voltage ranges are:
     * 
     * * 0V to 5V
     * * 0V to 10V
     * 
     * Possible current ranges are:
     * 
     * * 4mA to 20mA
     * * 0mA to 20mA
     * * 0mA to 24mA
     * 
     * The resolution will always be 12 bit. This means, that the
     * precision is higher with a smaller range.
     * 
     */
    public Short getVoltageRange(){
        return voltageRange;
    }

    public void setVoltageRange(Short voltageRange){
        this.voltageRange = voltageRange;
    }

    /**
     * 
     * Configures the voltage and current range.
     * 
     * Possible voltage ranges are:
     * 
     * * 0V to 5V
     * * 0V to 10V
     * 
     * Possible current ranges are:
     * 
     * * 4mA to 20mA
     * * 0mA to 20mA
     * * 0mA to 24mA
     * 
     * The resolution will always be 12 bit. This means, that the
     * precision is higher with a smaller range.
     * 
     */
    public Short getCurrentRange(){
        return currentRange;
    }

    public void setCurrentRange(Short currentRange){
        this.currentRange = currentRange;
    }



}
