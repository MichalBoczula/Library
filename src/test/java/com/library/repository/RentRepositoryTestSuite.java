package com.library.repository;

import com.library.domain.Book;
import com.library.domain.Reader;
import com.library.domain.Rent;
import com.library.domain.Specimen;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentRepositoryTestSuite {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private SpecimenRepository specimenRepository;
    @Autowired
    private RentRepository rentRepository;

    private Book book;
    private Reader reader;
    private Specimen specimen;
    private Rent rent;

    @Before
    public void init(){
        book = new Book("test", "test test", "2000");
        reader = new Reader("testFirstName", "testLastName");
        specimen = new Specimen(Specimen.SpecimenStatus.IN_LIBRARY, book);
        rent = new Rent(specimen, reader);
        bookRepository.saveAndFlush(book);
        readerRepository.saveAndFlush(reader);
        specimenRepository.saveAndFlush(specimen);
        rentRepository.saveAndFlush(rent);
    }

    @After
    public void cleanUp(){
        rentRepository.deleteAll();
        specimenRepository.deleteAll();
        bookRepository.deleteAll();
        readerRepository.deleteAll();
    }

    @Test
    public void save(){
        //given
        //init
        //when
        //init
        //then
        assertEquals(1, rentRepository.count());
    }


    @Test
    public void findAll(){
        //given
        //init
        //when
        final List<Rent> rents = rentRepository.findAll();
        //then
        assertEquals(1, rents.size());
    }

    @Test
    public void findById(){
        //given
        //init
        //when
        final Optional<Rent> testRent = rentRepository.findById(rent.getId());
        //then
        assertEquals(rent.getId(), testRent.get().getId());
        assertEquals(rent.getRentDate(), testRent.get().getRentDate());
        assertEquals(rent.getLastModifiedData(), testRent.get().getLastModifiedData());
        assertEquals(rent.getReturnDate(), testRent.get().getReturnDate());
    }

}