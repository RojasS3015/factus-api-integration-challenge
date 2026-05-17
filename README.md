# API Factus — Integración Spring Boot

Servicio reactivo (WebFlux) que actúa como **adaptador/BFF** sobre la [API de Factus](https://api-sandbox.factus.com.co): autenticación OAuth, facturas, catálogos y manejo unificado de errores.

**Versión:** `1.1.0` · **Java:** 17 · **Spring Boot:** 3.4.3

## Requisitos

- JDK 17+
- Maven 3.9+ (o usar `./mvnw` incluido en el proyecto)

## Configuración

1. Copia las variables de entorno desde el ejemplo:

   ```bash
   cp .env.example .env
   ```

   En PowerShell:

   ```powershell
   Copy-Item .env.example .env
   ```

2. Edita `.env` con tus credenciales del sandbox de Factus.

| Variable | Descripción |
|----------|-------------|
| `API_PASSWORD` | Contraseña del usuario sandbox |
| `API_CLIENT_ID` | Client ID OAuth |
| `API_CLIENT_SECRET` | Client secret OAuth |
| `PORT` | Puerto HTTP (por defecto `8080`) |

La URL base del sandbox y el email por defecto están en `src/main/resources/application.properties`.

## Ejecución

```bash
# Linux / macOS
export $(grep -v '^#' .env | xargs)
./mvnw spring-boot:run

# Windows PowerShell (ejemplo)
$env:API_PASSWORD="..."
$env:API_CLIENT_ID="..."
$env:API_CLIENT_SECRET="..."
.\mvnw.cmd spring-boot:run
```

## Endpoints útiles

| Recurso | URL |
|---------|-----|
| Swagger UI | http://localhost:8080/swagger-ui.html |
| OpenAPI JSON | http://localhost:8080/v3/api-docs |
| Health | http://localhost:8080/actuator/health |
| Info (versión) | http://localhost:8080/actuator/info |

## API propia (resumen)

| Método | Ruta | Descripción |
|--------|------|-------------|
| `POST` | `/api/facturas` | Crear / validar factura |
| `GET` | `/api/facturas` | Listar con filtros opcionales |
| `DELETE` | `/api/facturas/{referenceCode}` | Eliminar por código de referencia |
| `GET` | `/api/catalogos/*` | Municipios, tributos, países, unidades |
| `GET` | `/api/auth/...` | Login / refresh manual (sandbox) |

El `WebClient` obtiene y reutiliza el token OAuth automáticamente en las llamadas a Factus.

## Estructura del proyecto

```
src/main/java/com/factus/api/
├── presentation/     # Controllers y DTOs HTTP
├── app/              # Casos de uso y AuthService
├── domain/models/    # Modelos de respuesta alineados con Factus
└── infrastructure/   # WebClient, config, excepciones, token
```

No es un dominio rico con entidades propias: es una **capa de integración** con casos de uso que orquestan el cliente HTTP.

## Tests

```bash
./mvnw test
```

## Docker

```bash
docker build -t factus-api:1.1.0 .
docker run -p 8080:8080 \
  -e API_PASSWORD=... \
  -e API_CLIENT_ID=... \
  -e API_CLIENT_SECRET=... \
  factus-api:1.1.0
```

## Logs

Los logs se escriben en `logs/factus-api.log` (carpeta ignorada por git).

## Licencia

Proyecto de integración / portfolio. Ajusta según tu uso.
