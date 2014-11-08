##LCD20x4


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
|                line2 |      Short |
|                text2 |     String |
|                line3 |      Short |
|              counter |    Integer |



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
|       setDefaultText |                             line2, text2 |
|       getDefaultText |                                    line3 |
| setDefaultTextCounter |                                  counter |
| getDefaultTextCounter |                                          |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|        buttonPressed |                          firedBy, button |
|       buttonReleased |                          firedBy, button |


