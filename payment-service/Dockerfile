# Use Maven image for building
FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

# Copy the entire multi-module project (important for resolving parent POM)
COPY ../ .

# Ensure Maven downloads dependencies before building
RUN mvn -pl payment-service dependency:go-offline -B

# Build the payment-service module
RUN mvn -pl payment-service clean package -DskipTests

# Use OpenJDK to run the built application
FROM openjdk:21-jdk AS runner

WORKDIR /app

# Copy the built JAR file from the builder stage
COPY --from=builder /app/payment-service/target/payment-service-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "app.jar"]