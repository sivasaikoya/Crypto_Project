FROM maven:3.9.4-eclipse-temurin-21-alpine as build
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar", "/app.jar"]
EXPOSE 8080