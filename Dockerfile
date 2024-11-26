# Use the official OpenJDK image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
COPY target/backend-0.0.1-SNAPSHOT.jar /app/backend.jar

# Expose the port Spring Boot runs on
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "backend.jar"]