_**Este repositorio se está desarrollando**_
# Shop core
> Este proyecto es el microservicio principal del proyecto Shop. Se encarga de acceder a la db, comunicarse con otros microservicios a través de eventos...
 
# Tabla de contenidos

- [Arquitectura](#Arquitectura)
   - [Diagrama](#Diagrama)
   - [Módulos](#Módulos)
   - [Capa de Dominio](#Capa-de-Dominio)
      - [Modelos](#Modelos)
      - [Puertos](#Puertos)
      - [Eventos de Dominio](#Eventos-de-Dominio)
   - [Capa de Aplicación](#Capa-de-Aplicación)
     - [Casos de Uso](#Casos-de-Uso)
   - [Capa de Infraestructura](#Capa-de-Infraestructura)
      - [Adaptadores](#Adaptadores)
      - [Otras cosas que pueden ser parte de la capa de Infraestructura](#Otras-cosas-que-pueden-ser-parte-de-la-capa-de-Infraestructura)
        - [Api Rest](#Api-Rest)
          - [GET](#GET)
          - [POST](#POST)
          - [PUT](#PUT)
          - [DELETE](#DELETE)
   - [Recomendaciones y buenas prácticas](#Recomendaciones-y-buenas-prácticas)
      - [Manejo de errores](#Manejo-de-errores)
      - [Testing](#Testing)
      - [Configuración](#Configuración)
      - [Logging](#Logging)
      - [Estructura de carpetas y archivos](#Estructura-de-carpetas-y-archivos)
      - [Formato del código](#Formato-del-código)   
      - [Documentación](#Documentación)
      - [Hacer que la aplicación sea fácil de configurar](#Hacer-que-la-aplicación-sea-fácil-de-configurar)
      - [Evitar cadenas de herencia masivas](#Evitar-cadenas-de-herencia-masivas)
      - [Criteria](#Criteria)
- [Configuración](#Configuración)
- [Ejecución](#Ejecución)
# Arquitectura

Principalmente basada en:

- [Arquitectura Hexagonal](https://en.wikipedia.org/wiki/Hexagonal_architecture_(software))
- [Principios SOLID](https://en.wikipedia.org/wiki/SOLID)
- [Patrones de Diseño de Software](https://refactoring.guru/design-patterns/what-is-pattern)

## Pros:
- Es independiente de Frameworks, bases de datos, apis externas.. Frameworks y recursos externos pueden ser reemplazados sin mucho esfuerzo.
- Es testable, escalable y mantenible.
- Es más fácil añadir nuevas características.

## Contras:
- Es una arquitectura sofisticada que requiere conocer principios de software, como los principios SOLID, arquitecturas limpias, DDD..
- No es recomendable implementarla en aplicaciones pequeñas con poca lógica de negocio. Por ejemplo, en un simple CRUD.

# Diagrama

![Diagram](src/main/resources/static/architecture-hexagonal-diagram.png)

De forma breve, el flujo de datos es:

- La petición/comando/evento es enviado al controlador usando un DTO.
- El controlador se comunica con un caso de uso de la aplicación.
- El caso de uso ejecuta la lógica de negocio pudiendo usar servicios de dominio, se comunica con la capa de infraestructura a través de los puertos, y puede enviar o escuchar eventos.
- La capa de infraestructura mapea los objetos al formato que necesita, usa repositorios para obtener/persistir datos y usa otros adaptradores para enviar eventos o para hacer otros comunicaciones I/O, mapea los datos de vuelta y los devuelve de nuevo a la capa de aplicación. 
- La capa de aplicación mapea y devuelve los datos/confirmación de vuelta a los controladores.
- Los controladores devuelven los datos de vuelta a los usuarios.

Cada capa tiene su propia lógica y normalmente suele seguir un [Principio de Responsabilidad Única](https://en.wikipedia.org/wiki/Single-responsibility_principle) cuando es posible. Por ejemplo, usando 'Repositorios' solo para accesos a bases de datos, usando 'Casos de Uso' solo para ejecutar acciones concretas(Como puede ser llamar a un método del repositorio para insertar un usuario en la bae de datos). En definitiva para cualquier acción que solo tenga un motivo por el que cambiar.

# Módulos

El código del proyecto está separado en módulos. Cada módulo tiene su propio paquete con código dedicado. Es más fácil trabajar en cosas que cambian juntas si esas cosas están relativamente cerca unas de las otras.

Cada módulo intenta ser independiente y las interacciones entre módulos son mínimas.

Paquete de ejemplo: [article](src/main/java/org/dalvarez/shop/core/article)

# Capa de Dominio

Esta capa es el núcleo de la aplicación y contiene la lógica de negocio.

- Modelos
- Puertos
- Agregados _**TO DO**_
- Eventos de dominio
- Servicios de dominio _**TO DO**_ 
- Value Objects _**TO DO**_

## Modelos

Los modelos son el núcleo del dominio. Encapsulan las reglas y los atributos del negocio. Un moddelo es una objecto con propiedades, métodos, estructuras de datos..

Fichero de ejemplo: [Article.java](src/main/java/org/dalvarez/shop/core/article/domain/Article.java)

## Puertos

Los puertos con interfaces que definen contratos que deben implementar los adaptadores de infraestructura para ejecutar acciones. Estos puertos actúan como abstracciones para la lógica del negocio.

- Son intefaces que definen lo que se debe de hacer pero no cómo.
- Se usan para abstraer operaciones E/S, apis de terceros, persistencia...
- Las implementaciones simuladas se pueden pasar a los puertos durante los tests.
- Los Mocks hacen que las pruebas sean independientes y más rápidas.
- Al diseñar puertos, hay que tener en cuenta el principio ([ISP](https://en.wikipedia.org/wiki/Interface_segregation_principle) de [SOLID](https://en.wikipedia.org/wiki/SOLID)).
- Las implementaciones deben de ser inyectadas e invertidas (inyección de depencias y [DIP](https://en.wikipedia.org/wiki/Dependency_inversion_principle) de [SOLID](https://en.wikipedia.org/wiki/SOLID)). Esto hace que la lógica de negocio sea independiente de la técnología, facilia los tests, permite conectar/desconectar/intercambiar cualquier recurso externo fácilmente, lo que hace que la aplicación sea modular y no esté acoplada.

Ejemplos: 
    
- [ArticleRepository.java](src/main/java/org/dalvarez/shop/core/article/domain/ArticleRepository.java) - Puerto que cumple el principio [ISP](https://en.wikipedia.org/wiki/Interface_segregation_principle).
- [ArticleRepositoryConfig.java](src/main/java/org/dalvarez/shop/core/article/domain/ArticleRepositoryConfig.java) - Ejemplo de inversión de dependencias [DIP](https://en.wikipedia.org/wiki/Dependency_inversion_principle) junto a un único punto de instanciación.

## Eventos de Dominio

Un evento de dominio indica algo que sucedió en alguna parte (normalmente en caso de uso).

Por ejemplo, si un usuario se registra desde un formulario, deberían de suceder los siguientes eventos:

- Enviar un correo electrónico de confirmación a ese usuario.
- Enviar una notificación a un canal corporativo.
- Realizar otros efectos secundarios.

El enfoque típico que se utiliza habitualmente implica ejecutar toda la lógica en un servicio que realiza la inserción del usuario. Esto crea acoplamiento y además hace que no se cumplan los principios ([SRP](https://en.wikipedia.org/wiki/Single-responsibility_principle) y [OCP](https://en.wikipedia.org/wiki/Open%E2%80%93closed_principle) de [SOLID](https://en.wikipedia.org/wiki/SOLID)).

Una mejor solución sería publicar un evento de dominio. Cualquier operación que realice efectos secundarios se puede ejecutar simplemente publicando un evento de dominio, y consumiendo la publicación de este evento sin la necesidad de acoplar el código.

Ejemplos:

- [EventBus.java](src/main/java/org/dalvarez/shop/core/shared/domain/bus/event/EventBus.java) - Responsable de publicar/escuchar eventos de dominio.
- [ArticleCreatedDomainEvent.java](src/main/java/org/dalvarez/shop/core/article/domain/ArticleCreatedDomainEvent.java) - Objeto que contiene datos relaciones con el evento de dominio publicado.
- [OnArticleCreatedEvent.java](src/main/java/org/dalvarez/shop/core/article/application/create/OnArticleCreatedEvent.java) - Caso de uso que escucha la publiación de un tipo de evento de dominio.
- [ArticleCreator.java](src/main/java/org/dalvarez/shop/core/article/application/create/ArticleCreator.java) - Caso de uso que publica un evento de dominio.

# Capa de Aplicación

## Casos de Uso

Estos servicios orquestan los pasos necesarios para cumplir con las directrices impuestas por el cliente a través de los puertos del dominio.

- Declaran dependencias de los servicios de infraestructura necesarias para ejecutar la lógica del dominio (usando puertos).
- Se utilizan para obtener entidades de la base de datos.
- No contienen lógica de dominio especifica.
- Pueden ejecutar otras comunicaciones a través de puertos (como emitir eventos, enviar notificaciones..).
- No deberían de depender de otros casos de uso.

Fichero de ejemplo: [ArticleCreator.java](src/main/java/org/dalvarez/shop/core/article/application/create/ArticleCreator.java)

## CQRS

El patrón [Command Query Responsability Segregation (CQRS)](https://martinfowler.com/bliki/CQRS.html) separa los métodos en Comandos(operaciones de cambios de estado) y en Consultas (operaciones de recuperación de datos)

### Comandos

Los comandos se usan para realizar acciones que cambian estados, como crear un nuevo usuario en la base de datos. Las operaciones de creación, actualización y eliminación se consideran cambios de estado.

### Consultas

Las consultas se usan para recuperar información de la base datos.

# Capa de Infraestructura

## Adaptadores

Son implementaciones de los puertos. Toman los datos de entrada del usuario, la procesa y transforma para devolverla al usuario. El usuario puede ser una persona que utilice la aplicación u otro servidor.

- Contiene controladores y DTO de solicitud, persistencia, CLI..

Fichero de ejemplo [HibernateArticleRepository.java](src/main/java/org/dalvarez/shop/core/article/infrastructure/hibernate_persistence/HibernateArticleRepository.java)

## Otras cosas que pueden ser parte de la capa de Infraestructura

### Api Rest

Una [Api Rest](https://restfulapi.net/) es cualquier interfaz entre sistemas que usen el [protocolo HTTP](https://en.wikipedia.org/wiki/Hypertext_Transfer_Protocol) para obtener o generar operaciones sobre esos datos en todos los formatos posibles, por lo general en JSON.

Verbos más usados:

- GET
- POST
- PUT
- DELETE

#### GET

Se usa para recuperar un recurso. Si no se encuentra se debe de enviar un código [HTTP 404](https://en.wikipedia.org/wiki/HTTP_404).

Fichero de ejemplo [ArticleGetByIdController](src/main/java/org/dalvarez/shop/core/article/infrastructure/rest_api/controller/get/by_id/ArticleGetByIdController.java)

#### POST

Se usa para crear un recurso. En caso de que ese recurso exista se debe de enviar un código [HTTP 403](https://en.wikipedia.org/wiki/HTTP_403).

Fichero de ejemplo [ArticlePostController](src/main/java/org/dalvarez/shop/core/article/infrastructure/rest_api/controller/post/ArticlePostController.java)

#### PUT

Se usa para actualizar un recurso. En caso de que ese recurso no exista se debe de enviar un código [HTTP 404](https://en.wikipedia.org/wiki/HTTP_404).

Fichero de ejemplo [ArticlePutController](src/main/java/org/dalvarez/shop/core/article/infrastructure/rest_api/controller/put/ArticlePutController.java)

#### DELETE

Se usa para eliminar un recurso. Si ese recurso no existe, no se debe de mandar un código [HTTP 404](https://en.wikipedia.org/wiki/HTTP_404).

Fichero de ejemplo [ArticleDeleteController.java](src/main/java/org/dalvarez/shop/core/article/infrastructure/rest_api/controller/delete/ArticleDeleteController.java)

## Recomendaciones y buenas prácticas

### Manejo de errores

- Lanzar excepciones por encima de devolver códigos de error.
- Crear excepciones específicas.
- Lanzar [excepciones unchecked](https://docs.oracle.com/javase/tutorial/essential/exceptions/runtime.html) siempre y cuando sea necesario
- Centralizar el manejo de excepciones de la aplicación.

Ejemplos:

- [BadRequestException.java](src/main/java/org/dalvarez/shop/core/shared/domain/exception/BadRequestException.java) - Excepción unchecked.
- [ApiExceptionHandler.java](src/main/java/org/dalvarez/shop/core/shared/infrastructure/rest_api/exception_handler/ApiExceptionHandler.java) - Manejador de excepciones de la aplicación.

### Testing

Las pruebas de software ayudan a detectar errores. Un producto probado correctamente garantiza fiabilidad, seguridad y alto rendimiento, lo que se traduce en ahorro de tiempo y satisfacción del cliente.

- Deben de usar un propio fichero de configuración.
- Deben de usar bases de datos embebidas. Por ejemplo, [h2](https://www.h2database.com/html/main.html).
- Deben de aislarse.
- Deben de automatizarse.
- La [cobertura de código](https://dzone.com/articles/unit-testing-best-practices-how-to-get-the-most-ou) es una medida de la cantidad de código cubierto por pruebas automatizadas. Estas métricas se pueden generar con librerías como (JaCoCo)[https://www.eclemma.org/jacoco/] y se pueden visualizar con herramientas como (SonarQube)[https://www.sonarqube.org/]. Hay que tener en cuenta que aunque un código tenga una alta cobertura no tiene por qué funcionar correctamente.
- Los [mocks](https://en.wikipedia.org/wiki/Mock_object) hacen que las pruebas sean independientes y más rápidas.

Fichero de ejemplo usando mocks [ApplicationModuleTestCase.java](src/test/java/org/dalvarez/shop/core/shared/application/ApplicationModuleTestCase.java)

### Configuración

- Almacenar variables de configuración que dependen del entorno en distintos ficheros de configuración.
- Usar variables de entorno para almacenar información sensible.

Ejemplos:

- [application-dev.yml](src/main/resources/application-dev.yml) - Fichero de configuración del perfil de develop.

### Logging

- Evitar usar herramientas predeterminadas que den salida por pantalla (System.out.print()). Usar librerías maduras como [slf4j](http://www.slf4j.org/) que permiten indicar niveles de logging.
- Usar niveles de logging adecuados, (info) para eventos significativos, (trace/debug) para depurar errores durante el desarrollo y (error/warn) para mostrar el comportamiento no deseado durante cualquier etapa.
- Utilizar sistemas de gestión de logs como [Logstash](https://www.elastic.co/es/logstash).
- Visualizar logs usando herramientas como [Kibana](https://www.elastic.co/es/kibana).

### Estructura de carpetas y archivos

En lugar de usar el estilo típico de organizar la aplicación en capas de repositorios, servicios, controladores..  Se debe de dividir todo en módulos, dónde cada módulo está compuesto por componentes que están relacionados([CCP](https://ericbackhage.net/clean-code/the-common-closure-principle/)).

- Separar la aplicación en módulos.
- Mantener los archivos que cambian juntos cerca unos de los otros.
- Agrupar archivos por su comportamiento que cambia juntos, no por el tipo de funcionalidad que proporciona.
- Mantener separados los archivos utilizados por otros componentes.

Paquete de ejemplo: [article application](src/main/java/org/dalvarez/shop/core/article/application)

### Formato del código

La forma en que se muestra el código ayuda a la comprensión del mismo. Hay formateadores de código que ayudan a configurar estos estilos, por ejemplo, usando [IntelliJ IDEA](https://www.jetbrains.com/es-es/idea/) se puede configurar un fichero para hacer esto.

Fichero de ejemplo [editorconfig](src/.editorconfig)

### Documentación

Usar especificaciones de OpenAPI (Swagger) o GraphQL.

### Hacer que la aplicación sea fácil de configurar

Configurar una apliación puede llegar a ser muy tedioso. Es recomendable añadir scripts que hagan esto de forma automática:

- [docker-compose.yml](docker-compose.yml)

### Evitar cadenas de herencia masivas

Esto se puede lograr haciendo que la clase sea final.

Nota: El uso de algunas librerías o de las funcionalidades de algunos Frameworks se puede ver afectado. Por ejemplo, usando Mockito, para poder hacer un Mock de una clase final, se debe de añadir un fichero de extensión ``src/test/resources/mockito-extensions/org.mockito.plugins.MockMaker``. En cuanto a Spring, para poner anotar una clase con la anotación @Configuration, la clase no puede ser final.

### Criteria

Criteria es un patrón de diseño que permite filtrar, ordenar y paginar un conjunto de información utilizando diferentes criterios.

- Se suele usar para obtener/modificar información de la base de datos.

Paquete de ejemplo: [criteria](src/main/java/org/dalvarez/shop/core/shared/domain/criteria)

# Configuración

- [db](src/main/resources/postgres) Lanzar las sentencias para crear las bases de datos, usuarios, tablas..

## Sonarqube

- Crear proyecto en sonarqube.
  1. Iniciar sesión
        - Username: admin
        - Password: admin
  2. [Crear proyecto](http://localhost:9000/projects/create) 
- [pom.xml](pom.xml) Editar las propiedades del perfil de sonar.

# Ejecución

- Arrancar la base de datos: `> docker-compose up -d postgres`
- Arrancar sonarqube: `> docker-compose up -d sonarqube`
- Arrancar la aplicación en un contenedor: `> docker-compose up -d spring`
- Arrancar el proyecto en local: 
   - `> mvn clean install`
   - `> mvn spring-boot:run`

## Testing

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


# Urls
- [Api](http://localhost:8080/api/v0)
- [Api doc](http://localhost:8080/api/v0/swagger-ui.html)
- [Sonar](http://http://localhost:9000/projects)