##Moisture


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|               period |       Long |
|               option |  Character |
|                  min |    Integer |
|                  max |    Integer |
|             debounce |       Long |
|              average |      Short |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|     getMoistureValue |                                          |
| setMoistureCallbackPeriod |                                   period |
| getMoistureCallbackPeriod |                                          |
| setMoistureCallbackThreshold |                         option, min, max |
| getMoistureCallbackThreshold |                                          |
|    setDebouncePeriod |                                 debounce |
|    getDebouncePeriod |                                          |
|     setMovingAverage |                                  average |
|     getMovingAverage |                                          |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|             moisture |                        firedBy, moisture |
|      moistureReached |                        firedBy, moisture |


