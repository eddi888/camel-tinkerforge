##Stepper


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|             velocity |    Integer |
|         acceleration |    Integer |
|       deacceleration |    Integer |
|             position |    Integer |
|            position2 |    Integer |
|                steps |    Integer |
|                 mode |      Short |
|              current |    Integer |
|                decay |    Integer |
|              voltage |    Integer |
|             syncRect |    Boolean |
|             timeBase |       Long |
|               period |       Long |
|                 port |  Character |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|       setMaxVelocity |                                 velocity |
|       getMaxVelocity |                                          |
|   getCurrentVelocity |                                          |
|      setSpeedRamping |             acceleration, deacceleration |
|      getSpeedRamping |                                          |
|            fullBrake |                                          |
|   setCurrentPosition |                                 position |
|   getCurrentPosition |                                          |
|    setTargetPosition |                                position2 |
|    getTargetPosition |                                          |
|             setSteps |                                    steps |
|             getSteps |                                          |
|    getRemainingSteps |                                          |
|          setStepMode |                                     mode |
|          getStepMode |                                          |
|         driveForward |                                          |
|        driveBackward |                                          |
|                 stop |                                          |
| getStackInputVoltage |                                          |
| getExternalInputVoltage |                                          |
| getCurrentConsumption |                                          |
|      setMotorCurrent |                                  current |
|      getMotorCurrent |                                          |
|               enable |                                          |
|              disable |                                          |
|            isEnabled |                                          |
|             setDecay |                                    decay |
|             getDecay |                                          |
|    setMinimumVoltage |                                  voltage |
|    getMinimumVoltage |                                          |
|          setSyncRect |                                 syncRect |
|           isSyncRect |                                          |
|          setTimeBase |                                 timeBase |
|          getTimeBase |                                          |
|           getAllData |                                          |
|     setAllDataPeriod |                                   period |
|     getAllDataPeriod |                                          |
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
|      positionReached |                        firedBy, position |
|              allData | firedBy, currentVelocity, currentPosition, remainingSteps, stackVoltage, externalVoltage, currentConsumption |
|             newState |         firedBy, stateNew, statePrevious |


