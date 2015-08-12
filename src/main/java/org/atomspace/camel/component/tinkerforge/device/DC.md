##DC


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|             velocity |      Short |
|         acceleration |    Integer |
|            frequency |    Integer |
|              voltage |    Integer |
|                 mode |      Short |
|               period |    Integer |
|                 port |  Character |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|          setVelocity |                                 velocity |
|          getVelocity |                                          |
|   getCurrentVelocity |                                          |
|      setAcceleration |                             acceleration |
|      getAcceleration |                                          |
|      setPWMFrequency |                                frequency |
|      getPWMFrequency |                                          |
|            fullBrake |                                          |
| getStackInputVoltage |                                          |
| getExternalInputVoltage |                                          |
| getCurrentConsumption |                                          |
|               enable |                                          |
|              disable |                                          |
|            isEnabled |                                          |
|    setMinimumVoltage |                                  voltage |
|    getMinimumVoltage |                                          |
|         setDriveMode |                                     mode |
|         getDriveMode |                                          |
| setCurrentVelocityPeriod |                                   period |
| getCurrentVelocityPeriod |                                          |
|      enableStatusLED |                                          |
|     disableStatusLED |                                          |
|   isStatusLEDEnabled |                                          |
| getProtocol1BrickletName |                                     port |
|   getChipTemperature |                                          |
|                reset |                                          |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|         underVoltage |                         firedBy, voltage |
|    emergencyShutdown |                                  firedBy |
|      velocityReached |                        firedBy, velocity |
|      currentVelocity |                        firedBy, velocity |


