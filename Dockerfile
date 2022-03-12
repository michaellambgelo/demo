FROM openjdk:8u212-jdk-alpine3.9
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]