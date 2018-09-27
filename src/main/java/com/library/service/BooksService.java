package com.library.service;

import com.library.controller.BookNotFoundException;
import com.library.domain.Book;
import com.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BooksService {
    private final BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(final Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
    }

    public Book save(final Book book) {
        return bookRepository.save(book);
    }

    public void delete(final Book book) {
        bookRepository.delete(book);
    }

}
