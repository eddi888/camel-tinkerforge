##GPS


###Headers/Options (inside Exchange the Headers override the Options)


| Property             | Value-Type                              |
|----------------------|-----------------------------------------|
|          restartType |      Short |
|               period |       Long |
|              period2 |       Long |
|              period3 |       Long |
|              period4 |       Long |
|              period5 |       Long |



###Functions

| Name                 | Required Parameters                      |
|----------------------|------------------------------------------|
|       getCoordinates |                                          |
|            getStatus |                                          |
|          getAltitude |                                          |
|            getMotion |                                          |
|          getDateTime |                                          |
|              restart |                              restartType |
| setCoordinatesCallbackPeriod |                                   period |
| getCoordinatesCallbackPeriod |                                          |
| setStatusCallbackPeriod |                                  period2 |
| getStatusCallbackPeriod |                                          |
| setAltitudeCallbackPeriod |                                  period3 |
| getAltitudeCallbackPeriod |                                          |
| setMotionCallbackPeriod |                                  period4 |
| getMotionCallbackPeriod |                                          |
| setDateTimeCallbackPeriod |                                  period5 |
| getDateTimeCallbackPeriod |                                          |
|          getIdentity |                                          |




###Callbacks

| Name                 | Headers                                  |
|----------------------|------------------------------------------|
|          coordinates | firedBy, latitude, ns, longitude, ew, pdop, hdop, vdop, epe |
|               status | firedBy, fix, satellitesView, satellitesUsed |
|             altitude |     firedBy, altitude, geoidalSeparation |
|               motion |                   firedBy, course, speed |
|             dateTime |                      firedBy, date, time |


