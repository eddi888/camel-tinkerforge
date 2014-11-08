##IO4


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|            valueMask |      Short |
|        selectionMask |      Short |
|            direction |  Character |
|                value |    Boolean |
|             debounce |       Long |
|        interruptMask |      Short |
|       selectionMask2 |      Short |
|           valueMask2 |      Short |
|                 time |       Long |
|                  pin |      Short |
|       selectionMask3 |      Short |
|           valueMask3 |      Short |
|                 pin2 |      Short |
|         resetCounter |    Boolean |
|       selectionMask4 |      Short |
|             edgeType |      Short |
|            debounce2 |      Short |
|                 pin3 |      Short |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|             setValue |                                valueMask |
|             getValue |                                          |
|     setConfiguration |          selectionMask, direction, value |
|     getConfiguration |                                          |
|    setDebouncePeriod |                                 debounce |
|    getDebouncePeriod |                                          |
|         setInterrupt |                            interruptMask |
|         getInterrupt |                                          |
|          setMonoflop |         selectionMask2, valueMask2, time |
|          getMonoflop |                                      pin |
|    setSelectedValues |               selectionMask3, valueMask3 |
|         getEdgeCount |                       pin2, resetCounter |
|   setEdgeCountConfig |      selectionMask4, edgeType, debounce2 |
|   getEdgeCountConfig |                                     pin3 |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|            interrupt |        firedBy, interruptMask, valueMask |
|         monoflopDone |        firedBy, selectionMask, valueMask |


