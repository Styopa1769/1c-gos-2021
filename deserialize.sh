#!/usr/bin/env bash
mvn clean install
mvn exec:java -Dexec.mainClass="Deserialize" -Dexec.args="$1 $2"