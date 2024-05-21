package com.prabeshcodes.student.controller;

import com.prabeshcodes.student.model.Review;
import com.prabeshcodes.student.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@CrossOrigin
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    // Code to save review data into the database
    @PostMapping("/add")
    public String add(@RequestBody Review review) {
        reviewService.saveReview(review);
        return "New Review is Added";
    }

    // Logic to get all reviews
    @GetMapping("/getAll")
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    // Logic to get reviews by store ID
    @GetMapping("/getByStore/{storeId}")
    public List<Review> getReviewsByStoreId(@PathVariable Long storeId) {
        return reviewService.getReviewsByStoreId(storeId);
    }
}
