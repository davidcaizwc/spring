package com.example.service;

import com.example.domain.Review;

import java.util.List;

public interface IReviewService {

    Review getReviewById(long reviewId);

    List<Review> getAllReviews();

    List<Review> getReviewsByRating(int rating);

    void save(Review review);
}
