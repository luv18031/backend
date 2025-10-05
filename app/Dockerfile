# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the executable JAR file from your build folder to the container
# Make sure to replace 'your-app-name.jar' with the actual name of your JAR file
COPY target/your-app-name.jar app.jar

# Expose the port your application runs on (e.g., 8080 for Spring Boot)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]