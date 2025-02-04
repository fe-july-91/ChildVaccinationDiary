# Builder stage
FROM maven:3-openjdk-17 AS builder
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=builder /target/ChildVaccinationDiary-0.0.1-SNAPSHOT.jar ChildVaccinationDiary.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "ChildVaccinationDiary.jar"]
