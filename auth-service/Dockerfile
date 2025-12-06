#FROM gcr.io/distroless/java17-debian11
FROM eclipse-temurin:17-jdk-jammy AS builder
WORKDIR /auth-service

#Copy Maven Wrapper and POM
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

#Download dependencies
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline -B

# Copy source code and build JAR
COPY src ./src
RUN ./mvnw clean package -DskipTests

# --- Runtime stage ---
FROM gcr.io/distroless/java17-debian11
WORKDIR /auth-service

# Copy built JAR from builder
COPY --from=builder /auth-service/target/*.jar auth-service.jar

# Expose port your service will run on
EXPOSE 8081

# Run the JAR
ENTRYPOINT ["java","-jar","auth-service.jar"]