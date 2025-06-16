# Build stage
FROM gradle:8.4-jdk17 AS build
WORKDIR /app
COPY .. .
RUN gradle :order-service:bootJar

# Runtime stage
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/order-service/build/libs/order-service-*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
