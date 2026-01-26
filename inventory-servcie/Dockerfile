FROM eclipse-temurin:25-jdk-ubi10-minimal AS dependencies
RUN microdnf install -y maven && microdnf clean all
WORKDIR /build
COPY pom.xml .
RUN mvn -B -DskipTests dependency:resolve


FROM dependencies AS builder
COPY src ./src
RUN mvn -B clean package -DskipTests

FROM eclipse-temurin:25-jre-ubi10-minimal AS runtime
WORKDIR /app

COPY --from=builder /build/target/*.-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]