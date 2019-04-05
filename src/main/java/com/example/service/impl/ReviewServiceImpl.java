package com.example.service.impl;

import com.example.domain.Review;
import com.example.repository.ReviewRepository;
import com.example.service.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements IReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Review getReviewById(long reviewId) {
        Optional<Review> result = reviewRepository.findById(reviewId);
        return result.orElse(null);
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAllReviews();
    }

    @Override
    public List<Review> getReviewsByRating(int rating) {
        return reviewRepository.findReviewsByRating(rating);
    }

    @Transactional
    @Override
    public void save(Review review) {
        reviewRepository.save(review);
    }
}
