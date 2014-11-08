##RemoteSwitch


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|            houseCode |      Short |
|         receiverCode |      Short |
|             switchTo |      Short |
|              repeats |      Short |
|           houseCode2 |      Short |
|        receiverCode2 |      Short |
|            switchTo2 |      Short |
|              address |       Long |
|                 unit |      Short |
|            switchTo3 |      Short |
|             address2 |       Long |
|                unit2 |      Short |
|             dimValue |      Short |
|           systemCode |  Character |
|           deviceCode |      Short |
|            switchTo4 |      Short |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|         switchSocket |        houseCode, receiverCode, switchTo |
|    getSwitchingState |                                          |
|           setRepeats |                                  repeats |
|           getRepeats |                                          |
|        switchSocketA |     houseCode2, receiverCode2, switchTo2 |
|        switchSocketB |                 address, unit, switchTo3 |
|           dimSocketB |                address2, unit2, dimValue |
|        switchSocketC |        systemCode, deviceCode, switchTo4 |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|        switchingDone |                                  firedBy |


