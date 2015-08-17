##LoadCell


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|               period |       Long |
|               option |  Character |
|                  min |    Integer |
|                  max |    Integer |
|             debounce |       Long |
|              average |      Short |
|               weight |       Long |
|                 rate |      Short |
|                 gain |      Short |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|            getWeight |                                          |
| setWeightCallbackPeriod |                                   period |
| getWeightCallbackPeriod |                                          |
| setWeightCallbackThreshold |                         option, min, max |
| getWeightCallbackThreshold |                                          |
|    setDebouncePeriod |                                 debounce |
|    getDebouncePeriod |                                          |
|     setMovingAverage |                                  average |
|     getMovingAverage |                                          |
|                lEDOn |                                          |
|               lEDOff |                                          |
|              isLEDOn |                                          |
|            calibrate |                                   weight |
|                 tare |                                          |
|     setConfiguration |                               rate, gain |
|     getConfiguration |                                          |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|               weight |                          firedBy, weight |
|        weightReached |                          firedBy, weight |


