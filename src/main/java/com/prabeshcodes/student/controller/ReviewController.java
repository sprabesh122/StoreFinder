package com.prabeshcodes.student.controller;

import com.prabeshcodes.student.dtos.ReviewResponse;
import com.prabeshcodes.student.model.Review;
import com.prabeshcodes.student.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/review")
@CrossOrigin
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/add")
    public Review addReview(@RequestBody Review review) {
        return reviewService.addReview(review);
    }

    @GetMapping("/store/{storeId}")
    public List<ReviewResponse> getReviewsByStoreId(@PathVariable long storeId) {
        List<Review> reviews = reviewService.getReviewsByStore(storeId);
        List<ReviewResponse> result = new ArrayList<>();
        for(Review review : reviews){
            ReviewResponse reviewResponse = new ReviewResponse();
            reviewResponse.setComment(review.getComment());
            reviewResponse.setRating(review.getRating());
            reviewResponse.setDate(review.getDate());
            result.add(reviewResponse);
        }
        return result;
    }

    @PutMapping("/{id}")
    public Review updateReview(@PathVariable long id, @RequestBody Review reviewDetails) {
        return reviewService.updateReview(id, reviewDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable long id) {
        reviewService.deleteReview(id);
    }
}