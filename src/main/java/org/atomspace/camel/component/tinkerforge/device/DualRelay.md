##DualRelay


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|               relay1 |    Boolean |
|               relay2 |    Boolean |
|                relay |      Short |
|                state |    Boolean |
|                 time |       Long |
|               relay3 |      Short |
|               relay4 |      Short |
|               state2 |    Boolean |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|             setState |                           relay1, relay2 |
|             getState |                                          |
|          setMonoflop |                       relay, state, time |
|          getMonoflop |                                   relay3 |
|     setSelectedState |                           relay4, state2 |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|         monoflopDone |                    firedBy, relay, state |


