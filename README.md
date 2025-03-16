## MangaStore JEE 2025
MangaStore JEE 2025 is a modern RESTful web service developed using Jakarta EE. It manages a collection of manga and provides CRUD functionality (Create, Read, Update, Delete). It also provides the ability to filter manga based on various attributes.

## Features
CRUD Operations: Create, read, update, and delete manga entries.

Filtering: Filter manga based on title, author, and ISBN.

Validation: Data validation using Jakarta Bean Validation (e.g., ISBN format, unique title-author combination).

Error Handling: Custom exception classes and ExceptionMappers for meaningful error messages.

Containerization: Docker and Docker Compose for easy deployment.

Database Migration: Flyway for managing database schema changes.

## Technologies Used
Jakarta EE: For building RESTful APIs.

Jakarta Persistence: For database interaction.

Jakarta Bean Validation: For input validation.

Jakarta Data: For repository abstraction.

Maven: For build automation and dependency management.

Docker: For containerization.

PostgreSQL: For data storage.

Flyway: For database schema migration.

Instructions for Building and Running the Application
## Prerequisites
Docker: Ensure Docker is installed on your machine.

Docker Compose: Ensure Docker Compose is installed on your machine.

Maven: Ensure Maven is installed on your machine.

## Steps to Build and Run
#### Clone the Repository:
```bash
git clone https://github.com/your-username/mangastore-jee-2025.git
cd mangastore-jee-2025
```
#### Build the Project:
```bash
Copy
mvn clean package
```
#### Build and Start Containers with Docker Compose:

```bash
docker-compose build
docker-compose up
```
## Accessing the Application
The application will be accessible at http://localhost:8080.

## API Documentation
Endpoints
Method	Endpoint	Description
GET	/mangas	Get all manga entries.
GET	/mangas/{id}	Get a manga by ID.
GET	/mangas/isbn/{isbn}	Get a manga by ISBN.
GET	/mangas/author/{author}	Get manga by author.
POST	/mangas	Create a new manga entry.
PUT	/mangas/{id}	Update an existing manga entry.
DELETE	/mangas/{id}	Delete a manga entry by ID.
