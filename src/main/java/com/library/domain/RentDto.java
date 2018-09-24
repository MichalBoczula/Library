package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
public class RentDto {
    private Date rentDate;
    private Date returnDate;
    private List<Specimen> specimens;
    private List<Reader> readers;
}
