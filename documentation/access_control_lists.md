---
title: "Access Control Lists"
sidebar: documentation_sidebar
toc: true
---

## Introduction
ACLs provide a way to control who has access specific fields of a GUID. For example a user may want their home automation system and other services they use to see their location on the GNS, but having that information publicly accessible is extremely undesirable. ACLs allow users to specify who can and can't read and write specific fields of their GUID using blacklists and whitelists.

## ACL example
Start a GNS server. See the Getting Started (link) page for details.

Start the `ClientACLExample` class:

```
bin/gpClient.sh edu.umass.cs.gnsclient.examples.ClientACLExample
```

This class creates two Guids `guid` and `phoneGuid` to demonstrate ACL functionality. As the program executes it prints out what method is called along with the parameters passed. To improve readability some abbreviations are made, for example .getGuid() is not included in the printed statements and the `AclAccessType` prefix is dropped from parameters of the from `AclAccessType.WRITE_WHITELIST`.

The source code is available in the `src/edu/umass/cs/gnsclient/examples/` folder of the source and [on Github](https://github.com/MobilityFirst/GNS/blob/master/src/edu/umass/cs/gnsclient/examples/ClientACLExample.java).

## ACL Operations
There are three ACL commands that can be issued:

* `aclAdd(AclAccessType accessType, GuidEntry targetGUID, String field, String accesserGUID)` ([doc](https://mobilityfirst.github.io/GNS/doc/edu/umass/cs/gnsclient/client/GNSCommand.html#aclAdd-edu.umass.cs.gnscommon.AclAccessType-edu.umass.cs.gnsclient.client.util.GuidEntry-java.lang.String-java.lang.String-))

* `aclGet(AclAccessType accessType, GuidEntry targetGUID, String field, String querierGUID)` ([doc](https://mobilityfirst.github.io/GNS/doc/edu/umass/cs/gnsclient/client/GNSCommand.html#aclGet-edu.umass.cs.gnscommon.AclAccessType-edu.umass.cs.gnsclient.client.util.GuidEntry-java.lang.String-java.lang.String-))

* `aclRemove(AclAccessType accessType, GuidEntry targetGUID, String field, String accesserGUID)` ([doc](https://mobilityfirst.github.io/GNS/doc/edu/umass/cs/gnsclient/client/GNSCommand.html#aclRemove-edu.umass.cs.gnscommon.AclAccessType-edu.umass.cs.gnsclient.client.util.GuidEntry-java.lang.String-java.lang.String-))

The AclAccessType ([doc](https://mobilityfirst.github.io/GNS/doc/edu/umass/cs/gnscommon/AclAccessType.html)) specifies the read/write whitelist/blacklist that is being modified or read.

## ACL defaults

The GNS specifies the following defaults for ACLs.

* All GUIDs (account and otherwise) are created with read access of their fields to everyone.

* All subguids (those associated with an account GUID) give read and write access for all fields to their account guid.

### Group GUIDs

* Group members inherit the access permissions of the group they belong to. Meaning if a group GUID has access to a field in a random GUID, members of that group will have the same access.

* Group members cannot, however, access the fields of the group GUID itself. Group GUIDS give other guids access to OTHER guids fields, not their own (the fields of the group GUID).
