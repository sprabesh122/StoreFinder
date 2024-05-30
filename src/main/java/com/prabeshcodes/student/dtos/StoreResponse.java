package com.prabeshcodes.student.dtos;

import java.util.List;

public class StoreResponse {
    private Long id;
    private String name;
    private String description;
    private List<String> reviews;

    // Getters and Setters for id, name, description, and reviews
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

    public List<String> getReviews() {
        return reviews;
    }

    public void setReviews(List<String> reviews) {
        this.reviews = reviews;
    }
}
