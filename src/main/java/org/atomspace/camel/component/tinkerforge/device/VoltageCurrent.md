##VoltageCurrent


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|            averaging |      Short |
| voltageConversionTime |      Short |
| currentConversionTime |      Short |
|       gainMultiplier |    Integer |
|          gainDivisor |    Integer |
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



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|           getCurrent |                                          |
|           getVoltage |                                          |
|             getPower |                                          |
|     setConfiguration | averaging, voltageConversionTime, currentConversionTime |
|     getConfiguration |                                          |
|       setCalibration |              gainMultiplier, gainDivisor |
|       getCalibration |                                          |
| setCurrentCallbackPeriod |                                   period |
| getCurrentCallbackPeriod |                                          |
| setVoltageCallbackPeriod |                                  period2 |
| getVoltageCallbackPeriod |                                          |
| setPowerCallbackPeriod |                                  period3 |
| getPowerCallbackPeriod |                                          |
| setCurrentCallbackThreshold |                         option, min, max |
| getCurrentCallbackThreshold |                                          |
| setVoltageCallbackThreshold |                      option2, min2, max2 |
| getVoltageCallbackThreshold |                                          |
| setPowerCallbackThreshold |                      option3, min3, max3 |
| getPowerCallbackThreshold |                                          |
|    setDebouncePeriod |                                 debounce |
|    getDebouncePeriod |                                          |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|              current |                         firedBy, current |
|              voltage |                         firedBy, voltage |
|                power |                           firedBy, power |
|       currentReached |                         firedBy, current |
|       voltageReached |                         firedBy, voltage |
|         powerReached |                           firedBy, power |


