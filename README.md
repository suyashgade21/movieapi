# Movie Management API

A RESTful API for managing a collection of movies, built with Java and Spring Boot.

## 📋 Project Overview

This application demonstrates a simple backend REST API that allows users to:
- Add new movies to the collection
- Retrieve a specific movie by its ID
- List all movies in the collection

The data is stored in-memory using an ArrayList, and the application includes input validation to ensure data integrity.

## 🛠️ Technologies Used

- **Java 17**
- **Spring Boot 3.2.0**
- **Maven** - Build tool
- **Spring Web** - REST API framework
- **Spring Validation** - Input validation
- **In-Memory Storage** - ArrayList-based data store

## 📁 Project Structure

```
movie-api/
├── src/
│   ├── main/
│   │   ├── java/com/example/movieapi/
│   │   │   ├── MovieApiApplication.java       # Main application class
│   │   │   ├── controller/
│   │   │   │   └── MovieController.java       # REST endpoints
│   │   │   ├── model/
│   │   │   │   └── Movie.java                 # Movie entity with validation
│   │   │   ├── repository/
│   │   │   │   └── MovieRepository.java       # In-memory data store
│   │   │   ├── service/
│   │   │   │   └── MovieService.java          # Business logic
│   │   │   └── exception/
│   │   │       └── GlobalExceptionHandler.java # Error handling
│   │   └── resources/
│   │       └── application.properties          # Configuration
├── pom.xml                                     # Maven dependencies
└── README.md                                   # This file
```

## 🚀 How to Run the Application

### Prerequisites

- Java 17 or higher installed
- Maven 3.6+ installed (or use the Maven wrapper included)

### Steps to Run

1. **Navigate to the project directory:**
   ```bash
   cd movie-api
   ```

2. **Build the project:**
   ```bash
   mvn clean install
   ```

3. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```
   
   Or run the JAR directly:
   ```bash
   java -jar target/movie-api-1.0.0.jar
   ```

4. **The application will start on port 8080:**
   ```
   Server running at: http://localhost:8080
   ```

## 📡 API Endpoints

### Base URL
```
http://localhost:8080/api/movies
```

### 1. Add a New Movie
**Endpoint:** `POST /api/movies`

**Description:** Adds a new movie to the collection

**Request Body:**
```json
{
  "name": "The Dark Knight",
  "description": "Batman battles the Joker in Gotham City",
  "genre": "Action",
  "releaseYear": 2008,
  "rating": 9.0
}
```

**Success Response (201 Created):**
```json
{
  "id": 4,
  "name": "The Dark Knight",
  "description": "Batman battles the Joker in Gotham City",
  "genre": "Action",
  "releaseYear": 2008,
  "rating": 9.0
}
```

**Validation Error Response (400 Bad Request):**
```json
{
  "timestamp": "2026-02-08T10:30:00",
  "status": 400,
  "error": "Validation Failed",
  "validationErrors": {
    "name": "Movie name is required",
    "releaseYear": "Release year must be after 1900"
  }
}
```

**cURL Example:**
```bash
curl -X POST http://localhost:8080/api/movies \
  -H "Content-Type: application/json" \
  -d '{
    "name": "The Dark Knight",
    "description": "Batman battles the Joker in Gotham City",
    "genre": "Action",
    "releaseYear": 2008,
    "rating": 9.0
  }'
```

---

### 2. Get a Movie by ID
**Endpoint:** `GET /api/movies/{id}`

**Description:** Retrieves a specific movie by its ID

**Success Response (200 OK):**
```json
{
  "id": 1,
  "name": "The Shawshank Redemption",
  "description": "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
  "genre": "Drama",
  "releaseYear": 1994,
  "rating": 9.3
}
```

**Not Found Response (404 Not Found):**
```
(Empty response body with 404 status)
```

**cURL Example:**
```bash
curl http://localhost:8080/api/movies/1
```

---

### 3. Get All Movies (Bonus)
**Endpoint:** `GET /api/movies`

**Description:** Retrieves all movies in the collection

**Success Response (200 OK):**
```json
[
  {
    "id": 1,
    "name": "The Shawshank Redemption",
    "description": "Two imprisoned men bond over a number of years...",
    "genre": "Drama",
    "releaseYear": 1994,
    "rating": 9.3
  },
  {
    "id": 2,
    "name": "The Godfather",
    "description": "The aging patriarch of an organized crime dynasty...",
    "genre": "Crime",
    "releaseYear": 1972,
    "rating": 9.2
  }
]
```

**cURL Example:**
```bash
curl http://localhost:8080/api/movies
```

## ✅ Input Validation

The application validates all input data using Jakarta Bean Validation annotations:

| Field | Validation Rules |
|-------|-----------------|
| `name` | Required, cannot be blank |
| `description` | Required, cannot be blank |
| `genre` | Required, cannot be blank |
| `releaseYear` | Required, must be between 1900 and 2100 |
| `rating` | Optional, must be between 0 and 10 if provided |

If validation fails, the API returns a `400 Bad Request` response with detailed error messages for each invalid field.

## 🧪 Testing the API

### Using cURL (Command Line)

```bash
# Add a new movie
curl -X POST http://localhost:8080/api/movies \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Interstellar",
    "description": "A team of explorers travel through a wormhole in space",
    "genre": "Sci-Fi",
    "releaseYear": 2014,
    "rating": 8.6
  }'

# Get movie by ID
curl http://localhost:8080/api/movies/1

# Get all movies
curl http://localhost:8080/api/movies
```

### Using Postman or Similar Tools

1. Import the endpoints into your API testing tool
2. Set the request type (POST/GET)
3. Add the appropriate headers: `Content-Type: application/json`
4. Send the requests and view responses

## 🔑 Key Implementation Details

### 1. **In-Memory Data Storage**
- Uses `ArrayList<Movie>` to store movies
- Data persists only during the application runtime
- Sample data is initialized when the application starts

### 2. **ID Generation**
- Uses `AtomicLong` for thread-safe auto-incrementing IDs
- IDs start from 1 and increment with each new movie

### 3. **Layered Architecture**
- **Controller Layer:** Handles HTTP requests/responses
- **Service Layer:** Contains business logic
- **Repository Layer:** Manages data storage and retrieval
- **Model Layer:** Defines data structure with validation

### 4. **Error Handling**
- Global exception handler for consistent error responses
- Validation errors return field-specific messages
- HTTP status codes follow REST conventions

### 5. **RESTful Design**
- Uses proper HTTP methods (POST, GET)
- Returns appropriate HTTP status codes
- Follows REST naming conventions

## 📝 Sample Data

The application comes pre-loaded with 3 sample movies:

1. **The Shawshank Redemption** (1994) - Drama - Rating: 9.3
2. **The Godfather** (1972) - Crime - Rating: 9.2
3. **Inception** (2010) - Sci-Fi - Rating: 8.8

## 🔧 Configuration

You can modify the application behavior in `application.properties`:

- **Server Port:** Default is 8080
- **Logging Level:** Set to DEBUG for detailed logs
- **JSON Formatting:** Pretty-print enabled for readable responses

## 📌 Future Enhancements

Possible improvements for production use:
- Add UPDATE and DELETE endpoints
- Implement search and filtering
- Add pagination for large datasets
- Use a real database (PostgreSQL, MySQL)
- Add authentication and authorization
- Implement unit and integration tests
- Add API documentation with Swagger/OpenAPI

## 👨‍💻 Author

suyash gade

## 📄 License

This is a sample project for demonstration purposes.
