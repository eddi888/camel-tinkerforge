##LinearPoti


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
|          getPosition |                                          |
|       getAnalogValue |                                          |
| setPositionCallbackPeriod |                                   period |
| getPositionCallbackPeriod |                                          |
| setAnalogValueCallbackPeriod |                                  period2 |
| getAnalogValueCallbackPeriod |                                          |
| setPositionCallbackThreshold |                         option, min, max |
| getPositionCallbackThreshold |                                          |
| setAnalogValueCallbackThreshold |                      option2, min2, max2 |
| getAnalogValueCallbackThreshold |                                          |
|    setDebouncePeriod |                                 debounce |
|    getDebouncePeriod |                                          |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|             position |                        firedBy, position |
|          analogValue |                           firedBy, value |
|      positionReached |                        firedBy, position |
|   analogValueReached |                           firedBy, value |


