FROM openjdk:17-jdk-slim

COPY target/benchmark.jar /app/benchmark.jar

WORKDIR /app

ENTRYPOINT ["java", "-jar", "benchmark.jar"]
