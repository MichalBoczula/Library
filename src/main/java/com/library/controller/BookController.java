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
         final List<Book> books = booksService.findAll();
         return BookDto.fromBookListToBookDtoList(books);
    }

    @GetMapping("/{id}")
    public BookDto getBook(@PathVariable("id") final Long bookId) {
        final Book book = booksService.findById(bookId);
        return BookDto.fromBookToBookDto(book);
    }

    @PostMapping("/create")
    public BookDto createBook(@RequestBody final Book book) {
        return BookDto.fromBookToBookDto(
                booksService.save(book)
        );
    }

    @PutMapping("/update")
    public BookDto updateBook(@RequestBody final Book book) {
        return BookDto.fromBookToBookDto(
                booksService.save(book)
        );
    }
}
