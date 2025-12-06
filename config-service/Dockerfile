# Build stage
FROM eclipse-temurin:17-jdk-jammy AS builder
WORKDIR /cloud-config
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline -B
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Runtime stage
FROM gcr.io/distroless/java17-debian11
WORKDIR /app
COPY --from=builder /cloud-config/target/*.jar cloud-config.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","cloud-config.jar"]
