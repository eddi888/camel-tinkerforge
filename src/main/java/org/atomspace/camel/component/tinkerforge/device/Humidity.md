##Humidity


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|               period |       Long |
|              period2 |       Long |
|               option |  Character |
|                  min |      Short |
|                  max |      Short |
|              option2 |  Character |
|                 min2 |    Integer |
|                 max2 |    Integer |
|             debounce |       Long |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|          getHumidity |                                          |
|       getAnalogValue |                                          |
| setHumidityCallbackPeriod |                                   period |
| getHumidityCallbackPeriod |                                          |
| setAnalogValueCallbackPeriod |                                  period2 |
| getAnalogValueCallbackPeriod |                                          |
| setHumidityCallbackThreshold |                         option, min, max |
| getHumidityCallbackThreshold |                                          |
| setAnalogValueCallbackThreshold |                      option2, min2, max2 |
| getAnalogValueCallbackThreshold |                                          |
|    setDebouncePeriod |                                 debounce |
|    getDebouncePeriod |                                          |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|             humidity |                        firedBy, humidity |
|          analogValue |                           firedBy, value |
|      humidityReached |                        firedBy, humidity |
|   analogValueReached |                           firedBy, value |


