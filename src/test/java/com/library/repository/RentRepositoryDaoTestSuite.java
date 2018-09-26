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

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentRepositoryDaoTestSuite {
    @Autowired
    private BookRepositoryDao bookRepositoryDao;
    @Autowired
    private SpecimenRepositoryDao specimenRepositoryDao;
    @Autowired
    private ReaderRepositoryDao readerRepositoryDao;
    @Autowired
    private RentRepositoryDao rentRepositoryDao;

    private Book book1;
    private Book book2;
    private Specimen specimen1;
    private Specimen specimen2;
    private Reader reader;
    private Rent rent1;
    private Rent rent2;

    @Before
    public void init() {
        //given
        book1 = new Book("test1", "testtest1", "2001");
        book2 = new Book("test2", "testtest2", "2002");
        specimen1 = new Specimen("good", book1);
        specimen2 = new Specimen("good", book2);
        reader = new Reader("test", "test");
        rent1 = new Rent(specimen1, reader);
        rent2 = new Rent(specimen2, reader);
        //when in save
        bookRepositoryDao.save(book1);
        bookRepositoryDao.save(book2);
        specimenRepositoryDao.save(specimen1);
        specimenRepositoryDao.save(specimen2);
        readerRepositoryDao.save(reader);
        rentRepositoryDao.save(rent1);
        rentRepositoryDao.save(rent2);
    }

    @After
    public void cleanUp() {
        //cleanUp
        rentRepositoryDao.delete(rent1);
        rentRepositoryDao.delete(rent2);
        specimenRepositoryDao.delete(specimen1);
        specimenRepositoryDao.delete(specimen2);
        bookRepositoryDao.delete(book1);
        bookRepositoryDao.delete(book2);
        readerRepositoryDao.delete(reader);
    }

    @Test
    public void save() {
        //then
        assertEquals(2, rentRepositoryDao.count());
        assertEquals(rent1.getReader().getId(), rentRepositoryDao.findOne(rent1.getId()).getReader().getId());
        assertEquals(rent1.getRentDate().getDate(), rentRepositoryDao.findOne(rent1.getId()).getRentDate().getDate());
        assertEquals(rent1.getSpecimen().getId(), rentRepositoryDao.findOne(rent1.getId()).getSpecimen().getId());
        assertEquals(null, rent1.getReturnDate());
    }

    @Test
    public void findAll() {
        //when
        final List<Rent> rentList = rentRepositoryDao.findAll();
        assertEquals(2, rentList.size());
    }

    @Test
    public void findById() {
        //when
        final Optional<Rent> testR1 = rentRepositoryDao.findById(rent1.getId());
        final Optional<Rent> testR2 = rentRepositoryDao.findById(rent2.getId());
        //then
        assertEquals(2, rentRepositoryDao.count());
        assertEquals(rent1.getId(), testR1.get().getId());
        assertEquals(rent2.getId(), testR2.get().getId());
    }

    @Test
    public void setReturnDate(){
        //when
        rentRepositoryDao.setReturnDate(rent1.getId());
        //then
        assertNotNull(rent1.getReturnDate());
        //cleanUp
    }
}