package com.library.controller;

import com.library.domain.Book;
import com.library.dto.BookDto;
import com.library.exception.BookNotFoundException;
import com.library.service.BooksService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/library/books")
@RequiredArgsConstructor
public class BookController {
    private final BooksService booksService;

    @GetMapping
    public List<BookDto> getBooks() {
        final List<Book> books = booksService.findAll();
        return BookDto.fromBookListToBookDtoList(books);
    }

    @GetMapping("/{id}")
    public BookDto getBook(@PathVariable("id") final Long bookId) {
        final Book book = booksService.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
        return BookDto.fromBookToBookDto(book);
    }

    @PostMapping
    public BookDto createBook(@RequestBody final BookDto bookDto) {
        return BookDto.fromBookToBookDto(
                booksService.save(bookDto)
        );
    }

    @PutMapping("/{id}")
    public BookDto updateBook(
            @PathVariable("id") final Long bookId,
            @RequestBody final BookDto bookDto
    ) {
        return BookDto.fromBookToBookDto(
                booksService.save(bookDto)
        );
    }
}
