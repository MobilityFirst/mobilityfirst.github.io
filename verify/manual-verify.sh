#!/bin/bash
# A script that does a simple check for exceptions in the client

echo "Checking instructions with no properties file specified"

mkdir -p "default_config"
bin/gpServer.sh start all > default_config/server.txt
bin/gpClient.sh edu.umass.cs.gnsclient.examples.ClientExample > default_config/client.txt
bin/gpServer.sh stop all >> default_config/server.txt

if grep -q --ignore-case "exception" default_config/client.txt
then
    echo "Stopping, clientside exception found. See default_config/client.txt"
		exit
else
    echo "No exception found in client"
fi

echo "Checking instructions with single node properties file specified"

mkdir -p "single_config"
bin/gpServer.sh -DgigapaxosConfig=conf/gnsserver.1local.properties restart all > single_config/server.txt
bin/gpClient.sh -DgigapaxosConfig=conf/gnsclient.1local.properties edu.umass.cs.gnsclient.examples.ClientExample > single_config/client.txt
bin/gpServer.sh -DgigapaxosConfig=conf/gnsserver.1local.properties stop all >> single_config/server.txt

if grep -q --ignore-case "exception" single_config/client.txt
then
    echo "Stopping, clientside exception found. See single_config/client.txt"
		exit
else
    echo "No exception found in client"
fi

echo "Checking instructions with multi node properties file specified"

mkdir -p "multi_config"
bin/gpServer.sh -DgigapaxosConfig=conf/gnsserver.3local.properties restart all > multi_config/server.txt
bin/gpClient.sh -DgigapaxosConfig=conf/gnsclient.3local.properties edu.umass.cs.gnsclient.examples.ClientExample > multi_config/client.txt
bin/gpServer.sh -DgigapaxosConfig=conf/gnsserver.3local.properties stop all >> multi_config/server.txt

if grep -q --ignore-case "exception" multi_config/client.txt
then
    echo "Stopping, clientside exception found. See multi_config/client.txt"
		exit
else
    echo "No exception found in client"
fi
