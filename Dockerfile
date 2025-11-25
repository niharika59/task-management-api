# Use a lightweight JDK image
FROM eclipse-temurin:25-jdk

# Set working directory
WORKDIR /app

# Argument for the jar path
ARG JAR_FILE=target/TaskManagement-0.0.1-SNAPSHOT.jar

# Copy jar into image
COPY ${JAR_FILE} app.jar

# Expose the port Spring Boot uses
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]