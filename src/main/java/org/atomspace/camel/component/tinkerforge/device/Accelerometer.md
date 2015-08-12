##Accelerometer


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|               period |       Long |
|               option |  Character |
|                 minX |      Short |
|                 maxX |      Short |
|                 minY |      Short |
|                 maxY |      Short |
|                 minZ |      Short |
|                 maxZ |      Short |
|             debounce |       Long |
|             dataRate |      Short |
|            fullScale |      Short |
|      filterBandwidth |      Short |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|      getAcceleration |                                          |
| setAccelerationCallbackPeriod |                                   period |
| getAccelerationCallbackPeriod |                                          |
| setAccelerationCallbackThreshold | option, minX, maxX, minY, maxY, minZ, maxZ |
| getAccelerationCallbackThreshold |                                          |
|    setDebouncePeriod |                                 debounce |
|    getDebouncePeriod |                                          |
|       getTemperature |                                          |
|     setConfiguration |     dataRate, fullScale, filterBandwidth |
|     getConfiguration |                                          |
|                ledOn |                                          |
|               ledOff |                                          |
|              isLEDOn |                                          |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|         acceleration |                         firedBy, x, y, z |
|  accelerationReached |                         firedBy, x, y, z |


