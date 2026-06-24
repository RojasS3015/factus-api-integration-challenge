# FactusPro – Sistema de Gestión Comercial e Integración con Factus

## Descripción

FactusPro es una plataforma de gestión comercial desarrollada con el propósito de centralizar los procesos de administración de clientes, productos, inventario, ventas y facturación electrónica.

El sistema integra los servicios de la API de Factus para la generación y emisión de facturas electrónicas, permitiendo automatizar el proceso de facturación y mantener la trazabilidad de las operaciones comerciales.

La solución fue desarrollada siguiendo principios de arquitectura limpia, separación de responsabilidades y buenas prácticas de desarrollo de software, con el objetivo de ofrecer una plataforma escalable, mantenible y orientada a procesos empresariales reales.

---

## Características Principales

* Gestión de clientes.
* Gestión de productos.
* Control de inventario.
* Registro y administración de pedidos.
* Gestión de pagos.
* Generación de facturas electrónicas.
* Integración con la API Factus.
* Consulta de CUFE.
* Generación de documentos PDF.
* Dashboard con indicadores comerciales.
* Persistencia de información mediante PostgreSQL.
* API REST desarrollada con Spring Boot.

---

## Arquitectura del Sistema

La solución está compuesta por una arquitectura distribuida basada en frontend, backend y base de datos.

### Frontend

* React
* TypeScript
* Tailwind CSS
* Vercel

### Backend

* Java 21
* Spring Boot
* Spring Security
* Spring Data JPA
* JWT Authentication
* WebClient para consumo de APIs externas

### Base de Datos

* PostgreSQL
* Neon Database

### Servicios Externos

* API Factus
* Servicios de facturación electrónica
* Validación y emisión de documentos electrónicos

---

## Flujo de Negocio

El sistema implementa un flujo de facturación basado en estados operativos.

```text
Cliente
↓
Pedido
↓
Pago
↓
Factura Electrónica
↓
Factus
↓
Documento Emitido
```

Estados de la operación:

```text
CREADO
↓
PAGADO
↓
EMITIDO
```

Este enfoque permite separar el proceso comercial del proceso de facturación electrónica, brindando mayor control sobre las ventas antes de realizar la emisión definitiva del documento fiscal.

---

## Funcionalidades

### Gestión de Clientes

Permite registrar, consultar, editar y administrar la información de clientes.

### Gestión de Productos

Permite administrar el catálogo de productos y controlar existencias disponibles.

### Gestión de Inventario

Control automático del stock disponible para cada producto registrado.

### Gestión de Pedidos

Creación y seguimiento de pedidos asociados a clientes.

### Gestión de Pagos

Registro y validación de pagos realizados por los clientes.

### Facturación Electrónica

Generación de facturas electrónicas mediante integración con Factus.

### Consulta de Facturas

Permite visualizar:

* Estado de la factura.
* CUFE generado.
* PDF asociado.
* URL pública del documento.

---

## Requisitos

Antes de ejecutar el proyecto es necesario contar con:

* Java 21 o superior.
* Maven 3.9 o superior.
* PostgreSQL.
* Cuenta de Factus para pruebas.
* Git.

---

## Instalación

### Clonar el repositorio

```bash
git clone https://github.com/RojasS3015/factus-api-integration-challenge.git
```

### Ingresar al proyecto

```bash
cd factus-api-integration-challenge
```

### Compilar

```bash
mvn clean install
```

### Ejecutar

```bash
mvn spring-boot:run
```

---

## Variables de Entorno

Configurar las siguientes variables:

```properties
DB_URL=
DB_USERNAME=
DB_PASSWORD=

FACTUS_CLIENT_ID=
FACTUS_CLIENT_SECRET=

JWT_SECRET=
```

Las credenciales utilizadas en producción o pruebas no deben ser almacenadas dentro del repositorio.

---

## Estructura General del Proyecto

```text
src
├── controller
├── service
├── repository
├── entity
├── dto
├── mapper
├── security
├── config
└── integration
```

### Descripción

* controller: Exposición de endpoints REST.
* service: Lógica de negocio.
* repository: Acceso a datos.
* entity: Modelado de entidades.
* dto: Objetos de transferencia.
* mapper: Conversión entre entidades y DTOs.
* security: Configuración de autenticación.
* config: Configuración general.
* integration: Comunicación con Factus.

---

## Despliegue

### Frontend

Desplegado en Vercel.

### Backend

Desplegado en Render.

### Base de Datos

PostgreSQL desplegado en Neon.

---

## Documentación de Usuario

El proyecto cuenta con una guía de usuario desarrollada como parte del proceso de documentación académica.

La guía describe paso a paso el proceso de:

* Gestión de clientes.
* Gestión de inventario.
* Creación de pedidos.
* Registro de pagos.
* Emisión de facturas electrónicas.
* Consulta de documentos emitidos.

---

## Buenas Prácticas Aplicadas

* Arquitectura por capas.
* Principios SOLID.
* Clean Code.
* Separación de responsabilidades.
* DTO Pattern.
* Repository Pattern.
* Manejo centralizado de excepciones.
* Integración desacoplada con servicios externos.

---

## Estado del Proyecto

Proyecto funcional y en evolución continua.

Actualmente se encuentra en proceso de mejora de funcionalidades relacionadas con:

* Reportes comerciales.
* Optimización de procesos de facturación.
* Mejoras en la experiencia de usuario.
* Ampliación de cobertura de pruebas.

---

## Autor

Sebastián Rojas Rosero

Fundación Politécnico Minuto de Dios

Tecnología en Desarrollo de Software

---

## Licencia

Proyecto desarrollado con fines académicos y educativos.
