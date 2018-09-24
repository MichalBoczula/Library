package com.library.mapper;

import com.library.domain.Book;
import com.library.domain.BookDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {
    public Book mapToBook(final BookDto bookDto) {
        return bookDto != null ?
                new Book(
                        bookDto.getTitle(),
                        bookDto.getAuthor(),
                        bookDto.getDateOfPublication()
                ) :
                new Book(
                        "",
                        "",
                        ""
                );
    }

    public BookDto mapToBookDto(final Book book) {
        return book != null ?
                new BookDto(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getDateOfPublication()
                ) :
                new BookDto(
                        (long) 000,
                        "",
                        "",
                        ""
                );
    }

    public List<BookDto> mapToBookDtoList(final List<Book> bookList) {
        return bookList != null ?
                bookList.stream()
                        .map(book -> new BookDto(
                                        book.getId(),
                                        book.getTitle(),
                                        book.getAuthor(),
                                        book.getDateOfPublication()
                                )
                        )
                        .collect(Collectors.toList()) :
                new ArrayList<>();
    }
}
