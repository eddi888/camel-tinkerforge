##Line


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
|      getReflectivity |                                          |
| setReflectivityCallbackPeriod |                                   period |
| getReflectivityCallbackPeriod |                                          |
| setReflectivityCallbackThreshold |                         option, min, max |
| getReflectivityCallbackThreshold |                                          |
|    setDebouncePeriod |                                 debounce |
|    getDebouncePeriod |                                          |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|         reflectivity |                    firedBy, reflectivity |
|  reflectivityReached |                    firedBy, reflectivity |


