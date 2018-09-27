package com.library.controller;

import com.library.domain.ReaderDto;
import com.library.mapper.ReaderMapper;
import com.library.service.ReadersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/library/readers")
public class ReaderController {
    @Autowired
    private ReadersService readersService;
    @Autowired
    private ReaderMapper readerMapper;

    @RequestMapping(method = GET, value = "getReaders")
    public List<ReaderDto> getBooks() {
        return readerMapper.mapToReaderDtoList(readersService.getReaders());
    }

    @RequestMapping(method = GET, value = "getReader")
    public ReaderDto getReader(@RequestParam final Long readerId) throws ReaderNotFoundException {
        return readerMapper.mapToReaderDto(readersService.getReader(readerId).orElseThrow(ReaderNotFoundException::new));
    }

    @RequestMapping(method = POST, value = "createReader", consumes = APPLICATION_JSON_VALUE)
    public void createReader(@RequestBody final ReaderDto readerDto) {
        readersService.save(readerMapper.mapToReader(readerDto));
    }

    @RequestMapping(method = DELETE, value = "deleteReader")
    public void deleteReader(@RequestParam final Long readerId) throws ReaderNotFoundException {
        readersService.delete(readersService.getReader(readerId).orElseThrow(ReaderNotFoundException::new));
    }

    @RequestMapping(method = PUT, value = "updateReader")
    public ReaderDto updateReader(@RequestParam final ReaderDto readerDto) {
        return readerMapper.mapToReaderDto(readersService.save(readerMapper.mapToReader(readerDto)));
    }
}
