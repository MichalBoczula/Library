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

import java.util.List;
import java.util.stream.Collectors;

import static com.library.domain.Specimen.SpecimenStatus.IN_LIBRARY;
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
    private Book book1;
    private Book book2;
    private Specimen specimen1;
    private Specimen specimen2;
    private Specimen specimen3;
    private Specimen specimen4;
    private Specimen specimen5;
    private Reader reader;
    private Rent rent;
    @Autowired
    private SpecimenService specimenService;


    @Before
    public void init(){
        LOGGER.info("init started");
        rentRepository.deleteAll();
        specimenRepository.deleteAll();
        bookRepository.deleteAll();
        readerRepository.deleteAll();
        book1 = new Book("test", "test", "2000");
        book2 = new Book("try", "try", "2000");
        specimen1 = new Specimen(Specimen.SpecimenStatus.IN_LIBRARY, book1);
        specimen2 = new Specimen(Specimen.SpecimenStatus.IN_LIBRARY, book1);
        specimen3 = new Specimen(Specimen.SpecimenStatus.IN_LIBRARY, book2);
        specimen4 = new Specimen(Specimen.SpecimenStatus.RENTED, book1);
        specimen5 = new Specimen(Specimen.SpecimenStatus.DESTROYED, book2);
        reader = new Reader("test", "test");
        rent = new Rent(specimen1, reader);
        readerRepository.saveAndFlush(reader);
        bookRepository.saveAndFlush(book1);
        bookRepository.saveAndFlush(book2);
        specimenRepository.saveAndFlush(specimen1);
        specimenRepository.saveAndFlush(specimen2);
        specimenRepository.saveAndFlush(specimen3);
        specimenRepository.saveAndFlush(specimen4);
        specimenRepository.saveAndFlush(specimen5);
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
        final Specimen test = validator.validateSpecimenIsAvailable(specimen1.getId());
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

    @Test
    public void findAvailableSpecimenInLibrary() {
        final List<Specimen> specimenList = specimenService.findAll();
        final List<Specimen> availableSpecimens = specimenList.stream()
                .filter(s1 -> s1.getStatus().equals(IN_LIBRARY))
                .collect(Collectors.toList());
        final List<Specimen> avl = availableSpecimens.stream()
                .filter(s2 -> s2.getBook().getTitle().equals(book2.getTitle()))
                .collect(Collectors.toList());
        System.out.println(availableSpecimens.size());
        System.out.println(avl.size());
    }
}