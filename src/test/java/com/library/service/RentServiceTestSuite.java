package com.library.service;

import com.library.domain.Book;
import com.library.domain.Reader;
import com.library.domain.Rent;
import com.library.domain.Specimen;
import com.library.dto.RentDto;
import com.library.repository.BookRepository;
import com.library.repository.ReaderRepository;
import com.library.repository.RentRepository;
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
public class RentServiceTestSuite {
    private static final Logger LOGGER = LoggerFactory.getLogger(RentServiceTestSuite.class);
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private SpecimenRepository specimenRepository;
    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private RentRepository rentRepository;
    @Autowired
    private RentService rentService;
    private Book book;
    private Specimen specimen;
    private Reader reader;
    private Rent rent;

    @Before
    public void init(){
        LOGGER.info("init started");
        rentRepository.deleteAll();
        specimenRepository.deleteAll();
        bookRepository.deleteAll();
        readerRepository.deleteAll();
        book = new Book("test", "test", "2000");
        specimen = new Specimen(Specimen.SpecimenStatus.IN_LIBRARY, book);
        reader = new Reader("test", "test");
        rent = new Rent(specimen, reader);
        readerRepository.saveAndFlush(reader);
        bookRepository.saveAndFlush(book);
        specimenRepository.saveAndFlush(specimen);
        rentService.save(
                RentDto.fromRentToRentDto(rent)
        );
        LOGGER.info("init finished");
    }

    @After
    public void cleanUp(){
        LOGGER.info("cleanUp started");
        rentRepository.deleteAll();
        specimenRepository.deleteAll();
        bookRepository.deleteAll();
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
        //then
        assertEquals(1, rentService.findAll().size());
        LOGGER.info("save finished");
    }

    @Test
    public void findAll() {
        LOGGER.info("findAll started");
        //given
        //init
        //when
        final List<Rent> rents = rentService.findAll();
        //then
        assertEquals(1, rents.size());
        LOGGER.info("findAll finished");
    }

    @Test
    public void findById() {
        LOGGER.info("findById started");
        //given
        //init
        //when
        final Optional<Rent> test = rentService.findById(rent.getId());
        //then
        assertNotNull(test);
        LOGGER.info("findById finished");
    }
}