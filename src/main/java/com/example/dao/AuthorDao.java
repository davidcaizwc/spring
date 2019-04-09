package com.example.dao;

import com.example.domain.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorDao extends CrudRepository<Author, Long> {

}
