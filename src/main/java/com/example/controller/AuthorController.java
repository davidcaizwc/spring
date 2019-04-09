package com.example.controller;

import com.example.dao.AuthorDao;
import com.example.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/authors")
public class AuthorController {

    @Autowired
    private AuthorDao authorDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    Iterable<Author> getAuthors() {
        return authorDao.findAll();
    }
}
