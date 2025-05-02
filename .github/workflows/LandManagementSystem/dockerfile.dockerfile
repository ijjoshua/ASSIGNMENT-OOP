FROM openjdk:17-jdk-slim
WORKDIR /app
COPY src/*.java .
RUN javac LandManagementSystem.java
CMD ["java", "LandManagementSystem"]