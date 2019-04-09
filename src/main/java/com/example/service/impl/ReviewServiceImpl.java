package com.example.service.impl;

import com.example.domain.Review;
import com.example.dao.ReviewDao;
import com.example.service.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements IReviewService {

    @Autowired
    private ReviewDao reviewDao;

    @Override
    public Review getReviewById(long reviewId) {
        Optional<Review> result = reviewDao.findById(reviewId);
        return result.orElse(null);
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewDao.findAllReviews();
    }

    @Override
    public List<Review> getReviewsByRating(int rating) {
        return reviewDao.findReviewsByRating(rating);
    }

    @Transactional
    @Override
    public void save(Review review) {
        reviewDao.save(review);
    }
}
