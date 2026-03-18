# ── STAGE 1 : build ───────────────────────────────────────────
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /src

# Copier pom.xml en premier pour profiter du cache Maven
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copier le code source et compiler
COPY src ./src
RUN mvn package -B

# ── STAGE 2 : image de production ─────────────────────────────
# JAR exécutable Spring Boot — pas besoin de Tomcat externe
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

COPY --from=build /src/target/boutique.jar app.jar

EXPOSE 8080

# Spring Boot démarre sur le port 8080
# /actuator/health  → health check
# /actuator/prometheus → métriques Prometheus
# /api/articles     → liste des articles
ENTRYPOINT ["java", "-jar", "app.jar"]
