##OLED64x48


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|                 data |    short[] |
|           columnFrom |      Short |
|             columnTo |      Short |
|              rowFrom |      Short |
|                rowTo |      Short |
|             contrast |      Short |
|               invert |    Boolean |
|                 line |      Short |
|             position |      Short |
|                 text |     String |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|                write |                                     data |
|            newWindow |     columnFrom, columnTo, rowFrom, rowTo |
|         clearDisplay |                                          |
| setDisplayConfiguration |                         contrast, invert |
| getDisplayConfiguration |                                          |
|            writeLine |                     line, position, text |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|


