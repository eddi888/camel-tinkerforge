##RotaryEncoder


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|                reset |    Boolean |
|               period |       Long |
|               option |  Character |
|                  min |    Integer |
|                  max |    Integer |
|             debounce |       Long |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|             getCount |                                    reset |
| setCountCallbackPeriod |                                   period |
| getCountCallbackPeriod |                                          |
| setCountCallbackThreshold |                         option, min, max |
| getCountCallbackThreshold |                                          |
|    setDebouncePeriod |                                 debounce |
|    getDebouncePeriod |                                          |
|            isPressed |                                          |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|                count |                           firedBy, count |
|         countReached |                           firedBy, count |
|              pressed |                                  firedBy |
|             released |                                  firedBy |


