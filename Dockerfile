FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY ./target/user-service-0.1.0.jar user-service.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/user-service.jar"]
