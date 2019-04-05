package com.example.web;

import com.example.domain.User;
import com.example.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    List<User> getUsers(@RequestParam(value = "userId", required = false) Long userId) {
        if (userId != null) {
            return Arrays.asList(userService.getUserById(userId));
        } else {
            return userService.getAllUsers();
        }
    }
}
