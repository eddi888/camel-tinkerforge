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
package org.atomspace.camel.component.tinkerforge.device;

import org.apache.camel.Consumer;
import org.apache.camel.Endpoint;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.atomspace.camel.component.tinkerforge.TinkerforgeComponent;
import org.atomspace.camel.component.tinkerforge.TinkerforgeEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tinkerforge.BrickMaster;

public class MasterEndpoint extends TinkerforgeEndpoint<MasterConsumer, MasterProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(MasterEndpoint.class);
    
    private Short extension;
    private Long exttype;
    private Short address;
    private Short num;
    private Short frequency;
    private Short channel;
    private Long speed;
    private Character parity;
    private Short stopbits;
    private String ssid;
    private Short connection;
    private Short ip;
    private Short subnetMask;
    private Short gateway;
    private Integer port;
    private Short encryption;
    private String key;
    private Short keyIndex;
    private Short eapOptions;
    private Integer caCertificateLength;
    private Integer clientCertificateLength;
    private Integer privateKeyLength;
    private Integer index;
    private Short data;
    private Short dataLength;
    private Short mode;
    private Short domain;
    private String hostname;
    private Long period;
    private Character option;
    private Integer min;
    private Integer max;
    private Long debounce;
    private Short macAddress;
    private Short sockets;
    private String secret;

        
    public MasterEndpoint(String uri, TinkerforgeComponent tinkerforgeComponent) {
        super(uri, tinkerforgeComponent);
    }

    @Override
    public Producer createProducer() throws Exception {
        LOG.trace("createProducer()");
        if(producer==null){
            producer = new MasterProducer(this);
        }
        return producer;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.trace("createConsumer(Processor processor='"+processor+"')");
        if(consumer==null){
            consumer = new MasterConsumer(this, processor);
        }
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
    
    public void init(BrickMaster device) throws Exception {
        if(getInit()==null) return;
        
        String[] initFunctions = getInit().split(",");
        for (String function : initFunctions) {
            callFunction(device, function, null, this);
        }
    }
    
    public Object callFunction(BrickMaster device, String function, Message m, Endpoint e) throws Exception{
        Object response = null;
        switch (function) {
                
            case "getStackVoltage":
                response = device.getStackVoltage();
                break;

            case "getStackCurrent":
                response = device.getStackCurrent();
                break;

            case "setExtensionType":
                device.setExtensionType(
                        (short) getValue("extension", m, e),
                        (long) getValue("exttype", m, e)
                    );
                break;

            case "getExtensionType":
                response = device.getExtensionType(
                        (short) getValue("extension", m, e)
                    );
                break;

            case "isChibiPresent":
                response = device.isChibiPresent();
                break;

            case "setChibiAddress":
                device.setChibiAddress(
                        (short) getValue("address", m, e)
                    );
                break;

            case "getChibiAddress":
                response = device.getChibiAddress();
                break;

            case "setChibiMasterAddress":
                device.setChibiMasterAddress(
                        (short) getValue("address", m, e)
                    );
                break;

            case "getChibiMasterAddress":
                response = device.getChibiMasterAddress();
                break;

            case "setChibiSlaveAddress":
                device.setChibiSlaveAddress(
                        (short) getValue("num", m, e),
                        (short) getValue("address", m, e)
                    );
                break;

            case "getChibiSlaveAddress":
                response = device.getChibiSlaveAddress(
                        (short) getValue("num", m, e)
                    );
                break;

            case "getChibiSignalStrength":
                response = device.getChibiSignalStrength();
                break;

            case "getChibiErrorLog":
                response = device.getChibiErrorLog();
                break;

            case "setChibiFrequency":
                device.setChibiFrequency(
                        (short) getValue("frequency", m, e)
                    );
                break;

            case "getChibiFrequency":
                response = device.getChibiFrequency();
                break;

            case "setChibiChannel":
                device.setChibiChannel(
                        (short) getValue("channel", m, e)
                    );
                break;

            case "getChibiChannel":
                response = device.getChibiChannel();
                break;

            case "isWifiPresent":
                response = device.isWifiPresent();
                break;

            case "getWifiConfiguration":
                response = device.getWifiConfiguration();
                break;

            case "setWifiEncryption":
                device.setWifiEncryption(
                        (short) getValue("encryption", m, e),
                        (String) getValue("key", m, e),
                        (short) getValue("keyIndex", m, e),
                        (short) getValue("eapOptions", m, e),
                        (int) getValue("caCertificateLength", m, e),
                        (int) getValue("clientCertificateLength", m, e),
                        (int) getValue("privateKeyLength", m, e)
                    );
                break;

            case "getWifiEncryption":
                response = device.getWifiEncryption();
                break;

            case "getWifiStatus":
                response = device.getWifiStatus();
                break;

            case "refreshWifiStatus":
                device.refreshWifiStatus();
                break;

            case "getWifiCertificate":
                response = device.getWifiCertificate(
                        (int) getValue("index", m, e)
                    );
                break;

            case "setWifiPowerMode":
                device.setWifiPowerMode(
                        (short) getValue("mode", m, e)
                    );
                break;

            case "getWifiPowerMode":
                response = device.getWifiPowerMode();
                break;

            case "getWifiBufferInfo":
                response = device.getWifiBufferInfo();
                break;

            case "setWifiRegulatoryDomain":
                device.setWifiRegulatoryDomain(
                        (short) getValue("domain", m, e)
                    );
                break;

            case "getWifiRegulatoryDomain":
                response = device.getWifiRegulatoryDomain();
                break;

            case "getUsbVoltage":
                response = device.getUSBVoltage();
                break;

            case "setLongWifiKey":
                device.setLongWifiKey(
                        (String) getValue("key", m, e)
                    );
                break;

            case "getLongWifiKey":
                response = device.getLongWifiKey();
                break;

            case "setWifiHostname":
                device.setWifiHostname(
                        (String) getValue("hostname", m, e)
                    );
                break;

            case "getWifiHostname":
                response = device.getWifiHostname();
                break;

            case "setStackCurrentCallbackPeriod":
                device.setStackCurrentCallbackPeriod(
                        (long) getValue("period", m, e)
                    );
                break;

            case "getStackCurrentCallbackPeriod":
                response = device.getStackCurrentCallbackPeriod();
                break;

            case "setStackVoltageCallbackPeriod":
                device.setStackVoltageCallbackPeriod(
                        (long) getValue("period", m, e)
                    );
                break;

            case "getStackVoltageCallbackPeriod":
                response = device.getStackVoltageCallbackPeriod();
                break;

            case "setUsbVoltageCallbackPeriod":
                device.setUSBVoltageCallbackPeriod(
                        (long) getValue("period", m, e)
                    );
                break;

            case "getUsbVoltageCallbackPeriod":
                response = device.getUSBVoltageCallbackPeriod();
                break;

            case "setStackCurrentCallbackThreshold":
                device.setStackCurrentCallbackThreshold(
                        (char) getValue("option", m, e),
                        (int) getValue("min", m, e),
                        (int) getValue("max", m, e)
                    );
                break;

            case "getStackCurrentCallbackThreshold":
                response = device.getStackCurrentCallbackThreshold();
                break;

            case "setStackVoltageCallbackThreshold":
                device.setStackVoltageCallbackThreshold(
                        (char) getValue("option", m, e),
                        (int) getValue("min", m, e),
                        (int) getValue("max", m, e)
                    );
                break;

            case "getStackVoltageCallbackThreshold":
                response = device.getStackVoltageCallbackThreshold();
                break;

            case "setUsbVoltageCallbackThreshold":
                device.setUSBVoltageCallbackThreshold(
                        (char) getValue("option", m, e),
                        (int) getValue("min", m, e),
                        (int) getValue("max", m, e)
                    );
                break;

            case "setDebouncePeriod":
                device.setDebouncePeriod(
                        (long) getValue("debounce", m, e)
                    );
                break;

            case "getDebouncePeriod":
                response = device.getDebouncePeriod();
                break;

            case "isEthernetPresent":
                response = device.isEthernetPresent();
                break;

            case "getEthernetConfiguration":
                response = device.getEthernetConfiguration();
                break;

            case "getEthernetStatus":
                response = device.getEthernetStatus();
                break;

            case "setEthernetHostname":
                device.setEthernetHostname(
                        (String) getValue("hostname", m, e)
                    );
                break;

            case "setEthernetWebsocketConfiguration":
                device.setEthernetWebsocketConfiguration(
                        (short) getValue("sockets", m, e),
                        (int) getValue("port", m, e)
                    );
                break;

            case "getEthernetWebsocketConfiguration":
                response = device.getEthernetWebsocketConfiguration();
                break;

            case "setEthernetAuthenticationSecret":
                device.setEthernetAuthenticationSecret(
                        (String) getValue("secret", m, e)
                    );
                break;

            case "getEthernetAuthenticationSecret":
                response = device.getEthernetAuthenticationSecret();
                break;

            case "setWifiAuthenticationSecret":
                device.setWifiAuthenticationSecret(
                        (String) getValue("secret", m, e)
                    );
                break;

            case "getWifiAuthenticationSecret":
                response = device.getWifiAuthenticationSecret();
                break;

            case "getProtocol1BrickletName":
                response = device.getProtocol1BrickletName(
                        (char) getValue("port", m, e)
                    );
                break;

            case "getChipTemperature":
                response = device.getChipTemperature();
                break;

            case "reset":
                device.reset();
                break;

            case "getIdentity":
                response = device.getIdentity();
                break;


            default: throw new Exception("unknown function '"+function+"'");
            
        }
        
        return response;
    }
    
    
    public Short getExtension(){
        return extension;
    }

    public void setExtension(Short extension){
        this.extension = extension;
    }

    public Long getExttype(){
        return exttype;
    }

    public void setExttype(Long exttype){
        this.exttype = exttype;
    }

    public Short getAddress(){
        return address;
    }

    public void setAddress(Short address){
        this.address = address;
    }

    public Short getNum(){
        return num;
    }

    public void setNum(Short num){
        this.num = num;
    }

    public Short getFrequency(){
        return frequency;
    }

    public void setFrequency(Short frequency){
        this.frequency = frequency;
    }

    public Short getChannel(){
        return channel;
    }

    public void setChannel(Short channel){
        this.channel = channel;
    }

    public Long getSpeed(){
        return speed;
    }

    public void setSpeed(Long speed){
        this.speed = speed;
    }

    public Character getParity(){
        return parity;
    }

    public void setParity(Character parity){
        this.parity = parity;
    }

    public Short getStopbits(){
        return stopbits;
    }

    public void setStopbits(Short stopbits){
        this.stopbits = stopbits;
    }

    public String getSsid(){
        return ssid;
    }

    public void setSsid(String ssid){
        this.ssid = ssid;
    }

    public Short getConnection(){
        return connection;
    }

    public void setConnection(Short connection){
        this.connection = connection;
    }

    public Short getIp(){
        return ip;
    }

    public void setIp(Short ip){
        this.ip = ip;
    }

    public Short getSubnetMask(){
        return subnetMask;
    }

    public void setSubnetMask(Short subnetMask){
        this.subnetMask = subnetMask;
    }

    public Short getGateway(){
        return gateway;
    }

    public void setGateway(Short gateway){
        this.gateway = gateway;
    }

    public Integer getPort(){
        return port;
    }

    public void setPort(Integer port){
        this.port = port;
    }

    public Short getEncryption(){
        return encryption;
    }

    public void setEncryption(Short encryption){
        this.encryption = encryption;
    }

    public String getKey(){
        return key;
    }

    public void setKey(String key){
        this.key = key;
    }

    public Short getKeyIndex(){
        return keyIndex;
    }

    public void setKeyIndex(Short keyIndex){
        this.keyIndex = keyIndex;
    }

    public Short getEapOptions(){
        return eapOptions;
    }

    public void setEapOptions(Short eapOptions){
        this.eapOptions = eapOptions;
    }

    public Integer getCaCertificateLength(){
        return caCertificateLength;
    }

    public void setCaCertificateLength(Integer caCertificateLength){
        this.caCertificateLength = caCertificateLength;
    }

    public Integer getClientCertificateLength(){
        return clientCertificateLength;
    }

    public void setClientCertificateLength(Integer clientCertificateLength){
        this.clientCertificateLength = clientCertificateLength;
    }

    public Integer getPrivateKeyLength(){
        return privateKeyLength;
    }

    public void setPrivateKeyLength(Integer privateKeyLength){
        this.privateKeyLength = privateKeyLength;
    }

    public Integer getIndex(){
        return index;
    }

    public void setIndex(Integer index){
        this.index = index;
    }

    public Short getData(){
        return data;
    }

    public void setData(Short data){
        this.data = data;
    }

    public Short getDataLength(){
        return dataLength;
    }

    public void setDataLength(Short dataLength){
        this.dataLength = dataLength;
    }

    public Short getMode(){
        return mode;
    }

    public void setMode(Short mode){
        this.mode = mode;
    }

    public Short getDomain(){
        return domain;
    }

    public void setDomain(Short domain){
        this.domain = domain;
    }

    public String getHostname(){
        return hostname;
    }

    public void setHostname(String hostname){
        this.hostname = hostname;
    }

    public Long getPeriod(){
        return period;
    }

    public void setPeriod(Long period){
        this.period = period;
    }

    public Character getOption(){
        return option;
    }

    public void setOption(Character option){
        this.option = option;
    }

    public Integer getMin(){
        return min;
    }

    public void setMin(Integer min){
        this.min = min;
    }

    public Integer getMax(){
        return max;
    }

    public void setMax(Integer max){
        this.max = max;
    }

    public Long getDebounce(){
        return debounce;
    }

    public void setDebounce(Long debounce){
        this.debounce = debounce;
    }

    public Short getMacAddress(){
        return macAddress;
    }

    public void setMacAddress(Short macAddress){
        this.macAddress = macAddress;
    }

    public Short getSockets(){
        return sockets;
    }

    public void setSockets(Short sockets){
        this.sockets = sockets;
    }

    public String getSecret(){
        return secret;
    }

    public void setSecret(String secret){
        this.secret = secret;
    }



}
