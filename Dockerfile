FROM maven:3.6.3-adoptopenjdk-11 as build

WORKDIR /app
COPY pom.xml .
RUN mvn -e -B dependency:resolve
COPY src ./src

RUN mvn verify sonar:sonar
RUN mvn package -DskipTests

FROM openjdk:11-slim-buster
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
