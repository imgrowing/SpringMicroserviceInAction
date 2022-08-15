#!/bin/bash

# spring init --list
# web,data-jpa,security,lombok,actuator,devtools

PROJECT_NAME=organization
mkdir $PROJECT_NAME
cd $PROJECT_NAME

spring init --boot-version=2.3.12 --build=gradle --java-version=11 \
--group-id=my \
--artifactId=organization \
--package-name=my.study.license \
--name=OrganizationService \
--dependencies=web,lombok,actuator,cloud-config-client,data-jpa,postgresql \
--packaging=jar \
--extract

rm .gitignore

