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
    private Short extension2;
    private Short address;
    private Short address2;
    private Short num;
    private Short address3;
    private Short num2;
    private Short frequency;
    private Short channel;
    private Short address4;
    private Short num3;
    private Short address5;
    private Short num4;
    private Long speed;
    private Character parity;
    private Short stopbits;
    private String ssid;
    private Short connection;
    private short[] ip;
    private short[] subnetMask;
    private short[] gateway;
    private Integer port;
    private Short encryption;
    private String key;
    private Short keyIndex;
    private Short eapOptions;
    private Integer caCertificateLength;
    private Integer clientCertificateLength;
    private Integer privateKeyLength;
    private Integer index;
    private short[] data;
    private Short dataLength;
    private Integer index2;
    private Short mode;
    private Short domain;
    private String key2;
    private String hostname;
    private Long period;
    private Long period2;
    private Long period3;
    private Character option;
    private Integer min;
    private Integer max;
    private Character option2;
    private Integer min2;
    private Integer max2;
    private Character option3;
    private Integer min3;
    private Integer max3;
    private Long debounce;
    private Short connection2;
    private short[] ip2;
    private short[] subnetMask2;
    private short[] gateway2;
    private Integer port2;
    private String hostname2;
    private short[] macAddress;
    private Short sockets;
    private Integer port3;
    private String secret;
    private String secret2;
    private Character port4;

        
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
                        getValue(short.class, "extension", m, getExtension()),
                        getValue(long.class, "exttype", m, getExttype())
                    );
                break;

            case "getExtensionType":
                response = device.getExtensionType(
                        getValue(short.class, "extension2", m, getExtension2())
                    );
                break;

            case "isChibiPresent":
                response = device.isChibiPresent();
                break;

            case "setChibiAddress":
                device.setChibiAddress(
                        getValue(short.class, "address", m, getAddress())
                    );
                break;

            case "getChibiAddress":
                response = device.getChibiAddress();
                break;

            case "setChibiMasterAddress":
                device.setChibiMasterAddress(
                        getValue(short.class, "address2", m, getAddress2())
                    );
                break;

            case "getChibiMasterAddress":
                response = device.getChibiMasterAddress();
                break;

            case "setChibiSlaveAddress":
                device.setChibiSlaveAddress(
                        getValue(short.class, "num", m, getNum()),
                        getValue(short.class, "address3", m, getAddress3())
                    );
                break;

            case "getChibiSlaveAddress":
                response = device.getChibiSlaveAddress(
                        getValue(short.class, "num2", m, getNum2())
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
                        getValue(short.class, "frequency", m, getFrequency())
                    );
                break;

            case "getChibiFrequency":
                response = device.getChibiFrequency();
                break;

            case "setChibiChannel":
                device.setChibiChannel(
                        getValue(short.class, "channel", m, getChannel())
                    );
                break;

            case "getChibiChannel":
                response = device.getChibiChannel();
                break;

            case "isRs485Present":
                response = device.isRS485Present();
                break;

            case "setRs485Address":
                device.setRS485Address(
                        getValue(short.class, "address4", m, getAddress4())
                    );
                break;

            case "getRs485Address":
                response = device.getRS485Address();
                break;

            case "setRs485SlaveAddress":
                device.setRS485SlaveAddress(
                        getValue(short.class, "num3", m, getNum3()),
                        getValue(short.class, "address5", m, getAddress5())
                    );
                break;

            case "getRs485SlaveAddress":
                response = device.getRS485SlaveAddress(
                        getValue(short.class, "num4", m, getNum4())
                    );
                break;

            case "getRs485ErrorLog":
                response = device.getRS485ErrorLog();
                break;

            case "setRs485Configuration":
                device.setRS485Configuration(
                        getValue(long.class, "speed", m, getSpeed()),
                        getValue(char.class, "parity", m, getParity()),
                        getValue(short.class, "stopbits", m, getStopbits())
                    );
                break;

            case "getRs485Configuration":
                response = device.getRS485Configuration();
                break;

            case "isWifiPresent":
                response = device.isWifiPresent();
                break;

            case "setWifiConfiguration":
                device.setWifiConfiguration(
                        getValue(String.class, "ssid", m, getSsid()),
                        getValue(short.class, "connection", m, getConnection()),
                        getValue(short[].class, "ip", m, getIp()),
                        getValue(short[].class, "subnetMask", m, getSubnetMask()),
                        getValue(short[].class, "gateway", m, getGateway()),
                        getValue(int.class, "port", m, getPort())
                    );
                break;

            case "getWifiConfiguration":
                response = device.getWifiConfiguration();
                break;

            case "setWifiEncryption":
                device.setWifiEncryption(
                        getValue(short.class, "encryption", m, getEncryption()),
                        getValue(String.class, "key", m, getKey()),
                        getValue(short.class, "keyIndex", m, getKeyIndex()),
                        getValue(short.class, "eapOptions", m, getEapOptions()),
                        getValue(int.class, "caCertificateLength", m, getCaCertificateLength()),
                        getValue(int.class, "clientCertificateLength", m, getClientCertificateLength()),
                        getValue(int.class, "privateKeyLength", m, getPrivateKeyLength())
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

            case "setWifiCertificate":
                device.setWifiCertificate(
                        getValue(int.class, "index", m, getIndex()),
                        getValue(short[].class, "data", m, getData()),
                        getValue(short.class, "dataLength", m, getDataLength())
                    );
                break;

            case "getWifiCertificate":
                response = device.getWifiCertificate(
                        getValue(int.class, "index2", m, getIndex2())
                    );
                break;

            case "setWifiPowerMode":
                device.setWifiPowerMode(
                        getValue(short.class, "mode", m, getMode())
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
                        getValue(short.class, "domain", m, getDomain())
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
                        getValue(String.class, "key2", m, getKey2())
                    );
                break;

            case "getLongWifiKey":
                response = device.getLongWifiKey();
                break;

            case "setWifiHostname":
                device.setWifiHostname(
                        getValue(String.class, "hostname", m, getHostname())
                    );
                break;

            case "getWifiHostname":
                response = device.getWifiHostname();
                break;

            case "setStackCurrentCallbackPeriod":
                device.setStackCurrentCallbackPeriod(
                        getValue(long.class, "period", m, getPeriod())
                    );
                break;

            case "getStackCurrentCallbackPeriod":
                response = device.getStackCurrentCallbackPeriod();
                break;

            case "setStackVoltageCallbackPeriod":
                device.setStackVoltageCallbackPeriod(
                        getValue(long.class, "period2", m, getPeriod2())
                    );
                break;

            case "getStackVoltageCallbackPeriod":
                response = device.getStackVoltageCallbackPeriod();
                break;

            case "setUsbVoltageCallbackPeriod":
                device.setUSBVoltageCallbackPeriod(
                        getValue(long.class, "period3", m, getPeriod3())
                    );
                break;

            case "getUsbVoltageCallbackPeriod":
                response = device.getUSBVoltageCallbackPeriod();
                break;

            case "setStackCurrentCallbackThreshold":
                device.setStackCurrentCallbackThreshold(
                        getValue(char.class, "option", m, getOption()),
                        getValue(int.class, "min", m, getMin()),
                        getValue(int.class, "max", m, getMax())
                    );
                break;

            case "getStackCurrentCallbackThreshold":
                response = device.getStackCurrentCallbackThreshold();
                break;

            case "setStackVoltageCallbackThreshold":
                device.setStackVoltageCallbackThreshold(
                        getValue(char.class, "option2", m, getOption2()),
                        getValue(int.class, "min2", m, getMin2()),
                        getValue(int.class, "max2", m, getMax2())
                    );
                break;

            case "getStackVoltageCallbackThreshold":
                response = device.getStackVoltageCallbackThreshold();
                break;

            case "setUsbVoltageCallbackThreshold":
                device.setUSBVoltageCallbackThreshold(
                        getValue(char.class, "option3", m, getOption3()),
                        getValue(int.class, "min3", m, getMin3()),
                        getValue(int.class, "max3", m, getMax3())
                    );
                break;

            case "getUsbVoltageCallbackThreshold":
                response = device.getUSBVoltageCallbackThreshold();
                break;

            case "setDebouncePeriod":
                device.setDebouncePeriod(
                        getValue(long.class, "debounce", m, getDebounce())
                    );
                break;

            case "getDebouncePeriod":
                response = device.getDebouncePeriod();
                break;

            case "isEthernetPresent":
                response = device.isEthernetPresent();
                break;

            case "setEthernetConfiguration":
                device.setEthernetConfiguration(
                        getValue(short.class, "connection2", m, getConnection2()),
                        getValue(short[].class, "ip2", m, getIp2()),
                        getValue(short[].class, "subnetMask2", m, getSubnetMask2()),
                        getValue(short[].class, "gateway2", m, getGateway2()),
                        getValue(int.class, "port2", m, getPort2())
                    );
                break;

            case "getEthernetConfiguration":
                response = device.getEthernetConfiguration();
                break;

            case "getEthernetStatus":
                response = device.getEthernetStatus();
                break;

            case "setEthernetHostname":
                device.setEthernetHostname(
                        getValue(String.class, "hostname2", m, getHostname2())
                    );
                break;

            case "setEthernetMacAddress":
                device.setEthernetMACAddress(
                        getValue(short[].class, "macAddress", m, getMacAddress())
                    );
                break;

            case "setEthernetWebsocketConfiguration":
                device.setEthernetWebsocketConfiguration(
                        getValue(short.class, "sockets", m, getSockets()),
                        getValue(int.class, "port3", m, getPort3())
                    );
                break;

            case "getEthernetWebsocketConfiguration":
                response = device.getEthernetWebsocketConfiguration();
                break;

            case "setEthernetAuthenticationSecret":
                device.setEthernetAuthenticationSecret(
                        getValue(String.class, "secret", m, getSecret())
                    );
                break;

            case "getEthernetAuthenticationSecret":
                response = device.getEthernetAuthenticationSecret();
                break;

            case "setWifiAuthenticationSecret":
                device.setWifiAuthenticationSecret(
                        getValue(String.class, "secret2", m, getSecret2())
                    );
                break;

            case "getWifiAuthenticationSecret":
                response = device.getWifiAuthenticationSecret();
                break;

            case "getProtocol1BrickletName":
                response = device.getProtocol1BrickletName(
                        getValue(char.class, "port4", m, getPort4())
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

    public Short getExtension2(){
        return extension2;
    }

    public void setExtension2(Short extension2){
        this.extension2 = extension2;
    }

    public Short getAddress(){
        return address;
    }

    public void setAddress(Short address){
        this.address = address;
    }

    public Short getAddress2(){
        return address2;
    }

    public void setAddress2(Short address2){
        this.address2 = address2;
    }

    public Short getNum(){
        return num;
    }

    public void setNum(Short num){
        this.num = num;
    }

    public Short getAddress3(){
        return address3;
    }

    public void setAddress3(Short address3){
        this.address3 = address3;
    }

    public Short getNum2(){
        return num2;
    }

    public void setNum2(Short num2){
        this.num2 = num2;
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

    public Short getAddress4(){
        return address4;
    }

    public void setAddress4(Short address4){
        this.address4 = address4;
    }

    public Short getNum3(){
        return num3;
    }

    public void setNum3(Short num3){
        this.num3 = num3;
    }

    public Short getAddress5(){
        return address5;
    }

    public void setAddress5(Short address5){
        this.address5 = address5;
    }

    public Short getNum4(){
        return num4;
    }

    public void setNum4(Short num4){
        this.num4 = num4;
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

    public short[] getIp(){
        return ip;
    }

    public void setIp(short[] ip){
        this.ip = ip;
    }

    public short[] getSubnetMask(){
        return subnetMask;
    }

    public void setSubnetMask(short[] subnetMask){
        this.subnetMask = subnetMask;
    }

    public short[] getGateway(){
        return gateway;
    }

    public void setGateway(short[] gateway){
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

    public short[] getData(){
        return data;
    }

    public void setData(short[] data){
        this.data = data;
    }

    public Short getDataLength(){
        return dataLength;
    }

    public void setDataLength(Short dataLength){
        this.dataLength = dataLength;
    }

    public Integer getIndex2(){
        return index2;
    }

    public void setIndex2(Integer index2){
        this.index2 = index2;
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

    public String getKey2(){
        return key2;
    }

    public void setKey2(String key2){
        this.key2 = key2;
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

    public Long getPeriod2(){
        return period2;
    }

    public void setPeriod2(Long period2){
        this.period2 = period2;
    }

    public Long getPeriod3(){
        return period3;
    }

    public void setPeriod3(Long period3){
        this.period3 = period3;
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

    public Character getOption2(){
        return option2;
    }

    public void setOption2(Character option2){
        this.option2 = option2;
    }

    public Integer getMin2(){
        return min2;
    }

    public void setMin2(Integer min2){
        this.min2 = min2;
    }

    public Integer getMax2(){
        return max2;
    }

    public void setMax2(Integer max2){
        this.max2 = max2;
    }

    public Character getOption3(){
        return option3;
    }

    public void setOption3(Character option3){
        this.option3 = option3;
    }

    public Integer getMin3(){
        return min3;
    }

    public void setMin3(Integer min3){
        this.min3 = min3;
    }

    public Integer getMax3(){
        return max3;
    }

    public void setMax3(Integer max3){
        this.max3 = max3;
    }

    public Long getDebounce(){
        return debounce;
    }

    public void setDebounce(Long debounce){
        this.debounce = debounce;
    }

    public Short getConnection2(){
        return connection2;
    }

    public void setConnection2(Short connection2){
        this.connection2 = connection2;
    }

    public short[] getIp2(){
        return ip2;
    }

    public void setIp2(short[] ip2){
        this.ip2 = ip2;
    }

    public short[] getSubnetMask2(){
        return subnetMask2;
    }

    public void setSubnetMask2(short[] subnetMask2){
        this.subnetMask2 = subnetMask2;
    }

    public short[] getGateway2(){
        return gateway2;
    }

    public void setGateway2(short[] gateway2){
        this.gateway2 = gateway2;
    }

    public Integer getPort2(){
        return port2;
    }

    public void setPort2(Integer port2){
        this.port2 = port2;
    }

    public String getHostname2(){
        return hostname2;
    }

    public void setHostname2(String hostname2){
        this.hostname2 = hostname2;
    }

    public short[] getMacAddress(){
        return macAddress;
    }

    public void setMacAddress(short[] macAddress){
        this.macAddress = macAddress;
    }

    public Short getSockets(){
        return sockets;
    }

    public void setSockets(Short sockets){
        this.sockets = sockets;
    }

    public Integer getPort3(){
        return port3;
    }

    public void setPort3(Integer port3){
        this.port3 = port3;
    }

    public String getSecret(){
        return secret;
    }

    public void setSecret(String secret){
        this.secret = secret;
    }

    public String getSecret2(){
        return secret2;
    }

    public void setSecret2(String secret2){
        this.secret2 = secret2;
    }

    public Character getPort4(){
        return port4;
    }

    public void setPort4(Character port4){
        this.port4 = port4;
    }



}
