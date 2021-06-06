FROM maven:3.6.3-ibmjava-8-alpine as builder

WORKDIR /app
COPY pom.xml .
RUN mvn -e -B dependency:resolve
COPY src ./src
RUN mvn package -DskipTests

FROM tomcat:9.0.10-jre8-alpine

COPY --from=builder /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

RUN rm -rf /usr/local/tomcat/webapps/ROOT
CMD ["catalina.sh", "run"]
