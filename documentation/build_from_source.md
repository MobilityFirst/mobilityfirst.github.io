---
title: "Build from source"
last_updated: July 8, 2016
sidebar: documentation_sidebar
toc: true
---

Prerequisites: `JDK1.8+`, `ant1.9+`

Download the latest stable source package from the [GNS releases page](https://github.com/MobilityFirst/GNS/releases) or download the latest (possible unstable) [github commit](https://github.com/MobilityFirst/GNS) and compile as follows (replacing '\*' in the second line with the version number of the download):

```
git clone https://github.com/MobilityFirst/GNS
cd GNS-*   
ant
```

A successful build should print "BUILD SUCCESSFUL" at the end and place the compiled jars in the `jars/` sub-directory.

At this point, you can proceed as-is with the instructions in the [Getting Started]({{ site.baseurl }}/documentation/) or other pages.
