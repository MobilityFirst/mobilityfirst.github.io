---
title: "Contribute"
sidebar: documentation_sidebar
toc: false
---

The easiest way to contribute to the GNS is to open issues or make a pull request on [Github](https://github.com/MobilityFirst/GNS). You can also contribute to the this website on [Github](https://github.com/MobilityFirst/mobilityfirst.github.io).

## Build from source
Prerequisites: `JRE1.8+`, `bash`, `ant`, `mongodb (optional)`

Note that you will need `ant` in addition to the dependencies required for the binary release.

1. [Clone](https://help.github.com/articles/cloning-a-repository/) from the GNS Github page or your own fork. You can also download the the [source releases](https://github.com/MobilityFirst/GNS/releases).  
2. Run `ant` to build the JAR files.

## Setting up Eclipse
These steps will guide you in setting up the GNS source code in Eclipse so that you can contribute or make modifications to the GNS source code.

1. [Clone](https://help.github.com/articles/cloning-a-repository/) from the GNS Github page or your own fork. You can also download the the [source releases](https://github.com/MobilityFirst/GNS/releases).
2. Import the GNS folder as an existing project from File > Import > General > Existing Projects into Workspace
  * Select the "select root directory" option and browse to the GNS folder. Ensure the GNS project is selected and click Finish.
4. Open the ant window from Window > Show View > Ant
5. Drag and drop the build.xml file from the Project Explorer to the Ant window. Alternatively click the Add Buildfiles button in the Ant Window and select the build.xml file that way.
6. Right click the build file in the Ant Window > Run As > Ant Build. This will build the JAR files needed to operate the GNS. See the Documentation pages for instructions on how to start a server and run a specific class.
  * Remember to run ant when you make changes.
	* Depending on your needs you may prefer to run different ant targets e.g. client_jarbuild when you only modify client side code.
