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

import org.apache.camel.impl.DefaultProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tinkerforge.Device;
import com.tinkerforge.Device.Identity;

/**
 * The Tinkerforge producer.
 */
public abstract class TinkerforgeProducer <EndpointType extends TinkerforgeEndpoint<?,?>, DeviceType extends Device> extends DefaultProducer {
    
    private static final Logger LOG = LoggerFactory.getLogger(TinkerforgeProducer.class);
    
    protected EndpointType endpoint;

    protected DeviceType device;
    
    protected Identity identity = null;

    public TinkerforgeProducer(EndpointType endpoint) {
        super(endpoint);
        LOG.trace("TinkerforgeProducer(TinkerforgeEndpoint endpoint='"+endpoint+"')");
        this.endpoint = endpoint;
    }

}
