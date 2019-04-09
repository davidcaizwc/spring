package com.example.service.impl;

import com.example.domain.Review;
import com.example.domain.User;
import com.example.dao.ReviewDao;
import com.example.dao.UserDao;
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
    private UserDao userDao;

    @Autowired
    private ReviewDao reviewDao;

    @Transactional
    @Override
    public void addUser(User user) {
        userDao.save(user);
    }

    @Override
    public User getUserById(long userId) {
        return userDao.findById(userId).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new LinkedList<>();
        for (User user : userDao.findAll()) {
            users.add(user);
        }
        return users;
    }

    @Transactional
    @Override
    public void vote(long userId, long reviewId) {
        Optional<User> user = userDao.findById(userId);
        Optional<Review> review = reviewDao.findById(reviewId);
        if (user.isPresent() && review.isPresent()) {
            user.get().getReviews().add(review.get());
            userDao.save(user.get());
        }
    }

    @Transactional
    @Override
    public void deleteVote(long userId, long reviewId) {
        Optional<User> user = userDao.findById(userId);
        Optional<Review> review = reviewDao.findById(reviewId);
        if (user.isPresent() && review.isPresent()) {
            user.get().getReviews().remove(review.get());
            userDao.save(user.get());
        }
    }

    @Override
    public boolean haveUserVoted(long userId, long reviewId) {
        return reviewDao.hadUserFavoritedReview(userId, reviewId);
    }
}
