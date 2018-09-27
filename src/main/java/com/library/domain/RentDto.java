package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class RentDto {
    private Long id;
    private Instant rentDate;
    private Instant returnDate;
    private Instant lastModifiedData;
    private List<SpecimenDto> specimenDtos;
    private ReaderDto readerDto;

    public static RentDto fromRentToRentDto(Rent rent) {
        return rent == null ? null :
                new RentDto(
                        rent.getId(),
                        rent.getRentDate(),
                        rent.getReturnDate(),
                        rent.getLastModifiedData(),
                        SpecimenDto.fromSpecimenListToSpecimenListDto(rent.getSpecimens()),
                        ReaderDto.fromReaderToReaderDto(rent.getReader())
                );
    }

    public static Rent fromRentDtoToRent(RentDto rentDto) {
        return rentDto == null ? null :
                new Rent(
                        rentDto.id,
                        rentDto.getRentDate(),
                        rentDto.getReturnDate(),
                        rentDto.getLastModifiedData(),
                        SpecimenDto.fromSpecimenDtoListToSpecimenList(rentDto.getSpecimenDtos()),
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
                                        SpecimenDto.fromSpecimenListToSpecimenListDto(rent.getSpecimens()),
                                        ReaderDto.fromReaderToReaderDto(rent.getReader())
                                )
                        ).collect(Collectors.toList());
    }

}
