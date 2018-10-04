package com.library.service;

import com.library.domain.Reader;
import com.library.dto.ReaderDto;
import com.library.repository.ReaderRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReadersServiceTestSuite {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReadersServiceTestSuite.class);
    @Autowired
    private ReadersService readersService;
    @Autowired
    private ReaderRepository readerRepository;
    private Reader reader1;
    private Reader reader2;
    private Reader reader3;

    @Before
    public void init(){
        LOGGER.info("init started");
        readerRepository.deleteAll();
        reader1 = new Reader("test1", "test1");
        reader2 = new Reader("test2", "test2");
        reader3 = new Reader("test3", "test3");
        readersService.save(
                ReaderDto.fromReaderToReaderDto(reader1)
        );
        readersService.save(
                ReaderDto.fromReaderToReaderDto(reader2)
        );
        readersService.save(
                ReaderDto.fromReaderToReaderDto(reader3)
        );
        LOGGER.info("init finished");
    }

    @After
    public void cleanUp(){
        LOGGER.info("cleanUp started");
        readerRepository.deleteAll();
        LOGGER.info("cleanUp finished");
    }

    @Test
    public void save() {
        LOGGER.info("save started");
        //given
        //init
        //when
        //init
        assertEquals(3, readerRepository.count());
        LOGGER.info("save finished");
    }


    @Test
    public void findAll() {
        LOGGER.info("findAll started");
        //given
        //init
        //when
        final List<Reader> testList = readersService.findAll();
        //then
        assertEquals(3, testList.size());
        LOGGER.info("findAll finished");
    }

    @Test
    public void findById() {
        LOGGER.info("findById started");
        //given
        //init
        //when
        final Optional<Reader> test = readersService.findById(reader1.getId());
        //then
        assertNotNull(test);
        LOGGER.info("findById finished");
    }
}