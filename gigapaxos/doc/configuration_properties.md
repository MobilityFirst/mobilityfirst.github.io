---
title: "Configuration properties"
last_updated: July 14, 2016
sidebar: gigapaxos_sidebar
toc: true
---

## Overview
gigapaxos needs a configuration properties file with the default name `gigapaxos.properties` in the current directory or at a non-default location specified as a system property using `-DgigapaxosConfig=`. 

The gigapaxos properties file minimally consists of a set of active replicas and reconfigurators and their socket addresses. Here is an example:

```
#CLIENT_SSL_MODE=SERVER_AUTH
DISABLE_RECONFIGURATION=true

APPLICATION=edu.umass.cs.reconfiguration.testing.NoopAppTesting
#DEMAND_PROFILE_TYPE=edu.umass.cs.reconfiguration.reconfigurationutils.DemandProfile

active.100=127.0.0.1:2000
#active.101=127.0.0.1:2001
#active.102=127.0.0.1:2002

reconfigurator.700=127.0.0.1:3000
#reconfigurator.700=127.0.0.1:3001
#reconfigurator.700=127.0.0.1:3002
```

The uncommented (lines not starting with `#`) lines starting with `active` or `reconfigurator` specify the names and socket addresses of the servers. The application and reconfiguration policy classes are respectively specified using the `APPLICATION` and `DEMAND_PROFILE_TYPE` properties.

## Constraints

- **Port numbers**: The socket addresses for the servers must satisfy some constraints. If a socket address `IP:port` is used by some server, the addresses `IP:port+CLIENT_PORT_OFFSET` and `IP:port+CLIENT_PORT_SSL_OFFSET` can not be used by any other server. This is because the latter two ports are respectively used by the server to service client requests without and with SSL respectively. The default values of `CLIENT_PORT_OFFSET` and `CLIENT_PORT_SSL_OFFSET` are 100 and 200 respectively and can be configured to other values using the same properties file.

- **Modification**: Some properties can not be changed after bootstrap for safety reasons. These include `APPLICATION`, `GIGAPAXOS_DATA_DIR`, the list of active and reconfigurator servers and their socket addresses, `DISABLE_CHECKPOINTING`, `DISABLE_LOGGING`, etc. Attempting to change these properties by manually editing the properties file will result in errors.

- **Data directory**: The best practice is to pick a unique data directory (`GIGAPAXOS_DATA_DIR`) for each ensemble of servers described in a properties file. The safety-critical rule is that a server with the same name can not be part of two different server ensembles sharing the same data directory. If you do want to reuse the same data directory (the current directory by default) across different ensembles, it is best to ensure that all server names across all properties files are unique.

- **Socket addresses**: All servers must be able to communicate with all other servers under graceful conditions. Thus, it is not possible to have some servers running on loopback and others on non-loopback addresses. The servers must allow inbound TCP communication on any non-privileged port (ports greater than 1024), not just the three ports implicitly specified in the properties file; this is because there are internal server functions that use other system selected ports.
