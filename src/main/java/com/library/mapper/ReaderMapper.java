package com.library.mapper;

import com.library.domain.Reader;
import com.library.domain.ReaderDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReaderMapper {
    public Reader mapToReader(final ReaderDto readerDto) {
        return readerDto != null ?
                new Reader(
                        readerDto.getId(),
                        readerDto.getFirstName(),
                        readerDto.getLastName(),
                        readerDto.getDateOfAccountCreation()
                ) :
                new Reader(
                        0L,
                        "",
                        "",
                        new Date()
                );
    }

    public ReaderDto mapToReaderDto(final Reader reader) {
        return reader != null ?
                new ReaderDto(
                        reader.getId(),
                        reader.getFirstName(),
                        reader.getLastName(),
                        reader.getDateOfAccountCreation()
                ) :
                new ReaderDto(
                        0L,
                        "",
                        "",
                        new Date()
                );
    }

    public List<ReaderDto> mapToReaderDtoList(final List<Reader> readerList) {
        return readerList != null ?
                readerList.stream()
                        .map(reader -> new ReaderDto(
                                        reader.getId(),
                                        reader.getFirstName(),
                                        reader.getLastName(),
                                        reader.getDateOfAccountCreation()
                                )
                        )
                        .collect(Collectors.toList()) :
                new ArrayList<>();
    }
}
