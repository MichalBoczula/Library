package com.library.service;

import com.library.domain.Book;
import com.library.domain.Specimen;
import com.library.dto.SpecimenDto;
import com.library.repository.BookRepository;
import com.library.repository.SpecimenRepository;
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
public class SpecimenServiceTestSuite {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpecimenServiceTestSuite.class);
    @Autowired
    private SpecimenService specimenService;
    @Autowired
    private SpecimenRepository specimenRepository;
    @Autowired
    private BookRepository bookRepository;
    private Book book;
    private Specimen specimen1;
    private Specimen specimen2;

    @Before
    public void init(){
        LOGGER.info("init started");
        specimenRepository.deleteAll();
        bookRepository.deleteAll();
        book = new Book("test1", "test test1", "2000");
        specimen1 = new Specimen((long) 1, Specimen.SpecimenStatus.IN_LIBRARY, book);
        specimen2 = new Specimen((long) 2, Specimen.SpecimenStatus.IN_LIBRARY, book);
        bookRepository.save(book);
        specimenService.save(
                SpecimenDto.fromSpecimenToSpecimenDto(specimen1)
        );
        specimenService.save(
                SpecimenDto.fromSpecimenToSpecimenDto(specimen2)
        );
        LOGGER.info("init finished");
    }

    @After
    public void cleanUp(){
        LOGGER.info("cleanUp started");
        specimenRepository.deleteAll();
        bookRepository.deleteAll();
        LOGGER.info("cleanUp finished");
    }

    @Test
    public void save() {
        LOGGER.info("save started");
        //init
        //when
        //given
        //init
        //then
        assertEquals(2, specimenService.findAll().size());
        LOGGER.info("save finished");
    }

    @Test
    public void findAll() {
        LOGGER.info("findAll started");
        //given
        //init
        //when
        final List<Specimen> specimens = specimenService.findAll();
        //then
        assertEquals(2, specimens.size());
        LOGGER.info("findAll finished");
    }

    @Test
    public void findById() {
        LOGGER.info("findById started");
        //given
        //init
        //when
        final Optional<Specimen> specimenTest = specimenService.findById(specimen1.getId());
        //then
        assertNotNull(specimenTest);
        LOGGER.info("findById finished");
    }
}