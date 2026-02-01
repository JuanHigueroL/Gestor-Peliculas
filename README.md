# 🎬 Cineando - Gestor de Películas y Opiniones (Microservicios)

> **Práctica Final de Asignatura**: Frameworks Backend y Microservicios.
> **Máster**: Desarrollo Ágil de Software para la Web.
> **Autor**: Juan Higuero López.

Una plataforma web completa para la gestión de un catálogo cinematográfico, actores, usuarios y críticas. Este proyecto implementa una arquitectura moderna de **microservicios** utilizando el ecosistema **Spring Boot** y **Spring Cloud**.

---

## 🚀 Arquitectura del Sistema

El sistema se ha migrado de una estructura monolítica a una arquitectura distribuida y escalable compuesta por los siguientes componentes:

1.  **Service Discovery (Eureka Server):** Registro centralizado donde se inscriben todos los servicios para desacoplar direcciones físicas de nombres lógicos.
2.  **API Gateway:** Puerta de enlace única que intercepta las peticiones del cliente (Puerto 8100) y enruta dinámicamente con balanceo de carga (`lb://`).
3.  **Microservicios de Backend:**
    * **Películas y Actores:** Gestión del catálogo audiovisual (MySQL: `peliculasactoresdb`).
    * **Usuarios y Opiniones:** Gestión de usuarios, roles (RBAC) y críticas (MySQL: `usuariosopinionesdbsec`).
4.  **Cliente Web (Frontend):** Aplicación MVC con **Thymeleaf** que actúa como orquestador, consumiendo las APIs mediante **Feign Clients** y gestionando la seguridad y vistas.

## 🛠️ Stack Tecnológico

* **Java & Spring Boot 3.5.9** (Estandarizado en todos los módulos).
* **Spring Cloud:** Netflix Eureka, Spring Cloud Gateway, OpenFeign.
* **Datos:** Spring Data JPA, MySQL 8, MySQL Workbench.
* **Seguridad:** Spring Security, BCrypt, Thymeleaf Extras Security.
* **Frontend:** Thymeleaf, HTML5, CSS3, JavaScript.

---

## ✨ Funcionalidades

### 🌍 Área Pública
* Visualización de películas en formato Grid con portadas e información detallada.
* Buscador avanzado y filtros por ID, título, género o actor.
* Modo Oscuro / Modo Claro en tiempo real.

### 👤 Usuarios Registrados
* Registro e inicio de sesión seguro.
* Sistema de votación (estrellas) y comentarios en las fichas de películas.
* Visualización de la nota media calculada dinámicamente.

### 🛡️ Administración (Rol Admin)
* Panel de gestión completo en barra lateral.
* **CRUD de Películas y Actores:** Crear, editar, borrar y vincular actores a películas.
* **Gestión de Usuarios:** Administración de perfiles y roles.
* **Moderación:** Eliminación de críticas inapropiadas.

---

## ⚙️ Instalación y Despliegue

Para ejecutar el sistema correctamente, es vital seguir el orden de dependencia de los microservicios.

### 1. Base de Datos
Ejecuta los scripts SQL proporcionados en la carpeta `/sql` (o en la documentación) en tu servidor MySQL para crear los esquemas y poblar los datos iniciales:
* `peliculasactoresdb` (Tablas: Peliculas, Actores, Peliculas_y_actores).
* `usuariosopinionesdbsec` (Tablas: Users, Authorities, Opiniones).

### 2. Orden de Arranque
1.  🟢 **Eureka Server** (Service Discovery).
2.  🟢 **Microservicio Películas/Actores**.
3.  🟢 **Microservicio Usuarios/Opiniones**.
4.  🟢 **API Gateway**.
5.  🟢 **Cliente Web**.

### 3. Acceso
Una vez arrancados todos los servicios, abre tu navegador en:
👉 **http://localhost:8100**.

---

## 🔐 Credenciales de Prueba (Datos Seed)

El script de datos iniciales incluye los siguientes usuarios (Contraseña para todos: `1234`):

| Usuario | Rol | Descripción |
| :--- | :--- | :--- |
| `admin` | **ADMIN** | Acceso total al panel de gestión. |
| `cinefilo_pro` | USER | Usuario estándar. |
| `mr_hater` | USER | Usuario estándar. |

---

## 📄 Documentación y Capturas

Para ver el **Manual de Usuario completo**, consultar las **capturas de pantalla** de la aplicación y leer la **explicación detallada** del trabajo realizado, por favor consulta el **documento PDF adjunto** en este repositorio.

---

Desarrollado para la asignatura de **Frameworks Backend y Microservicios** - Universidad de Alcalá.
