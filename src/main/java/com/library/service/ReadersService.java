package com.library.service;

import com.library.domain.Reader;
import com.library.dto.ReaderDto;
import com.library.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReadersService {
    private final ReaderRepository readerRepository;

    public List<Reader> findAll() {
        return readerRepository.findAll();
    }

    public Optional<Reader> findById(final Long readerId) {
        return readerRepository.findById(readerId);
    }

    public Reader save(final ReaderDto readerDto) {
        return readerRepository.save(
                ReaderDto.fromReaderDtoToReader(readerDto)
        );
    }

}
