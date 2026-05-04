FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copiamos los archivos necesarios para construir
COPY . .

# Damos permisos y construimos el JAR
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# IMPORTANTE: El nombre del JAR debe coincidir con el artifactId y version del pom.xml
CMD ["java", "-jar", "target/demo-1.1.0.jar"]