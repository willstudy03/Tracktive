# Use Maven image for building
FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

# Copy the entire multi-module project (important for resolving parent POM)
COPY ../ .

# Ensure Maven downloads dependencies before building
RUN mvn -pl api-gateway dependency:go-offline -B

# Build the product-service module
RUN mvn -pl api-gateway clean package -DskipTests

# Use OpenJDK to run the built application
FROM openjdk:21-jdk AS runner

WORKDIR /app

# Copy the built JAR file from the builder stage
COPY --from=builder /app/api-gateway/target/api-gateway-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 4004

ENTRYPOINT ["java", "-jar", "app.jar"]

