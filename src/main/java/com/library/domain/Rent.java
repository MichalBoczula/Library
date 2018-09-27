package com.library.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.List;

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
            Instant rentDate,
            Date returnDate,
            Instant lastModifiedData,
            Specimen specimen,
            Reader reader
    ) {
        this.id = id;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.lastModifiedData = lastModifiedData;
        this.specimen = specimen;
        this.reader = reader;
    }
}
