# Step 1: Use an official Maven image to build the app
FROM maven:3.8.6-eclipse-temurin-17 AS build

# Step 2: Set working directory
WORKDIR /app

# Step 3: Copy all files needed for Maven build and install dependencies
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# ✅ FIX: Add execute permission to mvnw
RUN chmod +x mvnw

RUN ./mvnw dependency:go-offline

# Step 4: Copy the entire source code to the container
COPY src ./src

# Step 5: Package the application
RUN ./mvnw package -DskipTests

# Step 6: Use a lightweight JDK base image to run the app
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy the JAR file from the build image
COPY --from=build /app/target/*.jar app.jar

# Step 7: Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
