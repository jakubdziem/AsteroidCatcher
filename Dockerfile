FROM openjdk:21-jdk-slim AS build

WORKDIR /app

COPY target/asteroidcatcher-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

