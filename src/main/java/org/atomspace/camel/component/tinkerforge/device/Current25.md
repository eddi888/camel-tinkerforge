##Current25


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|               period |       Long |
|              period2 |       Long |
|               option |  Character |
|                  min |      Short |
|                  max |      Short |
|              option2 |  Character |
|                 min2 |    Integer |
|                 max2 |    Integer |
|             debounce |       Long |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|           getCurrent |                                          |
|            calibrate |                                          |
|        isOverCurrent |                                          |
|       getAnalogValue |                                          |
| setCurrentCallbackPeriod |                                   period |
| getCurrentCallbackPeriod |                                          |
| setAnalogValueCallbackPeriod |                                  period2 |
| getAnalogValueCallbackPeriod |                                          |
| setCurrentCallbackThreshold |                         option, min, max |
| getCurrentCallbackThreshold |                                          |
| setAnalogValueCallbackThreshold |                      option2, min2, max2 |
| getAnalogValueCallbackThreshold |                                          |
|    setDebouncePeriod |                                 debounce |
|    getDebouncePeriod |                                          |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|              current |                         firedBy, current |
|          analogValue |                           firedBy, value |
|       currentReached |                         firedBy, current |
|   analogValueReached |                           firedBy, value |
|          overCurrent |                                  firedBy |


