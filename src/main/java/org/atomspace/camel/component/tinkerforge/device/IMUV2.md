##IMUV2


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|               period |       Long |
|              period2 |       Long |
|              period3 |       Long |
|              period4 |       Long |
|              period5 |       Long |
|              period6 |       Long |
|              period7 |       Long |
|              period8 |       Long |
|              period9 |       Long |
|                 port |  Character |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|      getAcceleration |                                          |
|     getMagneticField |                                          |
|   getAngularVelocity |                                          |
|       getTemperature |                                          |
|       getOrientation |                                          |
| getLinearAcceleration |                                          |
|     getGravityVector |                                          |
|        getQuaternion |                                          |
|           getAllData |                                          |
|               ledsOn |                                          |
|              ledsOff |                                          |
|            areLedsOn |                                          |
|      saveCalibration |                                          |
| setAccelerationPeriod |                                   period |
| getAccelerationPeriod |                                          |
| setMagneticFieldPeriod |                                  period2 |
| getMagneticFieldPeriod |                                          |
| setAngularVelocityPeriod |                                  period3 |
| getAngularVelocityPeriod |                                          |
| setTemperaturePeriod |                                  period4 |
| getTemperaturePeriod |                                          |
| setOrientationPeriod |                                  period5 |
| getOrientationPeriod |                                          |
| setLinearAccelerationPeriod |                                  period6 |
| getLinearAccelerationPeriod |                                          |
| setGravityVectorPeriod |                                  period7 |
| getGravityVectorPeriod |                                          |
|  setQuaternionPeriod |                                  period8 |
|  getQuaternionPeriod |                                          |
|     setAllDataPeriod |                                  period9 |
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
|         acceleration |                         firedBy, x, y, z |
|        magneticField |                         firedBy, x, y, z |
|      angularVelocity |                         firedBy, x, y, z |
|          temperature |                     firedBy, temperature |
|   linearAcceleration |                         firedBy, x, y, z |
|        gravityVector |                         firedBy, x, y, z |
|          orientation |            firedBy, heading, roll, pitch |
|           quaternion |                      firedBy, w, x, y, z |
|              allData | firedBy, acceleration, magneticField, angularVelocity, eulerAngle, quaternion, linearAcceleration, gravityVector, temperature, calibrationStatus |


