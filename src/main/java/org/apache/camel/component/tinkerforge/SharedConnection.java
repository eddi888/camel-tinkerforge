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

import java.io.IOException;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tinkerforge.AlreadyConnectedException;
import com.tinkerforge.CryptoException;
import com.tinkerforge.IPConnection;
import com.tinkerforge.NotConnectedException;
import com.tinkerforge.TimeoutException;
import com.tinkerforge.IPConnection.ConnectedListener;

/**
 *  Share the IPConnection between Endpoint for the same BrickDaemon
 */
public class SharedConnection implements ConnectedListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(SharedConnection.class);
    
    private final IPConnection connection;
    private final String host;
    private final int port;
    private final String secret;
    private final boolean autoReconnect;    
    private final int timeout;
    
    
    public SharedConnection(String host, int port, String secret, boolean autoReconnect, int timeout) {
        LOG.trace("TinkerforgeConnection(String host='"+host+"', int port='"+port+"', String secret='"+secret+"', boolean autoReconnect='"+autoReconnect+"', int timeout='"+timeout+"')");
        this.host = host;
        this.port = port;
        this.secret = secret;
        this.autoReconnect = autoReconnect;
        this.timeout = timeout;
        connection = new IPConnection();
        connection.setAutoReconnect(autoReconnect);
        connection.setTimeout(timeout);
        connection.addConnectedListener(this);
    }
    
    @Override
    public void connected(short connectReason) {
        LOG.trace("connected(short connectReason='"+connectReason+"')");
        if (secret!=null)
            try {
                connection.authenticate(secret);
            } catch (TimeoutException | NotConnectedException | CryptoException e) {
                e.printStackTrace();
            }
    }
    
    public IPConnection getConnection() throws UnknownHostException, AlreadyConnectedException, IOException {
        LOG.trace("getConnection()");
        if(connection.getConnectionState()==IPConnection.CONNECTION_STATE_DISCONNECTED) {
            connection.connect(host, port);
        }
        return connection;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getSecret() {
        return secret;
    }

    void disconnect() {
        connection.setAutoReconnect(false);
        try {
            if(connection.getConnectionState()==IPConnection.CONNECTION_STATE_CONNECTED){
                connection.disconnect();
            }
        } catch (NotConnectedException e) {
            e.printStackTrace();
        }
    }

    public boolean isAutoReconnect() {
        return autoReconnect;
    }

    public int getTimeout() {
        return timeout;
    }
    
}
