# Proyecto 2 - Sistema-de-Administracion-de-Cines

##  Descripción del Proyecto

Sistema web completo para la gestión de cines, venta de boletos en línea y administración de contenido cinematográfico. Desarrollado como una aplicación SPA (Single Page Application) con arquitectura cliente-servidor.

---

## Objetivos del Proyecto

- Diseñar y documentar casos de uso.
- Implementar aplicación web usando SPA y servicios REST.
- Desarrollar una API para la realizacion de las diferentes acciones.
- Aplicar buenas prácticas de programación.

---

## Funcionalidades Principales

### Módulo de Usuarios

    Registro y autenticación segura

    Gestión de perfiles y cartera digital

### Gestión de Cines

    Creación y configuración de salas

    Definición de layouts (filas/columnas)

    Bloqueo/desbloqueo de salas

    Gestión de costos operativos

### Catálogo de Películas

    Administración centralizada de películas

    Información completa: sinopsis, cast, director, clasificación

    Categorías múltiples (Anime, Acción, etc.)

    Búsqueda por título y categorías

### Venta de Boletos

    Selección de cine, sala y horario

    Sistema de asientos visual

    Pago con cartera digital

    Confirmación inmediata

### Sistema de Anuncios

    3 tipos de anuncios:

        Texto

        Texto + Imagen

        Video + Texto

    Períodos de vigencia (1 día a 2 semanas)

    Sistema de pagos

    Reproducción automática en loop

### Comentarios y Calificaciones

    Calificación de 1-5 estrellas

    Comentarios en películas y salas

    Moderación por administradores

    Reportes de popularidad

---

## Sistema de Reportes

### Para Administradores de Cine

- Comentarios por intervalo de tiempo

- Películas proyectadas por sala

- Top 5 salas más gustadas

- Boletos vendidos con totales

Para Administradores del Sistema

- Reporte de ganancias generales

- Anuncios comprados y filtrados

- Ganancias por anunciante

- Top 5 salas más populares

- Salas más comentadas


---

## Tecnologías Utilizadas

### Frontend

- Angular 21 con TypeScript
- HTML5/SCSS

### Backend

- Java JAX-RS
- Jasper Reports para generación de PDF
- Maven para gestión de dependencias

### Base de Datos

- MariaDB
- JDBC
- Tomcat como servidor local


