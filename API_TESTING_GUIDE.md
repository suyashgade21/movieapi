# API Testing Guide

This guide provides detailed examples for testing the Movie Management API.

## Quick Test Scenarios

### Scenario 1: Basic Workflow

```bash
# 1. Start the application
./run.sh

# 2. Get all movies (should see 3 pre-loaded movies)
curl http://localhost:8080/api/movies

# 3. Get a specific movie
curl http://localhost:8080/api/movies/1

# 4. Add a new movie
curl -X POST http://localhost:8080/api/movies \
  -H "Content-Type: application/json" \
  -d '{
    "name": "The Matrix",
    "description": "A hacker discovers reality is a simulation",
    "genre": "Sci-Fi",
    "releaseYear": 1999,
    "rating": 8.7
  }'

# 5. Verify the new movie was added (should see 4 movies)
curl http://localhost:8080/api/movies

# 6. Get the newly added movie (ID will be 4)
curl http://localhost:8080/api/movies/4
```

### Scenario 2: Validation Testing

#### Test 1: Missing Required Fields
```bash
curl -X POST http://localhost:8080/api/movies \
  -H "Content-Type: application/json" \
  -d '{
    "genre": "Action",
    "releaseYear": 2020
  }'

# Expected: 400 Bad Request with validation errors for name and description
```

#### Test 2: Invalid Release Year
```bash
curl -X POST http://localhost:8080/api/movies \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Future Movie",
    "description": "A movie from the future",
    "genre": "Sci-Fi",
    "releaseYear": 2150,
    "rating": 8.0
  }'

# Expected: 400 Bad Request - release year exceeds maximum
```

#### Test 3: Invalid Rating
```bash
curl -X POST http://localhost:8080/api/movies \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Bad Rating Movie",
    "description": "Testing invalid rating",
    "genre": "Drama",
    "releaseYear": 2020,
    "rating": 15.0
  }'

# Expected: 400 Bad Request - rating exceeds maximum of 10
```

#### Test 4: Negative Rating
```bash
curl -X POST http://localhost:8080/api/movies \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Negative Rating",
    "description": "Testing negative rating",
    "genre": "Horror",
    "releaseYear": 2020,
    "rating": -5.0
  }'

# Expected: 400 Bad Request - rating below minimum of 0
```

### Scenario 3: Edge Cases

#### Test 1: Movie Not Found
```bash
curl http://localhost:8080/api/movies/999

# Expected: 404 Not Found
```

#### Test 2: Minimum Valid Year
```bash
curl -X POST http://localhost:8080/api/movies \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Old Movie",
    "description": "From the early days of cinema",
    "genre": "Silent",
    "releaseYear": 1900,
    "rating": 7.0
  }'

# Expected: 201 Created - minimum year is valid
```

#### Test 3: Movie Without Rating (Optional Field)
```bash
curl -X POST http://localhost:8080/api/movies \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Unrated Movie",
    "description": "A movie without a rating",
    "genre": "Documentary",
    "releaseYear": 2023
  }'

# Expected: 201 Created - rating is optional
```

## Sample Movies to Add

Here are some sample movies you can use for testing:

### Movie 1: Action
```json
{
  "name": "Mad Max: Fury Road",
  "description": "In a post-apocalyptic wasteland, a woman rebels against a tyrannical ruler",
  "genre": "Action",
  "releaseYear": 2015,
  "rating": 8.1
}
```

### Movie 2: Comedy
```json
{
  "name": "The Grand Budapest Hotel",
  "description": "A concierge and lobby boy become friends while serving at a famous hotel",
  "genre": "Comedy",
  "releaseYear": 2014,
  "rating": 8.1
}
```

### Movie 3: Horror
```json
{
  "name": "The Conjuring",
  "description": "Paranormal investigators help a family terrorized by a dark presence",
  "genre": "Horror",
  "releaseYear": 2013,
  "rating": 7.5
}
```

### Movie 4: Romance
```json
{
  "name": "La La Land",
  "description": "A jazz pianist and an aspiring actress fall in love in Los Angeles",
  "genre": "Romance",
  "releaseYear": 2016,
  "rating": 8.0
}
```

### Movie 5: Thriller
```json
{
  "name": "Gone Girl",
  "description": "A man becomes the prime suspect in his wife's disappearance",
  "genre": "Thriller",
  "releaseYear": 2014,
  "rating": 8.1
}
```

## Testing with Different Tools

### Using Postman

1. **Import Collection:**
   - Create a new collection called "Movie API"
   - Add the base URL: `http://localhost:8080/api/movies`

2. **Create Requests:**
   - GET All Movies: `GET {{baseUrl}}`
   - GET Movie by ID: `GET {{baseUrl}}/1`
   - POST New Movie: `POST {{baseUrl}}` with JSON body

3. **Set Headers:**
   - Content-Type: `application/json`

### Using HTTP Client (IntelliJ IDEA)

Create a file `api-tests.http`:

```http
### Get all movies
GET http://localhost:8080/api/movies

### Get movie by ID
GET http://localhost:8080/api/movies/1

### Add new movie
POST http://localhost:8080/api/movies
Content-Type: application/json

{
  "name": "Parasite",
  "description": "A poor family schemes to become employees of a wealthy family",
  "genre": "Thriller",
  "releaseYear": 2019,
  "rating": 8.5
}

### Test validation - missing fields
POST http://localhost:8080/api/movies
Content-Type: application/json

{
  "genre": "Action"
}
```

## Expected Response Codes

| Scenario | HTTP Status Code | Description |
|----------|-----------------|-------------|
| Successfully added movie | 201 Created | Movie created successfully |
| Successfully retrieved movie | 200 OK | Movie found and returned |
| Movie not found | 404 Not Found | No movie with specified ID |
| Validation failed | 400 Bad Request | Input data is invalid |
| Server error | 500 Internal Server Error | Unexpected server error |

## Verification Checklist

- [ ] Application starts successfully
- [ ] GET all movies returns 3 pre-loaded movies
- [ ] GET specific movie by ID works
- [ ] POST creates a new movie with auto-generated ID
- [ ] Missing required fields returns validation errors
- [ ] Invalid rating values are rejected
- [ ] Invalid year values are rejected
- [ ] GET non-existent movie returns 404
- [ ] JSON responses are properly formatted
- [ ] All error responses include meaningful messages

## Common Issues and Solutions

### Issue: Port 8080 already in use
**Solution:** Change port in `application.properties`:
```properties
server.port=8081
```

### Issue: Maven build fails
**Solution:** Clean Maven cache:
```bash
mvn clean install -U
```

### Issue: Java version error
**Solution:** Ensure Java 17+ is installed:
```bash
java -version
```

## Performance Testing

### Load Test Example (using Apache Bench)
```bash
# Test adding movies (100 requests, 10 concurrent)
ab -n 100 -c 10 -p movie.json -T application/json http://localhost:8080/api/movies
```

Where `movie.json` contains:
```json
{
  "name": "Load Test Movie",
  "description": "Testing performance",
  "genre": "Action",
  "releaseYear": 2024,
  "rating": 7.5
}
```

Happy Testing! 🎬
