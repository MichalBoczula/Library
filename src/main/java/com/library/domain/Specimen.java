package com.library.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "specimens")
public class Specimen {
    @Id
    @GeneratedValue
    @Column(name = "specimen_id")
    private Long id;
    @NotNull
    @Column(name = "status")
    private String status;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public Specimen(String status, Book book) {
        this.status = status;
        this.book = book;
    }
}
