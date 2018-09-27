package com.library.controller;

import com.library.domain.BookDto;
import com.library.mapper.BookMapper;
import com.library.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/library/")
public class BookController {
    @Autowired
    private BooksService booksService;
    @Autowired
    private BookMapper bookMapper;

    @GetMapping
    public List<BookDto> getBooks() {
        return bookMapper.mapToBookDtoList(booksService.getBooks());
    }

    @GetMapping("/{id}")
    public BookDto getBook(@PathVariable("id") final Long bookId) throws BookNotFoundException {
        return bookMapper.mapToBookDto(booksService.findBookWithId(bookId).orElseThrow(BookNotFoundException::new));
    }

    @PostMapping("/createBook")
    public void createBook(@RequestBody final BookDto bookDto) {
        booksService.saveBook(bookMapper.mapToBook(bookDto));
    }

    @PutMapping("/updateBook/{id}")
    public BookDto updateBook(@PathVariable("id") final BookDto bookDto) {
        return bookMapper.mapToBookDto(booksService.saveBook(bookMapper.mapToBook(bookDto)));
    }

    @RequestMapping(method = DELETE, value = "deleteBook")
    public void deleteBook(@RequestParam final Long bookId) throws BookNotFoundException {
        booksService.deleteBook(booksService.findBookWithId(bookId).orElseThrow(BookNotFoundException::new));
    }
}
