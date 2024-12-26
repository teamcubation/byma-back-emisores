FROM nexus.cajval.sba.com.ar:9005/ubi8/openjdk-21:1.18

USER root
ENV LANG='en_US.UTF-8' LANGUAGE='en_US:en'
ENV TZ=America/Argentina/Buenos_Aires

# Configurar zona horaria y actualizar paquetes
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && \
    echo $TZ > /etc/timezone && \
    /bin/microdnf update tzdata -y

# Copiar el archivo JAR con permisos
COPY --chown=185 target/emisor-*.jar /deployments/app.jar

# Exponer el puerto por defecto de Spring Boot
EXPOSE 8080

# Cambiar a un usuario no privilegiado
USER 185

# Configurar variables de entorno
ENV JAVA_OPTS="-Xms1g -Xmx8g"
ENV JAVA_APP_JAR="/deployments/app.jar"

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/deployments/app.jar"]
