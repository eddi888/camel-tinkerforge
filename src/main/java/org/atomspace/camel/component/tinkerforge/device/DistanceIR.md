##DistanceIR


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|             position |      Short |
|             distance |    Integer |
|            position2 |      Short |
|               period |       Long |
|              period2 |       Long |
|               option |  Character |
|                  min |    Integer |
|                  max |    Integer |
|              option2 |  Character |
|                 min2 |    Integer |
|                 max2 |    Integer |
|             debounce |       Long |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|          getDistance |                                          |
|       getAnalogValue |                                          |
|     setSamplingPoint |                       position, distance |
|     getSamplingPoint |                                position2 |
| setDistanceCallbackPeriod |                                   period |
| getDistanceCallbackPeriod |                                          |
| setAnalogValueCallbackPeriod |                                  period2 |
| getAnalogValueCallbackPeriod |                                          |
| setDistanceCallbackThreshold |                         option, min, max |
| getDistanceCallbackThreshold |                                          |
| setAnalogValueCallbackThreshold |                      option2, min2, max2 |
| getAnalogValueCallbackThreshold |                                          |
|    setDebouncePeriod |                                 debounce |
|    getDebouncePeriod |                                          |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|             distance |                        firedBy, distance |
|          analogValue |                           firedBy, value |
|      distanceReached |                        firedBy, distance |
|   analogValueReached |                           firedBy, value |


