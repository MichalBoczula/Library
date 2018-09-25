package com.library.mapper;

import com.library.domain.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RentMapper {
    public Rent mapToRent(final RentDto rentDto){
        return rentDto != null ?
                new Rent(
                        rentDto.getRentDate(),
                        rentDto.getSpecimen(),
                        rentDto.getReader()
                ) :
                new Rent(
                        new Specimen("", new Book()),
                        new Reader("", "")
                );
    }

    public RentDto mapToRentDto(final Rent rent){
        return rent != null ?
                new RentDto(
                        rent.getId(),
                        rent.getRentDate(),
                        rent.getReturnDate(),
                        rent.getSpecimen(),
                        rent.getReader()
                ) :
                new RentDto(
                        (long) 000,
                        new Date(),
                        new Date(),
                        new Specimen("", new Book()),
                        new Reader("", "")
                );
    }

    public List<RentDto> mapToRentDtoList(final List<Rent> rents){
        return rents != null ?
                rents.stream()
                        .map(rent -> new RentDto(
                                rent.getId(),
                                rent.getRentDate(),
                                rent.getReturnDate(),
                                rent.getSpecimen(),
                                rent.getReader())
                        ).collect(Collectors.toList()) :
                new ArrayList<>();
    }
}
