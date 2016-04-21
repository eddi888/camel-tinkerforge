##AnalogIn


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
|                range |      Short |
|              average |      Short |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|           getVoltage |                                          |
|       getAnalogValue |                                          |
| setVoltageCallbackPeriod |                                   period |
| getVoltageCallbackPeriod |                                          |
| setAnalogValueCallbackPeriod |                                  period2 |
| getAnalogValueCallbackPeriod |                                          |
| setVoltageCallbackThreshold |                         option, min, max |
| getVoltageCallbackThreshold |                                          |
| setAnalogValueCallbackThreshold |                      option2, min2, max2 |
| getAnalogValueCallbackThreshold |                                          |
|    setDebouncePeriod |                                 debounce |
|    getDebouncePeriod |                                          |
|             setRange |                                    range |
|             getRange |                                          |
|         setAveraging |                                  average |
|         getAveraging |                                          |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|              voltage |                         firedBy, voltage |
|          analogValue |                           firedBy, value |
|       voltageReached |                         firedBy, voltage |
|   analogValueReached |                           firedBy, value |


