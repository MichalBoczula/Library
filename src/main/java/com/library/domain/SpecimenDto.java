package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class SpecimenDto {
    private Long id;
    private Specimen.SpecimenStatus status;
    private BookDto bookDto;
    private Instant createdTime;
    private Instant lastModifiedTime;


    public static SpecimenDto fromSpecimenToSpecimenDto(final Specimen specimen) {
        return specimen == null ? null :
                new SpecimenDto(
                        specimen.getId(),
                        specimen.getStatus(),
                        BookDto.fromBookToBookDto(specimen.getBook()),
                        specimen.getCreatedTime(),
                        specimen.getLastModifiedTime()
                );
    }

    public static List<SpecimenDto> fromSpecimenListToSpecimenListDto(final List<Specimen> specimens) {
        return specimens == null ? null :
                specimens.stream()
                        .map(specimen -> new SpecimenDto(
                                        specimen.getId(),
                                        specimen.getStatus(),
                                        BookDto.fromBookToBookDto(specimen.getBook()),
                                        specimen.getCreatedTime(),
                                        specimen.getLastModifiedTime()
                                )
                        ).collect(Collectors.toList());
    }

    public static Specimen fromSpecimenDtoToSpecimen(final SpecimenDto specimenDto) {
        return specimenDto == null ? null :
                new Specimen(
                        specimenDto.getId(),
                        specimenDto.getStatus(),
                        BookDto.fromBookDtoToBook(specimenDto.getBookDto()),
                        specimenDto.getCreatedTime(),
                        specimenDto.getLastModifiedTime()
                );
    }
}
