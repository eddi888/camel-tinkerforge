##DualButton


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|                 ledL |      Short |
|                 ledR |      Short |
|                  led |      Short |
|                state |      Short |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|          setLEDState |                               ledL, ledR |
|          getLEDState |                                          |
|       getButtonState |                                          |
|  setSelectedLEDState |                               led, state |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|         stateChanged |    firedBy, buttonL, buttonR, ledL, ledR |


