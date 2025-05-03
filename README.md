# Project
- Business: Patient management
- Technology:
  - Java 21.
  - Spring Boot (3.x) + MySQL (8.x)
  - Maven (3.x)

# Requirement
- Docker
- JDK 21 (OpenJDK Temurin 21 is best)

# Run
1. Create MySQL image database
```shell
docker compose up -d
```
2. Compile source code
```shell
./mvnw clean compile
```
3. Run in development environment
```shell
./mvnw spring-boot:run
```
4. Open OpenAPI UI in your browser: http://localhost:4897/api/swagger-ui/index.html
