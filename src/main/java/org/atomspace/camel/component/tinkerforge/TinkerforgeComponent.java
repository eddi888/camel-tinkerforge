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

import java.net.URI;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;
import org.atomspace.camel.component.tinkerforge.device.DualRelayEndpoint;
import org.atomspace.camel.component.tinkerforge.device.MockEndpoint;
import org.atomspace.camel.component.tinkerforge.device.MotionDetectorEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents the component that manages {@link TinkerforgeEndpoint}.
 */
public class TinkerforgeComponent extends DefaultComponent {
    
    private static final Logger LOG = LoggerFactory.getLogger(TinkerforgeComponent.class);
    
    private Map<String, SharedConnection> connectionTable = new Hashtable<String, SharedConnection>();
    
    public TinkerforgeComponent(CamelContext context) {
        super(context);
    }
    
    public TinkerforgeComponent() {
        super();
        LOG.trace("TinkerforgeComponent()");
    }

    public static final String DEFAULT_PROTOCOL = "tcp";
    public static final String DEFAULT_HOST = "localhost";
    public static final int DEFAULT_PORT = 4223;
    
    private static final String URI_ERROR = "Invalid URI. Format must be of the form tinkerforge:[host[:port]/]brickletType?[options...]";
    
    
    protected Endpoint createEndpoint(String endpointUri, String remaining, Map<String, Object> parameters) throws Exception {
        LOG.trace("BEGIN createEndpoint(String endpointUri='"+endpointUri+"', String remaining='"+remaining+"', Map<String, Object> parameters='"+parameters+"' )");
        
        String protocol = DEFAULT_PROTOCOL;
        String host = DEFAULT_HOST;
        int port = DEFAULT_PORT;
        String brickletType;
        
        // DYNAMIC ENDPOINT WITHOUT CONNECTION HOST AND PORT USE DEFAULT localhost:
        if(remaining.contains("/")){
            URI uri = new URI("tcp://"+remaining);
            
            protocol = uri.getScheme();
            if (protocol == null) {
                throw new IllegalArgumentException(URI_ERROR);
            }
            
            host = uri.getHost();
            if (host == null) {
                host = DEFAULT_HOST;
            }
            
            port = uri.getPort() == -1 ? DEFAULT_PORT : uri.getPort();
            
            if (uri.getPath() == null || uri.getPath().trim().length() == 0) {
                throw new IllegalArgumentException(URI_ERROR);
            }
            brickletType = uri.getPath().substring(1).toLowerCase();
        }else{
            protocol = "tcp";
            host = DEFAULT_HOST;
            port = DEFAULT_PORT;
            brickletType = remaining.toLowerCase();
        }
        
        TinkerforgeEndpoint endpoint; 
        
        switch (brickletType) {
        
            case "ambientlight"             :       throw new Exception("Bricklet-Type AmbientLight not supported yet.");
            case "analogin"                 :       throw new Exception("Bricklet-Type AnalogIn not supported yet.");
            case "analogout"                :       throw new Exception("Bricklet-Type AnalogOut not supported yet.");
            case "barometer"                :       throw new Exception("Bricklet-Type Barometer not supported yet.");
            case "breakout"                 :       throw new Exception("Bricklet-Type Breakout not supported yet.");
            case "color"                    :       throw new Exception("Bricklet-Type Color not supported yet.");
            case "current12"                :       throw new Exception("Bricklet-Type Current12 not supported yet.");
            case "current25"                :       throw new Exception("Bricklet-Type Current25 not supported yet.");
            case "distanceir"               :       throw new Exception("Bricklet-Type DistanceIR not supported yet.");
            case "distanceus"               :       throw new Exception("Bricklet-Type DistanceUS not supported yet.");
            case "dualbutton"               :       throw new Exception("Bricklet-Type DualButton not supported yet.");
            case "dualrelay"                :       endpoint = new DualRelayEndpoint(endpointUri, this); break;
            case "gps"                      :       throw new Exception("Bricklet-Type GPS not supported yet.");
            case "halleffect"               :       throw new Exception("Bricklet-Type HallEffect not supported yet.");
            case "humidity"                 :       throw new Exception("Bricklet-Type Humidity not supported yet.");
            
            case "industrialdigitalin4"     :       throw new Exception("Bricklet-Type IndustrialDigitalIn4 not supported yet.");
            case "industrialdigitalout4"    :       throw new Exception("Bricklet-Type IndustrialDigitalOut4 not supported yet.");
            case "industrialdual020ma"      :       throw new Exception("Bricklet-Type IndustrialDual020mA not supported yet.");
            case "industrialquadrelay"      :       throw new Exception("Bricklet-Type IndustrialQuadRelay not supported yet.");
            case "io-16"                    :       throw new Exception("Bricklet-Type IO-16 not supported yet.");
            case "io-4"                     :       throw new Exception("Bricklet-Type IO-4 not supported yet.");
            case "joystick"                 :       throw new Exception("Bricklet-Type Joystick not supported yet.");
            case "lcd16x2"                  :       throw new Exception("Bricklet-Type LCD16x2 not supported yet.");
            case "lcd20x4"                  :       throw new Exception("Bricklet-Type LCD20x4 not supported yet.");
            case "ledstrip"                 :       throw new Exception("Bricklet-Type LEDStrip not supported yet.");
            case "line"                     :       throw new Exception("Bricklet-Type Line not supported yet.");
            case "linearpoti"               :       throw new Exception("Bricklet-Type LinearPoti not supported yet.");
            case "mock"                     :       endpoint = new MockEndpoint(endpointUri, this); break;
            case "moisture"                 :       throw new Exception("Bricklet-Type Moisture not supported yet.");
            case "motiondetector"           :       endpoint = new MotionDetectorEndpoint(endpointUri, this); break;
            case "multitouch"               :       throw new Exception("Bricklet-Type MultiTouch not supported yet.");
            
            case "nfcrfid"                  :       throw new Exception("Bricklet-Type NFCRFID not supported yet.");
            case "piezobuzzer"              :       throw new Exception("Bricklet-Type PiezoBuzzer not supported yet.");
            case "piezospeaker"             :       throw new Exception("Bricklet-Type PiezoSpeaker not supported yet.");
            case "ptc"                      :       throw new Exception("Bricklet-Type PTC not supported yet.");
            case "remoteswitch"             :       throw new Exception("Bricklet-Type RemoteSwitch not supported yet.");
            case "rotaryencoder"            :       throw new Exception("Bricklet-Type RotaryEncoder not supported yet.");
            case "rotarypoti"               :       throw new Exception("Bricklet-Type RotaryPoti not supported yet.");
            case "segmentdisplay4x7"        :       throw new Exception("Bricklet-Type SegmentDisplay4x7 not supported yet.");
            case "solidstaterelay"          :       throw new Exception("Bricklet-Type SolidStateRelay not supported yet.");
            case "soundintensity"           :       throw new Exception("Bricklet-Type SoundIntensity not supported yet.");
            case "temperature"              :       throw new Exception("Bricklet-Type Temperature not supported yet.");
            case "temperatureir"            :       throw new Exception("Bricklet-Type TemperatureIR not supported yet.");
            case "tilt"                     :       throw new Exception("Bricklet-Type Tilt not supported yet.");
            case "voltage"                  :       throw new Exception("Bricklet-Type Voltage not supported yet.");
            case "voltagecurrent"           :       throw new Exception("Bricklet-Type VoltageCurrent not supported yet.");
            default                         :       throw new Exception("Unknown Bricklet-Type.");
            
        }
        
        
        
        // Parameter for shared TinkerforgeConnection
        String connectionTableKey = host+":"+port;
        SharedConnection sharedConnection = connectionTable.get(connectionTableKey);
        if(sharedConnection==null){
            setProperties(endpoint, parameters);
            sharedConnection = new SharedConnection(host,port,endpoint.getSecret(),endpoint.isAutoReconnect(),endpoint.getTimeout());
            connectionTable.put(connectionTableKey, sharedConnection);
            endpoint.setSharedConnection(sharedConnection);
        }else{
            parameters.put("sharedConnection", sharedConnection);
            parameters.put("timeout", sharedConnection.getTimeout());
            parameters.put("autoReconnect", sharedConnection.isAutoReconnect());
            parameters.put("secret", sharedConnection.getSecret());
            setProperties(endpoint, parameters);
        }
        
        
        
        LOG.trace("END return endpoint='"+endpoint+"'");
        return endpoint;
    }
    
    @Override
    protected void doShutdown() throws Exception {
        Collection<SharedConnection> connections = connectionTable.values();
        for (SharedConnection connection : connections) {
            connection.disconnect();
        }
        super.doShutdown();
    }
}
