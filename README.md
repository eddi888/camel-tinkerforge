[![Build Status](https://travis-ci.org/eddi888/camel-tinkerforge.svg?branch=master)](https://travis-ci.org/eddi888/camel-tinkerforge)
# Tinkerforge Component (proof of concept)
------------------------------------------

The **Tinkerforge** Apache Camel Component for a simple access for use the Tinkerforge Daemon, Bricks and Bricklets.
```xml
<dependency>
    <groupId>org.atomspace.camel</groupId>
    <artifactId>camel-tinkerforge</artifactId>
    <version>x.x.x</version>
</dependency>
```

## URI format
----------------------------------------------

```
tinkerforge:[host[:port]/]deviceType?[uid=uid&options...]
```

## Options
------------------------------------------------


Name           | Default Value | Description
-------------- | ------------- | -------------
host           | localhost     | Hostname / IP-Address
port           | 4223          | Port
secret         |               | Secret String for authenticate
autoReconnect  | true          | reconnection on broken connection
timeout        | 2500          | Timeout
deviceType     |               | Temperatur, Line, IO-4, Color, MotionDetector, ...
uid            |               | UID of the Bricklet
callback       |               | add Listener to ConsumerEndpoint for receive values, if callback is null all Listeners will be registrate


## Producer Endpoints
------------------------------------------------
Function            | Options         | Body Type
------------------- | --------------- | -------------
**DualRelay**       |                 | 
state               | {relay1,relay2} | ?
state               |                 | ?
monoflop            | ???             | ?
get_monoflop        |                 | ?
set_selected_state  |                 | ?
get_identity        |                 | ?




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

