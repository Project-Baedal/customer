FROM openjdk:21-jdk-slim AS builder
WORKDIR /app
COPY . .
RUN ./gradlew clean build

FROM openjdk:21-jdk-slim AS runner
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar customer.jar
ENV TZ=Asia/Seoul
ENTRYPOINT ["java", "-jar", "customer.jar"]
