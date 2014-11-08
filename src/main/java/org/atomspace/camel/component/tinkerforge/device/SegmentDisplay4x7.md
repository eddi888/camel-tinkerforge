##SegmentDisplay4x7


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|             segments |    short[] |
|           brightness |      Short |
|                colon |    Boolean |
|            valueFrom |      Short |
|              valueTo |      Short |
|            increment |      Short |
|               length |       Long |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|          setSegments |              segments, brightness, colon |
|          getSegments |                                          |
|         startCounter |    valueFrom, valueTo, increment, length |
|      getCounterValue |                                          |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|      counterFinished |                                  firedBy |


