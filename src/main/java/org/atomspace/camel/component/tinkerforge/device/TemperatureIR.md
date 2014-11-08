##TemperatureIR


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|           emissivity |    Integer |
|               period |       Long |
|              period2 |       Long |
|               option |  Character |
|                  min |      Short |
|                  max |      Short |
|              option2 |  Character |
|                 min2 |      Short |
|                 max2 |      Short |
|             debounce |       Long |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
| getAmbientTemperature |                                          |
| getObjectTemperature |                                          |
|        setEmissivity |                               emissivity |
|        getEmissivity |                                          |
| setAmbientTemperatureCallbackPeriod |                                   period |
| getAmbientTemperatureCallbackPeriod |                                          |
| setObjectTemperatureCallbackPeriod |                                  period2 |
| getObjectTemperatureCallbackPeriod |                                          |
| setAmbientTemperatureCallbackThreshold |                         option, min, max |
| getAmbientTemperatureCallbackThreshold |                                          |
| setObjectTemperatureCallbackThreshold |                      option2, min2, max2 |
| getObjectTemperatureCallbackThreshold |                                          |
|    setDebouncePeriod |                                 debounce |
|    getDebouncePeriod |                                          |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|   ambientTemperature |                     firedBy, temperature |
|    objectTemperature |                     firedBy, temperature |
| ambientTemperatureReached |                     firedBy, temperature |
| objectTemperatureReached |                     firedBy, temperature |


