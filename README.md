# Customer Onboarding Service

Customer Onboarding Service is a Spring Boot microservice responsible for customer onboarding operations with support for both **RDBMS (Oracle 21c preferred)** and **MongoDB** persistence.

The service supports CRUD operations, authentication & authorization using JWT, Redis caching, distributed tracing, and Docker deployment.

---

# Tech Stack

## Backend

- Java 21
- Spring Boot 3.x
- Spring Security
- Spring Data JPA
- Spring Data MongoDB
- JWT Authentication
- Redis
- MDC Distributed Tracing

## Databases

### RDBMS

- Oracle 21c (Preferred)

### NoSQL

- MongoDB

---

# Features

## Customer Onboarding

Supports complete CRUD operations:

- Create Customer
- Update Customer
- Delete Customer
- Get All Customers
- Get Customer by Customer Id

---

## Authentication & Authorization

Authentication flow:

User Login
↓
Validate Username + Password
↓
Password stored encrypted in DB
↓
Decrypt / Validate Credentials
↓
Generate JWT Token
↓
Generate Refresh Token
↓
Pass token to downstream services

Features:

- JWT Access Token
- Refresh Token
- Secure APIs
- Database-backed authentication
- Password encryption
- Role-based access (optional extension)

---

## Redis Integration

Redis is used for caching strategy.

Caching use cases:

- Customer details
- Frequently accessed records
- Session information
- Token metadata

Example flow:

Client Request
↓
Redis Cache Lookup
↓
Cache Hit → Return Data
↓
Cache Miss
↓
Database Fetch
↓
Store in Redis
↓
Return Response

---

## Distributed Tracing

Distributed tracing implemented using MDC.

Trace information:

- Trace Id
- Request Id
- Correlation Id
- Service Name

Flow:

Gateway
↓
Onboarding Service
↓
Downstream Service

Common trace logging:

```java
MDC.put("traceId", traceId);
MDC.put("customerId", customerId);
```

---

# Project Structure

```text
customer-onboarding-service
│
├── controller
│     └── OnboardingController
│
├── service
│     └── OnboardingService
│
├── repository
│     ├── OracleRepository
│     └── MongoRepository
│
├── entity
│
├── dto
│
├── security
│     ├── JwtFilter
│     ├── JwtUtil
│     ├── SecurityConfig
│     └── AuthenticationProvider
│
├── cache
│     └── RedisConfiguration
│
├── tracing
│     └── MDCConfiguration
│
├── utilities
│
└── configuration
```

---

# Layer Responsibilities

## Controller Layer

Responsible for:

- REST endpoint exposure
- Request handling
- Input validation
- Response generation

Controller:

```java
@RestController
@RequestMapping("/api/onboarding")
public class OnboardingController
```

Endpoints:

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | /customer | Create customer |
| PUT | /customer/{custId} | Update customer |
| DELETE | /customer/{custId} | Delete customer |
| GET | /customer | Get all customers |
| GET | /customer/{custId} | Get customer by id |

---

## Service Layer

Responsibilities:

- Business validations
- Customer processing
- Persistence logic
- Token generation
- Cache coordination

Example:

```java
@Service
public class OnboardingService
```

Validations:

- Mandatory fields
- Duplicate customer check
- Data integrity validation
- Authentication checks

---

## Repository Layer

Supports dual persistence:

### Oracle Repository

```java
public interface CustomerJpaRepository
extends JpaRepository<CustomerEntity,Long>
```

Responsibilities:

- CRUD operations
- Transaction management
- Custom queries

---

### Mongo Repository

```java
public interface CustomerMongoRepository
extends MongoRepository<CustomerDocument,String>
```

Responsibilities:

- Document persistence
- Audit storage
- Event data storage

---

## Utility Layer

Responsibilities:

- Encryption utilities
- JWT utilities
- Common validators
- MDC helpers
- Mapping utilities

Examples:

```java
EncryptionUtil
JwtUtil
ValidationUtil
TraceUtil
CommonMapper
```

---

# Authentication Flow

```text
Client Login
      ↓
Authentication API
      ↓
User lookup from DB
      ↓
Encrypted password validation
      ↓
JWT creation
      ↓
Refresh token generation
      ↓
Store metadata in Redis
      ↓
Return token response
```

---

# Database Architecture

```text
Application
     ↓
Service Layer
     ↓
-------------------------
| Oracle 21c (Primary) |
-------------------------
            +
-------------------------
| MongoDB (Audit/Data) |
-------------------------
```

Oracle:

- Customer Master
- User Credentials
- Transaction Records

Mongo:

- Audit Documents
- Request Logs
- Event Tracking

---

# API Flow

```text
Client
   ↓
Controller
   ↓
Validation
   ↓
Service Layer
   ↓
Redis Cache
   ↓
Repository Layer
   ↓
Oracle / MongoDB
```

---

# Security

Implemented security:

- Spring Security
- JWT Authentication
- Refresh Tokens
- Password Encryption
- Stateless Authentication
- Secure API access

---

# Docker Support

Build image:

```bash
docker build -t onboarding-service .
```

Run container:

```bash
docker run -p 8080:8080 onboarding-service
```

Docker Compose can be extended for:

- Oracle DB
- MongoDB
- Redis

---

# Configuration

application.yml

```yaml
spring:
  datasource:
    url:
    username:
    password:

  data:
    mongodb:
      uri:

  redis:
    host:
    port:
```

---

# Future Enhancements

- API Gateway integration
- Kafka event publishing
- Circuit Breaker
- Retry Pattern
- Rate Limiting
- OpenTelemetry
- ELK Stack logging
- Kubernetes deployment

---

# Monitoring & Logging

Logging includes:

- MDC tracing
- Correlation IDs
- Request tracking
- Service tracing
- Error tracing

---

# Swagger

Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

OpenAPI Docs:

```text
http://localhost:8080/v3/api-docs
```