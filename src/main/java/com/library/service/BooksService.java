package com.library.service;

import com.library.domain.Book;
import com.library.dto.BookDto;
import com.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BooksService {
    private final BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(final Long bookId) {
        return bookRepository.findById(bookId);
    }

    public Book save(final BookDto bookDto) {
        return bookRepository.save(
                BookDto.fromBookDtoToBook(bookDto)
        );
    }
}
