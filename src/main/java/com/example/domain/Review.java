package com.example.domain;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "review")
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "review_id")
    private Long reviewId;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "review_text", nullable = false)
    private String reviewText;

    @Formula("(SELECT count(*) FROM user_vote v WHERE v.review_id = review_id)")
    private Long voteCount;

    @ManyToMany(mappedBy = "reviews")
    private List<User> users;

    protected Review() {}

    public Review(Integer rating, String reviewText) {
        this.rating = rating;
        this.reviewText = reviewText;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public String getReviewText() {
        return reviewText;
    }

    public Long getVoteCount() {
        return voteCount;
    }

    public Integer getRating() {
        return rating;
    }

    public List<User> getUsers() {
        return users;
    }
}
