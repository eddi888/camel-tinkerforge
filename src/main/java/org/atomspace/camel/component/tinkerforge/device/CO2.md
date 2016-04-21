##CO2


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
|  getCO2Concentration |                                          |
| setCO2ConcentrationCallbackPeriod |                                   period |
| getCO2ConcentrationCallbackPeriod |                                          |
| setCO2ConcentrationCallbackThreshold |                         option, min, max |
| getCO2ConcentrationCallbackThreshold |                                          |
|    setDebouncePeriod |                                 debounce |
|    getDebouncePeriod |                                          |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|     cO2Concentration |                firedBy, co2Concentration |
| cO2ConcentrationReached |                firedBy, co2Concentration |


