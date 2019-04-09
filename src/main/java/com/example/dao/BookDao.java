package com.example.dao;

import com.example.domain.Book;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface BookDao extends CrudRepository<Book, Long> {

    @Cacheable("books")
    default Book getBookById(Long bookId) {

        try {
            long time = 3000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }

        return new Book(bookId, "Some book");
    }
}
