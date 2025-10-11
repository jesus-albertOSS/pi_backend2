# Render.com

# Use official OpenJDK image as the base image
FROM eclipse-temurin:21-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the Maven wrapper files
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

# Set execute permissions for Maven wrapper
RUN chmod +x mvnw

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy the source code
COPY src ./src

# Build the application
RUN ./mvnw clean package -DskipTests

# Expose the port the app runs on
EXPOSE 10000

# Run the application
CMD ["java", "-jar", "target/pib2-0.0.1-SNAPSHOT.jar"]