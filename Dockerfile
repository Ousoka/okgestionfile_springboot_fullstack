# Build stage
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Runtime stage
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/target/GestionFile-0.0.1-SNAPSHOT.war GestionFile.war
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "GestionFile.war"]


# FROM maven:3.8.5-openjdk-17 AS build
# COPY . .
# RUN mvn clean package -DskipTests

# FROM openjdk:17.0.1-jdk-slim
# COPY --from=build /target/GestionFile-0.0.1-SNAPSHOT.war GestionFile.war
# EXPOSE 8080
# ENTRYPOINT ["java","-war","GestionFile.war"]

# FROM openjdk:17-jdk-alpine
# WORKDIR /GestionFile
# COPY /target/GestionFile-0.0.1-SNAPSHOT.jar GestionFile.jar
# EXPOSE 8080
# CMD ["java","-jar","GestionFile.jar"]
