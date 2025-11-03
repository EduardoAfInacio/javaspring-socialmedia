# javaspring-socialmedia

Example/production project that implements a social media application backend using Java with Spring Boot. The project demonstrates implementation of common social media features including user authentication, post management, and feed generation with Redis caching.

## Overview

- Goal: demonstrate Spring Boot patterns applied to a social media domain (users, posts, authentication, feed management)
- Implementation of security features including JWT authentication and password strength validation
- Asynchronous email notifications using Spring's async capabilities
- Redis integration for performance optimization through caching

## Project Structure

- `controller` — REST endpoints and DTOs for auth, posts, and user management
- `service` — Business logic implementation and integration with external services
- `repository` — Data access layer with Spring Data JPA
- `entity` — Domain entities (User, Post, Role)
- `config` — Configuration classes for security, caching, and async operations
- `Utils` — Utility classes and helpers

## Technologies

- Java 17
- Spring Boot
- Spring Security with JWT
- Spring Data JPA
- Redis for caching
- PostgreSQL
- Docker for containerization
- Maven

## Requirements

- JDK 17
- Maven
- Docker and Docker Compose (for running Redis and PostgreSQL)
- PostgreSQL database configured according to application.properties

## How to Run

1. Start the database and Redis using Docker Compose:

   ```
   cd docker
   docker-compose up -d
   ```
2. Build the project:

   ```
   ./mvnw clean install
   ```
3. Run the application:

   ```
   ./mvnw spring-boot:run
   ```
4. Run tests:

   ```
   ./mvnw test
   ```

## Features

- User registration and authentication with JWT
- Custom validations (CPF/CNPJ, password strength, date of birth)
- Post creation and management
- Feed generation with caching
- Asynchronous email notifications
- Role-based access control
- Admin user configuration

## API Endpoints

### Authentication

- POST /api/auth/register - User registration
- POST /api/auth/login - User login

### Posts

- POST /api/posts - Create new post
- GET /api/posts/feed - Get user feed
- Additional endpoints for post management

### Users

- Various endpoints for user management and profile operations

## Conventions

- RESTful API design principles
- DTO pattern for request/response handling
- Service layer for business logic
- Repository pattern for data access
- Proper exception handling and validation
