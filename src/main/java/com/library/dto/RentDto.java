package com.library.dto;

import com.library.domain.Rent;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class RentDto {
    private Long id;
    private Instant rentDate;
    private Date returnDate;
    private Instant lastModifiedData;
    private SpecimenDto specimenDto;
    private ReaderDto readerDto;

    public static RentDto fromRentToRentDto(Rent rent) {
        return rent == null ? null :
                new RentDto(
                        rent.getId(),
                        rent.getRentDate(),
                        rent.getReturnDate(),
                        rent.getLastModifiedData(),
                        SpecimenDto.fromSpecimenToSpecimenDto(rent.getSpecimen()),
                        ReaderDto.fromReaderToReaderDto(rent.getReader())
                );
    }

    public static Rent fromRentDtoToRent(RentDto rentDto) {
        return rentDto == null ? null :
                new Rent(
                        rentDto.id,
                        rentDto.getReturnDate(),
                        SpecimenDto.fromSpecimenDtoToSpecimen(rentDto.getSpecimenDto()),
                        ReaderDto.fromReaderDtoToReader(rentDto.getReaderDto())
                );
    }

    public static List<RentDto> fromRentListToRentDtoList(List<Rent> rents) {
        return rents == null ? null :
                rents.stream()
                        .map(rent -> new RentDto(
                                        rent.getId(),
                                        rent.getRentDate(),
                                        rent.getReturnDate(),
                                        rent.getLastModifiedData(),
                                        SpecimenDto.fromSpecimenToSpecimenDto(rent.getSpecimen()),
                                        ReaderDto.fromReaderToReaderDto(rent.getReader())
                                )
                        ).collect(Collectors.toList());
    }
}
