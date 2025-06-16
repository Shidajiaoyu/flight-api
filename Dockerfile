FROM eclipse-temurin:17-jre
 
# FROM openjdk:17-jdk-slim
  # 阿里云镜像
# FROM registry.cn-hangzhou.aliyuncs.com/library/openjdk:17-jdk-slim
# FROM mirror.ccs.tencentyun.com/library/openjdk:17-jdk-slim

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "app.jar" ]
