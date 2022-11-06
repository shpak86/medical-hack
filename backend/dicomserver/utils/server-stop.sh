#!/usr/bin/env bash

pid=$(ps -ef | grep 'java -jar dicomserver-0.0.1-SNAPSHOT.jar' | grep -v grep | awk '{ print $1 }')
kill pid

echo "Server process [$pid] stopped"
