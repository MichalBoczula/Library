package com.library.controller;

import com.library.domain.BookDto;
import com.library.mapper.BookMapper;
import com.library.service.DbServiceBooks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/library/")
public class BookController {
    @Autowired
    private DbServiceBooks dbServiceBooks;
    @Autowired
    private BookMapper bookMapper;

    @GetMapping
    public List<BookDto> getBooks() {
        return bookMapper.mapToBookDtoList(dbServiceBooks.getBooks());
    }

    @GetMapping("/{id}")
    public BookDto getBook(@PathVariable("id") final Long bookId) throws BookNotFoundException {
        return bookMapper.mapToBookDto(dbServiceBooks.findBookWithId(bookId).orElseThrow(BookNotFoundException::new));
    }

    @PostMapping("/createBook")
    public void createBook(@RequestBody final BookDto bookDto) {
        dbServiceBooks.saveBook(bookMapper.mapToBook(bookDto));
    }

    @PutMapping("/updateBook/{id}")
    public BookDto updateBook(@PathVariable("id") final BookDto bookDto) {
        return bookMapper.mapToBookDto(dbServiceBooks.saveBook(bookMapper.mapToBook(bookDto)));
    }

    @RequestMapping(method = DELETE, value = "deleteBook")
    public void deleteBook(@RequestParam final Long bookId) throws BookNotFoundException {
        dbServiceBooks.deleteBook(dbServiceBooks.findBookWithId(bookId).orElseThrow(BookNotFoundException::new));
    }
}
