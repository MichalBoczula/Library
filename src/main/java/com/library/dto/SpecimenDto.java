package com.library.dto;

import com.library.domain.Specimen;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SpecimenDto {
    private Long id;
    private Specimen.SpecimenStatus status;
    private BookDto bookDto;
    private Date createdTime;
    private Date lastModifiedTime;


    public static SpecimenDto fromSpecimenToSpecimenDto(Specimen specimen) {
        return specimen == null ? null :
                new SpecimenDto(
                        specimen.getId(),
                        specimen.getStatus(),
                        BookDto.fromBookToBookDto(specimen.getBook()),
                        specimen.getCreatedTime(),
                        specimen.getLastModifiedTime()
                );
    }

    public static List<SpecimenDto> fromSpecimenListToSpecimenDtoList(List<Specimen> specimens) {
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

    public static Specimen fromSpecimenDtoToSpecimen(SpecimenDto specimenDto) {
        return specimenDto == null ? null :
                new Specimen(
                        specimenDto.getId(),
                        specimenDto.getStatus(),
                        BookDto.fromBookDtoToBook(specimenDto.getBookDto())
                );
    }
}
