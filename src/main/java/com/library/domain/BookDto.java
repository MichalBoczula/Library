package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class BookDto {
    private Long id;
    private String title;
    private String author;
    private String dateOfPublication;
    private List<Specimen> specimens;

    public static BookDto fromBookToBookDto(Book book) {
        return book == null ? null :
                new BookDto(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getDateOfPublication(),
                        book.getSpecimens()
                );
    }

    public static Book fromBookDtoToBook(BookDto bookDto) {
        return bookDto == null ? null :
                new Book(
                        bookDto.getId(),
                        bookDto.getTitle(),
                        bookDto.getAuthor(),
                        bookDto.getDateOfPublication(),
                        bookDto.getSpecimens()
                );
    }
}
