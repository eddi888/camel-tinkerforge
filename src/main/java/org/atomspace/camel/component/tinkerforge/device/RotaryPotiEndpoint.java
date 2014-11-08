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

import com.tinkerforge.BrickletRotaryPoti;

public class RotaryPotiEndpoint extends TinkerforgeEndpoint<RotaryPotiConsumer, RotaryPotiProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(RotaryPotiEndpoint.class);
    
    private Long period;
    private Long period2;
    private Character option;
    private Short min;
    private Short max;
    private Character option2;
    private Integer min2;
    private Integer max2;
    private Long debounce;

        
    public RotaryPotiEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new RotaryPotiProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new RotaryPotiConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickletRotaryPoti device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickletRotaryPoti device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "getPosition":
                response = device.getPosition();
                break;

            case "getAnalogValue":
                response = device.getAnalogValue();
                break;

            case "setPositionCallbackPeriod":
                device.setPositionCallbackPeriod(
                        getValue(long.class, "period", m, getPeriod())
                    );
                break;

            case "getPositionCallbackPeriod":
                response = device.getPositionCallbackPeriod();
                break;

            case "setAnalogValueCallbackPeriod":
                device.setAnalogValueCallbackPeriod(
                        getValue(long.class, "period2", m, getPeriod2())
                    );
                break;

            case "getAnalogValueCallbackPeriod":
                response = device.getAnalogValueCallbackPeriod();
                break;

            case "setPositionCallbackThreshold":
                device.setPositionCallbackThreshold(
                        getValue(char.class, "option", m, getOption()),
                        getValue(short.class, "min", m, getMin()),
                        getValue(short.class, "max", m, getMax())
                    );
                break;

            case "getPositionCallbackThreshold":
                response = device.getPositionCallbackThreshold();
                break;

            case "setAnalogValueCallbackThreshold":
                device.setAnalogValueCallbackThreshold(
                        getValue(char.class, "option2", m, getOption2()),
                        getValue(int.class, "min2", m, getMin2()),
                        getValue(int.class, "max2", m, getMax2())
                    );
                break;

            case "getAnalogValueCallbackThreshold":
                response = device.getAnalogValueCallbackThreshold();
                break;

            case "setDebouncePeriod":
                device.setDebouncePeriod(
                        getValue(long.class, "debounce", m, getDebounce())
                    );
                break;

            case "getDebouncePeriod":
                response = device.getDebouncePeriod();
                break;

            case "getIdentity":
                response = device.getIdentity();
                break;


            default: throw new Exception("unknown function '"+function+"'");
            
        }
        
        return response;
    }
    
    
    public Long getPeriod(){
        return period;
    }

    public void setPeriod(Long period){
        this.period = period;
    }

    public Long getPeriod2(){
        return period2;
    }

    public void setPeriod2(Long period2){
        this.period2 = period2;
    }

    public Character getOption(){
        return option;
    }

    public void setOption(Character option){
        this.option = option;
    }

    public Short getMin(){
        return min;
    }

    public void setMin(Short min){
        this.min = min;
    }

    public Short getMax(){
        return max;
    }

    public void setMax(Short max){
        this.max = max;
    }

    public Character getOption2(){
        return option2;
    }

    public void setOption2(Character option2){
        this.option2 = option2;
    }

    public Integer getMin2(){
        return min2;
    }

    public void setMin2(Integer min2){
        this.min2 = min2;
    }

    public Integer getMax2(){
        return max2;
    }

    public void setMax2(Integer max2){
        this.max2 = max2;
    }

    public Long getDebounce(){
        return debounce;
    }

    public void setDebounce(Long debounce){
        this.debounce = debounce;
    }



}
