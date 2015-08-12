##IMU


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|                range |      Short |
|               range2 |      Short |
|                speed |    Integer |
|                  typ |      Short |
|                 data |    short[] |
|                 typ2 |      Short |
|               period |       Long |
|              period2 |       Long |
|              period3 |       Long |
|              period4 |       Long |
|              period5 |       Long |
|              period6 |       Long |
|                 port |  Character |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|      getAcceleration |                                          |
|     getMagneticField |                                          |
|   getAngularVelocity |                                          |
|           getAllData |                                          |
|       getOrientation |                                          |
|        getQuaternion |                                          |
|    getIMUTemperature |                                          |
|               ledsOn |                                          |
|              ledsOff |                                          |
|            areLedsOn |                                          |
| setAccelerationRange |                                    range |
| getAccelerationRange |                                          |
| setMagnetometerRange |                                   range2 |
| getMagnetometerRange |                                          |
|  setConvergenceSpeed |                                    speed |
|  getConvergenceSpeed |                                          |
|       setCalibration |                                typ, data |
|       getCalibration |                                     typ2 |
| setAccelerationPeriod |                                   period |
| getAccelerationPeriod |                                          |
| setMagneticFieldPeriod |                                  period2 |
| getMagneticFieldPeriod |                                          |
| setAngularVelocityPeriod |                                  period3 |
| getAngularVelocityPeriod |                                          |
|     setAllDataPeriod |                                  period4 |
|     getAllDataPeriod |                                          |
| setOrientationPeriod |                                  period5 |
| getOrientationPeriod |                                          |
|  setQuaternionPeriod |                                  period6 |
|  getQuaternionPeriod |                                          |
| orientationCalculationOn |                                          |
| orientationCalculationOff |                                          |
| isOrientationCalculationOn |                                          |
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
|         acceleration |                         firedBy, x, y, z |
|        magneticField |                         firedBy, x, y, z |
|      angularVelocity |                         firedBy, x, y, z |
|              allData | firedBy, accX, accY, accZ, magX, magY, magZ, angX, angY, angZ, temperature |
|          orientation |                firedBy, roll, pitch, yaw |
|           quaternion |                      firedBy, x, y, z, w |


