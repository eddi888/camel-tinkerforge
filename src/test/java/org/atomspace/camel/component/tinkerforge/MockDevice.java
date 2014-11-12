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

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tinkerforge.BrickletTemperature;
import com.tinkerforge.Device;
import com.tinkerforge.IPConnection;
import com.tinkerforge.NotConnectedException;
import com.tinkerforge.TimeoutException;

/**
 * 
 * Tinkerforge mock device for simulate incomming data. 
 *
 * @see <a href="http://www.rapidpm.org/2014/05/12/mocking-iot-tinkerforge-sensors.html" target="_blank" >http://www.rapidpm.org/2014/05/12/mocking-iot-tinkerforge-sensors.html</a>
 * 
 *
 */
public class MockDevice {

    private static final Logger log = LoggerFactory.getLogger(MockDevice.class);
    
    public Map<String, Thread> threads = new Hashtable<String, Thread>();
    
    public List<Integer> getCallbackIndex(Device device) throws IllegalArgumentException, IllegalAccessException{
        List<Integer> index = new ArrayList<>();
        Field[] declaredFields = BrickletTemperature.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            String fieldName = declaredField.getName();
            log.trace("fieldName: "+fieldName);
            if (fieldName.startsWith("CALLBACK_") && !fieldName.endsWith("REACHED")) {
                declaredField.setAccessible(true);
                int callbackIndex = declaredField.getInt(device);
                index.add(callbackIndex);
            }
        }
        return index;
    }
    
    /**
     * start simulating incomming data.
     * 
     * @param ipcon
     * @param bricklet
     * @param startValue
     * @param interval
     * @param scattering
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws TimeoutException
     * @throws NotConnectedException
     */
    public <Bricklet extends Device> void startCallbackListenerThread(final IPConnection ipcon, final Device bricklet, final byte startValue, final int interval, final int scattering) throws NoSuchMethodException, IllegalArgumentException, IllegalAccessException, TimeoutException, NotConnectedException {
        List<Integer> callbackIndizes = getCallbackIndex(bricklet);
        
        Class<IPConnection> ipConnectionClass = IPConnection.class;
        final Method callDeviceListener = ipConnectionClass.getDeclaredMethod("callDeviceListener", Device.class, byte.class, byte[].class);
        callDeviceListener.setAccessible(true);

        //start thread for each callback event
        for (final int callbackIndex : callbackIndizes) {
            
            Thread thread = new Thread(new Runnable() {
                        
                        @Override
                        public void run() {
                            try {
                                Random random = new Random();
                                while (true) {
                                    
                                    //Generates values from scattering
                                    int randomDiff = 0;
                                    if(scattering>0) randomDiff = random.nextInt(scattering) - 1;
                                    
                                    //Invoke on device
                                    callDeviceListener.invoke(ipcon, bricklet, (byte) callbackIndex, new byte[]{0, 0, 0, 0, 0, 0, 0, 0, (byte) (startValue + randomDiff), 0});
                                    
                                    //wait interval
                                    Thread.sleep(interval);
                                    
                                }
                            } catch (IllegalAccessException | InvocationTargetException  e) {
                                e.printStackTrace();
                            } catch (InterruptedException e){ }
                        }
                    }              
                    
                    /* JDK8
                    () -> {
                    try {
                        Random random = new Random();
                        while (true) {
                            
                            //Generates values from scattering
                            int randomDiff = 0;
                            if(scattering>0) randomDiff = random.nextInt(scattering) - 1;
                            
                            //Invoke on device
                            callDeviceListener.invoke(ipcon, bricklet, (byte) callbackIndex, new byte[]{0, 0, 0, 0, 0, 0, 0, 0, (byte) (startValue + randomDiff), 0});
                            
                            //wait interval
                            Thread.sleep(interval);
                            
                        }
                    } catch (IllegalAccessException | InvocationTargetException  e) {
                        e.printStackTrace();
                    } catch (InterruptedException e){ }
                }*/
            );
            
            threads.put(bricklet.toString()+"-"+callbackIndex,thread);
            log.debug("start callback thread for "+bricklet.toString()+"-"+callbackIndex);
            thread.start();
        }
    }
    
    /**
     * start simulating incomming data.
     * 
     * @param ipcon
     * @param bricklet
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws TimeoutException
     * @throws NotConnectedException
     */
    public <Bricklet extends Device> void stopCallbackListenerThread(IPConnection ipcon, Device bricklet) throws IllegalArgumentException, IllegalAccessException, TimeoutException, NotConnectedException{
        List<Integer> callbackIndizes = getCallbackIndex(bricklet);
        for (int callbackIndex : callbackIndizes) {
            log.debug("stop callback thread for "+bricklet.toString()+"-"+callbackIndex);
            Thread thread = threads.get(bricklet.toString()+"-"+callbackIndex);
            if(thread!=null) thread.stop(); //TODO BETTER WAY
        }
    }
    
}
