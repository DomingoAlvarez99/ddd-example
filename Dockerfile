FROM maven:3.6.3-adoptopenjdk-11 as build

WORKDIR /app
COPY pom.xml .
COPY src ./src
COPY Makefile .

RUN apt update && apt install make

RUN make deps
RUN make build

FROM openjdk:11-slim-buster
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
