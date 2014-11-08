##LEDStrip


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|                index |    Integer |
|               length |      Short |
|                    r |    short[] |
|                    g |    short[] |
|                    b |    short[] |
|               index2 |    Integer |
|              length2 |      Short |
|             duration |    Integer |
|            frequency |       Long |
|                 chip |    Integer |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|         setRGBValues |                   index, length, r, g, b |
|         getRGBValues |                          index2, length2 |
|     setFrameDuration |                                 duration |
|     getFrameDuration |                                          |
|     getSupplyVoltage |                                          |
|    setClockFrequency |                                frequency |
|    getClockFrequency |                                          |
|          setChipType |                                     chip |
|          getChipType |                                          |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|        frameRendered |                          firedBy, length |


