package com.example.dao;

import com.example.domain.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookDao extends CrudRepository<Book, Long> {

}
