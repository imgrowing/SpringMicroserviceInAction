#!/bin/bash

# spring init --list
# web,data-jpa,security,lombok,actuator,devtools

PROJECT_NAME=license
mkdir $PROJECT_NAME
cd $PROJECT_NAME

spring init --boot-version=2.3.12 --build=gradle --java-version=11 \
--group-id=my \
--artifactId=license \
--package-name=my.study.license \
--name=LicenseService \
--dependencies=web,lombok,actuator \
--packaging=jar \
--extract

rm .gitignore

