package com.library.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "rents")
public class Rent {
    @Id
    @GeneratedValue
    @Column(name = "rent_id")
    private Long id;

    @Column(name = "rent_date")
    private Date rentDate;

    @Column(name = "return_date")
    private Date returnDate;

    @OneToOne
    @JoinColumn(name = "specimen_id")
    private Specimen specimen;

    @OneToOne
    @JoinColumn(name = "reader_id")
    private Reader reader;

    public Rent(Specimen specimen, Reader reader) {
        this.rentDate = new Date();
        this.specimen = specimen;
        this.reader = reader;
    }

    public Rent(Long id, Date rentDate, Date returnDate, Specimen specimen, Reader reader) {
        this.id = id;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.specimen = specimen;
        this.reader = reader;
    }
}
