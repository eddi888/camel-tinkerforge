##Temperature


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|               period |       Long |
|               option |  Character |
|                  min |      Short |
|                  max |      Short |
|             debounce |       Long |
|                 mode |      Short |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|       getTemperature |                                          |
| setTemperatureCallbackPeriod |                                   period |
| getTemperatureCallbackPeriod |                                          |
| setTemperatureCallbackThreshold |                         option, min, max |
| getTemperatureCallbackThreshold |                                          |
|    setDebouncePeriod |                                 debounce |
|    getDebouncePeriod |                                          |
|           setI2CMode |                                     mode |
|           getI2CMode |                                          |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|          temperature |                     firedBy, temperature |
|   temperatureReached |                     firedBy, temperature |


