
# Tinkerforge Component
------------------------------------------

The **tinkerforge** component for a simple access to the Tinkerforge Daemon and all Bricklets.
```xml
<dependency>
    <groupId>org.apache.camel</groupId>
    <artifactId>camel-tinkerforge</artifactId>
    <version>x.x.x</version>
</dependency>
```

## URI format
----------------------------------------------

```
tinkerforge:[[host][:port]/]brickletType?[options]
```

## Options
------------------------------------------------


Name           | Default Value | Description
-------------- | ------------- | -------------
host           | localhost     | Hostname / IP-Address
port           | 4223          | Port
authenticate   | null          | Secret String
autoReconnect  | true          | reconnection on broken connection
timeout        | null          | Timeout
enumerate      | false         | Enumerate list over all devices
brickletType   | null          | Temperatur, Line, IO-4, Color, MotionDetector, ...
uid            | null          | UID of the Bricklet
callbackPeriod | 1000          | ...
Result
----------------------------------------------------
Todo...


## Message Headers
---------------------------------------------------

Header             | Description
------------------ | -------------
CamelBrickletUid   | TODO         
CamelBrickletType  | TODO         
CamelBrickletValue | TODO         

# Samples endpoints
---------------------------------------------------
Typically connection where the daemon running on same system 
```
<route>
  <from uri="timer://foo?period=10000"/>
  <to uri="tinkerforge:temperture?uid=bar" />
  <to uri="log:temperature?level=INFO&showHeaders=true"/>
</route>

```

Use remote temperatur sensor inside an Request Reply Route
```
<route>
  <from uri="jetty:0.0.0.0:8044/bathroom" />
  <to uri="tinkerforge:192.168.4.17:1433/temperature?uid=ABC" />
</route>

```

Temperatur consumer from remote masterbrick 
```
<route>
  <from uri="tinkerforge:192.168.4.17:1433/temperature?uid=ABC&callbackPeriod=5000" />
  <to uri="bean:save"/>
</route>
```

<dl>
   <br/>
   <br/>
   <br/>
   <br/>
   <br/>
   <br/>
   <br/>
</dl>

