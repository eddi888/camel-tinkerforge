##SolidStateRelay


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|                state |    Boolean |
|               state2 |    Boolean |
|                 time |       Long |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|             setState |                                    state |
|             getState |                                          |
|          setMonoflop |                             state2, time |
|          getMonoflop |                                          |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|         monoflopDone |                           firedBy, state |


