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
                        bookDto.getId(),
                        bookDto.getTitle(),
                        bookDto.getAuthor(),
                        bookDto.getDateOfPublication(),
                        bookDto.getSpecimens()
                ) :
                new Book(
                        0L,
                        "",
                        "",
                        "",
                        new ArrayList<>()
                );
    }

    public BookDto mapToBookDto(final Book book) {
        return book != null ?
                new BookDto(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getDateOfPublication(),
                        book.getSpecimens()
                ) :
                new BookDto(
                        0L,
                        "",
                        "",
                        "",
                        new ArrayList<>()
                );
    }

    public List<BookDto> mapToBookDtoList(final List<Book> bookList) {
        return bookList != null ?
                bookList.stream()
                        .map(book -> new BookDto(
                                        book.getId(),
                                        book.getTitle(),
                                        book.getAuthor(),
                                        book.getDateOfPublication(),
                                        book.getSpecimens()
                                )
                        )
                        .collect(Collectors.toList()) :
                new ArrayList<>();
    }
}
