package com.example.controller;


import com.example.dao.BookDao;
import com.example.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    private BookDao bookDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Book> getBooks() {
        return bookDao.findAll();
    }

    @RequestMapping(value = "/{bookId}", method = RequestMethod.GET)
    Book getBookById(@PathVariable long bookId) {
        return bookDao.getBookById(bookId);
    }
}
