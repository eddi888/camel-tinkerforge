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

import java.net.URI;
import java.util.Map;

import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;

/**
 * Represents the component that manages {@link TinkerforgeEndpoint}.
 */
public class TinkerforgeComponent extends DefaultComponent {
    
    public static final String DEFAULT_PROTOCOL = "tcp";
    public static final String DEFAULT_HOST = "localhost";
    public static final int DEFAULT_PORT = 4223;
    
    private static final String URI_ERROR = "Invalid URI. Format must be of the form tinkerforge:[host[:port]/]brickletType?[options...]";
    
    protected Endpoint createEndpoint(String endpointUri, String remaining, Map<String, Object> parameters) throws Exception {
        System.out.println(" "+remaining+" ");
        
        String protocol = DEFAULT_PROTOCOL;
        String host = DEFAULT_HOST;
        int port = DEFAULT_PORT;
        String brickletType;
        
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
            brickletType = uri.getPath().substring(1);
            
        }else{
            
            protocol = "tcp";
            host = DEFAULT_HOST;
            port = DEFAULT_PORT;
            brickletType = remaining;
           
        }

        Endpoint endpoint = new TinkerforgeEndpoint(endpointUri, this);
        setProperties(endpoint, parameters);
        return endpoint;
    }
}
