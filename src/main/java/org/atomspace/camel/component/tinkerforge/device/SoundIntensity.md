##SoundIntensity


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|               period |       Long |
|               option |  Character |
|                  min |    Integer |
|                  max |    Integer |
|             debounce |       Long |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|         getIntensity |                                          |
| setIntensityCallbackPeriod |                                   period |
| getIntensityCallbackPeriod |                                          |
| setIntensityCallbackThreshold |                         option, min, max |
| getIntensityCallbackThreshold |                                          |
|    setDebouncePeriod |                                 debounce |
|    getDebouncePeriod |                                          |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|            intensity |                       firedBy, intensity |
|     intensityReached |                       firedBy, intensity |


