package com.library.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ToString
@Getter
@NoArgsConstructor
@Entity
@Table(name = "readers")
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reader_id", unique = true)
    private Long id;
    @NotNull
    @Column(name = "first_name")
    private String firstName;
    @NotNull
    @Column(name = "last_name")
    private String lastName;
    @NotNull
    @Column(name = "date_of_account_creation")
    private Date dateOfAccountCreation;

    public Reader(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        dateOfAccountCreation = new Date();
    }

    public Reader(String firstName, String lastName, Date dateOfAccountCreation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfAccountCreation = dateOfAccountCreation;
    }
}
