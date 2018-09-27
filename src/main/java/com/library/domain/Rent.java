package com.library.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Rent {
    @Id
    @GeneratedValue
    private Long id;

    @CreatedDate
    private Instant rentDate;

    private Instant returnDate;

    @LastModifiedDate
    private Instant lastModifiedData;

    @ManyToOne
    @JoinColumn(name = "specimen_id")
    private List<Specimen> specimens;

    @OneToOne
    @JoinColumn(name = "reader_id")
    private Reader reader;

    public Rent(
            List<Specimen> specimens,
            Reader reader
    ) {
        this.specimens = specimens;
        this.reader = reader;
    }

    public Rent(
            Long id,
            Instant rentDate,
            Instant returnDate,
            Instant lastModifiedData,
            List<Specimen> specimens,
            Reader reader
    ) {
        this.id = id;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.lastModifiedData = lastModifiedData;
        this.specimens = specimens;
        this.reader = reader;
    }
}
