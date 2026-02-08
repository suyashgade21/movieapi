package com.example.movieapi.repository;

import com.example.movieapi.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Repository class for managing Movie data in memory
 * Uses ArrayList as the data store and AtomicLong for thread-safe ID generation
 */
@Repository
public class MovieRepository {
    
    // In-memory data store using ArrayList
    private final List<Movie> movies = new ArrayList<>();
    
    // Thread-safe counter for generating unique IDs
    private final AtomicLong idCounter = new AtomicLong(1);
    
    /**
     * Initialize repository with some sample data
     */
    public MovieRepository() {
        // Adding some initial sample movies
        movies.add(new Movie(idCounter.getAndIncrement(), 
            "The Shawshank Redemption", 
            "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
            "Drama",
            1994,
            9.3));
            
        movies.add(new Movie(idCounter.getAndIncrement(), 
            "The Godfather", 
            "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.",
            "Crime",
            1972,
            9.2));
            
        movies.add(new Movie(idCounter.getAndIncrement(), 
            "Inception", 
            "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea.",
            "Sci-Fi",
            2010,
            8.8));
    }
    
    /**
     * Save a new movie to the collection
     * Automatically assigns a unique ID
     * 
     * @param movie The movie to save (without ID)
     * @return The saved movie with assigned ID
     */
    public Movie save(Movie movie) {
        movie.setId(idCounter.getAndIncrement());
        movies.add(movie);
        return movie;
    }
    
    /**
     * Find a movie by its ID
     * 
     * @param id The movie ID to search for
     * @return Optional containing the movie if found, empty otherwise
     */
    public Optional<Movie> findById(Long id) {
        return movies.stream()
                .filter(movie -> movie.getId().equals(id))
                .findFirst();
    }
    
    /**
     * Get all movies in the collection
     * 
     * @return List of all movies
     */
    public List<Movie> findAll() {
        return new ArrayList<>(movies);
    }
    
    /**
     * Check if a movie exists by ID
     * 
     * @param id The movie ID to check
     * @return true if movie exists, false otherwise
     */
    public boolean existsById(Long id) {
        return movies.stream().anyMatch(movie -> movie.getId().equals(id));
    }
}
