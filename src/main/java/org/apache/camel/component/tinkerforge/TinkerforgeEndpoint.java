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

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;
import org.apache.camel.spi.UriParam;

/**
 * Represents a Tinkerforge endpoint.
 */
public class TinkerforgeEndpoint extends DefaultEndpoint {

    @UriParam private String host;
    @UriParam private Integer port;
    @UriParam private String authenticate;
    @UriParam private String uid;
    @UriParam private boolean autoReconnect;
    @UriParam private Integer timeout;
    
    
    public TinkerforgeEndpoint() {
    }

    public TinkerforgeEndpoint(String uri, TinkerforgeComponent component) {
        super(uri, component);
    }

    public Producer createProducer() throws Exception {
        return new TinkerforgeProducer(this);
    }

    public Consumer createConsumer(Processor processor) throws Exception {
        return new TinkerforgeConsumer(this, processor);
    }

    public boolean isSingleton() {
        return true;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getAuthenticate() {
        return authenticate;
    }

    public void setAuthenticate(String authenticate) {
        this.authenticate = authenticate;
    }

    public boolean isAutoReconnect() {
        return autoReconnect;
    }

    public void setAutoReconnect(boolean autoReconnect) {
        this.autoReconnect = autoReconnect;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }
}
