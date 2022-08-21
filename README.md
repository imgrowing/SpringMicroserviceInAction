# SpringMicroserviceInAction
Spring Microservice In Action 2nd 실습

- Java 11
- SpringBoot 2.2.3


# 실행 방법

## docker build
변경이 발생한 프로젝트에서 다음 명령을 실행하여 docker image를 생성한다.
```
{project} $ ./gradlew clean jibDockerBuild
```

## docker-compose 실행
기동하기
```
{project root} $ docker-compose -f ./license/docker/docker-compose.yml up
```
중지하기 (or `Ctrl+C`)
```
{project root} $ docker-compose -f ./license/docker/docker-compose.yml down
```


# 접속방법
* `license/docker/docker-compose.yml` 파일의 포트를 참고한다.
