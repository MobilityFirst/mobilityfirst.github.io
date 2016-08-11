---
title: "Access Control Lists"
last_updated: July 21, 2016
sidebar: documentation_sidebar
toc: true
---

This page is under construction, please check back soon!

{% comment %}
## Introduction
ACLs provide a way to control who has access to what fields. For example a user may want their home automation system and other services they use to see their location on the GNS, but having that information publicly accessible is extremely undesirable. ACLs allow users to specify who can and can't read and write specific fields of their GUID using blacklists and whitelists.

## ACL defaults

The GNS specifies the following defaults for ACLs.

* All GUIDs (account and otherwise) are created with read access of their fields to everyone.

* All subguids (those associated with an account GUID) give read and write access for all fields to their account guid.

### Group GUIDs

* Group members inherit the access permissions of the group they belong to. Meaning if a group GUID has access to a field in a random GUID, members of that group will have the same access.

* Group members cannot, however, access the fields of the group GUID itself. Group GUIDS give other guids access to OTHER guids fields, not their own (the fields of the group GUID). The original design for group GUIDs fields didn't consider providing access to members. It was more for using the group as a shortcut for specifying access to other GUIDs fields for a bunch of fields. We're looking at changing this.

## Adding ACLs
ACLs can be added to a GUID using the `aclAdd` method of the `GNSClientCommands` class. The full method declaration is `aclAdd(AclAccessType accessType, GuidEntry targetGuid, String field, String accesserGuid)`.

* `AclAccessType accessType`: One of the four AclAccessTypes `READ_WHITELIST`, `WRITE_WHITELIST`, `READ_BLACKLIST`, `WRITE_BLACKLIST`.
* `GuidEntry targetGuid`: The GUID that the ACL will be added to.
* `String field`: The field that the ACL will be applied to.
* `String accesserGuid`: The GUID to add to the ACL.

Here's an example of how we would add an ACL to allow our friend to 

``` java
import edu.umass.cs.gnscommon.GNSCommandProtocol;
import edu.umass.cs.gnscommon.AclAccessType;
	...
	GNSClientCommands client = new GNSClientCommands();
	...

	//This ensures that the friendly GUID can read our location field
	client.aclAdd(AclAccessType.READ_WHITELIST, myGuidEntry, "location", friendlyGuidEntry.getGuid());
	//Block everyone else from reading the location field
	client.aclAdd(AclAccessType.READ_BLACKLIST, myGuidEntry, "location", GNSCommandProtocol.ALL_GUIDS);
	
	//Let the Guid associated with our phone make updates to the location field. The logical thing to do here is to have our phone as a sub-GUID and we wouldn't need to do this.
	client.aclAdd(AclAccessType.WRITE_WHITELIST, myGuidEntry, "location", myPhoneGuidEntry.getGuid());
```

## Removing ACLs

ACLs can be removed a GUID using the `aclRemove` method of the `GNSClientCommands` class. The full method declaration is `aclRemove(AclAccessType accessType, GuidEntry guid, String field, String accesserGuid)`. The parameters are the same as the `aclAdd` method, but the `accessorGuid` is removed from the ACL.

Continuing from the example above

``` java
	//Turns out our friendly GUID wasn't so friendly, so lets remove it
	client.aclRemove(AclAccessType.READ_WHITELIST, myGuidEntry, "location", friendlyGuidEntry.getGuid());
```
{% endcomment %}

{% comment %}
THIS INFORMATION MAY BE OBSOLETE MUST BE VERIFIED
IT WILL NOT BE RENDERED ON THE SITE

One other possible subtle gotcha is in the handling of ALLFIELDS accessin ACLs. If you put ALLFIELDS as well as a GUID, and then you remove ALLFIELDS the GUID will still be there. The handling of the actual list is not terribly smart in that way right now. 
{% endcomment %}
