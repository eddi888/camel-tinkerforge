##DistanceUS


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|               period |       Long |
|               option |  Character |
|                  min |      Short |
|                  max |      Short |
|             debounce |       Long |
|              average |      Short |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|     getDistanceValue |                                          |
| setDistanceCallbackPeriod |                                   period |
| getDistanceCallbackPeriod |                                          |
| setDistanceCallbackThreshold |                         option, min, max |
| getDistanceCallbackThreshold |                                          |
|    setDebouncePeriod |                                 debounce |
|    getDebouncePeriod |                                          |
|     setMovingAverage |                                  average |
|     getMovingAverage |                                          |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|             distance |                        firedBy, distance |
|      distanceReached |                        firedBy, distance |


