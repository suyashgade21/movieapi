# Movie Management API - Project Summary

## 📦 Deliverable Contents

This submission includes a complete, production-ready Java RESTful API application with the following components:

### Core Application Files

1. **MovieApiApplication.java** - Main Spring Boot application class
2. **Movie.java** - Entity model with validation annotations
3. **MovieRepository.java** - In-memory data store using ArrayList
4. **MovieService.java** - Business logic layer
5. **MovieController.java** - REST API endpoints
6. **GlobalExceptionHandler.java** - Centralized error handling

### Configuration Files

7. **pom.xml** - Maven dependencies and build configuration
8. **application.properties** - Application settings
9. **.gitignore** - Version control exclusions

### Documentation

10. **README.md** - Comprehensive project documentation
11. **API_TESTING_GUIDE.md** - Detailed testing instructions with examples

### Helper Scripts

12. **run.sh** - Quick start script for easy execution

## ✅ Requirements Fulfilled

### 1. Item Model ✓
- **Movie.java** class with properties:
  - id (Long) - Auto-generated unique identifier
  - name (String) - Required
  - description (String) - Required
  - genre (String) - Required
  - releaseYear (Integer) - Required, validated (1900-2100)
  - rating (Double) - Optional, validated (0-10)

### 2. Data Storage ✓
- **MovieRepository.java** implements in-memory storage using ArrayList
- Thread-safe ID generation with AtomicLong
- Pre-loaded with 3 sample movies
- Support for save and findById operations

### 3. API Endpoints ✓

#### POST /api/movies
- Adds a new movie to the collection
- Returns 201 Created with the saved movie (including generated ID)
- Validates all input fields

#### GET /api/movies/{id}
- Retrieves a specific movie by ID
- Returns 200 OK with movie data if found
- Returns 404 Not Found if movie doesn't exist

#### Bonus: GET /api/movies
- Retrieves all movies in the collection
- Useful for testing and verification

### 4. Input Validation ✓
Implemented using Jakarta Bean Validation (@Valid):
- **@NotBlank** - Ensures name, description, and genre are not empty
- **@NotNull** - Ensures releaseYear is provided
- **@Min/@Max** - Validates releaseYear (1900-2100) and rating (0-10)
- Custom error messages for each validation rule
- Global exception handler returns structured error responses

### 5. Documentation ✓
- **Comprehensive README.md** with:
  - Project overview and architecture
  - Step-by-step setup instructions
  - Detailed API endpoint documentation
  - Request/response examples
  - cURL command examples
  - Configuration details
  
- **API_TESTING_GUIDE.md** with:
  - Test scenarios and workflows
  - Validation testing examples
  - Edge case testing
  - Sample data for testing
  - Troubleshooting guide

- **Inline code comments** explaining:
  - Class purposes and responsibilities
  - Method functionality
  - Important implementation details

## 🏗️ Architecture Highlights

### Layered Architecture
```
Controller → Service → Repository → Model
```
- **Controller Layer**: Handles HTTP requests/responses
- **Service Layer**: Contains business logic
- **Repository Layer**: Manages data storage
- **Model Layer**: Defines data structure and validation

### Best Practices Implemented
1. **Separation of Concerns** - Each layer has a single responsibility
2. **Dependency Injection** - Constructor-based injection for better testability
3. **RESTful Design** - Proper use of HTTP methods and status codes
4. **Input Validation** - Bean Validation for data integrity
5. **Error Handling** - Global exception handler for consistent errors
6. **Thread Safety** - AtomicLong for concurrent ID generation

## 🚀 Quick Start

```bash
# 1. Navigate to project directory
cd movie-api

# 2. Build the project
mvn clean install

# 3. Run the application
mvn spring-boot:run

# Or use the convenience script
./run.sh
```

## 📊 Testing Examples

### Add a Movie
```bash
curl -X POST http://localhost:8080/api/movies \
  -H "Content-Type: application/json" \
  -d '{
    "name": "The Dark Knight",
    "description": "Batman battles the Joker",
    "genre": "Action",
    "releaseYear": 2008,
    "rating": 9.0
  }'
```

### Get Movie by ID
```bash
curl http://localhost:8080/api/movies/1
```

### Get All Movies
```bash
curl http://localhost:8080/api/movies
```

## 📈 What's Included Beyond Requirements

1. **Global Exception Handler** - Professional error responses
2. **GET All Movies Endpoint** - Convenience endpoint for listing
3. **Sample Data** - Pre-loaded movies for immediate testing
4. **Run Script** - One-command startup
5. **Detailed Testing Guide** - Comprehensive test scenarios
6. **Production-Ready Code** - Clean, documented, and maintainable

## 🛠️ Technology Stack

- **Framework**: Spring Boot 3.2.0
- **Language**: Java 17
- **Build Tool**: Maven
- **Validation**: Jakarta Bean Validation
- **Architecture**: Layered (MVC)
- **Storage**: In-Memory (ArrayList)

## 📝 Notes

- The application runs on port 8080 by default
- Data persists only during runtime (in-memory storage)
- IDs are auto-generated starting from 1
- The application includes 3 pre-loaded sample movies
- All endpoints return JSON responses
- Validation errors include field-specific messages

## 🎯 Sample Task Completion

This project fully satisfies all requirements specified in the sample task:
- ✅ Simple Java backend application
- ✅ RESTful API implementation
- ✅ Item model with relevant fields
- ✅ In-memory ArrayList storage
- ✅ Add new item endpoint
- ✅ Get item by ID endpoint
- ✅ Input validation with constraints
- ✅ Comprehensive documentation

Thank you for reviewing this submission!
