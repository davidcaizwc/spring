package com.example.repository;

import com.example.domain.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {

    @Query("SELECT r FROM Review r ORDER BY r.rating, r.voteCount DESC")
    List<Review> findAllReviews();

    @Query("SELECT r FROM Review r WHERE r.rating = :rating ORDER BY r.voteCount DESC")
    List<Review> findReviewsByRating(@Param("rating") int rating);

    @Query(nativeQuery = true, value = "SELECT COUNT(*) > 0 FROM user_vote v WHERE v.user_id = :userId AND v.review_id = :reviewId")
    boolean hadUserFavoritedReview(@Param("userId") long userId, @Param("reviewId") long reviewId);
}
