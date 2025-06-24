# Build stage
FROM gradle:8.4-jdk17 AS build

WORKDIR /app

# 루트 프로젝트 설정 파일과 gradle 설정 복사
COPY settings.gradle build.gradle gradle.properties gradle/ ./

# 멀티 모듈 프로젝트 전체 소스 복사
COPY pos-common ./pos-common
COPY pos-order-service ./pos-order-service
COPY pos-inventory-service ./pos-inventory-service
COPY pos-api-gateway ./pos-api-gateway

# pos-order-service 경로로 이동 후 빌드
WORKDIR /app/pos-order-service
RUN gradle bootJar --no-daemon

# Runtime stage
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /app/pos-order-service/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
