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
package org.atomspace.camel.component.tinkerforge;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import com.tinkerforge.BrickletMotionDetector;

/**
 * Integration Test for the Hardware on my Table. 
 * For manual interacting with the sensors and see its working really.
 *
 */
public class TinkerforgeIntegrationTest extends CamelTestSupport {

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                
                // pair MotionDetection and DualRelay
                from("tinkerforge://MotionDetector?uid=oTu")
                    .choice()
                        .when(header("CALLBACK").isEqualTo(BrickletMotionDetector.CALLBACK_MOTION_DETECTED))
                            .to("tinkerforge://DualRelay?uid=kPu&method=setMonoflop(2, true, 500)")
                            
                        .when(header("CALLBACK").isEqualTo(BrickletMotionDetector.CALLBACK_DETECTION_CYCLE_ENDED))
                            .to("tinkerforge://DualRelay?uid=kPu&method=setSelectedState(2, false)")
                            
                        .endChoice()
                    .to("mock:result");
                
            }
        };
    }
    
    
    
    @Test
    public void testTinkerforge() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMinimumMessageCount(2);       
        Thread.sleep(60000);
        assertMockEndpointsSatisfied();
    }
    
}
