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

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tinkerforge.BrickletTemperature;
import com.tinkerforge.BrickletTemperature.TemperatureListener;
import com.tinkerforge.IPConnection;

/**
 * 
 *  {@link MockDevice} under test
 *
 */
public class MockDeviceTest {

    private static final Logger log = LoggerFactory.getLogger(MockDeviceTest.class);
    
    int callbackCount;
    
    /**
     * Testing the Mocked Device generating Callbacks
     * @throws Exception
     */
    @Test
    public void testMockDeviceStartStop() throws Exception {
        MockDevice mockDevice = new MockDevice();
        IPConnection ipConnection = new IPConnection(); 
        BrickletTemperature bricklet = new BrickletTemperature("123", ipConnection); 
        
        callbackCount = 0;
        bricklet.addTemperatureListener(new TemperatureListener() {
            @Override
            public void temperature(short temperature) {
                log.debug("receive temperature "+temperature);
                callbackCount++;
            }
        });
        
        // Mock Activation
        mockDevice.startCallbackListenerThread(ipConnection, bricklet, (byte)200, 500, 20);
        Thread.sleep(2300);
        Assert.assertEquals("Callback Count within 2300ms must 5 times called" ,  5 ,callbackCount);

        // Mock Stop
        mockDevice.stopCallbackListenerThread(ipConnection, bricklet);
        Thread.sleep(2000);
        Assert.assertEquals("after stop not have to get callbacks" ,  5 ,callbackCount);
        
    }
    
}
