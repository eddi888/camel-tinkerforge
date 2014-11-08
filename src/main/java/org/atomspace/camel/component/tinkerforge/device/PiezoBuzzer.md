##PiezoBuzzer


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|             duration |       Long |
|                morse |     String |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|                 beep |                                 duration |
|            morseCode |                                    morse |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|         beepFinished |                                  firedBy |
|    morseCodeFinished |                                  firedBy |


