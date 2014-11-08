##LCD16x2


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|                 line |      Short |
|             position |      Short |
|                 text |     String |
|               cursor |    Boolean |
|             blinking |    Boolean |
|               button |      Short |
|                index |      Short |
|            character |    short[] |
|               index2 |      Short |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|            writeLine |                     line, position, text |
|         clearDisplay |                                          |
|          backlightOn |                                          |
|         backlightOff |                                          |
|        isBacklightOn |                                          |
|            setConfig |                         cursor, blinking |
|            getConfig |                                          |
|      isButtonPressed |                                   button |
|   setCustomCharacter |                         index, character |
|   getCustomCharacter |                                   index2 |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|        buttonPressed |                          firedBy, button |
|       buttonReleased |                          firedBy, button |


