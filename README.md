# byma-back-emisores

# byma-fondos-up-back - Emisores

## Descripción General
Esta es una API basada en Spring Boot que permite administrar emisores. Puedes crear, obtener, actualizar y eliminar emisores utilizando esta API. La API está documentada con Swagger y puedes interactuar con ella a través de la interfaz de usuario de Swagger.

## Tecnologías

- **Spring Boot** - Framework para el backend
- **Java** - Lenguaje de programación
- **Maven** - Gestión de dependencias
- **Lombok** - Para reducir código repetitivo
- **Jakarta Validation** - Validación de DTOs
- **Swagger/OpenAPI** - Documentación de la API
- **Base de Datos H2 (Opcional)** - Base de datos en memoria para desarrollo/pruebas
- **Postman** - Testing de la API
- **JUnit 5** - Framework de pruebas unitarias
- **Mockito** - Framework de mocking para pruebas unitarias
- **Docker (Opcional)** - Contenerización
- **Spring Data JPA** - Capa de acceso a datos

## Comenzando

### 1. Clonar el Repositorio

```bash
git clone https://github.com/teamcubation/byma-back-emisores.git
cd byma-back-emisores
```

### 2. Construir el Proyecto
Usa Maven para instalar las dependencias y construir el proyecto:
```bash
mvn clean install
```

### 3. Ejecutar la Aplicación
```bash
mvn spring-boot:run
```

Por defecto, la aplicación se ejecuta en `http://localhost:8080`. Puedes acceder a la API a través de esta URL.

### 4. Acceder a Swagger UI
Una vez que la aplicación esté en ejecución, puedes ver la documentación de la API e interactuar con ella a través de Swagger UI:
```bash
http://localhost:8080/swagger-ui/index.html
```

### 5. Ejemplos de Solicitudes a la API
A continuación, algunos ejemplos para interactuar con la API utilizando Postman:

1. Crear un Emisor (POST /api/emisores)

**POST** `http://localhost:8080/api/emisores`
```json
{
  "denominacion": "Nombre de la organización",
  "email": "correo@ejemplo.com",
  "cuentaEmisor": "1234567890",
  "idOrganizacion": 1,
  "idEntidadLegal": 100
}
```

2. Obtener todos los emisores (GET /api/emisores)

**GET** `http://localhost:8080/api/emisores`

3. Obtener Emisor por ID (GET /api/emisores/{id})

**GET** `http://localhost:8080/api/emisores/{id}`

4. Actualizar un Emisor (PUT /api/emisores/{id})

**PUT** `http://localhost:8080/api/emisores/{id}`
```json
{
  "denominacion": "Nombre de la organización Actualizado",
  "email": "correoActualizado@ejemplo.com",
  "cuentaEmisor": "1234567890",
  "idOrganizacion": 1,
  "idEntidadLegal": 100
}
```

5. Eliminar un Emisor (DELETE /api/emisores/{id})

**DELETE** `http://localhost:8080/api/emisores/{id}`
