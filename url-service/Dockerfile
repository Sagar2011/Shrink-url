FROM maven:3.6-jdk-8 AS build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src src
RUN mvn -f pom.xml clean package -DskipTests=true


FROM openjdk:8
EXPOSE 9200
COPY /target/url-service.jar url.jar
ENTRYPOINT ["java","-jar","url.jar"]
