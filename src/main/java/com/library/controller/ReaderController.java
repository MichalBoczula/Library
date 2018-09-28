package com.library.controller;

import com.library.domain.Reader;
import com.library.domain.ReaderDto;
import com.library.service.ReadersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library/readers")
public class ReaderController {
    @Autowired
    private ReadersService readersService;

    @GetMapping
    public List<ReaderDto> getBooks() {
        return ReaderDto.formReaderListToReaderDtoList(
                readersService.findAll()
        );
    }

    @GetMapping("{id}")
    public ReaderDto getReader(@PathVariable("id") final Long readerId) {
        return ReaderDto.fromReaderToReaderDto(
                readersService.findByID(readerId)
        );
    }

    @PostMapping("/create")
    public ReaderDto createReader(@RequestBody final Reader reader) {
        return ReaderDto.fromReaderToReaderDto(
                readersService.save(reader)
        );
    }

    @PutMapping("/update")
    public ReaderDto updateReader(@RequestBody final Reader reader) {
        return ReaderDto.fromReaderToReaderDto(
                readersService.save(reader)
        );
    }
}

