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

import com.tinkerforge.BrickletHallEffect;

/**
 * Detects presence of magnetic field
 */
public class HallEffectEndpoint extends TinkerforgeEndpoint<HallEffectConsumer, HallEffectProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(HallEffectEndpoint.class);
    
    private Boolean resetCounter;
    private Short edgeType;
    private Short debounce;
    private Long edges;
    private Long period;

        
    public HallEffectEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new HallEffectProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new HallEffectConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletHallEffect device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletHallEffect device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "getValue":
                response = device.getValue();
                break;

            case "getEdgeCount":
                response = device.getEdgeCount(
                        getValue(boolean.class, "resetCounter", m, getResetCounter())
                    );
                break;

            case "setEdgeCountConfig":
                device.setEdgeCountConfig(
                        getValue(short.class, "edgeType", m, getEdgeType()),
                        getValue(short.class, "debounce", m, getDebounce())
                    );
                break;

            case "getEdgeCountConfig":
                response = device.getEdgeCountConfig();
                break;

            case "setEdgeInterrupt":
                device.setEdgeInterrupt(
                        getValue(long.class, "edges", m, getEdges())
                    );
                break;

            case "getEdgeInterrupt":
                response = device.getEdgeInterrupt();
                break;

            case "setEdgeCountCallbackPeriod":
                device.setEdgeCountCallbackPeriod(
                        getValue(long.class, "period", m, getPeriod())
                    );
                break;

            case "getEdgeCountCallbackPeriod":
                response = device.getEdgeCountCallbackPeriod();
                break;

            case "edgeInterrupt":
                response = device.edgeInterrupt();
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
     * Returns the current value of the edge counter. You can configure
     * edge type (rising, falling, both) that is counted with
     * :func:`SetEdgeCountConfig`.
     * 
     * If you set the reset counter to *true*, the count is set back to 0
     * directly after it is read.
     * 
     */
    public Boolean getResetCounter(){
        return resetCounter;
    }

    public void setResetCounter(Boolean resetCounter){
        this.resetCounter = resetCounter;
    }

    /**
     * 
     * The edge type parameter configures if rising edges, falling edges or 
     * both are counted. Possible edge types are:
     * 
     * * 0 = rising (default)
     * * 1 = falling
     * * 2 = both
     * 
     * A magnetic field of 35 Gauss (3.5mT) or greater causes a falling edge and a
     * magnetic field of 25 Gauss (2.5mT) or smaller causes a rising edge.
     * 
     * If a magnet comes near the Bricklet the signal goes low (falling edge), if
     * a magnet is removed from the vicinity the signal goes high (rising edge).
     * 
     * The debounce time is given in ms.
     * 
     * Configuring an edge counter resets its value to 0.
     * 
     * If you don't know what any of this means, just leave it at default. The
     * default configuration is very likely OK for you.
     * 
     * Default values: 0 (edge type) and 100ms (debounce time)
     * 
     */
    public Short getEdgeType(){
        return edgeType;
    }

    public void setEdgeType(Short edgeType){
        this.edgeType = edgeType;
    }

    /**
     * 
     * The edge type parameter configures if rising edges, falling edges or 
     * both are counted. Possible edge types are:
     * 
     * * 0 = rising (default)
     * * 1 = falling
     * * 2 = both
     * 
     * A magnetic field of 35 Gauss (3.5mT) or greater causes a falling edge and a
     * magnetic field of 25 Gauss (2.5mT) or smaller causes a rising edge.
     * 
     * If a magnet comes near the Bricklet the signal goes low (falling edge), if
     * a magnet is removed from the vicinity the signal goes high (rising edge).
     * 
     * The debounce time is given in ms.
     * 
     * Configuring an edge counter resets its value to 0.
     * 
     * If you don't know what any of this means, just leave it at default. The
     * default configuration is very likely OK for you.
     * 
     * Default values: 0 (edge type) and 100ms (debounce time)
     * 
     */
    public Short getDebounce(){
        return debounce;
    }

    public void setDebounce(Short debounce){
        this.debounce = debounce;
    }

    /**
     * 
     * Sets the number of edges until an interrupt is invoked.
     * 
     * If *edges* is set to n, an interrupt is invoked for every n-th detected edge.
     * 
     * If *edges* is set to 0, the interrupt is disabled.
     * 
     * Default value is 0.
     * 
     */
    public Long getEdges(){
        return edges;
    }

    public void setEdges(Long edges){
        this.edges = edges;
    }

    /**
     * 
     * Sets the period in ms with which the :func:`EdgeCount` callback is triggered
     * periodically. A value of 0 turns the callback off.
     * 
     * :func:`EdgeCount` is only triggered if the edge count has changed since the
     * last triggering.
     * 
     * The default value is 0.
     * 
     */
    public Long getPeriod(){
        return period;
    }

    public void setPeriod(Long period){
        this.period = period;
    }



}
