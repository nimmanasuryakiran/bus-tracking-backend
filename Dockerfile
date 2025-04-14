# ----------------------------
# Stage 1: Build with Maven
# ----------------------------
FROM maven:3.9.2-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy Maven files and grant execution permission
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw

# Download dependencies (faster builds)
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src ./src

# Build the project and skip tests
RUN ./mvnw clean package -DskipTests

# ----------------------------
# Stage 2: Run with minimal image
# ----------------------------
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy the built jar from previous stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Command to run the app
CMD ["java", "-jar", "app.jar"]
