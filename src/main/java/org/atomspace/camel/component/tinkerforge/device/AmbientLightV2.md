##AmbientLightV2


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|               period |       Long |
|               option |  Character |
|                  min |       Long |
|                  max |       Long |
|             debounce |       Long |
|     illuminanceRange |      Short |
|      integrationTime |      Short |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|       getIlluminance |                                          |
| setIlluminanceCallbackPeriod |                                   period |
| getIlluminanceCallbackPeriod |                                          |
| setIlluminanceCallbackThreshold |                         option, min, max |
| getIlluminanceCallbackThreshold |                                          |
|    setDebouncePeriod |                                 debounce |
|    getDebouncePeriod |                                          |
|     setConfiguration |        illuminanceRange, integrationTime |
|     getConfiguration |                                          |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|          illuminance |                     firedBy, illuminance |
|   illuminanceReached |                     firedBy, illuminance |


