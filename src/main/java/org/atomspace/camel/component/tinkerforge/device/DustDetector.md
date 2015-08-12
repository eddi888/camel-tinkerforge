##DustDetector


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
|       getDustDensity |                                          |
| setDustDensityCallbackPeriod |                                   period |
| getDustDensityCallbackPeriod |                                          |
| setDustDensityCallbackThreshold |                         option, min, max |
| getDustDensityCallbackThreshold |                                          |
|    setDebouncePeriod |                                 debounce |
|    getDebouncePeriod |                                          |
|     setMovingAverage |                                  average |
|     getMovingAverage |                                          |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|          dustDensity |                     firedBy, dustDensity |
|   dustDensityReached |                     firedBy, dustDensity |


