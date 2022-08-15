#!/bin/bash

# spring init --list
# web,data-jpa,security,lombok,actuator,devtools

PROJECT_NAME=eurekaserver
mkdir $PROJECT_NAME
cd $PROJECT_NAME

spring init --boot-version=2.3.12 --build=gradle --java-version=11 \
--group-id=my \
--artifactId=$PROJECT_NAME \
--package-name=my.study.$PROJECT_NAME \
--name=EurekaServer \
--dependencies=cloud-eureka-server,cloud-config-client,actuator \
--packaging=jar \
--extract

rm .gitignore
cd ..
