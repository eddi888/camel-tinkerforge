##AmbientLight


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



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|       getIlluminance |                                          |
|       getAnalogValue |                                          |
| setIlluminanceCallbackPeriod |                                   period |
| getIlluminanceCallbackPeriod |                                          |
| setAnalogValueCallbackPeriod |                                  period2 |
| getAnalogValueCallbackPeriod |                                          |
| setIlluminanceCallbackThreshold |                         option, min, max |
| getIlluminanceCallbackThreshold |                                          |
| setAnalogValueCallbackThreshold |                      option2, min2, max2 |
| getAnalogValueCallbackThreshold |                                          |
|    setDebouncePeriod |                                 debounce |
|    getDebouncePeriod |                                          |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|          illuminance |                     firedBy, illuminance |
|          analogValue |                           firedBy, value |
|   illuminanceReached |                     firedBy, illuminance |
|   analogValueReached |                           firedBy, value |


