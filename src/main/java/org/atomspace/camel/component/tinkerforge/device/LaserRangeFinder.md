##LaserRangeFinder


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|               period |       Long |
|              period2 |       Long |
|               option |  Character |
|                  min |    Integer |
|                  max |    Integer |
|              option2 |  Character |
|                 min2 |      Short |
|                 max2 |      Short |
|             debounce |       Long |
| distanceAverageLength |      Short |
| velocityAverageLength |      Short |
|                 mode |      Short |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|          getDistance |                                          |
|          getVelocity |                                          |
| setDistanceCallbackPeriod |                                   period |
| getDistanceCallbackPeriod |                                          |
| setVelocityCallbackPeriod |                                  period2 |
| getVelocityCallbackPeriod |                                          |
| setDistanceCallbackThreshold |                         option, min, max |
| getDistanceCallbackThreshold |                                          |
| setVelocityCallbackThreshold |                      option2, min2, max2 |
| getVelocityCallbackThreshold |                                          |
|    setDebouncePeriod |                                 debounce |
|    getDebouncePeriod |                                          |
|     setMovingAverage | distanceAverageLength, velocityAverageLength |
|     getMovingAverage |                                          |
|              setMode |                                     mode |
|              getMode |                                          |
|          enableLaser |                                          |
|         disableLaser |                                          |
|       isLaserEnabled |                                          |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|             distance |                        firedBy, distance |
|             velocity |                        firedBy, velocity |
|      distanceReached |                        firedBy, distance |
|      velocityReached |                        firedBy, velocity |


