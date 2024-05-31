package com.prabeshcodes.student.dtos;

import java.time.LocalDateTime;

public class ReviewResponse {

    private Long id;
    private int rating;
    private String comment;
    private LocalDateTime date;

    // Default constructor
    public ReviewResponse() {}

    // Parameterized constructor
    public ReviewResponse(Long id, int rating, String comment, LocalDateTime date) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ReviewResponse{" +
                "id=" + id +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", date=" + date +
                '}';
    }
}
