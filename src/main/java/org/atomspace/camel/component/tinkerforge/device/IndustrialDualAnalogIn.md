##IndustrialDualAnalogIn


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|              channel |      Short |
|             channel2 |      Short |
|               period |       Long |
|             channel3 |      Short |
|             channel4 |      Short |
|               option |  Character |
|                  min |    Integer |
|                  max |    Integer |
|             channel5 |      Short |
|             debounce |       Long |
|                 rate |      Short |
|               offset |      int[] |
|                 gain |      int[] |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|           getVoltage |                                  channel |
| setVoltageCallbackPeriod |                         channel2, period |
| getVoltageCallbackPeriod |                                 channel3 |
| setVoltageCallbackThreshold |               channel4, option, min, max |
| getVoltageCallbackThreshold |                                 channel5 |
|    setDebouncePeriod |                                 debounce |
|    getDebouncePeriod |                                          |
|        setSampleRate |                                     rate |
|        getSampleRate |                                          |
|       setCalibration |                             offset, gain |
|       getCalibration |                                          |
|         getADCValues |                                          |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|              voltage |                firedBy, channel, voltage |
|       voltageReached |                firedBy, channel, voltage |


