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

import org.apache.camel.impl.DefaultEndpoint;
import org.apache.camel.spi.UriParam;

import com.tinkerforge.IPConnection;

/**
 * Represents a Tinkerforge endpoint.
 */
public abstract class TinkerforgeEndpoint extends DefaultEndpoint {

    private final IPConnection connection;
    
    @UriParam 
    protected String uid;

    
    public TinkerforgeEndpoint(String uri, IPConnection connection, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
        this.connection = connection;
        
    }
    
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public IPConnection getConnection() {
        return connection;
    }

}
