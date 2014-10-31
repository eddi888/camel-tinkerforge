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
package org.apache.camel.component.tinkerforge;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class TinkerforgeComponentTest extends CamelTestSupport {

    @Test
    public void testTinkerforge() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMinimumMessageCount(1);       
        
        assertMockEndpointsSatisfied();
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                from("tinkerforge://Temperature?uid=ABC&autoReconnect&authenticate=secret&timeout=5000")
                  .to("tinkerforge://192.168.99.5:4567/IO4?uid=XYZ&autoReconnect&authenticate=secret&timeout=5000")
                  .to("tinkerforge:LCD20x4?uid=ABC")
                  .to("tinkerforge:192.168.99.5:4567/DualRelay?uid=ABC&autoReconnect=true&autoReconnect&authenticate=secret&timeout=5000")
                  .to("mock:result");
            }
        };
    }
    
    
}
