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
import org.atomspace.camel.component.tinkerforge.device.AccelerometerEndpoint;
import org.atomspace.camel.component.tinkerforge.device.AmbientLightEndpoint;
import org.atomspace.camel.component.tinkerforge.device.AmbientLightV2Endpoint;
import org.atomspace.camel.component.tinkerforge.device.AnalogInEndpoint;
import org.atomspace.camel.component.tinkerforge.device.AnalogInV2Endpoint;
import org.atomspace.camel.component.tinkerforge.device.AnalogOutEndpoint;
import org.atomspace.camel.component.tinkerforge.device.AnalogOutV2Endpoint;
import org.atomspace.camel.component.tinkerforge.device.BarometerEndpoint;
import org.atomspace.camel.component.tinkerforge.device.ColorEndpoint;
import org.atomspace.camel.component.tinkerforge.device.Current12Endpoint;
import org.atomspace.camel.component.tinkerforge.device.Current25Endpoint;
import org.atomspace.camel.component.tinkerforge.device.DCEndpoint;
import org.atomspace.camel.component.tinkerforge.device.DistanceIREndpoint;
import org.atomspace.camel.component.tinkerforge.device.DistanceUSEndpoint;
import org.atomspace.camel.component.tinkerforge.device.DualButtonEndpoint;
import org.atomspace.camel.component.tinkerforge.device.DualRelayEndpoint;
import org.atomspace.camel.component.tinkerforge.device.DustDetectorEndpoint;
import org.atomspace.camel.component.tinkerforge.device.GPSEndpoint;
import org.atomspace.camel.component.tinkerforge.device.HallEffectEndpoint;
import org.atomspace.camel.component.tinkerforge.device.HumidityEndpoint;
import org.atomspace.camel.component.tinkerforge.device.IMUEndpoint;
import org.atomspace.camel.component.tinkerforge.device.IMUV2Endpoint;
import org.atomspace.camel.component.tinkerforge.device.IO16Endpoint;
import org.atomspace.camel.component.tinkerforge.device.IO4Endpoint;
import org.atomspace.camel.component.tinkerforge.device.IndustrialAnalogOutEndpoint;
import org.atomspace.camel.component.tinkerforge.device.IndustrialDigitalIn4Endpoint;
import org.atomspace.camel.component.tinkerforge.device.IndustrialDigitalOut4Endpoint;
import org.atomspace.camel.component.tinkerforge.device.IndustrialDual020mAEndpoint;
import org.atomspace.camel.component.tinkerforge.device.IndustrialDualAnalogInEndpoint;
import org.atomspace.camel.component.tinkerforge.device.IndustrialQuadRelayEndpoint;
import org.atomspace.camel.component.tinkerforge.device.JoystickEndpoint;
import org.atomspace.camel.component.tinkerforge.device.LCD16x2Endpoint;
import org.atomspace.camel.component.tinkerforge.device.LCD20x4Endpoint;
import org.atomspace.camel.component.tinkerforge.device.LEDStripEndpoint;
import org.atomspace.camel.component.tinkerforge.device.LaserRangeFinderEndpoint;
import org.atomspace.camel.component.tinkerforge.device.LineEndpoint;
import org.atomspace.camel.component.tinkerforge.device.LinearPotiEndpoint;
import org.atomspace.camel.component.tinkerforge.device.LoadCellEndpoint;
import org.atomspace.camel.component.tinkerforge.device.MasterEndpoint;
import org.atomspace.camel.component.tinkerforge.device.MockEndpoint;
import org.atomspace.camel.component.tinkerforge.device.MoistureEndpoint;
import org.atomspace.camel.component.tinkerforge.device.MotionDetectorEndpoint;
import org.atomspace.camel.component.tinkerforge.device.MultiTouchEndpoint;
import org.atomspace.camel.component.tinkerforge.device.NFCRFIDEndpoint;
import org.atomspace.camel.component.tinkerforge.device.PTCEndpoint;
import org.atomspace.camel.component.tinkerforge.device.PiezoBuzzerEndpoint;
import org.atomspace.camel.component.tinkerforge.device.PiezoSpeakerEndpoint;
import org.atomspace.camel.component.tinkerforge.device.RS232Endpoint;
import org.atomspace.camel.component.tinkerforge.device.RemoteSwitchEndpoint;
import org.atomspace.camel.component.tinkerforge.device.RotaryEncoderEndpoint;
import org.atomspace.camel.component.tinkerforge.device.RotaryPotiEndpoint;
import org.atomspace.camel.component.tinkerforge.device.SegmentDisplay4x7Endpoint;
import org.atomspace.camel.component.tinkerforge.device.ServoEndpoint;
import org.atomspace.camel.component.tinkerforge.device.SolidStateRelayEndpoint;
import org.atomspace.camel.component.tinkerforge.device.SoundIntensityEndpoint;
import org.atomspace.camel.component.tinkerforge.device.StepperEndpoint;
import org.atomspace.camel.component.tinkerforge.device.TemperatureEndpoint;
import org.atomspace.camel.component.tinkerforge.device.TemperatureIREndpoint;
import org.atomspace.camel.component.tinkerforge.device.TiltEndpoint;
import org.atomspace.camel.component.tinkerforge.device.VoltageCurrentEndpoint;
import org.atomspace.camel.component.tinkerforge.device.VoltageEndpoint;
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
        
        TinkerforgeEndpoint<?, ?> endpoint; 
        
        switch (brickletType) {
        
            case "accelerometer"            :       endpoint = new AccelerometerEndpoint(endpointUri, this); break;
            case "ambientlight"             :       endpoint = new AmbientLightEndpoint(endpointUri, this); break;
            case "ambientlightV2"           :       endpoint = new AmbientLightV2Endpoint(endpointUri, this); break;
            case "analogin"                 :       endpoint = new AnalogInEndpoint(endpointUri, this); break;
            case "analoginV2"               :       endpoint = new AnalogInV2Endpoint(endpointUri, this); break;
            case "analogout"                :       endpoint = new AnalogOutEndpoint(endpointUri, this); break;
            case "analogoutV2"              :       endpoint = new AnalogOutV2Endpoint(endpointUri, this); break;
            case "barometer"                :       endpoint = new BarometerEndpoint(endpointUri, this); break;
            case "breakout"                 :       throw new Exception("Bricklet-Type Breakout not supported yet.");
            case "color"                    :       endpoint = new ColorEndpoint(endpointUri, this); break;
            case "current12"                :       endpoint = new Current12Endpoint(endpointUri, this); break;
            case "current25"                :       endpoint = new Current25Endpoint(endpointUri, this); break;
            case "dc"                       :       endpoint = new DCEndpoint(endpointUri, this); break;
            case "distanceir"               :       endpoint = new DistanceIREndpoint(endpointUri, this); break;
            case "distanceus"               :       endpoint = new DistanceUSEndpoint(endpointUri, this); break;
            case "dualbutton"               :       endpoint = new DualButtonEndpoint(endpointUri, this); break;
            case "dualrelay"                :       endpoint = new DualRelayEndpoint(endpointUri, this); break;
            case "dustdetector"             :       endpoint = new DustDetectorEndpoint(endpointUri, this); break;
            case "gps"                      :       endpoint = new GPSEndpoint(endpointUri, this); break;
            case "halleffect"               :       endpoint = new HallEffectEndpoint(endpointUri, this); break;
            case "humidity"                 :       endpoint = new HumidityEndpoint(endpointUri, this); break;
            case "imu"                      :       endpoint = new IMUEndpoint(endpointUri, this); break;
            
            case "imuV2"                    :       endpoint = new IMUV2Endpoint(endpointUri, this); break;
            case "industrialanalogout"      :       endpoint = new IndustrialAnalogOutEndpoint(endpointUri, this); break;
            case "industrialdigitalin4"     :       endpoint = new IndustrialDigitalIn4Endpoint(endpointUri, this); break;
            case "industrialdigitalout4"    :       endpoint = new IndustrialDigitalOut4Endpoint(endpointUri, this); break;
            case "industrialdual020ma"      :       endpoint = new IndustrialDual020mAEndpoint(endpointUri, this); break;
            case "industrialdualanalogin"   :       endpoint = new IndustrialDualAnalogInEndpoint(endpointUri, this); break;
            case "industrialquadrelay"      :       endpoint = new IndustrialQuadRelayEndpoint(endpointUri, this); break;
            case "io16"                     :       endpoint = new IO16Endpoint(endpointUri, this); break;
            case "io4"                      :       endpoint = new IO4Endpoint(endpointUri, this); break;
            case "joystick"                 :       endpoint = new JoystickEndpoint(endpointUri, this); break;
            case "laserrangefinder"         :       endpoint = new LaserRangeFinderEndpoint(endpointUri, this); break;
            case "lcd16x2"                  :       endpoint = new LCD16x2Endpoint(endpointUri, this); break;
            case "lcd20x4"                  :       endpoint = new LCD20x4Endpoint(endpointUri, this); break;
            case "ledstrip"                 :       endpoint = new LEDStripEndpoint(endpointUri, this); break;
            case "line"                     :       endpoint = new LineEndpoint(endpointUri, this); break;
            case "linearpoti"               :       endpoint = new LinearPotiEndpoint(endpointUri, this); break;
            case "loadcell"                 :       endpoint = new LoadCellEndpoint(endpointUri, this); break;
            case "master"                   :       endpoint = new MasterEndpoint(endpointUri, this); break;
            case "mock"                     :       endpoint = new MockEndpoint(endpointUri, this); break;
            case "moisture"                 :       endpoint = new MoistureEndpoint(endpointUri, this); break;
            case "motiondetector"           :       endpoint = new MotionDetectorEndpoint(endpointUri, this); break;
            case "multitouch"               :       endpoint = new MultiTouchEndpoint(endpointUri, this); break;
            
            case "nfcrfid"                  :       endpoint = new NFCRFIDEndpoint(endpointUri, this); break;
            case "piezobuzzer"              :       endpoint = new PiezoBuzzerEndpoint(endpointUri, this); break;
            case "piezospeaker"             :       endpoint = new PiezoSpeakerEndpoint(endpointUri, this); break;
            case "ptc"                      :       endpoint = new PTCEndpoint(endpointUri, this); break;
            case "remoteswitch"             :       endpoint = new RemoteSwitchEndpoint(endpointUri, this); break;
            case "rotaryencoder"            :       endpoint = new RotaryEncoderEndpoint(endpointUri, this); break;
            case "rotarypoti"               :       endpoint = new RotaryPotiEndpoint(endpointUri, this); break;
            case "rs232"                    :       endpoint = new RS232Endpoint(endpointUri, this); break;
            case "segmentdisplay4x7"        :       endpoint = new SegmentDisplay4x7Endpoint(endpointUri, this); break;
            case "servo"                    :       endpoint = new ServoEndpoint(endpointUri, this); break;
            case "solidstaterelay"          :       endpoint = new SolidStateRelayEndpoint(endpointUri, this); break;
            case "soundintensity"           :       endpoint = new SoundIntensityEndpoint(endpointUri, this); break;
            case "stepper"                  :       endpoint = new StepperEndpoint(endpointUri, this); break;
            case "temperature"              :       endpoint = new TemperatureEndpoint(endpointUri, this); break;
            case "temperatureir"            :       endpoint = new TemperatureIREndpoint(endpointUri, this); break;
            case "tilt"                     :       endpoint = new TiltEndpoint(endpointUri, this); break;
            case "voltage"                  :       endpoint = new VoltageEndpoint(endpointUri, this); break;
            case "voltagecurrent"           :       endpoint = new VoltageCurrentEndpoint(endpointUri, this); break;
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
