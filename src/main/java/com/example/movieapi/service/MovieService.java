package com.example.movieapi.service;

import com.example.movieapi.model.Movie;
import com.example.movieapi.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for Movie business logic
 * Acts as an intermediary between the controller and repository
 */
@Service
public class MovieService {
    
    private final MovieRepository movieRepository;
    
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    
    /**
     * Add a new movie to the collection
     * 
     * @param movie The movie to add
     * @return The saved movie with generated ID
     */
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }
    
    /**
     * Retrieve a movie by its ID
     * 
     * @param id The movie ID
     * @return Optional containing the movie if found
     */
    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }
    
    /**
     * Get all movies in the collection
     * 
     * @return List of all movies
     */
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
}
