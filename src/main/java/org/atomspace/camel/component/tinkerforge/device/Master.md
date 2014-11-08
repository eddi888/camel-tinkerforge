##Master


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|            extension |      Short |
|              exttype |       Long |
|           extension2 |      Short |
|              address |      Short |
|             address2 |      Short |
|                  num |      Short |
|             address3 |      Short |
|                 num2 |      Short |
|            frequency |      Short |
|              channel |      Short |
|             address4 |      Short |
|                 num3 |      Short |
|             address5 |      Short |
|                 num4 |      Short |
|                speed |       Long |
|               parity |  Character |
|             stopbits |      Short |
|                 ssid |     String |
|           connection |      Short |
|                   ip |    short[] |
|           subnetMask |    short[] |
|              gateway |    short[] |
|                 port |    Integer |
|           encryption |      Short |
|                  key |     String |
|             keyIndex |      Short |
|           eapOptions |      Short |
|  caCertificateLength |    Integer |
| clientCertificateLength |    Integer |
|     privateKeyLength |    Integer |
|                index |    Integer |
|                 data |    short[] |
|           dataLength |      Short |
|               index2 |    Integer |
|                 mode |      Short |
|               domain |      Short |
|                 key2 |     String |
|             hostname |     String |
|               period |       Long |
|              period2 |       Long |
|              period3 |       Long |
|               option |  Character |
|                  min |    Integer |
|                  max |    Integer |
|              option2 |  Character |
|                 min2 |    Integer |
|                 max2 |    Integer |
|              option3 |  Character |
|                 min3 |    Integer |
|                 max3 |    Integer |
|             debounce |       Long |
|          connection2 |      Short |
|                  ip2 |    short[] |
|          subnetMask2 |    short[] |
|             gateway2 |    short[] |
|                port2 |    Integer |
|            hostname2 |     String |
|           macAddress |    short[] |
|              sockets |      Short |
|                port3 |    Integer |
|               secret |     String |
|              secret2 |     String |
|                port4 |  Character |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|      getStackVoltage |                                          |
|      getStackCurrent |                                          |
|     setExtensionType |                       extension, exttype |
|     getExtensionType |                               extension2 |
|       isChibiPresent |                                          |
|      setChibiAddress |                                  address |
|      getChibiAddress |                                          |
| setChibiMasterAddress |                                 address2 |
| getChibiMasterAddress |                                          |
| setChibiSlaveAddress |                            num, address3 |
| getChibiSlaveAddress |                                     num2 |
| getChibiSignalStrength |                                          |
|     getChibiErrorLog |                                          |
|    setChibiFrequency |                                frequency |
|    getChibiFrequency |                                          |
|      setChibiChannel |                                  channel |
|      getChibiChannel |                                          |
|       isRS485Present |                                          |
|      setRS485Address |                                 address4 |
|      getRS485Address |                                          |
| setRS485SlaveAddress |                           num3, address5 |
| getRS485SlaveAddress |                                     num4 |
|     getRS485ErrorLog |                                          |
| setRS485Configuration |                  speed, parity, stopbits |
| getRS485Configuration |                                          |
|        isWifiPresent |                                          |
| setWifiConfiguration | ssid, connection, ip, subnetMask, gateway, port |
| getWifiConfiguration |                                          |
|    setWifiEncryption | encryption, key, keyIndex, eapOptions, caCertificateLength, clientCertificateLength, privateKeyLength |
|    getWifiEncryption |                                          |
|        getWifiStatus |                                          |
|    refreshWifiStatus |                                          |
|   setWifiCertificate |                  index, data, dataLength |
|   getWifiCertificate |                                   index2 |
|     setWifiPowerMode |                                     mode |
|     getWifiPowerMode |                                          |
|    getWifiBufferInfo |                                          |
| setWifiRegulatoryDomain |                                   domain |
| getWifiRegulatoryDomain |                                          |
|        getUSBVoltage |                                          |
|       setLongWifiKey |                                     key2 |
|       getLongWifiKey |                                          |
|      setWifiHostname |                                 hostname |
|      getWifiHostname |                                          |
| setStackCurrentCallbackPeriod |                                   period |
| getStackCurrentCallbackPeriod |                                          |
| setStackVoltageCallbackPeriod |                                  period2 |
| getStackVoltageCallbackPeriod |                                          |
| setUSBVoltageCallbackPeriod |                                  period3 |
| getUSBVoltageCallbackPeriod |                                          |
| setStackCurrentCallbackThreshold |                         option, min, max |
| getStackCurrentCallbackThreshold |                                          |
| setStackVoltageCallbackThreshold |                      option2, min2, max2 |
| getStackVoltageCallbackThreshold |                                          |
| setUSBVoltageCallbackThreshold |                      option3, min3, max3 |
| getUSBVoltageCallbackThreshold |                                          |
|    setDebouncePeriod |                                 debounce |
|    getDebouncePeriod |                                          |
|    isEthernetPresent |                                          |
| setEthernetConfiguration | connection2, ip2, subnetMask2, gateway2, port2 |
| getEthernetConfiguration |                                          |
|    getEthernetStatus |                                          |
|  setEthernetHostname |                                hostname2 |
| setEthernetMACAddress |                               macAddress |
| setEthernetWebsocketConfiguration |                           sockets, port3 |
| getEthernetWebsocketConfiguration |                                          |
| setEthernetAuthenticationSecret |                                   secret |
| getEthernetAuthenticationSecret |                                          |
| setWifiAuthenticationSecret |                                  secret2 |
| getWifiAuthenticationSecret |                                          |
| getProtocol1BrickletName |                                    port4 |
|   getChipTemperature |                                          |
|                reset |                                          |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|         stackCurrent |                         firedBy, current |
|         stackVoltage |                         firedBy, voltage |
|           uSBVoltage |                         firedBy, voltage |
|  stackCurrentReached |                         firedBy, current |
|  stackVoltageReached |                         firedBy, voltage |
|    uSBVoltageReached |                         firedBy, voltage |


