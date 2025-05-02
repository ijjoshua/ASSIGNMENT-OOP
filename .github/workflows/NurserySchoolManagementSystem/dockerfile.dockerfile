FROM openjdk:17-jdk-slim
WORKDIR /app
COPY src/*.java .
RUN javac NurserySchoolManagementSystem.java
CMD ["java", "NurserySchoolManagementSystem"]