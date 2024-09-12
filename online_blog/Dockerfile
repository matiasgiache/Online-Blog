FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/online_blog-1.0.0.jar
COPY ${JAR_FILE} online_blog.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "online_blog.jar"]