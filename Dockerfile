FROM openjdk:23-jdk-jammy
WORKDIR /app
COPY target/JEEexampel2025-1.0-SNAPSHOT.war app.war
EXPOSE 8080
CMD ["java", "-jar", "app.war"]