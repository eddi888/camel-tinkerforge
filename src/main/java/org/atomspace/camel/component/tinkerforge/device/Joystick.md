##Joystick


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|               period |       Long |
|              period2 |       Long |
|               option |  Character |
|                 minX |      Short |
|                 maxX |      Short |
|                 minY |      Short |
|                 maxY |      Short |
|              option2 |  Character |
|                minX2 |    Integer |
|                maxX2 |    Integer |
|                minY2 |    Integer |
|                maxY2 |    Integer |
|             debounce |       Long |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|          getPosition |                                          |
|            isPressed |                                          |
|       getAnalogValue |                                          |
|            calibrate |                                          |
| setPositionCallbackPeriod |                                   period |
| getPositionCallbackPeriod |                                          |
| setAnalogValueCallbackPeriod |                                  period2 |
| getAnalogValueCallbackPeriod |                                          |
| setPositionCallbackThreshold |           option, minX, maxX, minY, maxY |
| getPositionCallbackThreshold |                                          |
| setAnalogValueCallbackThreshold |      option2, minX2, maxX2, minY2, maxY2 |
| getAnalogValueCallbackThreshold |                                          |
|    setDebouncePeriod |                                 debounce |
|    getDebouncePeriod |                                          |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|             position |                            firedBy, x, y |
|          analogValue |                            firedBy, x, y |
|      positionReached |                            firedBy, x, y |
|   analogValueReached |                            firedBy, x, y |
|              pressed |                                  firedBy |
|             released |                                  firedBy |


