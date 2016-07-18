THIS INFORMATION MAY BE OBSOLETE MUST BE VERIFIED

Group members inherit the access permissions of the group they belong to. Meaning if a group GUID has access to a field in a random GUID, members of that group will have the same access.

Group members cannot, however, access the fields of the group GUID itself. Group GUIDS give other guids access to OTHER guids fields, not their own (the fields of the group GUID). The original design for group GUIDs fields didn't consider providing access to members. It was more for using the group as a shortcut for specifying access to other GUIDs fields for a bunch of fields. We're looking at changing this.

All GUIDs (account and otherwise) are created with read access of their fields to everyone.

All subguids (those associated with an account GUID) give read and write access for all fields to their account guid.

One other possible subtle gotcha is in the handling of ALLFIELDS accessin ACLs. If you put ALLFIELDS as well as a GUID, and then you remove ALLFIELDS the GUID will still be there. The handling of the actual list is not terribly smart in that way right now. 
