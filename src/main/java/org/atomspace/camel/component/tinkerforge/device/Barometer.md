##Barometer


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|               period |       Long |
|              period2 |       Long |
|               option |  Character |
|                  min |    Integer |
|                  max |    Integer |
|              option2 |  Character |
|                 min2 |    Integer |
|                 max2 |    Integer |
|             debounce |       Long |
|          airPressure |    Integer |
| movingAveragePressure |      Short |
|      averagePressure |      Short |
|   averageTemperature |      Short |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|       getAirPressure |                                          |
|          getAltitude |                                          |
| setAirPressureCallbackPeriod |                                   period |
| getAirPressureCallbackPeriod |                                          |
| setAltitudeCallbackPeriod |                                  period2 |
| getAltitudeCallbackPeriod |                                          |
| setAirPressureCallbackThreshold |                         option, min, max |
| getAirPressureCallbackThreshold |                                          |
| setAltitudeCallbackThreshold |                      option2, min2, max2 |
| getAltitudeCallbackThreshold |                                          |
|    setDebouncePeriod |                                 debounce |
|    getDebouncePeriod |                                          |
| setReferenceAirPressure |                              airPressure |
|   getChipTemperature |                                          |
| getReferenceAirPressure |                                          |
|         setAveraging | movingAveragePressure, averagePressure, averageTemperature |
|         getAveraging |                                          |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|          airPressure |                     firedBy, airPressure |
|             altitude |                        firedBy, altitude |
|   airPressureReached |                     firedBy, airPressure |
|      altitudeReached |                        firedBy, altitude |


