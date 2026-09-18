# Microservices-Architechture
Spring Boot and GraphQL based Microservices Architecture — independent services, GraphQL API Gateway

## 📌 Summary

Adds complete JWT-based authentication to the GraphQL API — user registration, login, stateless security, and role-based access control.

## ✨ What's New

### Authentication
- `register` mutation — creates a user, hashes password with BCrypt, returns JWT
- `login` mutation — verifies credentials via `AuthenticationManager`, returns JWT
- `AuthPayload` type — `{ token, user }` returned by both mutations
- `AuthInput` DTO — `{ email, password }`

### Security
- `JWTService` — signs/verifies tokens with HS256
- `JwtAutFilter` — validates `Authorization: Bearer <token>` on every request, populates `SecurityContext`
- `CustomUserDetailService` — loads user by email for Spring Security
- `SecurityConfig` — stateless sessions, CSRF disabled, form login disabled, `/graphql` and `/graphiql` permitted
- `@PreAuthorize` on resolvers:
  - `allUsers`, `getUser` → authenticated users only
  - `deleteUser` → `ADMIN` role only

### Model & Data
- Added `UserRole` enum (`USER`, `ADMIN`)
- `User` entity: unique email, BCrypt-hashed password, role, auto-managed `createdAt` / `updatedAt` via `@PrePersist` / `@PreUpdate`
- `UserRepository` — `findByEmail`, `existsByEmail`

### Config
- `application.properties` — MySQL datasource, GraphQL path + GraphiQL endpoint, JWT secret & expiration
- `AppConfig` — `PasswordEncoder` (BCrypt) and `AuthenticationManager` beans

## 🔧 Fixes Along The Way
- Corrected Spring Data JPA method name (`existsByEmail`)
- Removed unused imports and a duplicate `application.properties`
- Enabled GraphiQL UI at `/graphiql`
- Fixed JWT filter chain (`filterChain.doFilter` was skipped on valid tokens)

## 🧪 How to Test

1. Start the app on port `8081`
2. Open GraphiQL → `http://localhost:8081/graphiql`

**Register:**
```graphql
mutation {
  register(input: {
    name: "Alice"
    email: "alice@test.com"
    password: "secret123"
    address: "NY"
  }) {
    token
    user { id email role }
  }
}