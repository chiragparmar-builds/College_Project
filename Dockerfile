FROM eclipse-temurin:17-jdk-jammy

# Set working directory
WORKDIR /app

# Copy the jar file
COPY target/contact_manager-0.0.1-SNAPSHOT.jar app.jar

# Expose application port
EXPOSE 8080

# Run Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
