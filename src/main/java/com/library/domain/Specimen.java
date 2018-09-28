package com.library.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Specimen {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private SpecimenStatus status;

    @ManyToOne
    private Book book;

    @OneToMany(
            targetEntity = Rent.class,
            mappedBy = "specimen",
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY
    )
    private List<Rent> rents;

    @CreatedDate
    private Instant createdTime;

    @LastModifiedDate
    private Instant lastModifiedTime;

    public Specimen(
            Long id,
            SpecimenStatus status,
            Book book,
            Instant createdTime,
            Instant lastModifiedTime
    ) {
        this.id = id;
        this.status = status;
        this.book = book;
        this.createdTime = createdTime;
        this.lastModifiedTime = lastModifiedTime;
    }

    public Specimen(
            SpecimenStatus status,
            Book book
    ) {
        this.status = status;
        this.book = book;
    }

    public static enum SpecimenStatus {
        IN_LIBRARY, RENTED, DESTROYED
    }
}