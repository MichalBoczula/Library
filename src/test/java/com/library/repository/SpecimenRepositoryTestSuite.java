package com.library.repository;

import com.library.domain.Book;
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

import static com.library.domain.Specimen.SpecimenStatus.DESTROYED;
import static com.library.domain.Specimen.SpecimenStatus.IN_LIBRARY;
import static com.library.domain.Specimen.SpecimenStatus.RENTED;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpecimenRepositoryTestSuite {
    @Autowired
    private SpecimenRepository specimenRepository;
    @Autowired

    private BookRepository bookRepository;
    private Book book;
    private Specimen specimen1;
    private Specimen specimen2;
    private Specimen specimen3;

    @Before
    public void init() {
        book = new Book("test", "test test", "2000");
        specimen1 = new Specimen(IN_LIBRARY, book);
        specimen2 = new Specimen(RENTED, book);
        specimen3 = new Specimen(DESTROYED, book);
        bookRepository.save(book);
        specimenRepository.save(specimen1);
        specimenRepository.save(specimen2);
        specimenRepository.save(specimen3);
    }

    @After
    public void cleanUp(){
        specimenRepository.delete(specimen1);
        specimenRepository.delete(specimen2);
        specimenRepository.delete(specimen3);
    }

    @Test
    public void save() {
        //given
        //init
        //when
        //init
        //then
        assertEquals(3, specimenRepository.count());
    }

    @Test
    public void findAll() {
        //given
        //init
        //when
        final List<Specimen> specimens = specimenRepository.findAll();
        //then
        assertEquals(3, specimens.size());
    }

    @Test
    public void findById() {
        //given
        //init
        //when
        final Optional<Specimen> testSpecimen = specimenRepository.findById(specimen1.getId());
        //then
        assertEquals(specimen1.getId(), testSpecimen.get().getId());
        assertEquals(specimen1.getStatus(), testSpecimen.get().getStatus());
        assertEquals(specimen1.getCreatedTime(), testSpecimen.get().getCreatedTime());
        assertEquals(specimen1.getLastModifiedTime(), testSpecimen.get().getLastModifiedTime());
    }
}