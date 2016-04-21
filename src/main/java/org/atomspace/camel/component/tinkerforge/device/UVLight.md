##UVLight


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|               period |       Long |
|               option |  Character |
|                  min |       Long |
|                  max |       Long |
|             debounce |       Long |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|           getUVLight |                                          |
| setUVLightCallbackPeriod |                                   period |
| getUVLightCallbackPeriod |                                          |
| setUVLightCallbackThreshold |                         option, min, max |
| getUVLightCallbackThreshold |                                          |
|    setDebouncePeriod |                                 debounce |
|    getDebouncePeriod |                                          |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|              uVLight |                         firedBy, uvLight |
|       uVLightReached |                         firedBy, uvLight |


