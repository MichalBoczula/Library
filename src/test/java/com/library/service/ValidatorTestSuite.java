package com.library.service;

import com.library.domain.Book;
import com.library.domain.Reader;
import com.library.domain.Rent;
import com.library.domain.Specimen;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidatorTestSuite {
    private static final Logger LOGGER = LoggerFactory.getLogger(ValidatorTestSuite.class);
    @Autowired
    private Validator validator;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private SpecimenRepository specimenRepository;
    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private RentRepository rentRepository;
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
        rentRepository.saveAndFlush(rent);
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
    public void validateSpecimenIsAvailable() {
        LOGGER.info("validateSpecimenIsAvailable started");
        //given
        //init
        //when
        final Specimen test = validator.validateSpecimenIsAvailable(specimen.getId());
        //test
        assertEquals(Specimen.SpecimenStatus.RENTED, test.getStatus());
        LOGGER.info("validateSpecimenIsAvailable finished");
    }

    @Test
    public void validateEndRent() {
        LOGGER.info("validateEndRent started");
        //given
        //init
        //when
        final Rent test = validator.validateEndRent(rent.getId());
        //assert
        assertEquals(Specimen.SpecimenStatus.IN_LIBRARY, test.getSpecimen().getStatus());
        LOGGER.info("validateEndRent finished");
    }

    @Test
    public void validateEndRentBookIsDestroyed() {
        LOGGER.info("validateEndRentBookIsDestroyed started");
        //given
        //init
        //when
        final Rent test = validator.validateEndRentBookIsDestroyed(rent.getId());
        //assert
        assertEquals(Specimen.SpecimenStatus.DESTROYED, test.getSpecimen().getStatus());
        LOGGER.info("validateEndRentBookIsDestroyed finished");
    }
}