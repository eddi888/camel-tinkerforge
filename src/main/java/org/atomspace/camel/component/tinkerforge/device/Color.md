##Color


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|               period |       Long |
|               option |  Character |
|                 minR |    Integer |
|                 maxR |    Integer |
|                 minG |    Integer |
|                 maxG |    Integer |
|                 minB |    Integer |
|                 maxB |    Integer |
|                 minC |    Integer |
|                 maxC |    Integer |
|             debounce |       Long |
|                 gain |      Short |
|      integrationTime |      Short |
|              period2 |       Long |
|              period3 |       Long |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|             getColor |                                          |
| setColorCallbackPeriod |                                   period |
| getColorCallbackPeriod |                                          |
| setColorCallbackThreshold | option, minR, maxR, minG, maxG, minB, maxB, minC, maxC |
| getColorCallbackThreshold |                                          |
|    setDebouncePeriod |                                 debounce |
|    getDebouncePeriod |                                          |
|              lightOn |                                          |
|             lightOff |                                          |
|            isLightOn |                                          |
|            setConfig |                    gain, integrationTime |
|            getConfig |                                          |
|       getIlluminance |                                          |
|  getColorTemperature |                                          |
| setIlluminanceCallbackPeriod |                                  period2 |
| getIlluminanceCallbackPeriod |                                          |
| setColorTemperatureCallbackPeriod |                                  period3 |
| getColorTemperatureCallbackPeriod |                                          |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|                color |                      firedBy, r, g, b, c |
|         colorReached |                      firedBy, r, g, b, c |
|          illuminance |                     firedBy, illuminance |
|     colorTemperature |                firedBy, colorTemperature |


