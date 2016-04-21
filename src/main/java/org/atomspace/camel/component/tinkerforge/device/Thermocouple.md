##Thermocouple


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|               period |       Long |
|               option |  Character |
|                  min |    Integer |
|                  max |    Integer |
|             debounce |       Long |
|            averaging |      Short |
|     thermocoupleType |      Short |
|               filter |      Short |



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
|     setConfiguration |      averaging, thermocoupleType, filter |
|     getConfiguration |                                          |
|        getErrorState |                                          |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|          temperature |                     firedBy, temperature |
|   temperatureReached |                     firedBy, temperature |
|           errorState |          firedBy, overUnder, openCircuit |


