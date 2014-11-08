##IndustrialDigitalOut4


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|            valueMask |    Integer |
|        selectionMask |    Integer |
|           valueMask2 |    Integer |
|                 time |       Long |
|                  pin |      Short |
|                group |     char[] |
|       selectionMask2 |    Integer |
|           valueMask3 |    Integer |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|             setValue |                                valueMask |
|             getValue |                                          |
|          setMonoflop |          selectionMask, valueMask2, time |
|          getMonoflop |                                      pin |
|             setGroup |                                    group |
|             getGroup |                                          |
| getAvailableForGroup |                                          |
|    setSelectedValues |               selectionMask2, valueMask3 |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|         monoflopDone |        firedBy, selectionMask, valueMask |


