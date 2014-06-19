#JavaScript implementation for Vert.x js-lang

It is ported from the nodejs lib. The usages are very similiar.

##Features
###Protocols:
  - Binary protocol
  - Compact protocol
  - JSON protocol
  - Multiplexed

###Transports:
  - Vert.x EventBus (supports only on Vert.x platform)
  - TCP/TLS (TODO)
  - HTTP (TODO)
  - WebSocket (TODO)

##Compiler
Specify _--gen js:vertx_ to generate your thrift files into javascript source for using with this lib.

##How to use with Vert.x
In your Vert.x module project's mod.json file, put this line:
```
"include": "org.apache.thrift~libthrift-vertx-js~1.0-SNAPSHOT"
```
