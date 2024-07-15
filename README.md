# ForoAlura

ForoAlura es un backend para un foro de discusión, desarrollado utilizando Spring Boot. Este proyecto proporciona una API RESTful para manejar la autenticación, la gestión de usuarios, y la administración de temas y respuestas.

## Características

- **Gestión de Usuarios**: Registro, autenticación y autorización de usuarios.
- **Gestión de Temas**: Creación, actualización, eliminación y visualización de temas del foro.
- **Gestión de Respuestas**: Creación, actualización, eliminación y visualización de respuestas en los temas.
- **Seguridad**: Autenticación y autorización utilizando JWT.
- **Migraciones de Base de Datos**: Gestión de migraciones con Flyway.

## Tecnologías Utilizadas

- **Spring Boot 3.3.1**: Framework principal para el desarrollo de la aplicación.
- **Spring Data JPA**: Para la persistencia de datos.
- **Spring Security**: Para la gestión de la seguridad.
- **JWT (JSON Web Tokens)**: Para la autenticación y autorización.
- **Flyway**: Para la gestión de migraciones de la base de datos.
- **MariaDB**: Base de datos utilizada en el proyecto.
- **Lombok**: Para reducir el código boilerplate.
- **Swagger**: Para la documentación de la API.

## Estructura del Proyecto

El proyecto está organizado en los siguientes paquetes:

- **config/error**: Clases para manejo de errores.
  - `TratarErrores`

- **security**: Clases relacionadas con la seguridad y autenticación.
  - `AutenticationService`
  - `SecurityConfiguration`
  - `SecurityFilter`
  - `TokenService`

- **controller**: Controladores de la API.
  - `AutenticationController`
  - `RespuestaController`
  - `TopicoController`
  - `UsuarioController`

- **models**: Clases modelo que representan las entidades de la base de datos.
  - `Curso`
  - `Respuesta`
  - `StatusTopico`
  - `Topico`
  - `Usuario`

- **repository**: Repositorios para la interacción con la base de datos.

## Requisitos Previos

- JDK 17 o superior
- Maven
- MariaDB

## Configuración del Proyecto

1. Clona el repositorio:
   ```sh
   git clone https://github.com/tu-usuario/ForoAlura.git
