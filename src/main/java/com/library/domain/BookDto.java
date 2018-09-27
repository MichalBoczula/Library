package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class BookDto {
    private Long id;
    private String title;
    private String author;
    private String dateOfPublication;
    private List<SpecimenDto> specimens;

    public static BookDto fromBookToBookDto(final Book book) {
        return book == null ? null :
                new BookDto(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getDateOfPublication(),
                        SpecimenDto.fromSpecimenListToSpecimenListDto(book.getSpecimens())
                );
    }

    public static Book fromBookDtoToBook(final BookDto bookDto) {
        return bookDto == null ? null :
                new Book(
                        bookDto.getId(),
                        bookDto.getTitle(),
                        bookDto.getAuthor(),
                        bookDto.getDateOfPublication(),
                        SpecimenDto.fromSpecimenDtoListToSpecimenList(bookDto.getSpecimens())
                );
    }

    public static List<BookDto> fromBookListToBookDtoList(final List<Book> books) {
        return books == null ? null :
                books.stream()
                        .map(book -> new BookDto(
                                        book.getId(),
                                        book.getTitle(),
                                        book.getAuthor(),
                                        book.getDateOfPublication(),
                                        SpecimenDto.fromSpecimenListToSpecimenListDto(book.getSpecimens())
                                )
                        ).collect(Collectors.toList());
    }
}
