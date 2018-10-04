package com.library.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Rent {
    @Id
    @GeneratedValue
    private Long id;

    @CreatedDate
    private Instant rentDate;

    private Date returnDate;

    @LastModifiedDate
    private Instant lastModifiedData;

    @ManyToOne
    @JoinColumn(name = "specimen_id")
    private Specimen specimen;

    @OneToOne
    @JoinColumn(name = "reader_id")
    private Reader reader;

    public Rent(
            Specimen specimen,
            Reader reader
    ) {
        this.specimen = specimen;
        this.reader = reader;
    }

    public Rent(
            Long id,
            Date returnDate,
            Specimen specimen,
            Reader reader
    ) {
        this.id = id;
        this.returnDate = returnDate;
        this.specimen = specimen;
        this.reader = reader;
    }
}
