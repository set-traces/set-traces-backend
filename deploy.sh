#!/bin/bash

./gradlew build
docker build -t backend:latest . --no-cache
docker tag backend:latest settraces/backend:latest
docker push settraces/backend:latest

ssh settraces_master "./backendDeploy.sh"