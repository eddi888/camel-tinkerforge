##HallEffect


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|         resetCounter |    Boolean |
|             edgeType |      Short |
|             debounce |      Short |
|                edges |       Long |
|               period |       Long |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|             getValue |                                          |
|         getEdgeCount |                             resetCounter |
|   setEdgeCountConfig |                       edgeType, debounce |
|   getEdgeCountConfig |                                          |
|     setEdgeInterrupt |                                    edges |
|     getEdgeInterrupt |                                          |
| setEdgeCountCallbackPeriod |                                   period |
| getEdgeCountCallbackPeriod |                                          |
|        edgeInterrupt |                                          |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|            edgeCount |                    firedBy, count, value |


