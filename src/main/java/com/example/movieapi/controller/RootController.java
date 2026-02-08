package com.example.movieapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Root controller to provide API information at the base URL
 */
@RestController
@RequestMapping("/")
public class RootController {
    
    @GetMapping
    public ResponseEntity<Map<String, Object>> welcome() {
        Map<String, Object> response = new HashMap<>();
        response.put("application", "Movie Management API");
        response.put("version", "1.0.0");
        response.put("status", "running");
        
        Map<String, String> endpoints = new HashMap<>();
        endpoints.put("Get All Movies", "GET http://localhost:8080/api/movies");
        endpoints.put("Get Movie by ID", "GET http://localhost:8080/api/movies/{id}");
        endpoints.put("Add New Movie", "POST http://localhost:8080/api/movies");
        
        response.put("endpoints", endpoints);
        
        Map<String, String> sampleRequest = new HashMap<>();
        sampleRequest.put("method", "POST");
        sampleRequest.put("url", "http://localhost:8080/api/movies");
        sampleRequest.put("body", "{ \"name\": \"Movie Name\", \"description\": \"Description\", \"genre\": \"Genre\", \"releaseYear\": 2024, \"rating\": 8.5 }");
        
        response.put("sampleRequest", sampleRequest);
        
        return ResponseEntity.ok(response);
    }
}
