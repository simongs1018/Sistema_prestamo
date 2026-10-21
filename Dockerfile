# Etapa 1: Compilar la aplicación Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests || mvn clean package -DskipTests

# Etapa 2: Ejecutar la aplicación de forma segura
FROM eclipse-temurin:21-jre
WORKDIR /app

# Crear un usuario no privilegiado para cumplir con las normas de seguridad
RUN useradd -u 1000 appuser && chown -R appuser:appuser /app
USER appuser

COPY --from=build --chown=appuser:appuser /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]