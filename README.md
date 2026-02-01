# 🎬 Cineando - Gestor de Películas y Opiniones (Microservicios)

> [cite_start]**Trabajo Final de Máster**: Frameworks Backend y Microservicios[cite: 71, 73].
> [cite_start]**Autor**: Juan Higuero López[cite: 74].

Una plataforma web completa para la gestión de un catálogo cinematográfico, actores, usuarios y críticas. [cite_start]Este proyecto implementa una arquitectura moderna de **microservicios** utilizando el ecosistema **Spring Boot** y **Spring Cloud**[cite: 90, 91].

---

## 🚀 Arquitectura del Sistema

[cite_start]El sistema se ha migrado de una estructura monolítica a una arquitectura distribuida y escalable compuesta por los siguientes componentes[cite: 94, 96, 99]:

1.  [cite_start]**Service Discovery (Eureka Server):** Registro centralizado donde se inscriben todos los servicios para desacoplar direcciones físicas de nombres lógicos[cite: 100, 101].
2.  [cite_start]**API Gateway:** Puerta de enlace única que intercepta las peticiones del cliente (Puerto 8100) y enruta dinámicamente con balanceo de carga (`lb://`)[cite: 103, 104].
3.  **Microservicios de Backend:**
    * [cite_start]**Películas y Actores:** Gestión del catálogo audiovisual (MySQL: `peliculasactoresdb`)[cite: 106, 120].
    * [cite_start]**Usuarios y Opiniones:** Gestión de usuarios, roles (RBAC) y críticas (MySQL: `usuariosopinionesdbsec`)[cite: 106, 117].
4.  [cite_start]**Cliente Web (Frontend):** Aplicación MVC con **Thymeleaf** que actúa como orquestador, consumiendo las APIs mediante **Feign Clients** y gestionando la seguridad y vistas[cite: 108, 109].

## 🛠️ Stack Tecnológico

* [cite_start]**Java & Spring Boot 3.5.9** (Estandarizado en todos los módulos)[cite: 99].
* **Spring Cloud:** Netflix Eureka, Spring Cloud Gateway, OpenFeign.
* [cite_start]**Datos:** Spring Data JPA, MySQL 8, MySQL Workbench[cite: 97].
* [cite_start]**Seguridad:** Spring Security, BCrypt, Thymeleaf Extras Security[cite: 112, 114].
* **Frontend:** Thymeleaf, HTML5, CSS3, JavaScript.

---

## ✨ Funcionalidades

### 🌍 Área Pública
* [cite_start]Visualización de películas en formato Grid con portadas e información detallada[cite: 135, 137].
* [cite_start]Buscador avanzado y filtros por ID, título, género o actor[cite: 140].
* [cite_start]Modo Oscuro / Modo Claro en tiempo real[cite: 144].

### 👤 Usuarios Registrados
* [cite_start]Registro e inicio de sesión seguro[cite: 149].
* [cite_start]Sistema de votación (estrellas) y comentarios en las fichas de películas[cite: 154].
* Visualización de la nota media calculada dinámicamente.

### 🛡️ Administración (Rol Admin)
* [cite_start]Panel de gestión completo en barra lateral[cite: 160].
* [cite_start]**CRUD de Películas y Actores:** Crear, editar, borrar y vincular actores a películas[cite: 161].
* **Gestión de Usuarios:** Administración de perfiles y roles.
* [cite_start]**Moderación:** Eliminación de críticas inapropiadas[cite: 161].

---

## ⚙️ Instalación y Despliegue

[cite_start]Para ejecutar el sistema correctamente, es vital seguir el orden de dependencia de los microservicios[cite: 125].

### 1. Base de Datos
[cite_start]Ejecuta los scripts SQL proporcionados en la carpeta `/sql` (o en la documentación) en tu servidor MySQL para crear los esquemas y poblar los datos iniciales[cite: 126, 127]:
* `peliculasactoresdb` (Tablas: Peliculas, Actores, Peliculas_y_actores).
* `usuariosopinionesdbsec` (Tablas: Users, Authorities, Opiniones).

### [cite_start]2. Orden de Arranque [cite: 128-131]
1.  🟢 **Eureka Server** (Service Discovery).
2.  🟢 **Microservicio Películas/Actores**.
3.  🟢 **Microservicio Usuarios/Opiniones**.
4.  🟢 **API Gateway**.
5.  🟢 **Cliente Web**.

### 3. Acceso
Una vez arrancados todos los servicios, abre tu navegador en:
[cite_start]👉 **http://localhost:8100**[cite: 132].

---

## 🔐 Credenciales de Prueba (Datos Seed)

[cite_start]El script de datos iniciales incluye los siguientes usuarios (Contraseña para todos: `1234`)[cite: 178]:

| Usuario | Rol | Descripción |
| :--- | :--- | :--- |
| `admin` | **ADMIN** | Acceso total al panel de gestión. |
| `cinefilo_pro` | USER | Usuario estándar. |
| `mr_hater` | USER | Usuario estándar. |

---


[cite_start]Desarrollado para el Máster en Desarrollo Ágil de Software para la Web - Universidad de Alcalá[cite: 70, 71].
