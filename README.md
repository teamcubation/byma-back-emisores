# byma-back-emisores

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


*****SUSCRIPCION*****

1. Crear una Suscripción (POST /api/v1/suscripcion)
   POST http://localhost:10001/api/v1/suscripcion



```json{
"estado": "ACTIVO",
"nroCertificado": 12345,
"idEspecie": 1,
"cantCuotapartes": 100,
"idAcdi": 50,
"idEmisor": 10,
"nroPedido": 7890,
"nroSecuencia": 5,
"fechaCambioDeEstado": "2024-12-02",
"rolIngresante": "ADMIN",
"monto": 10000,
"liquidaEnByma": true,
"numeroReferencia": 98765,
"procesadoCustodia": false,
"ultimoError": "",
"command": "CREATE",
"procesadoLiquidacionesSlyq": false,
"idGerente": 7,
"obligacionDePagoGenerada": true,
"idBilletera": 101,
"fechaSincronizacion": "2024-12-03T15:30:00",
"nasdaqSiStatusReason": "",
"mdwStatusCode": 200,
"mdwBusinessMessageId": "ABC123",
"mdwResponseMessage": "OK",
"mdwResponseDatetime": "2024-12-03T15:30:00",
"nasdaqSiStatus": "COMPLETED"
}
```
2. Obtener todas las Suscripciones (GET /api/v1/suscripcion)
   GET http://localhost:10001/api/v1/suscripcion

3. Obtener Suscripción por ID (GET /api/v1/suscripcion/{id})
   GET http://localhost:10001/api/v1/suscripcion/{id}

4. Actualizar una Suscripción (PUT /api/v1/suscripciones/{id})
   PUT http://localhost:10001/api/v1/suscripciones/{id}


```json{
"estado": "INACTIVO",
"nroCertificado": 12345,
"idEspecie": 1,
"cantCuotapartes": 50,
"idAcdi": 50,
"idEmisor": 20,
"nroPedido": 7891,
"nroSecuencia": 6,
"fechaCambioDeEstado": "2024-12-02",
"rolIngresante": "USUARIO",
"monto": 5000,
"liquidaEnByma": false,
"numeroReferencia": 98766,
"procesadoCustodia": true,
"ultimoError": "Error desconocido",
"command": "UPDATE",
"procesadoLiquidacionesSlyq": true,
"idGerente": 8,
"obligacionDePagoGenerada": false,
"idBilletera": 102,
"fechaSincronizacion": "2024-12-03T16:00:00",
"nasdaqSiStatusReason": "Pendiente de confirmación",
"mdwStatusCode": 400,
"mdwBusinessMessageId": "DEF456",
"mdwResponseMessage": "Error",
"mdwResponseDatetime": "2024-12-03T16:00:00",
"nasdaqSiStatus": "FAILED"
}
```
5. Eliminar una Suscripción (DELETE /api/v1/suscripcion/{id})
   DELETE http://localhost:10001/api/v1/suscripcion/{id}