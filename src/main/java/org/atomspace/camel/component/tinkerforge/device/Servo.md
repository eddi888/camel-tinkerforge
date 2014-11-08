##Servo


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|             servoNum |      Short |
|            servoNum2 |      Short |
|            servoNum3 |      Short |
|            servoNum4 |      Short |
|             position |      Short |
|            servoNum5 |      Short |
|            servoNum6 |      Short |
|            servoNum7 |      Short |
|             velocity |    Integer |
|            servoNum8 |      Short |
|            servoNum9 |      Short |
|           servoNum10 |      Short |
|         acceleration |    Integer |
|           servoNum11 |      Short |
|              voltage |    Integer |
|           servoNum12 |      Short |
|                  min |    Integer |
|                  max |    Integer |
|           servoNum13 |      Short |
|           servoNum14 |      Short |
|                 min2 |      Short |
|                 max2 |      Short |
|           servoNum15 |      Short |
|           servoNum16 |      Short |
|               period |    Integer |
|           servoNum17 |      Short |
|           servoNum18 |      Short |
|             voltage2 |    Integer |
|                 port |  Character |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|               enable |                                 servoNum |
|              disable |                                servoNum2 |
|            isEnabled |                                servoNum3 |
|          setPosition |                      servoNum4, position |
|          getPosition |                                servoNum5 |
|   getCurrentPosition |                                servoNum6 |
|          setVelocity |                      servoNum7, velocity |
|          getVelocity |                                servoNum8 |
|   getCurrentVelocity |                                servoNum9 |
|      setAcceleration |                 servoNum10, acceleration |
|      getAcceleration |                               servoNum11 |
|     setOutputVoltage |                                  voltage |
|     getOutputVoltage |                                          |
|        setPulseWidth |                     servoNum12, min, max |
|        getPulseWidth |                               servoNum13 |
|            setDegree |                   servoNum14, min2, max2 |
|            getDegree |                               servoNum15 |
|            setPeriod |                       servoNum16, period |
|            getPeriod |                               servoNum17 |
|      getServoCurrent |                               servoNum18 |
|    getOverallCurrent |                                          |
| getStackInputVoltage |                                          |
| getExternalInputVoltage |                                          |
|    setMinimumVoltage |                                 voltage2 |
|    getMinimumVoltage |                                          |
| enablePositionReachedCallback |                                          |
| disablePositionReachedCallback |                                          |
| isPositionReachedCallbackEnabled |                                          |
| enableVelocityReachedCallback |                                          |
| disableVelocityReachedCallback |                                          |
| isVelocityReachedCallbackEnabled |                                          |
| getProtocol1BrickletName |                                     port |
|   getChipTemperature |                                          |
|                reset |                                          |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|         underVoltage |                         firedBy, voltage |
|      positionReached |              firedBy, servoNum, position |
|      velocityReached |              firedBy, servoNum, velocity |


