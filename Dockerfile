#! /bin/bash

FROM openjdk:11-jdk-buster

RUN mkdir -p /usr/local/zeebe-script-worker/config

ADD target/zeebe-script-worker-0.8.1.jar /usr/local/zeebe-script-worker/zeebe-script-worker.jar

WORKDIR /usr/local/zeebe-script-worker

ENTRYPOINT ["java","-jar","/usr/local/zeebe-script-worker/zeebe-script-worker.jar"]