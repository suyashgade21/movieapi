package com.example.movieapi.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Movie entity representing an item in our collection
 * Contains validation constraints to ensure data integrity
 */
public class Movie {
    
    private Long id;
    
    @NotBlank(message = "Movie name is required")
    private String name;
    
    @NotBlank(message = "Description is required")
    private String description;
    
    @NotBlank(message = "Genre is required")
    private String genre;
    
    @NotNull(message = "Release year is required")
    @Min(value = 1900, message = "Release year must be after 1900")
    @Max(value = 2100, message = "Release year must be before 2100")
    private Integer releaseYear;
    
    @Min(value = 0, message = "Rating must be between 0 and 10")
    @Max(value = 10, message = "Rating must be between 0 and 10")
    private Double rating;
    
    // Constructors
    public Movie() {
    }
    
    public Movie(Long id, String name, String description, String genre, Integer releaseYear, Double rating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.rating = rating;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getGenre() {
        return genre;
    }
    
    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public Integer getReleaseYear() {
        return releaseYear;
    }
    
    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }
    
    public Double getRating() {
        return rating;
    }
    
    public void setRating(Double rating) {
        this.rating = rating;
    }
    
    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", genre='" + genre + '\'' +
                ", releaseYear=" + releaseYear +
                ", rating=" + rating +
                '}';
    }
}
