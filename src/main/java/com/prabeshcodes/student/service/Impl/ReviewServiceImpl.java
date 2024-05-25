package com.prabeshcodes.student.service.Impl;

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
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public List<Review> getReviewsByStoreId(Long storeId) {
        return reviewRepository.findAllByStoreId(storeId);
    }
}