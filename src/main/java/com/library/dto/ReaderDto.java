package com.library.dto;

import com.library.domain.Reader;
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
public class ReaderDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Date dateOfAccountCreation;
    private Date lastModifiedDate;

    public static ReaderDto fromReaderToReaderDto(Reader reader) {
        return reader == null ? null :
                new ReaderDto(
                        reader.getId(),
                        reader.getFirstName(),
                        reader.getLastName(),
                        reader.getDateOfAccountCreation(),
                        reader.getLastModifiedDate()
                );
    }

    public static Reader fromReaderDtoToReader(ReaderDto readerDto) {
        return readerDto == null ? null :
                new Reader(
                        readerDto.getId(),
                        readerDto.getFirstName(),
                        readerDto.getLastName()
                );
    }

    public static List<ReaderDto> formReaderListToReaderDtoList(List<Reader> readers) {
        return readers == null ? null :
                readers.stream()
                        .map(reader -> new ReaderDto(
                                        reader.getId(),
                                        reader.getFirstName(),
                                        reader.getLastName(),
                                        reader.getDateOfAccountCreation(),
                                        reader.getLastModifiedDate()
                                )
                        ).collect(Collectors.toList());
    }

}