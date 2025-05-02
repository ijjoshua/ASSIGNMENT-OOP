FROM openjdk:17-jdk-slim
WORKDIR /app
COPY src/*.java .
RUN javac MissionManagementSystem.java
CMD ["java", "MissionManagementSystem"]