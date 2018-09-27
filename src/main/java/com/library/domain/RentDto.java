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
    private SpecimenDto specimenDto;
    private ReaderDto readerDto;

    public static RentDto fromRentToRentDto(Rent rent){
        return rent == null ? null :
                new RentDto(
                        rent.getId(),
                        rent.getRentDate(),
                        rent.getReturnDate(),
                        SpecimenDto.fromSpecimenToSpecimenDto(rent.getSpecimen()),
                        ReaderDto.fromReaderToReaderDto(rent.getReader())
                );
    }
}
