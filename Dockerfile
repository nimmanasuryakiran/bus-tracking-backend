# Step 1: Use official Java 17 image as the base
FROM openjdk:17-jdk-slim

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy Maven wrapper files and pom.xml to install dependencies
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

# Step 4: Copy the entire source code to the container
COPY src ./src

# Step 5: Build the project using Maven
RUN ./mvnw clean package -DskipTests

# Step 6: Expose port 8080 (Spring Boot default)
EXPOSE 8080

# Step 7: Run the Spring Boot JAR
CMD ["java", "-jar", "target/bus-tracking-0.0.1-SNAPSHOT.jar"]
