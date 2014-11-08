##IndustrialDigitalIn4


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|                group |     char[] |
|             debounce |       Long |
|        interruptMask |    Integer |
|                  pin |      Short |
|         resetCounter |    Boolean |
|        selectionMask |    Integer |
|             edgeType |      Short |
|            debounce2 |      Short |
|                 pin2 |      Short |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|             getValue |                                          |
|             setGroup |                                    group |
|             getGroup |                                          |
| getAvailableForGroup |                                          |
|    setDebouncePeriod |                                 debounce |
|    getDebouncePeriod |                                          |
|         setInterrupt |                            interruptMask |
|         getInterrupt |                                          |
|         getEdgeCount |                        pin, resetCounter |
|   setEdgeCountConfig |       selectionMask, edgeType, debounce2 |
|   getEdgeCountConfig |                                     pin2 |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|            interrupt |        firedBy, interruptMask, valueMask |


