_**Este repositorio está en desarrollo**_
# Arquitectura Hexagonal y DDD en Java
Ejemplo de una aplicación Java que utiliza la Arquitectura de *Puertos y Adaptadores* ([Arquitectura Hexagonal](https://es.wikipedia.org/wiki/Arquitectura_hexagonal_(software))) junto a Domain Driven Design ([DDD](https://es.wikipedia.org/wiki/Dise%C3%B1o_guiado_por_el_dominio)) para mantener el código lo más simple posible.

## Tabla de contenidos
- [Arquitectura Hexagonal y DDD en Java](#arquitectura-hexagonal-y-ddd-en-java)
  - [Tabla de contenidos](#tabla-de-contenidos)
  - [1. Tecnologías necesarias](#1-tecnologías-necesarias)
  - [2. Entrypoint](#2-entrypoint)
    - [Endpoints](#endpoints)
    - [Cli](#cli)
  - [3. Librerías y ejemplos de implementación](#3-librerías-y-ejemplos-de-implementación)
  - [4. Casos de uso y patrones implementados](#4-casos-de-uso-y-patrones-implementados)
  - [5. Configuración del entorno](#5-configuración-del-entorno)
  - [6. Testing](#6-testing)
  - [7. Logs](#7-logs)
  - [8. Deploy](#8-deploy)
 

## 1. Tecnologías necesarias

## 2. Entrypoint

### Endpoints

### Cli

## 3. Librerías y ejemplos de implementación
| Característica | Librería | Ejemplos de implementación |
| ------------------------- | ----------------------------------------------------------- | ------------------------- |
| Build tool | [Maven](https://maven.apache.org/) | [Dependencies, configuration and build](pom.xml) |
| Style formatting | [EditorConf](https://www.jetbrains.com/help/idea/editorconfig.html) | [Rules](src/.editorconf)   |
| HTTP server	| [Spring Boot Starter Web](https://spring.io/guides/gs/rest-service/) | [Article POST controller](src/main/java/org/dalvarez/ddd_example/article/infrastructure/rest_api/controller/post/ArticlePostController.java) |
| Database integration | [Spring Data](https://spring.io/projects/spring-data) + [JPA](https://www.objectdb.com/api/java/jpa) + [Hibernate](https://hibernate.org/) | [Article repository](src/main/java/org/dalvarez/ddd_example/article/infrastructure/persistence/hibernate/repository/HibernateArticleRepository.java) |
| Domain events publishing & consuming | [Project Reactor](https://projectreactor.io/) | [Publisher and consumer integration](src/main/java/org/dalvarez/ddd_example/shared/infrastructure/bus/reactor/ReactorEventBus.java) |
| Infrastructure management | [Docker](https://www.docker.com/) | [Docker compose](docker-compose.yml) |
| Logging | [Logback](https://logback.qos.ch/) + [Logstash encoder](https://github.com/logfellow/logstash-logback-encoder) | [Logback configuration](src/main/resources/logback-spring.xml), [Logger implementation](src/main/java/org/dalvarez/ddd_example/shared/infrastructure/logger/Slf4jLogger.java) |
| Code coverage	 | [Jacoco](https://github.com/jacoco/jacoco) + [Sonar Scanner](https://docs.sonarqube.org/latest/analysis/scan/sonarscanner-for-maven/) | [Config](pom.xml) |
| Unit tests	 | [Spring Boot Starter Test](https://docs.spring.io/spring-boot/docs/1.5.7.RELEASE/reference/html/boot-features-testing.html) | [Unit test case](src/test/java/org/dalvarez/ddd_example/article/application/create/ArticleCreatorShouldTestCase.java) |
| Integration tests	 | [Spring Boot Starter Test](https://docs.spring.io/spring-boot/docs/1.5.7.RELEASE/reference/html/boot-features-testing.html) | [Integration test case](src/test/java/org/dalvarez/ddd_example/article/infrastructure/persistence/hibernate/repository/HibernateArticleRepositoryShouldItTestCase.java) |

## 4. Casos de uso y patrones implementados
| Casos de uso y patrones | Ejemplos de implementación |
| ------------------------- | ------------------------ | 
| [Adapter pattern & infrastructure Service](https://refactoring.guru/es/design-patterns/adapter) | [Logger implementation](src/main/java/org/dalvarez/ddd_example/shared/infrastructure/logger/Slf4jLogger.java) |
| [Repository pattern](https://www.martinfowler.com/eaaCatalog/repository.html) | [Repository implementation](src/main/java/org/dalvarez/ddd_example/article/infrastructure/persistence/hibernate/repository/HibernateArticleRepository.java) |
| [Specification pattern](https://en.wikipedia.org/wiki/Specification_pattern) | [Criteria implementation](src/main/java/org/dalvarez/ddd_example/shared/domain/criteria) |
| Domain Service | [CategoryFinder](src/main/java/org/dalvarez/ddd_example/shared/domain/category/DomainCategoryByIdFinder.java) |
| [Value Object](https://martinfowler.com/bliki/ValueObject.html) | [Identifier Value Object](src/main/java/org/dalvarez/ddd_example/shared/domain/value_object/id/Identifier.java) |
| Rich Domain models ([Tell don't ask](https://martinfowler.com/bliki/TellDontAsk.html), [Avoid anemic domain models](https://martinfowler.com/bliki/AnemicDomainModel.html)) | [Article model](src/main/java/org/dalvarez/ddd_example/article/domain/model/Article.java) |
| Clean code patterns ([Guard clauses](https://refactoring.com/catalog/replaceNestedConditionalWithGuardClauses.html), [Named constructors](https://verraes.net/2014/06/named-constructors-in-php/), [Self encapsulation](https://refactoring.guru/es/self-encapsulate-field)) | [Example](src/main/java/org/dalvarez/ddd_example/shared/domain/value_object/id/Identifier.java) |
| Application Service | [ArticleCreator](src/main/java/org/dalvarez/ddd_example/article/application/create/ArticleCreator.java) |

## 5. Configuración del entorno

## 6. Testing
- Instalar las dependencias: `> mvn clean install`
- Ejecutar tests:
   - Integración (*Su nombre debe seguir el patrón `*ItTestCase`*): `> mvn failsafe:integration-test`
   - Todos `mvn test`
- Generar métricas: `> mvn verify`
- Enviar las métricas a SonarQube:
```
  mvn sonar:sonar \
    -Dsonar.projectKey=${KEY} \
    -Dsonar.host.url=${HOST} \
    -Dsonar.login=${TOKEN}
```
- Generar y enviar las métricas a SonarQube: `> mvn verify sonar:sonar`
## 7. Logs

## 8. Deploy
