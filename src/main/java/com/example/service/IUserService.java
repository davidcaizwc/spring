package com.example.service;

import com.example.domain.User;

import java.util.List;

public interface IUserService {

    void addUser(User user);

    User getUserById(long userId);

    List<User> getAllUsers();

    void vote(long userId, long reviewId);

    void deleteVote(long userId, long reviewId);

    boolean haveUserVoted(long userId, long reviewId);
}
