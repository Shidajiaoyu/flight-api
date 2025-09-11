#选择base image
#FROM eclipse-temurin:21-jre
FROM registry.redhat.io/ubi9/openjdk-21

# 设置工作目录
workdir /app

#其他写法：target/*.jar
COPY target/flight-api-0.0.1-SNAPSHOT.jar flight-api.jar

#baolou端口号
EXPOSE 8080

# 告诉docker如何运行jar包
ENTRYPOINT ["java", "-jar", "flight-api.jar"]