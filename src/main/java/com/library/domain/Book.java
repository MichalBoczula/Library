package com.library.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Book {

    @Id
    @GeneratedValue()
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String author;

    @NotNull
    private String dateOfPublication;

    @OneToMany(
            targetEntity = Specimen.class,
            mappedBy = "book",
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY
    )
    private List<Specimen> specimens = new ArrayList<>();

    @CreatedDate
    private Instant createdTime;

    @LastModifiedDate
    private Instant lastModifiedTime;


    public Book(
            String title,
            String author,
            String dateOfPublication
    ) {
        this.title = title;
        this.author = author;
        this.dateOfPublication = dateOfPublication;
    }

    public Book(
            Long id,
            String title,
            String author,
            String dateOfPublication
    ) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.dateOfPublication = dateOfPublication;
    }
}
