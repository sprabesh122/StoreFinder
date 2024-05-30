package com.prabeshcodes.student.service.Impl;

import com.prabeshcodes.student.exception.ResourceNotFoundException;
import com.prabeshcodes.student.model.Review;
import com.prabeshcodes.student.model.Store;
import com.prabeshcodes.student.model.User;
import com.prabeshcodes.student.repository.ReviewRepository;
import com.prabeshcodes.student.repository.StoreRepository;
import com.prabeshcodes.student.repository.UserRepository;
import com.prabeshcodes.student.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoreRepository storeRepository;


    @Override
    public List<Review> getReviewsByStore(long storeId) {
        return reviewRepository.findAllByStoreId(storeId);
    }

    @Override
    public Review addReview(Review review) {
        User user = userRepository.findById(review.getUser().getId()).orElseThrow(()-> new ResourceNotFoundException("User Not Found"));
        Store store = storeRepository.findById(review.getStore().getId()).orElseThrow(() -> new ResourceNotFoundException("Store Not Found"));
        store.getReviews().add(review);
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
