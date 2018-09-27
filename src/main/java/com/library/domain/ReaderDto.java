package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ReaderDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Date dateOfAccountCreation;

    public static ReaderDto fromReaderToReaderDto(Reader reader){
        return reader == null ? null :
                new ReaderDto(
                        reader.getId(),
                        reader.getFirstName(),
                        reader.getFirstName(),
                        reader.getDateOfAccountCreation()
                );
    }
}

