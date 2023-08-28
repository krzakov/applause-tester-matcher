#!/bin/sh

echo ">> gradle wrapper clean build"
./gradlew clean build

echo ">> docker compose multicontainer app"
docker-compose -p mosek_applause up --build --force-recreate -d --remove-orphans
