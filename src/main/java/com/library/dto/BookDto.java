package com.library.dto;

import com.library.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private Long id;
    private String title;
    private String author;
    private String dateOfPublication;
    private Date createdTime;
    private Date lastModifiedTime;

    public static BookDto fromBookToBookDto(Book book) {
        return book == null ? null :
                new BookDto(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getDateOfPublication(),
                        book.getCreatedTime(),
                        book.getLastModifiedTime()
                );
    }

    public static Book fromBookDtoToBook(BookDto bookDto) {
        return bookDto == null ? null :
                new Book(
                        bookDto.getId(),
                        bookDto.getTitle(),
                        bookDto.getAuthor(),
                        bookDto.getDateOfPublication()
                );
    }

    public static List<BookDto> fromBookListToBookDtoList(List<Book> books) {
        return books == null ? null :
                books.stream()
                        .map(book -> new BookDto(
                                        book.getId(),
                                        book.getTitle(),
                                        book.getAuthor(),
                                        book.getDateOfPublication(),
                                        book.getCreatedTime(),
                                        book.getLastModifiedTime()
                                )
                        ).collect(Collectors.toList());
    }
}
