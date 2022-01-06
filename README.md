_**The repository is under develop**_

# Hexagonal Architecture and DDD in Java
Example of a Java application using the * Ports and Adapters * Architecture ([Hexagonal Architecture](https://es.wikipedia.org/wiki/Arquitectura_hexagonal_(software))) with Domain Driven Design ([DDD](https://es.wikipedia.org/wiki/Dise%C3%B1o_guiado_por_el_dominio)) to keep the code as simple as possible.

## 0. Table of contents
- [Hexagonal Architecture and DDD in Java](#hexagonal-architecture-and-ddd-in-java)
  - [0. Table of contents](#0-table-of-contents)
  - [1. Libraries and examples of implementation](#1-libraries-and-examples-of-implementation)
  - [2. Use cases, patterns and examples of implementation](#2-use-cases-patterns-and-examples-of-implementation)
  - [3. Environment setup](#3-environment-setup)
    - [Install the needed tools](#install-the-needed-tools)
    - [Prepare the application environment](#prepare-the-application-environment)
    - [Run the tests](#run-the-tests)
    - [Start the app](#start-the-app)
  - [4. Entrypoint](#4-entrypoint)
    - [Endpoints](#endpoints)
    - [Cli](#cli)
  - [5. Logs](#5-logs)
  - [6. Code gen](#6-code-gen)
  - [7. Deploy](#7-deploy)

## 1. Libraries and examples of implementation
| Feature | Library | Example of implementation |
| ------------------------- | ----------------------------------------------------------- | ------------------------- |
| Build tool | [Maven](https://maven.apache.org/) | [Dependencies, configuration and build](pom.xml) |
| Style formatting | [EditorConf](https://www.jetbrains.com/help/idea/editorconfig.html) | [Rules](src/.editorconf)   |
| Extract metadata at runtime | [Reflections](https://github.com/ronmamo/reflections) | [DomainEventSuscribersInfo](src/main/java/org/dalvarez/ddd_example/shared/infrastructure/bus/DomainEventSubscribersInformation) |
| HTTP server	| [Spring Boot Starter Web](https://spring.io/guides/gs/rest-service/) | [Article POST controller](src/main/java/org/dalvarez/ddd_example/article/infrastructure/rest_api/controller/post/ArticlePostController.java) |
| Database integration | [Spring Data](https://spring.io/projects/spring-data) + [JPA](https://www.objectdb.com/api/java/jpa) + [Hibernate](https://hibernate.org/) | [Article repository](src/main/java/org/dalvarez/ddd_example/article/infrastructure/persistence/hibernate/repository/HibernateArticleRepository.java) |
| Domain events publishing & consuming | [Project Reactor](https://projectreactor.io/) | [Publisher and consumer integration](src/main/java/org/dalvarez/ddd_example/shared/infrastructure/bus/reactor/ReactorEventBus.java) |
| Document storer | [MinIO](https://min.io/) | *TO DO* |
| Infrastructure management | [Docker](https://www.docker.com/) | [Docker compose](docker-compose.yml) |
| Logging | [Logback](https://logback.qos.ch/) + [Logstash encoder](https://github.com/logfellow/logstash-logback-encoder) | [Logback configuration](src/main/resources/logback-spring.xml), [Logger implementation](src/main/java/org/dalvarez/ddd_example/shared/infrastructure/logger/Slf4jLogger.java) |
| Code coverage	 | [Jacoco](https://github.com/jacoco/jacoco) + [Sonar Scanner](https://docs.sonarqube.org/latest/analysis/scan/sonarscanner-for-maven/) | [Config](pom.xml) |
| Unit tests	 | [Spring Boot Starter Test](https://docs.spring.io/spring-boot/docs/1.5.7.RELEASE/reference/html/boot-features-testing.html) (Mainly [JUnit 5](https://junit.org/junit5/) and [Mockito](https://site.mockito.org/)) | [Unit test case](src/test/java/org/dalvarez/ddd_example/article/application/create/ArticleCreatorShouldTestCase.java) |
| Integration tests	 | [Spring Boot Starter Test](https://docs.spring.io/spring-boot/docs/1.5.7.RELEASE/reference/html/boot-features-testing.html) (Mainly [JUnit 5](https://junit.org/junit5/) and [Spring Test](https://docs.spring.io/spring-framework/docs/4.3.11.RELEASE/spring-framework-reference/htmlsingle/#integration-testing)) | [Integration test case](src/test/java/org/dalvarez/ddd_example/article/infrastructure/persistence/hibernate/repository/HibernateArticleRepositoryShouldItTestCase.java) |
| Acceptance tests	 | [Cucumber](https://cucumber.io/) | [Feature](src/test/java/org/dalvarez/ddd_example/shared/infrastructure/rest_api/controller/health-check.feature), [Test case](src/test/java/org/dalvarez/ddd_example/shared/infrastructure/rest_api/controller/HealthCheckGetControllerShouldAcceptanceTest.java) |
| Code gen | [Open Api generator](https://github.com/OpenAPITools/openapi-generator) | Before importing the client you must generate it |
| Api documentation | [Swagger Open Api 3](https://swagger.io/specification/) | [Article POST controller](src/main/java/org/dalvarez/ddd_example/article/infrastructure/rest_api/controller/post/ArticlePostController.java) |

## 2. Use cases, patterns and examples of implementation
| Use cases and patterns | Example of implementation |
| ------------------------- | ------------------------ | 
| [Adapter pattern & infrastructure Service](https://refactoring.guru/es/design-patterns/adapter) | [Logger implementation](src/main/java/org/dalvarez/ddd_example/shared/infrastructure/logger/Slf4jLogger.java) |
| [Repository pattern](https://www.martinfowler.com/eaaCatalog/repository.html) | [Repository implementation](src/main/java/org/dalvarez/ddd_example/article/infrastructure/persistence/hibernate/repository/HibernateArticleRepository.java) |
| [Specification pattern](https://en.wikipedia.org/wiki/Specification_pattern) | [Criteria implementation](src/main/java/org/dalvarez/ddd_example/shared/domain/criteria) |
| Domain Service | [CategoryFinder](src/main/java/org/dalvarez/ddd_example/shared/domain/category/DomainCategoryByIdFinder.java) |
| [Value Object](https://martinfowler.com/bliki/ValueObject.html) | [Identifier Value Object](src/main/java/org/dalvarez/ddd_example/shared/domain/value_object/id/Identifier.java) |
| Rich Domain models ([Tell don't ask](https://martinfowler.com/bliki/TellDontAsk.html), [Avoid anemic domain models](https://martinfowler.com/bliki/AnemicDomainModel.html)) | [Article model](src/main/java/org/dalvarez/ddd_example/article/domain/model/Article.java) |
| Clean code patterns ([Guard clauses](https://refactoring.com/catalog/replaceNestedConditionalWithGuardClauses.html), [Named constructors](https://verraes.net/2014/06/named-constructors-in-php/), [Self encapsulation](https://refactoring.guru/es/self-encapsulate-field)) | [Example](src/main/java/org/dalvarez/ddd_example/shared/domain/value_object/id/Identifier.java) |
| Application Service | [ArticleCreator](src/main/java/org/dalvarez/ddd_example/article/application/create/ArticleCreator.java) |

## 3. Environment setup
### Install the needed tools
1. Clone this repository: `> git clone https://github.com/DomingoAlvarez99/ddd-example.git`
2. Download and install: [![Docker](https://img.shields.io/badge/-Docker-blue?&logo=Docker&logoColor=white)](https://www.docker.com/)
[![Java](https://img.shields.io/badge/-java-yellow?&logo=java&logoColor=white)](https://www.docker.com/)
[![Maven](https://img.shields.io/badge/-maven-red?&logo=apache-maven&logoColor=white)](https://www.docker.com/)

### Prepare the application environment
1. Copy the Docker environment variables config file and tune it with your desired values: `> cp .env.dist .env`
2. Start Docker and brin up the database container `docker-compose up -d postgres`
3. Configure the users and databases of the backend and Sonarqube containers.
  - Backend
    - Create the user, the database and the schema:
      - Access to the root db replacing the variables: `> docker exec -it postgres -U ${root_username} ${root_db}`
      - Run the following script replacing the variables:
        ```sql
          CREATE ROLE ${username} LOGIN PASSWORD ${password};
          CREATE DATABASE ${db};
          GRANT CREATE ON DATABASE ${db} TO ${username};
          CREATE SCHEMA ${schema};
          ALTER ROLE ${username} IN DATABASE ${db} SET search_path TO ${schema};
        ```
    - Create the database tables:
      - Find the tables: `> find . -path '*/migration/*' -type f` 
      - Access to te database: `> docker exec -it postgres -U ${username} ${db}`
      - Copy and paste the definition of the tables.
  - SonarQube
    - Create the user, the database and the schema:
      - Access to the root db replacing the variables: `> docker exec -it postgres -U ${root_username} ${root_db}`
        - Run the following script replacing the variables:
          ```sql
            CREATE ROLE ${username} LOGIN PASSWORD ${password};
            CREATE DATABASE ${db};
            GRANT CREATE ON DATABASE ${db} TO ${username};
            CREATE SCHEMA ${schema};
            ALTER ROLE ${username} IN DATABASE ${db} SET search_path TO ${schema};
          ```
4. Bring up the other containers: `> docker-compose up -d sonarqube elasticsearch logstash kibana minio`

### Run the tests

- Unit: (*The name must follow the following pattern `*TestCase`*):
- Integration (*The name must follow the following pattern `*ItTestCase`*)
- Acceptance: Must have a .feature file linked with a .java file

Before all install the dependencies: `> mvn clean install`

- Execute the tests and generate the metrics: `> mvn verify`
- Send metrics to SonarQube:
  ```
    mvn sonar:sonar \
      -Dsonar.projectKey=${key} \
      -Dsonar.host.url=${host} \
      -Dsonar.login=${token}
  ```
- Do all:
  ```
    mvn verify sonar:sonar \
      -Dsonar.projectKey=${key} \
      -Dsonar.host.url=${host} \
      -Dsonar.login=${token}
  ```
### Start the app
Backend: `mvn spring-boot:run`

## 4. Entrypoint

### Endpoints
[Swagger ui](http://localhost:8080/api/v0/swagger-ui.html)

### Cli
*TO DO*

## 5. Logs
The logging mechanism uses Logback and logstash-logback-encoder in order to:

- Output the log records through the standard output channel (STDOUT, your terminal).
- Append the log records in JSON format into Logstash.

After this we can:

- Send the log records to an Elasticsearch index.
- And finally visualize them centrally in Kibana.

## 6. Code gen
Open Api code generation allows generating a rest api client from a specification (controller definition):

1. Download the spec: `> wget -P /generated/swagger-api http://localhost:8080/api-docs -O api-docs.json`
2. Generate the client:
  ```
    > mvn clean install -P code-gen
    > cd /target/generated-sources-swagger-api
    > mvn clean install
  ```

After that you can import the client:
```xml
<dependency>
  <groupId>org.dalvarez.shop</groupId>
  <artifactId>${project.artifactId}-api-client</artifactId>
  <version>${project.version}</version>
</dependency>
```

## 7. Deploy
The project uses [Maven](https://maven.apache.org/) in order to package the app in single Jar file that you can execute.

1. Create the package: `> mvn package`.
2. Copy the generated binary to the destination folder: `> mkdir -p /var/www/ddd-example && cp target/*.jar /var/www/ddd-example/app.jar`
3. Run the app binary:  `> java -jar /var/www/ddd-example/app.jar`
