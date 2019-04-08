package com.example.service.impl;

import com.example.domain.Review;
import com.example.domain.User;
import com.example.repository.ReviewRepository;
import com.example.repository.UserRepository;
import com.example.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Transactional
    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserById(long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new LinkedList<>();
        for (User user : userRepository.findAll()) {
            users.add(user);
        }
        return users;
    }

    @Transactional
    @Override
    public void vote(long userId, long reviewId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Review> review = reviewRepository.findById(reviewId);
        if (user.isPresent() && review.isPresent()) {
            user.get().getFavoriteReviews().add(review.get());
            userRepository.save(user.get());
        }
    }

    @Transactional
    @Override
    public void deleteVote(long userId, long reviewId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Review> review = reviewRepository.findById(reviewId);
        if (user.isPresent() && review.isPresent()) {
            user.get().getFavoriteReviews().remove(review.get());
            userRepository.save(user.get());
        }
    }

    @Override
    public boolean haveUserVoted(long userId, long reviewId) {
        return reviewRepository.hadUserFavoritedReview(userId, reviewId);
    }
}
