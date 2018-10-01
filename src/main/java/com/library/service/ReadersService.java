package com.library.service;

import com.library.domain.Reader;
import com.library.dto.ReaderDto;
import com.library.exception.ReaderNotFoundException;
import com.library.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReadersService {
    private final ReaderRepository readerRepository;

    public List<Reader> findAll() {
        return readerRepository.findAll();
    }

    public Reader findByID(final Long readerId) {
        return readerRepository.findById(readerId).
                orElseThrow(() -> new ReaderNotFoundException(readerId));
    }

    public Reader save(final ReaderDto readerDto) {
        return readerRepository.save(
                ReaderDto.fromReaderDtoToReader(readerDto)
        );
    }

}
