FROM gradle:8.4-jdk17 AS build

ARG USERNAME
ARG TOKEN

ENV GPR_USER=$USERNAME
ENV GPR_KEY=$TOKEN

WORKDIR /app
COPY . .

RUN ./gradlew bootJar -Pgpr.user=$GPR_USER -Pgpr.key=$GPR_KEY --build-cache --parallel

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
