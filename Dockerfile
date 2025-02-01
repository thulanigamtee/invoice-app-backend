FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/invoice-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "/app/invoice-0.0.1-SNAPSHOT.jar" ]

# FROM openjdk:17-jdk-alpine

# COPY target/invoice-0.0.1-SNAPSHOT.jar app-1.0.0.jar

# ENTRYPOINT [ "java", "-jar", "app-1.0.0.jar" ]