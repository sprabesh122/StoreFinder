package com.prabeshcodes.student.service.Impl;

import com.prabeshcodes.student.exception.ResourceNotFoundException;
import com.prabeshcodes.student.model.Review;
import com.prabeshcodes.student.repository.ReviewRepository;
import com.prabeshcodes.student.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;


    @Override
    public List<Review> getReviewsByStore(long storeId) {
        return reviewRepository.findAllByStoreId(storeId);
    }

    @Override
    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review updateReview(long id, Review reviewDetails) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Review not found"));
        review.setRating(reviewDetails.getRating());
        review.setComment(reviewDetails.getComment());
        return reviewRepository.save(review);
    }

    @Override
    public void deleteReview(long id) {
        reviewRepository.deleteById(id);
    }
}
