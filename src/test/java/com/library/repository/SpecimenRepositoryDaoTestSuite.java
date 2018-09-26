package com.library.repository;

import com.library.domain.Book;
import com.library.domain.Specimen;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpecimenRepositoryDaoTestSuite {
    @Autowired
    private BookRepositoryDao bookRepositoryDao;
    @Autowired
    private SpecimenRepositoryDao specimenRepositoryDao;

    private Book book;
    private Specimen specimen1;
    private Specimen specimen2;

    @Before
    public void init() {
        //given
        book = new Book("test", "test", "2000");
        specimen1 = new Specimen("good", book);
        specimen2 = new Specimen("not good", book);
        //when in save
        bookRepositoryDao.save(book);
        specimenRepositoryDao.save(specimen1);
        specimenRepositoryDao.save(specimen2);

    }

    @After
    public void cleanUp() {
        //cleanUp
//        specimenRepositoryDao.delete(specimen1);
//        specimenRepositoryDao.delete(specimen2);
//        bookRepositoryDao.delete(book);
    }

    @Test
    public void save() {
        //then
        assertEquals(2, specimenRepositoryDao.count());
        assertEquals(book.getId(), specimenRepositoryDao.findOne(specimen1.getId()).getBook().getId());
        assertEquals(book.getId(), specimenRepositoryDao.findOne(specimen2.getId()).getBook().getId());
        assertEquals(specimen1.getStatus(), specimenRepositoryDao.findOne(specimen1.getId()).getStatus());
        assertEquals(specimen2.getStatus(), specimenRepositoryDao.findOne(specimen2.getId()).getStatus());
    }

    @Test
    public void findAll() {
        //when
        final List<Specimen> specimensList = specimenRepositoryDao.findAll();
        //then
        assertEquals(2, specimensList.size());
    }

    @Test
    public void findById() {
        //when
        final Optional<Specimen> testSpecimen1 = specimenRepositoryDao.findById(specimen1.getId());
        final Optional<Specimen> testSpecimen2 = specimenRepositoryDao.findById(specimen2.getId());
        //then
        assertEquals(2, specimenRepositoryDao.count());
        assertEquals(specimen1.getId(), testSpecimen1.get().getId());
        assertEquals(specimen2.getId(), testSpecimen2.get().getId());
    }

    @Test
    public void setStatusOnRented(){
        specimen1.setStatus("rented");
        assertEquals("rented", specimen1.getStatus());
        specimenRepositoryDao.save(specimen1);
    }

}