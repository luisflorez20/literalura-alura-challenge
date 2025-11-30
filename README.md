# 📚 LiterAlura - Catálogo de Libros

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.4-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17-blue)
![Maven](https://img.shields.io/badge/Maven-4.0.0-red)

**LiterAlura** es una aplicación de consola desarrollada en Java con Spring Boot que permite gestionar un catálogo de libros consumiendo la API de [Gutendx](https://gutendx.com/). Los usuarios pueden buscar libros, registrarlos en una base de datos PostgreSQL y realizar diversas consultas sobre la información almacenada.

## 🎯 Características Principales

- 🔍 **Búsqueda de libros por título**: Consulta la API de Gutendx y registra automáticamente el libro en la base de datos
- 📖 **Listado de libros registrados**: Visualiza todos los libros almacenados en la base de datos
- 👥 **Listado de autores**: Muestra todos los autores registrados sin duplicados
- 📅 **Autores por año**: Filtra autores que estaban vivos en un año específico
- 🌍 **Libros por idioma**: Filtra libros por idioma (Español, Inglés, Francés, Portugués)
- 🚫 **Prevención de duplicados**: Evita registrar el mismo libro o autor múltiples veces

## 🛠️ Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3.2.4**
- **Spring Data JPA**
- **PostgreSQL 17**
- **Jackson Databind** (para serialización JSON)
- **Jakarta Validation API**
- **Maven** (gestión de dependencias)

## 📋 Requisitos Previos

- Java 17 o superior
- PostgreSQL 17
- Maven 3.6 o superior
- IDE (IntelliJ IDEA, Eclipse, VS Code)

## ⚙️ Configuración

### 1. Clonar el repositorio
```bash
git clone https://github.com/luisflorez20/literalura-alura-challenge
cd literalura
```

### 2. Configurar la base de datos
```sql
-- Crear la base de datos en PostgreSQL
CREATE DATABASE db_iteralura;
```

### 3. Configurar application.properties
```properties
# Actualizar con tus credenciales
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
```

### 4. Ejecutar la aplicación
```bash
mvn spring-boot:run
```

## 🎮 Uso de la Aplicación

Al ejecutar la aplicación, verás un menú interactivo con las siguientes opciones:

```
===============================================
    BIENVENIDO AL CATÁLOGO DE LIBROS LITERALURA
===============================================

Elija la opción a través de su número:
1 - Buscar libro por título
2 - Listar libros registrados
3 - Listar autores registrados  
4 - Listar autores vivos en un determinado año
5 - Listar libros por idioma
0 - Salir
```

### Ejemplos de uso:

- **Buscar libro**: Ingresa "Pride" para encontrar "Pride and Prejudice"
- **Filtrar por año**: Ingresa "1600" para ver autores como Cervantes y Shakespeare
- **Filtrar por idioma**: Usa "es", "en", "fr" o "pt"

## 📁 Estructura del Proyecto

```
src/main/java/com/alura/literalura/literalura/
├── config/         # Configuraciones de Spring
├── dto/            # DTOs para la API de Gutendx
├── model/          # Entidades JPA (Libro, Autor)
├── repository/     # Repositorios de Spring Data JPA
├── service/        # Lógica de negocio y consumo de API
├── principal/      # Clase principal del menú de consola
└── LiteraluraApplication.java  # Clase main de Spring Boot
```

## 🔗 API Utilizada

Este proyecto consume la API de **Gutendx** (https://gutendx.com/), que proporciona acceso a más de 70,000 libros del Proyecto Gutenberg de forma gratuita.

## 👨‍💻 Desarrollado por

**Challenge Alura Latam - Oracle Next Education**

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo `LICENSE` para más detalles.

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Para contribuir:

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/nueva-funcionalidad`)
3. Commit tus cambios (`git commit -m 'Agregar nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Abre un Pull Request

---

⭐ **¡Si te gusta este proyecto, no olvides darle una estrella!** ⭐
