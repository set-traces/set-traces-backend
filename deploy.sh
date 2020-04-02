#!/bin/bash

./gradlew build
docker build -t backend:latest . --no-cache
docker tag settraces/backend:latest backend:latest
docker push settraces/backend:latest