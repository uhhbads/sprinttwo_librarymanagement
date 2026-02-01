# SPRINT TWO - Library Management System API
## Project Duration: February 1, 2026
### Overview
The Library Management System is a robust RESTful API built to demonstrate the power of Spring Data JPA and Object-Relational Mapping (ORM). Unlike the previous in-memory system, this project persists data using an H2 SQL database, managing complex relationships between Authors, Books, and Publishers.

This project was built to master database interactions, moving beyond simple CRUD operations to handle relational data integrity, custom queries, and the Data Transfer Object (DTO) pattern for secure API communication.

### Concepts & Architecture
This project expands on previous architectural patterns while introducing database persistence and relationship mapping:

#### 1. JPA & Object-Relational Mapping (ORM)
The application utilizes Hibernate (via Spring Data JPA) to map Java objects directly to database tables:
```
a. Entities (@Entity): Java classes representing database tables (Authors, Books).
b. JpaRepository: Replaced standard list manipulation with powerful interfaces that provide out-of-the-box SQL operations.
c. H2 Database: Configured an in-memory SQL database for rapid development and testing via the H2 Console.
```
#### 2. Database Relationships & Fetching
```
a. @OneToMany & @ManyToOne: Established the relationship where one Author can have multiple Books, but a Book belongs to one Author.
b. @ManyToMany: Implemented a bonus feature connecting Books to Publishers.
c. Cascading & Fetch Types: Configured CascadeType.ALL for parent-child lifecycle management and utilized FetchType.LAZY to optimize database performance.
```
#### 3. DTO Pattern (Data Transfer Objects)
To decouple the internal database entities from the external API layer:
```
a. Request DTOs: Used to capture input data (e.g., BookRequest) containing IDs rather than full objects.
b. Response DTOs: Used to return shaped data (e.g., BookResponse) to the client, flattening relationships (e.g., returning "Author Name" string instead of a full Author object).
```
#### 4. Advanced Validation & Querying
```
a. Jakarta Validation: Implemented @NotBlank, @NotNull, and @Pattern (for ISBN regex) to ensure data integrity before reaching the service layer.
b. Global Exception Handling: Centralized error handling to return clean 400 Bad Request responses for validation failures.
c. Pagination & Custom Queries: Added support for paginated results (Pageable) and derived queries (findBooksByTitleContaining).
```
### Tech Stack
Language: Java 21

Framework: Spring Boot 3.2+ (Web, Data JPA, Validation)

Database: H2 (In-Memory SQL)

Build Tool: Maven

Utilities: Lombok

Testing: Postman/Insomnia & H2 Console
