FROM openjdk:21-jdk-slim AS build

WORKDIR /app

COPY . .

RUN ./mvnw clean package -DskipTests

RUN cp target/asteroidcatcher-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]

