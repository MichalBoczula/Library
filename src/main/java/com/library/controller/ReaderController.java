package com.library.controller;

import com.library.dto.ReaderDto;
import com.library.exception.ReaderNotFoundException;
import com.library.service.ReadersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library/readers")
@RequiredArgsConstructor
public class ReaderController {
    private final ReadersService readersService;

    @GetMapping
    public List<ReaderDto> getReaders() {
        return ReaderDto.formReaderListToReaderDtoList(
                readersService.findAll()
        );
    }

    @GetMapping("/{id}")
    public ReaderDto getReader(@PathVariable("id") final Long readerId) {
        return ReaderDto.fromReaderToReaderDto(
                readersService.findById(readerId)
                        .orElseThrow(() -> new ReaderNotFoundException(readerId))
        );
    }

    @PostMapping
    public ReaderDto createReader(@RequestBody final ReaderDto readerDto) {
        return ReaderDto.fromReaderToReaderDto(
                readersService.save(readerDto)
        );
    }

    @PutMapping("/{id}")
    public ReaderDto updateReader(
            @PathVariable("id") final Long readerId,
            @RequestBody final ReaderDto readerDto
    ) {
        return ReaderDto.fromReaderToReaderDto(
                readersService.save(readerDto)
        );
    }
}

