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
import org.apache.camel.spi.UriEndpoint;
import org.atomspace.camel.component.tinkerforge.TinkerforgeComponent;
import org.atomspace.camel.component.tinkerforge.TinkerforgeEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tinkerforge.BrickMaster;

/**
 * Basis to build stacks and has 4 Bricklet ports
 */
@UriEndpoint(scheme = "tinkerforgegen", syntax = "tinkerforgegen:[host[:port]/]master", consumerClass = MasterConsumer.class, label = "iot", title = "Tinkerforge")
public class MasterEndpoint extends TinkerforgeEndpoint<MasterConsumer, MasterProducer> {

    private static final Logger LOG = LoggerFactory.getLogger(MasterEndpoint.class);

    public static final String EXTENSION="extension";
    public static final String EXTTYPE="exttype";
    public static final String EXTENSION2="extension2";
    public static final String ADDRESS="address";
    public static final String ADDRESS2="address2";
    public static final String NUM="num";
    public static final String ADDRESS3="address3";
    public static final String NUM2="num2";
    public static final String FREQUENCY="frequency";
    public static final String CHANNEL="channel";
    public static final String ADDRESS4="address4";
    public static final String NUM3="num3";
    public static final String ADDRESS5="address5";
    public static final String NUM4="num4";
    public static final String SPEED="speed";
    public static final String PARITY="parity";
    public static final String STOPBITS="stopbits";
    public static final String SSID="ssid";
    public static final String CONNECTION="connection";
    public static final String IP="ip";
    public static final String SUBNETMASK="subnetMask";
    public static final String GATEWAY="gateway";
    public static final String PORT="port";
    public static final String ENCRYPTION="encryption";
    public static final String KEY="key";
    public static final String KEYINDEX="keyIndex";
    public static final String EAPOPTIONS="eapOptions";
    public static final String CACERTIFICATELENGTH="caCertificateLength";
    public static final String CLIENTCERTIFICATELENGTH="clientCertificateLength";
    public static final String PRIVATEKEYLENGTH="privateKeyLength";
    public static final String INDEX="index";
    public static final String DATA="data";
    public static final String DATALENGTH="dataLength";
    public static final String INDEX2="index2";
    public static final String MODE="mode";
    public static final String DOMAIN="domain";
    public static final String KEY2="key2";
    public static final String HOSTNAME="hostname";
    public static final String PERIOD="period";
    public static final String PERIOD2="period2";
    public static final String PERIOD3="period3";
    public static final String OPTION="option";
    public static final String MIN="min";
    public static final String MAX="max";
    public static final String OPTION2="option2";
    public static final String MIN2="min2";
    public static final String MAX2="max2";
    public static final String OPTION3="option3";
    public static final String MIN3="min3";
    public static final String MAX3="max3";
    public static final String DEBOUNCE="debounce";
    public static final String CONNECTION2="connection2";
    public static final String IP2="ip2";
    public static final String SUBNETMASK2="subnetMask2";
    public static final String GATEWAY2="gateway2";
    public static final String PORT2="port2";
    public static final String HOSTNAME2="hostname2";
    public static final String MACADDRESS="macAddress";
    public static final String SOCKETS="sockets";
    public static final String PORT3="port3";
    public static final String SECRET="secret";
    public static final String SECRET2="secret2";
    public static final String PORT4="port4";

    
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

            case "enableStatusLed":
                device.enableStatusLED();
                break;

            case "disableStatusLed":
                device.disableStatusLED();
                break;

            case "isStatusLedEnabled":
                response = device.isStatusLEDEnabled();
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
    
    
    /**
     * 
     * Writes the extension type to the EEPROM of a specified extension. 
     * The extension is either 0 or 1 (0 is the on the bottom, 1 is the on on top, 
     * if only one extension is present use 0).
     * 
     * Possible extension types:
     * 
     * .. csv-table::
     *  :header: "Type", "Description"
     *  :widths: 10, 100
     * 
     *  "1",    "Chibi"
     *  "2",    "RS485"
     *  "3",    "WIFI"
     *  "4",    "Ethernet"
     * 
     * The extension type is already set when bought and it can be set with the 
     * Brick Viewer, it is unlikely that you need this function.
     * 
     */
    public Short getExtension(){
        return extension;
    }

    public void setExtension(Short extension){
        this.extension = extension;
    }

    /**
     * 
     * Writes the extension type to the EEPROM of a specified extension. 
     * The extension is either 0 or 1 (0 is the on the bottom, 1 is the on on top, 
     * if only one extension is present use 0).
     * 
     * Possible extension types:
     * 
     * .. csv-table::
     *  :header: "Type", "Description"
     *  :widths: 10, 100
     * 
     *  "1",    "Chibi"
     *  "2",    "RS485"
     *  "3",    "WIFI"
     *  "4",    "Ethernet"
     * 
     * The extension type is already set when bought and it can be set with the 
     * Brick Viewer, it is unlikely that you need this function.
     * 
     */
    public Long getExttype(){
        return exttype;
    }

    public void setExttype(Long exttype){
        this.exttype = exttype;
    }

    /**
     * 
     * Returns the type for a given extension as set by :func:`SetExtensionType`.
     * 
     */
    public Short getExtension2(){
        return extension2;
    }

    public void setExtension2(Short extension2){
        this.extension2 = extension2;
    }

    /**
     * 
     * Sets the address (1-255) belonging to the Chibi Extension.
     * 
     * It is possible to set the address with the Brick Viewer and it will be 
     * saved in the EEPROM of the Chibi Extension, it does not
     * have to be set on every startup.
     * 
     */
    public Short getAddress(){
        return address;
    }

    public void setAddress(Short address){
        this.address = address;
    }

    /**
     * 
     * Sets the address (1-255) of the Chibi Master. This address is used if the
     * Chibi Extension is used as slave (i.e. it does not have a USB connection).
     * 
     * It is possible to set the address with the Brick Viewer and it will be 
     * saved in the EEPROM of the Chibi Extension, it does not
     * have to be set on every startup.
     * 
     */
    public Short getAddress2(){
        return address2;
    }

    public void setAddress2(Short address2){
        this.address2 = address2;
    }

    /**
     * 
     * Sets up to 254 slave addresses. Valid addresses are in range 1-255. 0 has a
     * special meaning, it is used as list terminator and not allowed as normal slave
     * address. The address numeration (via :param:`num` parameter) has to be used
     * ascending from 0. For example: If you use the Chibi Extension in Master mode
     * (i.e. the stack has an USB connection) and you want to talk to three other
     * Chibi stacks with the slave addresses 17, 23, and 42, you should call with
     * ``(0, 17)``, ``(1, 23)``, ``(2, 42)`` and ``(3, 0)``. The last call with
     * ``(3, 0)`` is a list terminator and indicates that the Chibi slave address
     * list contains 3 addresses in this case.
     * 
     * It is possible to set the addresses with the Brick Viewer, that will take care
     * of correct address numeration and list termination.
     * 
     * The slave addresses will be saved in the EEPROM of the Chibi Extension, they
     * don't have to be set on every startup.
     * 
     */
    public Short getNum(){
        return num;
    }

    public void setNum(Short num){
        this.num = num;
    }

    /**
     * 
     * Sets up to 254 slave addresses. Valid addresses are in range 1-255. 0 has a
     * special meaning, it is used as list terminator and not allowed as normal slave
     * address. The address numeration (via :param:`num` parameter) has to be used
     * ascending from 0. For example: If you use the Chibi Extension in Master mode
     * (i.e. the stack has an USB connection) and you want to talk to three other
     * Chibi stacks with the slave addresses 17, 23, and 42, you should call with
     * ``(0, 17)``, ``(1, 23)``, ``(2, 42)`` and ``(3, 0)``. The last call with
     * ``(3, 0)`` is a list terminator and indicates that the Chibi slave address
     * list contains 3 addresses in this case.
     * 
     * It is possible to set the addresses with the Brick Viewer, that will take care
     * of correct address numeration and list termination.
     * 
     * The slave addresses will be saved in the EEPROM of the Chibi Extension, they
     * don't have to be set on every startup.
     * 
     */
    public Short getAddress3(){
        return address3;
    }

    public void setAddress3(Short address3){
        this.address3 = address3;
    }

    /**
     * 
     * Returns the slave address for a given :param:`num` as set by
     * :func:`SetChibiSlaveAddress`.
     * 
     */
    public Short getNum2(){
        return num2;
    }

    public void setNum2(Short num2){
        this.num2 = num2;
    }

    /**
     * 
     * Sets the Chibi frequency range for the Chibi Extension. Possible values are:
     * 
     * .. csv-table::
     *  :header: "Type", "Description"
     *  :widths: 10, 100
     * 
     *  "0",    "OQPSK 868MHz (Europe)"
     *  "1",    "OQPSK 915MHz (US)"
     *  "2",    "OQPSK 780MHz (China)"
     *  "3",    "BPSK40 915MHz"
     * 
     * It is possible to set the frequency with the Brick Viewer and it will be 
     * saved in the EEPROM of the Chibi Extension, it does not
     * have to be set on every startup.
     * 
     */
    public Short getFrequency(){
        return frequency;
    }

    public void setFrequency(Short frequency){
        this.frequency = frequency;
    }

    /**
     * 
     * Sets the channel used by the Chibi Extension. Possible channels are
     * different for different frequencies:
     * 
     * .. csv-table::
     *  :header: "Frequency",             "Possible Channels"
     *  :widths: 40, 60
     * 
     *  "OQPSK 868MHz (Europe)", "0"
     *  "OQPSK 915MHz (US)",     "1, 2, 3, 4, 5, 6, 7, 8, 9, 10"
     *  "OQPSK 780MHz (China)",  "0, 1, 2, 3"
     *  "BPSK40 915MHz",         "1, 2, 3, 4, 5, 6, 7, 8, 9, 10"
     * 
     * It is possible to set the channel with the Brick Viewer and it will be 
     * saved in the EEPROM of the Chibi Extension, it does not
     * have to be set on every startup.
     * 
     */
    public Short getChannel(){
        return channel;
    }

    public void setChannel(Short channel){
        this.channel = channel;
    }

    /**
     * 
     * Sets the address (0-255) belonging to the RS485 Extension.
     * 
     * Set to 0 if the RS485 Extension should be the RS485 Master (i.e.
     * connected to a PC via USB).
     * 
     * It is possible to set the address with the Brick Viewer and it will be 
     * saved in the EEPROM of the RS485 Extension, it does not
     * have to be set on every startup.
     * 
     */
    public Short getAddress4(){
        return address4;
    }

    public void setAddress4(Short address4){
        this.address4 = address4;
    }

    /**
     * 
     * Sets up to 255 slave addresses. Valid addresses are in range 1-255. 0 has a
     * special meaning, it is used as list terminator and not allowed as normal slave
     * address. The address numeration (via ``num`` parameter) has to be used
     * ascending from 0. For example: If you use the RS485 Extension in Master mode
     * (i.e. the stack has an USB connection) and you want to talk to three other
     * RS485 stacks with the addresses 17, 23, and 42, you should call with
     * ``(0, 17)``, ``(1, 23)``, ``(2, 42)`` and ``(3, 0)``. The last call with
     * ``(3, 0)`` is a list terminator and indicates that the RS485 slave address list
     * contains 3 addresses in this case.
     * 
     * It is possible to set the addresses with the Brick Viewer, that will take care
     * of correct address numeration and list termination.
     * 
     * The slave addresses will be saved in the EEPROM of the Chibi Extension, they
     * don't have to be set on every startup.
     * 
     */
    public Short getNum3(){
        return num3;
    }

    public void setNum3(Short num3){
        this.num3 = num3;
    }

    /**
     * 
     * Sets up to 255 slave addresses. Valid addresses are in range 1-255. 0 has a
     * special meaning, it is used as list terminator and not allowed as normal slave
     * address. The address numeration (via ``num`` parameter) has to be used
     * ascending from 0. For example: If you use the RS485 Extension in Master mode
     * (i.e. the stack has an USB connection) and you want to talk to three other
     * RS485 stacks with the addresses 17, 23, and 42, you should call with
     * ``(0, 17)``, ``(1, 23)``, ``(2, 42)`` and ``(3, 0)``. The last call with
     * ``(3, 0)`` is a list terminator and indicates that the RS485 slave address list
     * contains 3 addresses in this case.
     * 
     * It is possible to set the addresses with the Brick Viewer, that will take care
     * of correct address numeration and list termination.
     * 
     * The slave addresses will be saved in the EEPROM of the Chibi Extension, they
     * don't have to be set on every startup.
     * 
     */
    public Short getAddress5(){
        return address5;
    }

    public void setAddress5(Short address5){
        this.address5 = address5;
    }

    /**
     * 
     * Returns the slave address for a given ``num`` as set by
     * :func:`SetRS485SlaveAddress`.
     * 
     */
    public Short getNum4(){
        return num4;
    }

    public void setNum4(Short num4){
        this.num4 = num4;
    }

    /**
     * 
     * Sets the configuration of the RS485 Extension. Speed is given in baud. The
     * Master Brick will try to match the given baud rate as exactly as possible.
     * The maximum recommended baud rate is 2000000 (2Mbit/s).
     * Possible values for parity are 'n' (none), 'e' (even) and 'o' (odd).
     * Possible values for stop bits are 1 and 2.
     * 
     * If your RS485 is unstable (lost messages etc.), the first thing you should
     * try is to decrease the speed. On very large bus (e.g. 1km), you probably
     * should use a value in the range of 100000 (100kbit/s).
     * 
     * The values are stored in the EEPROM and only applied on startup. That means
     * you have to restart the Master Brick after configuration.
     * 
     */
    public Long getSpeed(){
        return speed;
    }

    public void setSpeed(Long speed){
        this.speed = speed;
    }

    /**
     * 
     * Sets the configuration of the RS485 Extension. Speed is given in baud. The
     * Master Brick will try to match the given baud rate as exactly as possible.
     * The maximum recommended baud rate is 2000000 (2Mbit/s).
     * Possible values for parity are 'n' (none), 'e' (even) and 'o' (odd).
     * Possible values for stop bits are 1 and 2.
     * 
     * If your RS485 is unstable (lost messages etc.), the first thing you should
     * try is to decrease the speed. On very large bus (e.g. 1km), you probably
     * should use a value in the range of 100000 (100kbit/s).
     * 
     * The values are stored in the EEPROM and only applied on startup. That means
     * you have to restart the Master Brick after configuration.
     * 
     */
    public Character getParity(){
        return parity;
    }

    public void setParity(Character parity){
        this.parity = parity;
    }

    /**
     * 
     * Sets the configuration of the RS485 Extension. Speed is given in baud. The
     * Master Brick will try to match the given baud rate as exactly as possible.
     * The maximum recommended baud rate is 2000000 (2Mbit/s).
     * Possible values for parity are 'n' (none), 'e' (even) and 'o' (odd).
     * Possible values for stop bits are 1 and 2.
     * 
     * If your RS485 is unstable (lost messages etc.), the first thing you should
     * try is to decrease the speed. On very large bus (e.g. 1km), you probably
     * should use a value in the range of 100000 (100kbit/s).
     * 
     * The values are stored in the EEPROM and only applied on startup. That means
     * you have to restart the Master Brick after configuration.
     * 
     */
    public Short getStopbits(){
        return stopbits;
    }

    public void setStopbits(Short stopbits){
        this.stopbits = stopbits;
    }

    /**
     * 
     * Sets the configuration of the WIFI Extension. The ``ssid`` can have a max length
     * of 32 characters. Possible values for ``connection`` are:
     * 
     * .. csv-table::
     *  :header: "Value", "Description"
     *  :widths: 10, 90
     * 
     *  "0", "DHCP"
     *  "1", "Static IP"
     *  "2", "Access Point: DHCP"
     *  "3", "Access Point: Static IP"
     *  "4", "Ad Hoc: DHCP"
     *  "5", "Ad Hoc: Static IP"
     * 
     * If you set ``connection`` to one of the static IP options then you have to
     * supply ``ip``, ``subnet_mask`` and ``gateway`` as an array of size 4 (first
     * element of the array is the least significant byte of the address). If
     * ``connection`` is set to one of the DHCP options then ``ip``, ``subnet_mask``
     * and ``gateway`` are ignored, you can set them to 0.
     * 
     * The last parameter is the port that your program will connect to. The
     * default port, that is used by brickd, is 4223.
     * 
     * The values are stored in the EEPROM and only applied on startup. That means
     * you have to restart the Master Brick after configuration.
     * 
     * It is recommended to use the Brick Viewer to set the WIFI configuration.
     * 
     */
    public String getSsid(){
        return ssid;
    }

    public void setSsid(String ssid){
        this.ssid = ssid;
    }

    /**
     * 
     * Sets the configuration of the WIFI Extension. The ``ssid`` can have a max length
     * of 32 characters. Possible values for ``connection`` are:
     * 
     * .. csv-table::
     *  :header: "Value", "Description"
     *  :widths: 10, 90
     * 
     *  "0", "DHCP"
     *  "1", "Static IP"
     *  "2", "Access Point: DHCP"
     *  "3", "Access Point: Static IP"
     *  "4", "Ad Hoc: DHCP"
     *  "5", "Ad Hoc: Static IP"
     * 
     * If you set ``connection`` to one of the static IP options then you have to
     * supply ``ip``, ``subnet_mask`` and ``gateway`` as an array of size 4 (first
     * element of the array is the least significant byte of the address). If
     * ``connection`` is set to one of the DHCP options then ``ip``, ``subnet_mask``
     * and ``gateway`` are ignored, you can set them to 0.
     * 
     * The last parameter is the port that your program will connect to. The
     * default port, that is used by brickd, is 4223.
     * 
     * The values are stored in the EEPROM and only applied on startup. That means
     * you have to restart the Master Brick after configuration.
     * 
     * It is recommended to use the Brick Viewer to set the WIFI configuration.
     * 
     */
    public Short getConnection(){
        return connection;
    }

    public void setConnection(Short connection){
        this.connection = connection;
    }

    /**
     * 
     * Sets the configuration of the WIFI Extension. The ``ssid`` can have a max length
     * of 32 characters. Possible values for ``connection`` are:
     * 
     * .. csv-table::
     *  :header: "Value", "Description"
     *  :widths: 10, 90
     * 
     *  "0", "DHCP"
     *  "1", "Static IP"
     *  "2", "Access Point: DHCP"
     *  "3", "Access Point: Static IP"
     *  "4", "Ad Hoc: DHCP"
     *  "5", "Ad Hoc: Static IP"
     * 
     * If you set ``connection`` to one of the static IP options then you have to
     * supply ``ip``, ``subnet_mask`` and ``gateway`` as an array of size 4 (first
     * element of the array is the least significant byte of the address). If
     * ``connection`` is set to one of the DHCP options then ``ip``, ``subnet_mask``
     * and ``gateway`` are ignored, you can set them to 0.
     * 
     * The last parameter is the port that your program will connect to. The
     * default port, that is used by brickd, is 4223.
     * 
     * The values are stored in the EEPROM and only applied on startup. That means
     * you have to restart the Master Brick after configuration.
     * 
     * It is recommended to use the Brick Viewer to set the WIFI configuration.
     * 
     */
    public short[] getIp(){
        return ip;
    }

    public void setIp(short[] ip){
        this.ip = ip;
    }

    /**
     * 
     * Sets the configuration of the WIFI Extension. The ``ssid`` can have a max length
     * of 32 characters. Possible values for ``connection`` are:
     * 
     * .. csv-table::
     *  :header: "Value", "Description"
     *  :widths: 10, 90
     * 
     *  "0", "DHCP"
     *  "1", "Static IP"
     *  "2", "Access Point: DHCP"
     *  "3", "Access Point: Static IP"
     *  "4", "Ad Hoc: DHCP"
     *  "5", "Ad Hoc: Static IP"
     * 
     * If you set ``connection`` to one of the static IP options then you have to
     * supply ``ip``, ``subnet_mask`` and ``gateway`` as an array of size 4 (first
     * element of the array is the least significant byte of the address). If
     * ``connection`` is set to one of the DHCP options then ``ip``, ``subnet_mask``
     * and ``gateway`` are ignored, you can set them to 0.
     * 
     * The last parameter is the port that your program will connect to. The
     * default port, that is used by brickd, is 4223.
     * 
     * The values are stored in the EEPROM and only applied on startup. That means
     * you have to restart the Master Brick after configuration.
     * 
     * It is recommended to use the Brick Viewer to set the WIFI configuration.
     * 
     */
    public short[] getSubnetMask(){
        return subnetMask;
    }

    public void setSubnetMask(short[] subnetMask){
        this.subnetMask = subnetMask;
    }

    /**
     * 
     * Sets the configuration of the WIFI Extension. The ``ssid`` can have a max length
     * of 32 characters. Possible values for ``connection`` are:
     * 
     * .. csv-table::
     *  :header: "Value", "Description"
     *  :widths: 10, 90
     * 
     *  "0", "DHCP"
     *  "1", "Static IP"
     *  "2", "Access Point: DHCP"
     *  "3", "Access Point: Static IP"
     *  "4", "Ad Hoc: DHCP"
     *  "5", "Ad Hoc: Static IP"
     * 
     * If you set ``connection`` to one of the static IP options then you have to
     * supply ``ip``, ``subnet_mask`` and ``gateway`` as an array of size 4 (first
     * element of the array is the least significant byte of the address). If
     * ``connection`` is set to one of the DHCP options then ``ip``, ``subnet_mask``
     * and ``gateway`` are ignored, you can set them to 0.
     * 
     * The last parameter is the port that your program will connect to. The
     * default port, that is used by brickd, is 4223.
     * 
     * The values are stored in the EEPROM and only applied on startup. That means
     * you have to restart the Master Brick after configuration.
     * 
     * It is recommended to use the Brick Viewer to set the WIFI configuration.
     * 
     */
    public short[] getGateway(){
        return gateway;
    }

    public void setGateway(short[] gateway){
        this.gateway = gateway;
    }

    /**
     * 
     * Sets the configuration of the WIFI Extension. The ``ssid`` can have a max length
     * of 32 characters. Possible values for ``connection`` are:
     * 
     * .. csv-table::
     *  :header: "Value", "Description"
     *  :widths: 10, 90
     * 
     *  "0", "DHCP"
     *  "1", "Static IP"
     *  "2", "Access Point: DHCP"
     *  "3", "Access Point: Static IP"
     *  "4", "Ad Hoc: DHCP"
     *  "5", "Ad Hoc: Static IP"
     * 
     * If you set ``connection`` to one of the static IP options then you have to
     * supply ``ip``, ``subnet_mask`` and ``gateway`` as an array of size 4 (first
     * element of the array is the least significant byte of the address). If
     * ``connection`` is set to one of the DHCP options then ``ip``, ``subnet_mask``
     * and ``gateway`` are ignored, you can set them to 0.
     * 
     * The last parameter is the port that your program will connect to. The
     * default port, that is used by brickd, is 4223.
     * 
     * The values are stored in the EEPROM and only applied on startup. That means
     * you have to restart the Master Brick after configuration.
     * 
     * It is recommended to use the Brick Viewer to set the WIFI configuration.
     * 
     */
    public Integer getPort(){
        return port;
    }

    public void setPort(Integer port){
        this.port = port;
    }

    /**
     * 
     * Sets the encryption of the WIFI Extension. The first parameter is the
     * type of the encryption. Possible values are:
     * 
     * .. csv-table::
     *  :header: "Value", "Description"
     *  :widths: 10, 90
     * 
     *  "0", "WPA/WPA2"
     *  "1", "WPA Enterprise (EAP-FAST, EAP-TLS, EAP-TTLS, PEAP)"
     *  "2", "WEP"
     *  "3", "No Encryption"
     * 
     * The ``key`` has a max length of 50 characters and is used if ``encryption``
     * is set to 0 or 2 (WPA/WPA2 or WEP). Otherwise the value is ignored.
     * 
     * For WPA/WPA2 the key has to be at least 8 characters long. If you want to set
     * a key with more than 50 characters, see :func:`SetLongWifiKey`.
     * 
     * For WEP the key has to be either 10 or 26 hexadecimal digits long. It is
     * possible to set the WEP ``key_index`` (1-4). If you don't know your
     * ``key_index``, it is likely 1.
     * 
     * If you choose WPA Enterprise as encryption, you have to set ``eap_options`` and
     * the length of the certificates (for other encryption types these parameters
     * are ignored). The certificate length are given in byte and the certificates
     * themselves can be set with :func:`SetWifiCertificate`. ``eap_options`` consist
     * of the outer authentication (bits 1-2), inner authentication (bit 3) and
     * certificate type (bits 4-5):
     * 
     * .. csv-table::
     *  :header: "Option", "Bits", "Description"
     *  :widths: 20, 10, 70
     * 
     *  "outer authentication", "1-2", "0=EAP-FAST, 1=EAP-TLS, 2=EAP-TTLS, 3=EAP-PEAP"
     *  "inner authentication", "3", "0=EAP-MSCHAP, 1=EAP-GTC"
     *  "certificate type", "4-5", "0=CA Certificate, 1=Client Certificate, 2=Private Key"
     * 
     * Example for EAP-TTLS + EAP-GTC + Private Key: ``option = 2 | (1 << 2) | (2 << 3)``.
     * 
     * The values are stored in the EEPROM and only applied on startup. That means
     * you have to restart the Master Brick after configuration.
     * 
     * It is recommended to use the Brick Viewer to set the Wi-Fi encryption.
     * 
     */
    public Short getEncryption(){
        return encryption;
    }

    public void setEncryption(Short encryption){
        this.encryption = encryption;
    }

    /**
     * 
     * Sets the encryption of the WIFI Extension. The first parameter is the
     * type of the encryption. Possible values are:
     * 
     * .. csv-table::
     *  :header: "Value", "Description"
     *  :widths: 10, 90
     * 
     *  "0", "WPA/WPA2"
     *  "1", "WPA Enterprise (EAP-FAST, EAP-TLS, EAP-TTLS, PEAP)"
     *  "2", "WEP"
     *  "3", "No Encryption"
     * 
     * The ``key`` has a max length of 50 characters and is used if ``encryption``
     * is set to 0 or 2 (WPA/WPA2 or WEP). Otherwise the value is ignored.
     * 
     * For WPA/WPA2 the key has to be at least 8 characters long. If you want to set
     * a key with more than 50 characters, see :func:`SetLongWifiKey`.
     * 
     * For WEP the key has to be either 10 or 26 hexadecimal digits long. It is
     * possible to set the WEP ``key_index`` (1-4). If you don't know your
     * ``key_index``, it is likely 1.
     * 
     * If you choose WPA Enterprise as encryption, you have to set ``eap_options`` and
     * the length of the certificates (for other encryption types these parameters
     * are ignored). The certificate length are given in byte and the certificates
     * themselves can be set with :func:`SetWifiCertificate`. ``eap_options`` consist
     * of the outer authentication (bits 1-2), inner authentication (bit 3) and
     * certificate type (bits 4-5):
     * 
     * .. csv-table::
     *  :header: "Option", "Bits", "Description"
     *  :widths: 20, 10, 70
     * 
     *  "outer authentication", "1-2", "0=EAP-FAST, 1=EAP-TLS, 2=EAP-TTLS, 3=EAP-PEAP"
     *  "inner authentication", "3", "0=EAP-MSCHAP, 1=EAP-GTC"
     *  "certificate type", "4-5", "0=CA Certificate, 1=Client Certificate, 2=Private Key"
     * 
     * Example for EAP-TTLS + EAP-GTC + Private Key: ``option = 2 | (1 << 2) | (2 << 3)``.
     * 
     * The values are stored in the EEPROM and only applied on startup. That means
     * you have to restart the Master Brick after configuration.
     * 
     * It is recommended to use the Brick Viewer to set the Wi-Fi encryption.
     * 
     */
    public String getKey(){
        return key;
    }

    public void setKey(String key){
        this.key = key;
    }

    /**
     * 
     * Sets the encryption of the WIFI Extension. The first parameter is the
     * type of the encryption. Possible values are:
     * 
     * .. csv-table::
     *  :header: "Value", "Description"
     *  :widths: 10, 90
     * 
     *  "0", "WPA/WPA2"
     *  "1", "WPA Enterprise (EAP-FAST, EAP-TLS, EAP-TTLS, PEAP)"
     *  "2", "WEP"
     *  "3", "No Encryption"
     * 
     * The ``key`` has a max length of 50 characters and is used if ``encryption``
     * is set to 0 or 2 (WPA/WPA2 or WEP). Otherwise the value is ignored.
     * 
     * For WPA/WPA2 the key has to be at least 8 characters long. If you want to set
     * a key with more than 50 characters, see :func:`SetLongWifiKey`.
     * 
     * For WEP the key has to be either 10 or 26 hexadecimal digits long. It is
     * possible to set the WEP ``key_index`` (1-4). If you don't know your
     * ``key_index``, it is likely 1.
     * 
     * If you choose WPA Enterprise as encryption, you have to set ``eap_options`` and
     * the length of the certificates (for other encryption types these parameters
     * are ignored). The certificate length are given in byte and the certificates
     * themselves can be set with :func:`SetWifiCertificate`. ``eap_options`` consist
     * of the outer authentication (bits 1-2), inner authentication (bit 3) and
     * certificate type (bits 4-5):
     * 
     * .. csv-table::
     *  :header: "Option", "Bits", "Description"
     *  :widths: 20, 10, 70
     * 
     *  "outer authentication", "1-2", "0=EAP-FAST, 1=EAP-TLS, 2=EAP-TTLS, 3=EAP-PEAP"
     *  "inner authentication", "3", "0=EAP-MSCHAP, 1=EAP-GTC"
     *  "certificate type", "4-5", "0=CA Certificate, 1=Client Certificate, 2=Private Key"
     * 
     * Example for EAP-TTLS + EAP-GTC + Private Key: ``option = 2 | (1 << 2) | (2 << 3)``.
     * 
     * The values are stored in the EEPROM and only applied on startup. That means
     * you have to restart the Master Brick after configuration.
     * 
     * It is recommended to use the Brick Viewer to set the Wi-Fi encryption.
     * 
     */
    public Short getKeyIndex(){
        return keyIndex;
    }

    public void setKeyIndex(Short keyIndex){
        this.keyIndex = keyIndex;
    }

    /**
     * 
     * Sets the encryption of the WIFI Extension. The first parameter is the
     * type of the encryption. Possible values are:
     * 
     * .. csv-table::
     *  :header: "Value", "Description"
     *  :widths: 10, 90
     * 
     *  "0", "WPA/WPA2"
     *  "1", "WPA Enterprise (EAP-FAST, EAP-TLS, EAP-TTLS, PEAP)"
     *  "2", "WEP"
     *  "3", "No Encryption"
     * 
     * The ``key`` has a max length of 50 characters and is used if ``encryption``
     * is set to 0 or 2 (WPA/WPA2 or WEP). Otherwise the value is ignored.
     * 
     * For WPA/WPA2 the key has to be at least 8 characters long. If you want to set
     * a key with more than 50 characters, see :func:`SetLongWifiKey`.
     * 
     * For WEP the key has to be either 10 or 26 hexadecimal digits long. It is
     * possible to set the WEP ``key_index`` (1-4). If you don't know your
     * ``key_index``, it is likely 1.
     * 
     * If you choose WPA Enterprise as encryption, you have to set ``eap_options`` and
     * the length of the certificates (for other encryption types these parameters
     * are ignored). The certificate length are given in byte and the certificates
     * themselves can be set with :func:`SetWifiCertificate`. ``eap_options`` consist
     * of the outer authentication (bits 1-2), inner authentication (bit 3) and
     * certificate type (bits 4-5):
     * 
     * .. csv-table::
     *  :header: "Option", "Bits", "Description"
     *  :widths: 20, 10, 70
     * 
     *  "outer authentication", "1-2", "0=EAP-FAST, 1=EAP-TLS, 2=EAP-TTLS, 3=EAP-PEAP"
     *  "inner authentication", "3", "0=EAP-MSCHAP, 1=EAP-GTC"
     *  "certificate type", "4-5", "0=CA Certificate, 1=Client Certificate, 2=Private Key"
     * 
     * Example for EAP-TTLS + EAP-GTC + Private Key: ``option = 2 | (1 << 2) | (2 << 3)``.
     * 
     * The values are stored in the EEPROM and only applied on startup. That means
     * you have to restart the Master Brick after configuration.
     * 
     * It is recommended to use the Brick Viewer to set the Wi-Fi encryption.
     * 
     */
    public Short getEapOptions(){
        return eapOptions;
    }

    public void setEapOptions(Short eapOptions){
        this.eapOptions = eapOptions;
    }

    /**
     * 
     * Sets the encryption of the WIFI Extension. The first parameter is the
     * type of the encryption. Possible values are:
     * 
     * .. csv-table::
     *  :header: "Value", "Description"
     *  :widths: 10, 90
     * 
     *  "0", "WPA/WPA2"
     *  "1", "WPA Enterprise (EAP-FAST, EAP-TLS, EAP-TTLS, PEAP)"
     *  "2", "WEP"
     *  "3", "No Encryption"
     * 
     * The ``key`` has a max length of 50 characters and is used if ``encryption``
     * is set to 0 or 2 (WPA/WPA2 or WEP). Otherwise the value is ignored.
     * 
     * For WPA/WPA2 the key has to be at least 8 characters long. If you want to set
     * a key with more than 50 characters, see :func:`SetLongWifiKey`.
     * 
     * For WEP the key has to be either 10 or 26 hexadecimal digits long. It is
     * possible to set the WEP ``key_index`` (1-4). If you don't know your
     * ``key_index``, it is likely 1.
     * 
     * If you choose WPA Enterprise as encryption, you have to set ``eap_options`` and
     * the length of the certificates (for other encryption types these parameters
     * are ignored). The certificate length are given in byte and the certificates
     * themselves can be set with :func:`SetWifiCertificate`. ``eap_options`` consist
     * of the outer authentication (bits 1-2), inner authentication (bit 3) and
     * certificate type (bits 4-5):
     * 
     * .. csv-table::
     *  :header: "Option", "Bits", "Description"
     *  :widths: 20, 10, 70
     * 
     *  "outer authentication", "1-2", "0=EAP-FAST, 1=EAP-TLS, 2=EAP-TTLS, 3=EAP-PEAP"
     *  "inner authentication", "3", "0=EAP-MSCHAP, 1=EAP-GTC"
     *  "certificate type", "4-5", "0=CA Certificate, 1=Client Certificate, 2=Private Key"
     * 
     * Example for EAP-TTLS + EAP-GTC + Private Key: ``option = 2 | (1 << 2) | (2 << 3)``.
     * 
     * The values are stored in the EEPROM and only applied on startup. That means
     * you have to restart the Master Brick after configuration.
     * 
     * It is recommended to use the Brick Viewer to set the Wi-Fi encryption.
     * 
     */
    public Integer getCaCertificateLength(){
        return caCertificateLength;
    }

    public void setCaCertificateLength(Integer caCertificateLength){
        this.caCertificateLength = caCertificateLength;
    }

    /**
     * 
     * Sets the encryption of the WIFI Extension. The first parameter is the
     * type of the encryption. Possible values are:
     * 
     * .. csv-table::
     *  :header: "Value", "Description"
     *  :widths: 10, 90
     * 
     *  "0", "WPA/WPA2"
     *  "1", "WPA Enterprise (EAP-FAST, EAP-TLS, EAP-TTLS, PEAP)"
     *  "2", "WEP"
     *  "3", "No Encryption"
     * 
     * The ``key`` has a max length of 50 characters and is used if ``encryption``
     * is set to 0 or 2 (WPA/WPA2 or WEP). Otherwise the value is ignored.
     * 
     * For WPA/WPA2 the key has to be at least 8 characters long. If you want to set
     * a key with more than 50 characters, see :func:`SetLongWifiKey`.
     * 
     * For WEP the key has to be either 10 or 26 hexadecimal digits long. It is
     * possible to set the WEP ``key_index`` (1-4). If you don't know your
     * ``key_index``, it is likely 1.
     * 
     * If you choose WPA Enterprise as encryption, you have to set ``eap_options`` and
     * the length of the certificates (for other encryption types these parameters
     * are ignored). The certificate length are given in byte and the certificates
     * themselves can be set with :func:`SetWifiCertificate`. ``eap_options`` consist
     * of the outer authentication (bits 1-2), inner authentication (bit 3) and
     * certificate type (bits 4-5):
     * 
     * .. csv-table::
     *  :header: "Option", "Bits", "Description"
     *  :widths: 20, 10, 70
     * 
     *  "outer authentication", "1-2", "0=EAP-FAST, 1=EAP-TLS, 2=EAP-TTLS, 3=EAP-PEAP"
     *  "inner authentication", "3", "0=EAP-MSCHAP, 1=EAP-GTC"
     *  "certificate type", "4-5", "0=CA Certificate, 1=Client Certificate, 2=Private Key"
     * 
     * Example for EAP-TTLS + EAP-GTC + Private Key: ``option = 2 | (1 << 2) | (2 << 3)``.
     * 
     * The values are stored in the EEPROM and only applied on startup. That means
     * you have to restart the Master Brick after configuration.
     * 
     * It is recommended to use the Brick Viewer to set the Wi-Fi encryption.
     * 
     */
    public Integer getClientCertificateLength(){
        return clientCertificateLength;
    }

    public void setClientCertificateLength(Integer clientCertificateLength){
        this.clientCertificateLength = clientCertificateLength;
    }

    /**
     * 
     * Sets the encryption of the WIFI Extension. The first parameter is the
     * type of the encryption. Possible values are:
     * 
     * .. csv-table::
     *  :header: "Value", "Description"
     *  :widths: 10, 90
     * 
     *  "0", "WPA/WPA2"
     *  "1", "WPA Enterprise (EAP-FAST, EAP-TLS, EAP-TTLS, PEAP)"
     *  "2", "WEP"
     *  "3", "No Encryption"
     * 
     * The ``key`` has a max length of 50 characters and is used if ``encryption``
     * is set to 0 or 2 (WPA/WPA2 or WEP). Otherwise the value is ignored.
     * 
     * For WPA/WPA2 the key has to be at least 8 characters long. If you want to set
     * a key with more than 50 characters, see :func:`SetLongWifiKey`.
     * 
     * For WEP the key has to be either 10 or 26 hexadecimal digits long. It is
     * possible to set the WEP ``key_index`` (1-4). If you don't know your
     * ``key_index``, it is likely 1.
     * 
     * If you choose WPA Enterprise as encryption, you have to set ``eap_options`` and
     * the length of the certificates (for other encryption types these parameters
     * are ignored). The certificate length are given in byte and the certificates
     * themselves can be set with :func:`SetWifiCertificate`. ``eap_options`` consist
     * of the outer authentication (bits 1-2), inner authentication (bit 3) and
     * certificate type (bits 4-5):
     * 
     * .. csv-table::
     *  :header: "Option", "Bits", "Description"
     *  :widths: 20, 10, 70
     * 
     *  "outer authentication", "1-2", "0=EAP-FAST, 1=EAP-TLS, 2=EAP-TTLS, 3=EAP-PEAP"
     *  "inner authentication", "3", "0=EAP-MSCHAP, 1=EAP-GTC"
     *  "certificate type", "4-5", "0=CA Certificate, 1=Client Certificate, 2=Private Key"
     * 
     * Example for EAP-TTLS + EAP-GTC + Private Key: ``option = 2 | (1 << 2) | (2 << 3)``.
     * 
     * The values are stored in the EEPROM and only applied on startup. That means
     * you have to restart the Master Brick after configuration.
     * 
     * It is recommended to use the Brick Viewer to set the Wi-Fi encryption.
     * 
     */
    public Integer getPrivateKeyLength(){
        return privateKeyLength;
    }

    public void setPrivateKeyLength(Integer privateKeyLength){
        this.privateKeyLength = privateKeyLength;
    }

    /**
     * 
     * This function is used to set the certificate as well as password and username
     * for WPA Enterprise. To set the username use index 0xFFFF,
     * to set the password use index 0xFFFE. The max length of username and 
     * password is 32.
     * 
     * The certificate is written in chunks of size 32 and the index is used as
     * the index of the chunk. ``data_length`` should nearly always be 32. Only
     * the last chunk can have a length that is not equal to 32.
     * 
     * The starting index of the CA Certificate is 0, of the Client Certificate
     * 10000 and for the Private Key 20000. Maximum sizes are 1312, 1312 and
     * 4320 byte respectively.
     * 
     * The values are stored in the EEPROM and only applied on startup. That means
     * you have to restart the Master Brick after uploading the certificate.
     * 
     * It is recommended to use the Brick Viewer to set the certificate, username
     * and password.
     * 
     */
    public Integer getIndex(){
        return index;
    }

    public void setIndex(Integer index){
        this.index = index;
    }

    /**
     * 
     * This function is used to set the certificate as well as password and username
     * for WPA Enterprise. To set the username use index 0xFFFF,
     * to set the password use index 0xFFFE. The max length of username and 
     * password is 32.
     * 
     * The certificate is written in chunks of size 32 and the index is used as
     * the index of the chunk. ``data_length`` should nearly always be 32. Only
     * the last chunk can have a length that is not equal to 32.
     * 
     * The starting index of the CA Certificate is 0, of the Client Certificate
     * 10000 and for the Private Key 20000. Maximum sizes are 1312, 1312 and
     * 4320 byte respectively.
     * 
     * The values are stored in the EEPROM and only applied on startup. That means
     * you have to restart the Master Brick after uploading the certificate.
     * 
     * It is recommended to use the Brick Viewer to set the certificate, username
     * and password.
     * 
     */
    public short[] getData(){
        return data;
    }

    public void setData(short[] data){
        this.data = data;
    }

    /**
     * 
     * This function is used to set the certificate as well as password and username
     * for WPA Enterprise. To set the username use index 0xFFFF,
     * to set the password use index 0xFFFE. The max length of username and 
     * password is 32.
     * 
     * The certificate is written in chunks of size 32 and the index is used as
     * the index of the chunk. ``data_length`` should nearly always be 32. Only
     * the last chunk can have a length that is not equal to 32.
     * 
     * The starting index of the CA Certificate is 0, of the Client Certificate
     * 10000 and for the Private Key 20000. Maximum sizes are 1312, 1312 and
     * 4320 byte respectively.
     * 
     * The values are stored in the EEPROM and only applied on startup. That means
     * you have to restart the Master Brick after uploading the certificate.
     * 
     * It is recommended to use the Brick Viewer to set the certificate, username
     * and password.
     * 
     */
    public Short getDataLength(){
        return dataLength;
    }

    public void setDataLength(Short dataLength){
        this.dataLength = dataLength;
    }

    /**
     * 
     * Returns the certificate for a given index as set by :func:`SetWifiCertificate`.
     * 
     */
    public Integer getIndex2(){
        return index2;
    }

    public void setIndex2(Integer index2){
        this.index2 = index2;
    }

    /**
     * 
     * Sets the power mode of the WIFI Extension. Possible modes are:
     * 
     * .. csv-table::
     *  :header: "Mode", "Description"
     *  :widths: 10, 90
     * 
     *  "0", "Full Speed (high power consumption, high throughput)"
     *  "1", "Low Power (low power consumption, low throughput)"
     * 
     * The default value is 0 (Full Speed).
     * 
     */
    public Short getMode(){
        return mode;
    }

    public void setMode(Short mode){
        this.mode = mode;
    }

    /**
     * 
     * Sets the regulatory domain of the WIFI Extension. Possible domains are:
     * 
     * .. csv-table::
     *  :header: "Domain", "Description"
     *  :widths: 10, 90
     * 
     *  "0", "FCC: Channel 1-11 (N/S America, Australia, New Zealand)"
     *  "1", "ETSI: Channel 1-13 (Europe, Middle East, Africa)"
     *  "2", "TELEC: Channel 1-14 (Japan)"
     * 
     * The default value is 1 (ETSI).
     * 
     */
    public Short getDomain(){
        return domain;
    }

    public void setDomain(Short domain){
        this.domain = domain;
    }

    /**
     * 
     * Sets a long Wi-Fi key (up to 63 chars, at least 8 chars) for WPA encryption.
     * This key will be used
     * if the key in :func:`SetWifiEncryption` is set to "-". In the old protocol,
     * a payload of size 63 was not possible, so the maximum key length was 50 chars.
     * 
     * With the new protocol this is possible, since we didn't want to break API,
     * this function was added additionally.
     * 
     */
    public String getKey2(){
        return key2;
    }

    public void setKey2(String key2){
        this.key2 = key2;
    }

    /**
     * 
     * Sets the hostname of the WIFI Extension. The hostname will be displayed 
     * by access points as the hostname in the DHCP clients table.
     * 
     * Setting an empty String will restore the default hostname.
     * 
     */
    public String getHostname(){
        return hostname;
    }

    public void setHostname(String hostname){
        this.hostname = hostname;
    }

    /**
     * 
     * Sets the period in ms with which the :func:`StackCurrent` callback is triggered
     * periodically. A value of 0 turns the callback off.
     * 
     * :func:`StackCurrent` is only triggered if the current has changed since the
     * last triggering.
     * 
     * The default value is 0.
     * 
     */
    public Long getPeriod(){
        return period;
    }

    public void setPeriod(Long period){
        this.period = period;
    }

    /**
     * 
     * Sets the period in ms with which the :func:`StackVoltage` callback is triggered
     * periodically. A value of 0 turns the callback off.
     * 
     * :func:`StackVoltage` is only triggered if the voltage has changed since the
     * last triggering.
     * 
     * The default value is 0.
     * 
     */
    public Long getPeriod2(){
        return period2;
    }

    public void setPeriod2(Long period2){
        this.period2 = period2;
    }

    /**
     * 
     * Sets the period in ms with which the :func:`USBVoltage` callback is triggered
     * periodically. A value of 0 turns the callback off.
     * 
     * :func:`USBVoltage` is only triggered if the voltage has changed since the
     * last triggering.
     * 
     * The default value is 0.
     * 
     */
    public Long getPeriod3(){
        return period3;
    }

    public void setPeriod3(Long period3){
        this.period3 = period3;
    }

    /**
     * 
     * Sets the thresholds for the :func:`StackCurrentReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the current is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the current is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the current is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the current is greater than the min value (max is ignored)"
     * 
     * The default value is ('x', 0, 0).
     * 
     */
    public Character getOption(){
        return option;
    }

    public void setOption(Character option){
        this.option = option;
    }

    /**
     * 
     * Sets the thresholds for the :func:`StackCurrentReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the current is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the current is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the current is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the current is greater than the min value (max is ignored)"
     * 
     * The default value is ('x', 0, 0).
     * 
     */
    public Integer getMin(){
        return min;
    }

    public void setMin(Integer min){
        this.min = min;
    }

    /**
     * 
     * Sets the thresholds for the :func:`StackCurrentReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the current is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the current is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the current is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the current is greater than the min value (max is ignored)"
     * 
     * The default value is ('x', 0, 0).
     * 
     */
    public Integer getMax(){
        return max;
    }

    public void setMax(Integer max){
        this.max = max;
    }

    /**
     * 
     * Sets the thresholds for the :func:`StackStackVoltageReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the voltage is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the voltage is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the voltage is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the voltage is greater than the min value (max is ignored)"
     * 
     * The default value is ('x', 0, 0).
     * 
     */
    public Character getOption2(){
        return option2;
    }

    public void setOption2(Character option2){
        this.option2 = option2;
    }

    /**
     * 
     * Sets the thresholds for the :func:`StackStackVoltageReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the voltage is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the voltage is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the voltage is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the voltage is greater than the min value (max is ignored)"
     * 
     * The default value is ('x', 0, 0).
     * 
     */
    public Integer getMin2(){
        return min2;
    }

    public void setMin2(Integer min2){
        this.min2 = min2;
    }

    /**
     * 
     * Sets the thresholds for the :func:`StackStackVoltageReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the voltage is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the voltage is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the voltage is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the voltage is greater than the min value (max is ignored)"
     * 
     * The default value is ('x', 0, 0).
     * 
     */
    public Integer getMax2(){
        return max2;
    }

    public void setMax2(Integer max2){
        this.max2 = max2;
    }

    /**
     * 
     * Sets the thresholds for the :func:`USBVoltageReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the voltage is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the voltage is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the voltage is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the voltage is greater than the min value (max is ignored)"
     * 
     * The default value is ('x', 0, 0).
     * 
     */
    public Character getOption3(){
        return option3;
    }

    public void setOption3(Character option3){
        this.option3 = option3;
    }

    /**
     * 
     * Sets the thresholds for the :func:`USBVoltageReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the voltage is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the voltage is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the voltage is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the voltage is greater than the min value (max is ignored)"
     * 
     * The default value is ('x', 0, 0).
     * 
     */
    public Integer getMin3(){
        return min3;
    }

    public void setMin3(Integer min3){
        this.min3 = min3;
    }

    /**
     * 
     * Sets the thresholds for the :func:`USBVoltageReached` callback. 
     * 
     * The following options are possible:
     * 
     * .. csv-table::
     *  :header: "Option", "Description"
     *  :widths: 10, 100
     * 
     *  "'x'",    "Callback is turned off"
     *  "'o'",    "Callback is triggered when the voltage is *outside* the min and max values"
     *  "'i'",    "Callback is triggered when the voltage is *inside* the min and max values"
     *  "'<'",    "Callback is triggered when the voltage is smaller than the min value (max is ignored)"
     *  "'>'",    "Callback is triggered when the voltage is greater than the min value (max is ignored)"
     * 
     * The default value is ('x', 0, 0).
     * 
     */
    public Integer getMax3(){
        return max3;
    }

    public void setMax3(Integer max3){
        this.max3 = max3;
    }

    /**
     * 
     * Sets the period in ms with which the threshold callbacks
     * 
     * * :func:`StackCurrentReached`,
     * * :func:`StackVoltageReached`,
     * * :func:`USBVoltageReached`
     * 
     * are triggered, if the thresholds
     * 
     * * :func:`SetStackCurrentCallbackThreshold`,
     * * :func:`SetStackVoltageCallbackThreshold`,
     * * :func:`SetUSBVoltageCallbackThreshold`
     * 
     * keep being reached.
     * 
     * The default value is 100.
     * 
     */
    public Long getDebounce(){
        return debounce;
    }

    public void setDebounce(Long debounce){
        this.debounce = debounce;
    }

    /**
     * 
     * Sets the configuration of the Ethernet Extension. Possible values for
     * ``connection`` are:
     * 
     * .. csv-table::
     *  :header: "Value", "Description"
     *  :widths: 10, 90
     * 
     *  "0", "DHCP"
     *  "1", "Static IP"
     * 
     * If you set ``connection`` to static IP options then you have to supply ``ip``,
     * ``subnet_mask`` and ``gateway`` as an array of size 4 (first element of the
     * array is the least significant byte of the address). If ``connection`` is set
     * to the DHCP options then ``ip``, ``subnet_mask`` and ``gateway`` are ignored,
     * you can set them to 0.
     * 
     * The last parameter is the port that your program will connect to. The
     * default port, that is used by brickd, is 4223.
     * 
     * The values are stored in the EEPROM and only applied on startup. That means
     * you have to restart the Master Brick after configuration.
     * 
     * It is recommended to use the Brick Viewer to set the Ethernet configuration.
     * 
     */
    public Short getConnection2(){
        return connection2;
    }

    public void setConnection2(Short connection2){
        this.connection2 = connection2;
    }

    /**
     * 
     * Sets the configuration of the Ethernet Extension. Possible values for
     * ``connection`` are:
     * 
     * .. csv-table::
     *  :header: "Value", "Description"
     *  :widths: 10, 90
     * 
     *  "0", "DHCP"
     *  "1", "Static IP"
     * 
     * If you set ``connection`` to static IP options then you have to supply ``ip``,
     * ``subnet_mask`` and ``gateway`` as an array of size 4 (first element of the
     * array is the least significant byte of the address). If ``connection`` is set
     * to the DHCP options then ``ip``, ``subnet_mask`` and ``gateway`` are ignored,
     * you can set them to 0.
     * 
     * The last parameter is the port that your program will connect to. The
     * default port, that is used by brickd, is 4223.
     * 
     * The values are stored in the EEPROM and only applied on startup. That means
     * you have to restart the Master Brick after configuration.
     * 
     * It is recommended to use the Brick Viewer to set the Ethernet configuration.
     * 
     */
    public short[] getIp2(){
        return ip2;
    }

    public void setIp2(short[] ip2){
        this.ip2 = ip2;
    }

    /**
     * 
     * Sets the configuration of the Ethernet Extension. Possible values for
     * ``connection`` are:
     * 
     * .. csv-table::
     *  :header: "Value", "Description"
     *  :widths: 10, 90
     * 
     *  "0", "DHCP"
     *  "1", "Static IP"
     * 
     * If you set ``connection`` to static IP options then you have to supply ``ip``,
     * ``subnet_mask`` and ``gateway`` as an array of size 4 (first element of the
     * array is the least significant byte of the address). If ``connection`` is set
     * to the DHCP options then ``ip``, ``subnet_mask`` and ``gateway`` are ignored,
     * you can set them to 0.
     * 
     * The last parameter is the port that your program will connect to. The
     * default port, that is used by brickd, is 4223.
     * 
     * The values are stored in the EEPROM and only applied on startup. That means
     * you have to restart the Master Brick after configuration.
     * 
     * It is recommended to use the Brick Viewer to set the Ethernet configuration.
     * 
     */
    public short[] getSubnetMask2(){
        return subnetMask2;
    }

    public void setSubnetMask2(short[] subnetMask2){
        this.subnetMask2 = subnetMask2;
    }

    /**
     * 
     * Sets the configuration of the Ethernet Extension. Possible values for
     * ``connection`` are:
     * 
     * .. csv-table::
     *  :header: "Value", "Description"
     *  :widths: 10, 90
     * 
     *  "0", "DHCP"
     *  "1", "Static IP"
     * 
     * If you set ``connection`` to static IP options then you have to supply ``ip``,
     * ``subnet_mask`` and ``gateway`` as an array of size 4 (first element of the
     * array is the least significant byte of the address). If ``connection`` is set
     * to the DHCP options then ``ip``, ``subnet_mask`` and ``gateway`` are ignored,
     * you can set them to 0.
     * 
     * The last parameter is the port that your program will connect to. The
     * default port, that is used by brickd, is 4223.
     * 
     * The values are stored in the EEPROM and only applied on startup. That means
     * you have to restart the Master Brick after configuration.
     * 
     * It is recommended to use the Brick Viewer to set the Ethernet configuration.
     * 
     */
    public short[] getGateway2(){
        return gateway2;
    }

    public void setGateway2(short[] gateway2){
        this.gateway2 = gateway2;
    }

    /**
     * 
     * Sets the configuration of the Ethernet Extension. Possible values for
     * ``connection`` are:
     * 
     * .. csv-table::
     *  :header: "Value", "Description"
     *  :widths: 10, 90
     * 
     *  "0", "DHCP"
     *  "1", "Static IP"
     * 
     * If you set ``connection`` to static IP options then you have to supply ``ip``,
     * ``subnet_mask`` and ``gateway`` as an array of size 4 (first element of the
     * array is the least significant byte of the address). If ``connection`` is set
     * to the DHCP options then ``ip``, ``subnet_mask`` and ``gateway`` are ignored,
     * you can set them to 0.
     * 
     * The last parameter is the port that your program will connect to. The
     * default port, that is used by brickd, is 4223.
     * 
     * The values are stored in the EEPROM and only applied on startup. That means
     * you have to restart the Master Brick after configuration.
     * 
     * It is recommended to use the Brick Viewer to set the Ethernet configuration.
     * 
     */
    public Integer getPort2(){
        return port2;
    }

    public void setPort2(Integer port2){
        this.port2 = port2;
    }

    /**
     * 
     * Sets the hostname of the Ethernet Extension. The hostname will be displayed 
     * by access points as the hostname in the DHCP clients table.
     * 
     * Setting an empty String will restore the default hostname.
     * 
     * The current hostname can be discovered with :func:`GetEthernetStatus`.
     * 
     */
    public String getHostname2(){
        return hostname2;
    }

    public void setHostname2(String hostname2){
        this.hostname2 = hostname2;
    }

    /**
     * 
     * Sets the MAC address of the Ethernet Extension. The Ethernet Extension should
     * come configured with a valid MAC address, that is also written on a
     * sticker of the extension itself.
     * 
     * The MAC address can be read out again with :func:`GetEthernetStatus`.
     * 
     */
    public short[] getMacAddress(){
        return macAddress;
    }

    public void setMacAddress(short[] macAddress){
        this.macAddress = macAddress;
    }

    /**
     * 
     * Sets the Ethernet WebSocket configuration. The first parameter sets the number of socket
     * connections that are reserved for WebSockets. The range is 0-7. The connections
     * are shared with the plain sockets. Example: If you set the connections to 3,
     * there will be 3 WebSocket and 4 plain socket connections available.
     * 
     * The second parameter is the port for the WebSocket connections. The port can
     * not be the same as the port for the plain socket connections.
     * 
     * The values are stored in the EEPROM and only applied on startup. That means
     * you have to restart the Master Brick after configuration.
     * 
     * It is recommended to use the Brick Viewer to set the Ethernet configuration.
     * 
     * The default values are 3 for the socket connections and 4280 for the port.
     * 
     */
    public Short getSockets(){
        return sockets;
    }

    public void setSockets(Short sockets){
        this.sockets = sockets;
    }

    /**
     * 
     * Sets the Ethernet WebSocket configuration. The first parameter sets the number of socket
     * connections that are reserved for WebSockets. The range is 0-7. The connections
     * are shared with the plain sockets. Example: If you set the connections to 3,
     * there will be 3 WebSocket and 4 plain socket connections available.
     * 
     * The second parameter is the port for the WebSocket connections. The port can
     * not be the same as the port for the plain socket connections.
     * 
     * The values are stored in the EEPROM and only applied on startup. That means
     * you have to restart the Master Brick after configuration.
     * 
     * It is recommended to use the Brick Viewer to set the Ethernet configuration.
     * 
     * The default values are 3 for the socket connections and 4280 for the port.
     * 
     */
    public Integer getPort3(){
        return port3;
    }

    public void setPort3(Integer port3){
        this.port3 = port3;
    }

    /**
     * 
     * Sets the Ethernet authentication secret. The secret can be a string of up to 64
     * characters. An empty string disables the authentication.
     * 
     * See the :ref:`authentication tutorial <tutorial_authentication>` for more
     * information.
     * 
     * The secret is stored in the EEPROM and only applied on startup. That means
     * you have to restart the Master Brick after configuration.
     * 
     * It is recommended to use the Brick Viewer to set the Ethernet authentication secret.
     * 
     * The default value is an empty string (authentication disabled).
     * 
     */
    public String getSecret(){
        return secret;
    }

    public void setSecret(String secret){
        this.secret = secret;
    }

    /**
     * 
     * Sets the WIFI authentication secret. The secret can be a string of up to 64
     * characters. An empty string disables the authentication.
     * 
     * See the :ref:`authentication tutorial <tutorial_authentication>` for more
     * information.
     * 
     * The secret is stored in the EEPROM and only applied on startup. That means
     * you have to restart the Master Brick after configuration.
     * 
     * It is recommended to use the Brick Viewer to set the WIFI authentication secret.
     * 
     * The default value is an empty string (authentication disabled).
     * 
     */
    public String getSecret2(){
        return secret2;
    }

    public void setSecret2(String secret2){
        this.secret2 = secret2;
    }

    /**
     * 
     * Returns the firmware and protocol version and the name of the Bricklet for a
     * given port.
     * 
     * This functions sole purpose is to allow automatic flashing of v1.x.y Bricklet
     * plugins.
     * 
     */
    public Character getPort4(){
        return port4;
    }

    public void setPort4(Character port4){
        this.port4 = port4;
    }



}
