---
title: "Managed DNS"
last_updated: July 8, 2016
sidebar: documentation_sidebar
toc: false
---

The GNS can also be run as a managed DNS service. When specified the GNS will start with the DNS Translator service running. The DNS Translator will forward DNS queries that it is unable to answer. DNS entries in the GNS use the standard DNS formatted names (e.g. subsubdomain.subdomain.domain.com.).

## Start a server with a DNS Translator

The DNS Translator is started on nodes specified in the `DNS_SERVER_NODES` of the gigapaxos properties file. When `DNS_SERVER_NODES=all` every node specified in the properties file will start the DNS Translator.

```
sudo bin/gpServer.sh -DgigapaxosConfig=conf/gnsserver.1local.withDNS.properties -Djava.util.logging.config.file=conf/logging.gns.properties restart all
```

## Test existing DNS records

Lookup an existing record with

```
nslookup cs.umass.edu 127.0.0.1
```

You should see a result like this:
```
Server:		127.0.0.1
Address:	127.0.0.1#53

Non-authoritative answer:
Name:	google.com
Address: 74.125.29.101
Name:	google.com
Address: 74.125.29.138
...
```

## Example class

Run this client class ([source](https://github.com/MobilityFirst/GNS/blob/master/src/edu/umass/cs/gnsclient/examples/CreateDnsRecord.java)) to create a test record

```
bin/gpClient.sh -DgigapaxosConfig=conf/gnsclient.1local.properties -Djava.util.logging.config.file=conf/logging.gns.properties edu.umass.cs.gnsclient.examples.CreateDnsRecord test.site.org 10.0.0.87
```

Output:
```
...

Client connected to GNS
Value of A in test.site.org. is 10.0.0.87
```

{% comment %}
Leaving this here for reference purposes. Code should be updated to use GNSCommand/GNSClient. -mdews
## Code to create records with the GNS Client

Alternatively here is some client code that does the same thing as the above class invocation:

```java
import edu.umass.cs.gnsclient.client.GNSClientCommands;
import edu.umass.cs.gnsclient.client.util.GuidEntry;
import edu.umass.cs.gnsclient.client.util.GuidUtils;

GNSClientCommands client = new GNSClientCommands();
GuidEntry accountGuid = GuidUtils.lookupOrCreateAccountGuid(client, "dnstest@cs.umass.edu", "password", true);
GuidEntry guid = GuidUtils.lookupOrCreateGuid(client, accountGuid, "test.site.org."); // note the period at the end
client.fieldUpdate(guid, "A", "10.0.0.87");
```
{% endcomment %}

## Test the record

Lookup the record with

```
nslookup test.site.org 127.0.0.1
```

You should see a result like this:

```
Server:		127.0.0.1
Address:	127.0.0.1#53

Name:	test.site.org
Address: 10.0.0.87
```
