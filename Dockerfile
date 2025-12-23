###############################
# Dockerfile for Spring Boot WAR
###############################

# Use a minimal OpenJDK runtime as base image
FROM eclipse-temurin:17-jdk-alpine

# Optional: create a non-root user to run the application
# RUN addgroup --system spring && adduser --system --ingroup spring spring

# Set working directory
WORKDIR /app

# Copy the WAR file built by Gradle
# It should be located at build/libs/Hello-1.0.war
ARG WAR_FILE=build/libs/Hello-1.0.war
COPY ${WAR_FILE} app.war

# Expose the application's port (default: 8080)
EXPOSE 8080

# Use a non-root user (uncomment if user is created)
# USER spring:spring

# Run the WAR as an executable JAR
ENTRYPOINT ["java", "-jar", "app.war"]
