# spring init --list
# web,data-jpa,security,lombok,actuator,devtools

mkdir config
cd config

spring init --boot-version=2.3.12 --build=gradle --java-version=11 \
--group-id=my \
--artifactId=configserver \
--package-name=my.study.configserver \
--name=ConfigurationServer \
--dependencies=cloud-config-server,actuator \
--packaging=jar \
--extract

rm .gitignore
cd ..

