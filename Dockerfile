# --- Etapa de Compilación ---
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copiar archivos de dependencias para aprovechar la caché de Docker
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar código fuente y empaquetar
COPY src ./src
RUN mvn clean package -DskipTests

# --- Etapa de Ejecución ---
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Crear un usuario y grupo sin privilegios por seguridad
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copiar el JAR generado desde la etapa de compilación
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto estándar del microservicio
EXPOSE 8080

# Comando de ejecución con optimizaciones JVM básicas para contenedores
ENTRYPOINT ["java", "-jar", "app.jar"]