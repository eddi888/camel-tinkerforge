##MultiTouch


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|    enabledElectrodes |    Integer |
|          sensitivity |      Short |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|        getTouchState |                                          |
|          recalibrate |                                          |
|   setElectrodeConfig |                        enabledElectrodes |
|   getElectrodeConfig |                                          |
| setElectrodeSensitivity |                              sensitivity |
| getElectrodeSensitivity |                                          |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|           touchState |                           firedBy, state |


