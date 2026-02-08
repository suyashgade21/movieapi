package com.example.movieapi.controller;

import com.example.movieapi.model.Movie;
import com.example.movieapi.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Movie API endpoints
 * Handles HTTP requests and responses for movie management
 */
@RestController
@RequestMapping("/api/movies")
public class MovieController {
    
    private final MovieService movieService;
    
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }
    
    /**
     * POST /api/movies - Add a new movie
     * 
     * Request Body Example:
     * {
     *   "name": "The Dark Knight",
     *   "description": "Batman battles the Joker in Gotham City",
     *   "genre": "Action",
     *   "releaseYear": 2008,
     *   "rating": 9.0
     * }
     * 
     * @param movie The movie to add (validated)
     * @return ResponseEntity with created movie and HTTP 201 status
     */
    @PostMapping
    public ResponseEntity<Movie> addMovie(@Valid @RequestBody Movie movie) {
        Movie savedMovie = movieService.addMovie(movie);
        return new ResponseEntity<>(savedMovie, HttpStatus.CREATED);
    }
    
    /**
     * GET /api/movies/{id} - Get a movie by ID
     * 
     * Example: GET /api/movies/1
     * 
     * @param id The movie ID
     * @return ResponseEntity with movie if found (HTTP 200) or not found (HTTP 404)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id)
                .map(movie -> new ResponseEntity<>(movie, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    /**
     * GET /api/movies - Get all movies (bonus endpoint)
     * 
     * @return ResponseEntity with list of all movies
     */
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }
}
