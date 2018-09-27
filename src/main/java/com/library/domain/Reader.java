package com.library.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Reader {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @CreatedDate
    private Instant dateOfAccountCreation;

    @LastModifiedDate
    private Instant lastModifiedDate;

    public Reader(
            String firstName,
            String lastName
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Reader(
            Long id,
            String firstName,
            String lastName,
            Instant dateOfAccountCreation,
            Instant lastModifiedDate
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfAccountCreation = dateOfAccountCreation;
        this.lastModifiedDate = lastModifiedDate;
    }
}
