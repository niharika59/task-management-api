# Task Management API

This is a RESTful API for a Task Management application, built with Java and Spring Boot.

## Features

*   Create, Read, Update, and Delete tasks.
*   Filter tasks by status.
*   Pagination support for task lists.
*   API documentation with Swagger UI.

## Prerequisites

*   Java 21
*   Maven 3.x

## How to Build and Run

1.  **Clone the repository:**
    ```bash
    git clone <repository-url>
    ```
2.  **Navigate to the project directory:**
    ```bash
    cd TaskManagement
    ```
3.  **Build the project:**
    ```bash
    ./mvnw clean package
    ```
4.  **Run the application:**
    ```bash
    java -jar target/TaskManagement-0.0.1-SNAPSHOT.jar
    ```

The application will start on port `8080`.

## API Endpoints

The following endpoints are available:

| Method | Endpoint              | Description              |
|--------|-----------------------|--------------------------|
| `POST` | `/api/v1/tasks`       | Create a new task.       |
| `GET`  | `/api/v1/tasks/{id}`  | Get a task by its ID.    |
| `GET`  | `/api/v1/tasks`       | Get a list of all tasks. |
| `PUT`  | `/api/v1/tasks/{id}`  | Update an existing task. |
| `DELETE`| `/api/v1/tasks/{id}`  | Delete a task.           |

### Filtering by Status

You can filter the tasks by status by adding a `status` query parameter to the `GET /api/v1/tasks` endpoint. The possible values for `status` are:

*   `TO_DO`
*   `IN_PROGRESS`
*   `DONE`

**Example:**

```
GET /api/v1/tasks?status=IN_PROGRESS
```

## API Documentation

This project uses Springdoc OpenAPI to generate API documentation. Once the application is running, you can access the Swagger UI at the following URL:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

This interface provides detailed information about the API endpoints and allows you to interact with them directly.
=======
A simple, clean, and production-ready RESTful API built using **Java**, **Spring Boot**, **Spring Data JPA**, and **H2 Database**.  
This project demonstrates API design, DTO usage, validation, global exception handling, pagination, sorting, rate limiting, and Dockerization.

---

## 🚀 Features

- Create, update, delete, and retrieve tasks  
- Request validation (`@Valid`, `@NotBlank`, etc.)
- Global exception handling with structured error responses  
- Pagination, filtering, and sorting support  
- H2 in-memory DB for development  
- Swagger UI for API documentation  
- Dockerized application for easy deployment  

---

## 🛠 Tech Stack

| Layer / Component   | Technology |
|---------------------|------------|
| Language            | Java 25 |
| Framework           | Spring Boot |
| Build Tool          | Maven |
| Database            | H2 (in-memory) |
| ORM                 | Spring Data JPA (Hibernate) |
| API Docs            | Swagger (Springdoc OpenAPI) |
| Validation          | Spring Boot Starter Validation |
| Containerization    | Docker |

---

## 📁 Project Structure

## Build
mvn clean install

## Run 
mvn spring-boot:run 

## Running Tests
mvn test


>>>>>>> 011e78ccd2dcbefea087b1cd3bbae4b80e6bc979
