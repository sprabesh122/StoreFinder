package com.prabeshcodes.student.service;

import com.prabeshcodes.student.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getReviewsByStore(long storeId);
    Review addReview(Review review);
    Review updateReview(long id, Review reviewDetails);
    void deleteReview(long id);
}
