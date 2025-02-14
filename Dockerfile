# Use an official OpenJDK runtime as the base image
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/pvawnings-0.0.1-SNAPSHOT.jar pvawnings.jar

# Expose the application port
EXPOSE 8080

# Run the JAR file with Java
ENTRYPOINT ["java", "-jar", "pvawnings.jar"]