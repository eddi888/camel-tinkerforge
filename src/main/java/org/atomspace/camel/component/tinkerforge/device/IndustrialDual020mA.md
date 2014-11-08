##IndustrialDual020mA


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|               sensor |      Short |
|              sensor2 |      Short |
|               period |       Long |
|              sensor3 |      Short |
|              sensor4 |      Short |
|               option |  Character |
|                  min |    Integer |
|                  max |    Integer |
|              sensor5 |      Short |
|             debounce |       Long |
|                 rate |      Short |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|           getCurrent |                                   sensor |
| setCurrentCallbackPeriod |                          sensor2, period |
| getCurrentCallbackPeriod |                                  sensor3 |
| setCurrentCallbackThreshold |                sensor4, option, min, max |
| getCurrentCallbackThreshold |                                  sensor5 |
|    setDebouncePeriod |                                 debounce |
|    getDebouncePeriod |                                          |
|        setSampleRate |                                     rate |
|        getSampleRate |                                          |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|              current |                 firedBy, sensor, current |
|       currentReached |                 firedBy, sensor, current |


