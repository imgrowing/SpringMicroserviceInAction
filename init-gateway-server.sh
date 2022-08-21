#!/bin/bash

# spring init --list
# web,data-jpa,security,lombok,actuator,devtools

PROJECT_NAME=gateway-server
mkdir $PROJECT_NAME
cd $PROJECT_NAME

spring init --boot-version=2.3.12 --build=gradle --java-version=11 \
--group-id=my \
--artifactId=gatewayserver \
--package-name=my.study.gateway \
--name=GatewayService \
--dependencies=cloud-gateway,cloud-eureka,cloud-config-client,actuator,lombok \
--packaging=jar \
--extract

rm .gitignore

