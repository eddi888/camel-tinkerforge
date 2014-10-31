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
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Assert;
import org.junit.Test;

/**
 * 
 *  {@link SharedConnection} under test
 *
 */
public class SharedConnectionTest extends CamelTestSupport {
    private static final String ENDPOINT01 = "tinkerforge://Mock?uid=aAa&autoReconnect=true&timeout=4000";
    private static final String ENDPOINT02 = "tinkerforge://Mock?uid=bBb&autoReconnect=false&timeout=5000";
    private static final String ENDPOINT03 = "tinkerforge://192.168.5.34:4444/Mock?uid=cCc&autoReconnect=true&timeout=6000";
    private static final String ENDPOINT04 = "tinkerforge://192.168.5.34:4444/Mock?uid=dDd&autoReconnect=false&timeout=7000";

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                
                // FIRST TINKGERFORGE BRICKLET GROUP
                from(ENDPOINT01)
                    .to("log:endpoint01")
                    .to("mock:result");
                
                from(ENDPOINT02)
                    .to("log:endpoint02")
                    .to("mock:result");
                
                // SECOUND TINKGERFORGE BRICKLET GROUP
                from(ENDPOINT03)
                    .to("log:endpoint03")
                    .to("mock:result");
                
                from(ENDPOINT04)
                    .to("log:endpoint04")
                    .to("mock:result");
            }
        };
    }

    @Test
    public void testTinkerforge() throws Exception {
        TinkerforgeEndpoint endpoint01 = (TinkerforgeEndpoint) context.getEndpoint(ENDPOINT01);
        TinkerforgeEndpoint endpoint02 = (TinkerforgeEndpoint) context.getEndpoint(ENDPOINT02);
        TinkerforgeEndpoint endpoint03 = (TinkerforgeEndpoint) context.getEndpoint(ENDPOINT03);
        TinkerforgeEndpoint endpoint04 = (TinkerforgeEndpoint) context.getEndpoint(ENDPOINT04);

        
        // FIRST BRICKLET GROUP HAVE TO HAVE SAME CONNECTION PARAMETER and SharedConnection
        Assert.assertEquals(endpoint01.getTimeout(), endpoint02.getTimeout());
        Assert.assertEquals(endpoint01.getSharedConnection(), endpoint02.getSharedConnection());
        Assert.assertEquals(endpoint01.isAutoReconnect(), endpoint02.isAutoReconnect());
        Assert.assertEquals(endpoint01.getTimeout(), endpoint02.getTimeout());

        Assert.assertEquals("aAa", endpoint01.getUid());
        Assert.assertEquals("bBb", endpoint02.getUid());


        // SECOUND BRICKLET GROUP HAVE TO HAVE SAME CONNECTION PARAMETER and SharedConnection
        Assert.assertEquals(endpoint03.getTimeout(), endpoint04.getTimeout());
        Assert.assertEquals(endpoint03.getSharedConnection(), endpoint04.getSharedConnection());
        Assert.assertEquals(endpoint03.isAutoReconnect(), endpoint04.isAutoReconnect());
        Assert.assertEquals(endpoint03.getTimeout(), endpoint04.getTimeout());

        Assert.assertEquals("cCc", endpoint03.getUid());
        Assert.assertEquals("dDd", endpoint04.getUid());
        
        // FIRST AND SECOUND GROUP need different SharedConnection
        Assert.assertNotEquals(endpoint01.getSharedConnection(), endpoint03.getSharedConnection());
        
        
    }

}
