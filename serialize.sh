#!/usr/bin/env bash
mvn clean install
mvn exec:java -Dexec.mainClass="Serialize" -Dexec.args="$1 $2"