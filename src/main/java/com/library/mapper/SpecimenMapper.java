package com.library.mapper;

import com.library.domain.Book;
import com.library.domain.Specimen;
import com.library.domain.SpecimenDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SpecimenMapper {
    public Specimen mapToSpecimen(final SpecimenDto specimenDto) {
        return specimenDto != null ?
                new Specimen(
                        specimenDto.getId(),
                        specimenDto.getStatus(),
                        specimenDto.getBook()
                ) :
                new Specimen(
                        0L,
                        "",
                        new Book("", "", "")
                );
    }

    public SpecimenDto mapToSpecimenDto(final Specimen specimen) {
        return specimen != null ?
                new SpecimenDto(
                        specimen.getId(),
                        specimen.getStatus(),
                        specimen.getBook()
                ) :
                new SpecimenDto(
                        0L,
                        "",
                        new Book("", "", "")
                );
    }

    public List<SpecimenDto> mapToSpecimenDtoList(final List<Specimen> specimens) {
        return specimens != null ?
                specimens.stream()
                        .map(specimen -> new SpecimenDto(
                                        specimen.getId(),
                                        specimen.getStatus(),
                                        specimen.getBook()
                                )
                        ).collect(Collectors.toList()) :
                new ArrayList<>();
    }
}
