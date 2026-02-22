# 🛠️ ForoHub — API REST con Spring Boot

ForoHub es una API desarrollada en Java utilizando **Spring Boot 3**, que permite gestionar un sistema de tópicos dentro de un foro, con **autenticación mediante JWT**, control de acceso y operaciones CRUD completas sobre los tópicos.

Este proyecto fue desarrollado como parte de un *challenge* 
---

## 📌 Tecnologías utilizadas

- **Java 17**
- **Spring Boot 4**
- **Spring Web**
- **Spring Data JPA**
- **Spring Security**
- **JWT (JSON Web Tokens)**
- **MySQL 8**
- **Flyway** (migraciones de base de datos)
- **Maven**
- **Lombok**

---

## 🚀 Descripción del proyecto

ForoHub es una API que ofrece funcionalidades para:

✔ Crear un tópico  
✔ Listar todos los tópicos (con paginación)  
✔ Consultar un tópico por ID  
✔ Actualizar un tópico  
✔ Eliminar un tópico  
✔ Controlar acceso mediante JWT

La API está protegida y requiere que el usuario esté autenticado mediante un token JWT para interactuar con los endpoints del recurso *tópicos*. Solo el endpoint de login es público.

---

## 🗄️ Estructura de la base de datos

### Tabla `topicos`

| Campo           | Tipo             | Descripción                  |
|----------------|------------------|------------------------------|
| id             | BIGINT           | Identificador único          |
| titulo         | VARCHAR(255)     | Título del tópico            |
| mensaje        | TEXT             | Contenido del tópico         |
| fecha_creacion | DATETIME         | Fecha de creación            |
| status         | VARCHAR(50)      | Estado del tópico            |
| autor          | VARCHAR(100)     | Autor del tópico             |
| curso          | VARCHAR(100)     | Curso relacionado            |

### Tabla `usuarios`

| Campo    | Tipo            | Descripción                 |
|----------|------------------|-----------------------------|
| id       | BIGINT           | Identificador único         |
| login    | VARCHAR(100)     | Nombre de usuario          |
| password | VARCHAR(255)     | Contraseña encriptada       |

---

## 🔐 Autenticación con JWT

La API implementa autenticación mediante JWT:

- El usuario se autentica mediante:
