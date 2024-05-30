package com.prabeshcodes.student.controller;

import com.prabeshcodes.student.model.Review;
import com.prabeshcodes.student.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<Review> getReviewsByStoreId(@PathVariable long storeId) {
        return reviewService.getReviewsByStore(storeId);
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