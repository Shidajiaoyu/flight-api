FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/*.jar ./flight-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "flight-api.jar"]
