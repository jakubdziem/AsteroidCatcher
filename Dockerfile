FROM openjdk:21-jdk-slim

WORKDIR /app

COPY . .

RUN apt-get update && apt-get install -y maven

RUN mvn clean package -DskipTests

RUN cp target/AsteroidCatcher-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
