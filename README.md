_**Este repositorio está en desarrollo**_
# Shop core
> Este proyecto es el microservicio principal del grupo Shop.
 
# Tabla de contenidos

- [Arquitectura](#Arquitectura)
- [Configuración](#Configuración)
- [Ejecución](#Ejecución)
  - [Testing](#Testing)
- [Generar Cliente](#Generar-cliente)
- [Urls](#Urls)

# [Arquitectura](https://github.com/DomingoAlvarez99/shop/blob/master/README.md#arquitectura-de-cada-microservicio)

# Configuración

## [Común](https://github.com/DomingoAlvarez99/shop/blob/master/README.md#servicios)

## Propia

- [db](src/main/resources/postgres) Lanzar las sentencias para crear los schemas, tablas, índices..

# Ejecución

## [Común](https://github.com/DomingoAlvarez99/shop#Servicios)

## Propia

- Arrancar el proyecto en local: 
   - `> mvn clean install`
   - `> mvn spring-boot:run`

### [Testing](https://github.com/DomingoAlvarez99/shop/blob/master/README.md#generar-api-del-cliente)

- Ejecutar tests: `> mvn test`
- Generar métricas: `> mvn verify`
- Enviar las métricas a SonarQube: 
   - `> mvn sonar:sonar` (Las propiedades se deben de haber añadido previamente en el perfil de sonar del [pom.xml](pom.xml))
   - ```bash
     mvn sonar:sonar \
             -Dsonar.projectKey=${KEY} \
             -Dsonar.host.url=${HOST} \
             -Dsonar.login=${TOKEN}
     ```
- Realizar las 2 cosas: `> mvn verify sonar:sonar`

# [Generar cliente](https://github.com/DomingoAlvarez99/shop/blob/master/README.md#generar-api-del-cliente)

# Urls

- [Api](http://localhost:8080/api/v0)
- [Api doc](http://localhost:8080/api/v0/swagger-ui.html)
- [Api spec](http://localhost:8080/api/v0/api-docs)
