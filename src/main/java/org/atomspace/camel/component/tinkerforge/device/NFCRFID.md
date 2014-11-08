##NFCRFID


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|              tagType |      Short |
|                 page |    Integer |
|            keyNumber |      Short |
|                  key |    short[] |
|                page2 |    Integer |
|                 data |    short[] |
|                page3 |    Integer |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|         requestTagID |                                  tagType |
|             getTagID |                                          |
|             getState |                                          |
| authenticateMifareClassicPage |                     page, keyNumber, key |
|            writePage |                              page2, data |
|          requestPage |                                    page3 |
|              getPage |                                          |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|         stateChanged |                     firedBy, state, idle |


