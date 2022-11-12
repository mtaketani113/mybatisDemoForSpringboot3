FROM gradle:7.5.1-jdk17-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
ARG GITHUB_ACTOR
ARG GITHUB_TOKEN
WORKDIR /home/gradle/src
RUN gradle clean build -x test -x spotbugsMain -x spotbugsTest --no-daemon 

FROM openjdk:17-jdk-slim

EXPOSE 8080

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar

ENTRYPOINT ["java","-jar","/app/spring-boot-application.jar"]