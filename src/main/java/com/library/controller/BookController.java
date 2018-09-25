package com.library.controller;

import com.library.domain.BookDto;
import com.library.mapper.BookMapper;
import com.library.service.DbServiceBooks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/library/books")
public class BookController {
    @Autowired
    private DbServiceBooks dbServiceBooks;
    @Autowired
    private BookMapper bookMapper;

    @RequestMapping(method = GET, value = "getBooks")
    public List<BookDto> getBooks() {
        return bookMapper.mapToBookDtoList(dbServiceBooks.getBooks());
    }

    @RequestMapping(method = GET, value = "getBook")
    public BookDto getBook(@RequestParam final Long bookId) throws BookNotFoundException {
        return bookMapper.mapToBookDto(dbServiceBooks.findBookWithId(bookId).orElseThrow(BookNotFoundException::new));
    }

    @RequestMapping(method = POST, value = "createBook", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createBook(@RequestBody final BookDto bookDto) {
        dbServiceBooks.saveBook(bookMapper.mapToBook(bookDto));
    }

    @RequestMapping(method = PUT, value = "updateBook")
    public BookDto updateBook(@RequestBody final BookDto bookDto) {
        return bookMapper.mapToBookDto(dbServiceBooks.saveBook(bookMapper.mapToBook(bookDto)));
    }

    @RequestMapping(method = DELETE, value = "deleteBook")
    public void deleteBook(@RequestParam final Long bookId) throws BookNotFoundException {
        dbServiceBooks.deleteBook(dbServiceBooks.findBookWithId(bookId).orElseThrow(BookNotFoundException::new));
    }
}
