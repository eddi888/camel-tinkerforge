##PiezoSpeaker


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|             duration |       Long |
|            frequency |    Integer |
|                morse |     String |
|           frequency2 |    Integer |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|                 beep |                      duration, frequency |
|            morseCode |                        morse, frequency2 |
|            calibrate |                                          |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|         beepFinished |                                  firedBy |
|    morseCodeFinished |                                  firedBy |


