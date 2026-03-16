FROM ubuntu:22.04

RUN apt update -y
RUN apt install openjdk-17-jdk -y

WORKDIR /app
COPY target/order-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE  8081

ENTRYPOINT [ "java","-jar","app.jar" ]






