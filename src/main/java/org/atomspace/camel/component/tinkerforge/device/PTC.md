##PTC


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
|               filter |      Short |
|                 mode |      Short |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|       getTemperature |                                          |
|        getResistance |                                          |
| setTemperatureCallbackPeriod |                                   period |
| getTemperatureCallbackPeriod |                                          |
| setResistanceCallbackPeriod |                                  period2 |
| getResistanceCallbackPeriod |                                          |
| setTemperatureCallbackThreshold |                         option, min, max |
| getTemperatureCallbackThreshold |                                          |
| setResistanceCallbackThreshold |                      option2, min2, max2 |
| getResistanceCallbackThreshold |                                          |
|    setDebouncePeriod |                                 debounce |
|    getDebouncePeriod |                                          |
| setNoiseRejectionFilter |                                   filter |
| getNoiseRejectionFilter |                                          |
|    isSensorConnected |                                          |
|          setWireMode |                                     mode |
|          getWireMode |                                          |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|          temperature |                     firedBy, temperature |
|   temperatureReached |                     firedBy, temperature |
|           resistance |                      firedBy, resistance |
|    resistanceReached |                      firedBy, resistance |


