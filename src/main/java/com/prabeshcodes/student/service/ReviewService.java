package com.prabeshcodes.student.service;

import com.prabeshcodes.student.model.Review;

import java.util.List;

public interface ReviewService {
    Review saveReview(Review review);

    List<Review> getAllReviews();

    List<Review> getReviewsByStoreId(Long storeId);
}
