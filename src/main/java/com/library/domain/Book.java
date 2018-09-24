package com.library.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id", unique = true)
    private Long id;
    @NotNull
    @Column(name = "title")
    private String title;
    @NotNull
    @Column(name = "author")
    private String author;
    @NotNull
    @Column(name = "publication_date")
    private String dateOfPublication;
    @OneToMany(
            targetEntity = Specimen.class,
            mappedBy = "book",
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY
    )
    private List<Specimen> specimens = new ArrayList<>();

    public Book(String title, String author, String dateOfPublication) {
        this.title = title;
        this.author = author;
        this.dateOfPublication = dateOfPublication;
    }
}
