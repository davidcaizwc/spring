package com.example.controller;

import com.example.domain.Review;
import com.example.domain.VoteResponse;
import com.example.service.IReviewService;
import com.example.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/reviews")
public class ReviewController {

    @Autowired
    private IReviewService reviewService;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    List<Review> getReviews(@RequestParam(value = "rating", required = false) Integer rating) {
        if (rating != null) {
            return reviewService.getReviewsByRating(rating);
        } else {
            return reviewService.getAllReviews();
        }
    }

    @RequestMapping(value = "/{reviewId}", method = RequestMethod.GET)
    Review getReviewById(@PathVariable long reviewId) {
        return reviewService.getReviewById(reviewId);
    }

    @RequestMapping(value = "/{reviewId}/vote", method = RequestMethod.POST)
    VoteResponse vote(@PathVariable long reviewId,
                      @RequestParam(value = "userId", required = true) long userId) {

        VoteResponse response = new VoteResponse();
        Review review = reviewService.getReviewById(reviewId);
        if (review == null) {
            response.setErrorDetails("Invalid reviewId: " + reviewId);
            return response;
        }
        userService.vote(userId, review.getReviewId());
        return response;
    }

    @RequestMapping(value = "/{reviewId}/vote", method = RequestMethod.DELETE)
    VoteResponse removeVote(@PathVariable long reviewId,
                            @RequestParam(value = "userId", required = true) long userId) {

        VoteResponse response = new VoteResponse();
        Review review = reviewService.getReviewById(reviewId);
        if (review == null) {
            response.setErrorDetails("Invalid reviewId: " + reviewId);
            return response;
        }
        userService.deleteVote(userId, review.getReviewId());
        return response;
    }
}
