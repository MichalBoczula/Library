package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class RentDto {
    private Long id;
    private Date rentDate;
    private Date returnDate;
    private Specimen specimen;
    private Reader reader;
}
