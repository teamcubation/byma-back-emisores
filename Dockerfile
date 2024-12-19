# Etapa 1: Construcción
FROM maven:3.9.5-eclipse-temurin-21 AS builder

# Establecer directorio de trabajo
WORKDIR /app

# Copiar archivos del proyecto
COPY pom.xml .
COPY src ./src

# Construir el proyecto
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución
FROM eclipse-temurin:21-jre

# Establecer directorio de trabajo
WORKDIR /app

# Copiar el archivo JAR desde la etapa de construcción
COPY --from=builder /app/target/emisor-*.jar app.jar

# Exponer el puerto en el que corre Spring Boot (por defecto es 8080)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
