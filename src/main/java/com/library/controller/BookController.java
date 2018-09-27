package com.library.controller;

import com.library.domain.Book;
import com.library.domain.BookDto;
import com.library.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library/books")
public class BookController {
    @Autowired
    private BooksService booksService;

    @GetMapping
    public List<BookDto> getBooks() {
        return BookDto.fromBookListToBookDtoList(booksService.findAll());
    }

    @GetMapping("/{id}")
    public BookDto getBook(@PathVariable("id") final Long bookId) {
        final Book book = booksService.findById(bookId);
        return BookDto.fromBookToBookDto(book);
    }

    @PostMapping("/create")
    public void createBook(@RequestBody final BookDto bookDto) {
        booksService.save(BookDto.fromBookDtoToBook(bookDto));
    }

    @PutMapping("/update")
    public BookDto updateBook(@RequestBody final BookDto bookDto) {
        return BookDto.fromBookToBookDto(
                booksService.save(
                        BookDto.fromBookDtoToBook(
                                bookDto
                        )
                )
        );
    }
}
