# Quick Reference & Troubleshooting

## ✅ Correct URLs to Access

### Root URL (Welcome Page)
```
http://localhost:8080/
```
Returns API information and available endpoints.

### API Endpoints

1. **Get All Movies**
   ```
   GET http://localhost:8080/api/movies
   ```

2. **Get Movie by ID**
   ```
   GET http://localhost:8080/api/movies/1
   GET http://localhost:8080/api/movies/2
   GET http://localhost:8080/api/movies/3
   ```

3. **Add New Movie**
   ```
   POST http://localhost:8080/api/movies
   Content-Type: application/json
   
   Body:
   {
     "name": "The Dark Knight",
     "description": "Batman battles the Joker",
     "genre": "Action",
     "releaseYear": 2008,
     "rating": 9.0
   }
   ```

## 🚀 Quick Test Commands

### Using Browser
Simply paste these URLs in your browser:
- `http://localhost:8080/` - Welcome page
- `http://localhost:8080/api/movies` - Get all movies
- `http://localhost:8080/api/movies/1` - Get first movie

### Using cURL (Command Line)

```bash
# Get all movies
curl http://localhost:8080/api/movies

# Get specific movie
curl http://localhost:8080/api/movies/1

# Add new movie
curl -X POST http://localhost:8080/api/movies \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Inception",
    "description": "A thief enters dreams to steal secrets",
    "genre": "Sci-Fi",
    "releaseYear": 2010,
    "rating": 8.8
  }'
```

### Using Postman

1. **GET Request:**
   - Method: GET
   - URL: `http://localhost:8080/api/movies`
   - Click Send

2. **POST Request:**
   - Method: POST
   - URL: `http://localhost:8080/api/movies`
   - Headers: `Content-Type: application/json`
   - Body → raw → JSON:
   ```json
   {
     "name": "Your Movie",
     "description": "Movie description",
     "genre": "Genre",
     "releaseYear": 2024,
     "rating": 8.0
   }
   ```

## 🔧 Common Issues & Solutions

### Issue 1: "No static resource ." error at localhost:8080
**Problem:** Accessing `http://localhost:8080/` without the API path  
**Solution:** 
- Use `http://localhost:8080/api/movies` for the API
- Or just visit `http://localhost:8080/` for the welcome page (after update)

### Issue 2: Connection Refused
**Problem:** Application not running  
**Solution:**
```bash
# Make sure the application is running
mvn spring-boot:run

# Or
./run.sh
```

### Issue 3: Port 8080 Already in Use
**Problem:** Another application is using port 8080  
**Solution:** Edit `application.properties` and change port:
```properties
server.port=8081
```
Then access at `http://localhost:8081/api/movies`

### Issue 4: 404 Not Found
**Problem:** Wrong URL or endpoint  
**Solution:** Verify you're using the correct endpoints:
- ✅ `http://localhost:8080/api/movies`
- ❌ `http://localhost:8080/movies`
- ❌ `http://localhost:8080/api/movie`

### Issue 5: Validation Error (400 Bad Request)
**Problem:** Missing required fields or invalid data  
**Solution:** Ensure all required fields are present:
- ✅ Required: name, description, genre, releaseYear
- ✅ Optional: rating
- ✅ releaseYear must be between 1900-2100
- ✅ rating must be between 0-10

## 📊 Expected Responses

### Successful GET All Movies (200 OK)
```json
[
  {
    "id": 1,
    "name": "The Shawshank Redemption",
    "description": "Two imprisoned men bond...",
    "genre": "Drama",
    "releaseYear": 1994,
    "rating": 9.3
  }
]
```

### Successful POST (201 Created)
```json
{
  "id": 4,
  "name": "The Dark Knight",
  "description": "Batman battles the Joker",
  "genre": "Action",
  "releaseYear": 2008,
  "rating": 9.0
}
```

### Not Found (404)
```
Empty response body
HTTP Status: 404
```

### Validation Error (400)
```json
{
  "timestamp": "2026-02-08T13:10:01",
  "status": 400,
  "error": "Validation Failed",
  "validationErrors": {
    "name": "Movie name is required",
    "description": "Description is required"
  }
}
```

## ⚡ Quick Commands Cheat Sheet

```bash
# Start the application
mvn spring-boot:run

# Test endpoints
curl http://localhost:8080/api/movies              # List all
curl http://localhost:8080/api/movies/1            # Get by ID

# Add movie (one-line)
curl -X POST http://localhost:8080/api/movies -H "Content-Type: application/json" -d '{"name":"Test","description":"Test movie","genre":"Action","releaseYear":2024,"rating":7.5}'

# Stop the application
Ctrl + C
```

## 📝 Pre-loaded Sample Data

The application starts with 3 movies (IDs 1-3):

1. **The Shawshank Redemption** (1994) - Drama - 9.3
2. **The Godfather** (1972) - Crime - 9.2
3. **Inception** (2010) - Sci-Fi - 8.8

You can add more movies starting from ID 4.

## 💡 Pro Tips

1. **Use the welcome page**: Visit `http://localhost:8080/` to see available endpoints
2. **Check logs**: Watch the console for request details and errors
3. **Browser for GET**: Use your browser for simple GET requests
4. **cURL/Postman for POST**: Use these tools for creating movies
5. **Start simple**: First try GET all movies, then GET by ID, then POST

## 🎯 Verification Steps

1. ✅ Start application: `mvn spring-boot:run`
2. ✅ See "Started MovieApiApplication" in console
3. ✅ Visit: `http://localhost:8080/`
4. ✅ Visit: `http://localhost:8080/api/movies`
5. ✅ Should see 3 pre-loaded movies

Happy Testing! 🎬
