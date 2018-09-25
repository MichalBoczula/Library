package com.library.service;

import com.library.domain.Book;
import com.library.repository.BookRepositoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DbServiceBooks {
    @Autowired
    private BookRepositoryDao bookRepositoryDao;

    public List<Book> getBooks() {
        return bookRepositoryDao.findAll();
    }

    public Optional<Book> findBookWithId(final Long bookId) {
        return bookRepositoryDao.findById(bookId);
    }

    public Book saveBook(final Book book) {
        return bookRepositoryDao.save(book);
    }

    public void deleteBook(final Book book) {
        bookRepositoryDao.delete(book);
    }

}
