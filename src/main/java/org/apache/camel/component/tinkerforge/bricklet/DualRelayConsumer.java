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
package org.apache.camel.component.tinkerforge.bricklet;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.tinkerforge.TinkerforgeConsumer;
import org.apache.camel.component.tinkerforge.TinkerforgeEndpoint;

import com.tinkerforge.BrickletDualRelay;
import com.tinkerforge.BrickletDualRelay.MonoflopDoneListener;
import com.tinkerforge.BrickletMotionDetector;
import com.tinkerforge.IPConnection;
import com.tinkerforge.BrickletMotionDetector.DetectionCycleEndedListener;
import com.tinkerforge.BrickletMotionDetector.MotionDetectedListener;

public class DualRelayConsumer extends TinkerforgeConsumer<TinkerforgeEndpoint, BrickletDualRelay> implements MonoflopDoneListener {

    private final DualRelayEndpoint endpoint;
    
    public DualRelayConsumer(DualRelayEndpoint endpoint, Processor processor, IPConnection connection,String uid) {
        super(endpoint, processor);
        this.endpoint = endpoint;
        
        bricklet = new BrickletDualRelay(uid, connection);

    }

    @Override
    public void monoflopDone(short relay, boolean state) {
        System.out.println("monoflopDone");
        Exchange exchange = null;
        try {
            exchange = createExchange(endpoint, bricklet.getIdentity());
            
            // ADD HEADER
            exchange.getOut().setHeader("CALLBACK", BrickletDualRelay.CALLBACK_MONOFLOP_DONE);
            
            // ADD BODY
            exchange.getOut().setBody("monoflopDone");;
            
            getProcessor().process(exchange);
        } catch (Exception e) {
            getExceptionHandler().handleException("Error processing exchange", exchange, e);
        } finally {
            if (exchange != null && exchange.getException() != null) {
                getExceptionHandler().handleException("Error processing exchange", exchange, exchange.getException());
            }
        }
        System.out.println("monoflopDone");
    }
       

}
