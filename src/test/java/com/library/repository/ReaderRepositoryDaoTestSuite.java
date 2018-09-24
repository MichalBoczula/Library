package com.library.repository;

import com.library.domain.Reader;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReaderRepositoryDaoTestSuite {
    @Autowired
    private ReaderRepositoryDao readerRepositoryDao;

    @Test
    public void save() {
        //given
        final Reader reader = new Reader("test1", "test1");
        //when
        readerRepositoryDao.save(reader);
        //then
        assertEquals(1, readerRepositoryDao.count());
        //cleanUp
        readerRepositoryDao.delete(reader);
    }

    @Test
    public void findAll() {
        //given
        final Reader reader1 = new Reader("test", "test test");
        final Reader reader2 = new Reader("test", "test test");
        final Reader reader3 = new Reader("test", "test test");
        readerRepositoryDao.save(reader1);
        readerRepositoryDao.save(reader2);
        readerRepositoryDao.save(reader3);
        //when
        final List<Reader> testList = readerRepositoryDao.findAll();
        //then
        assertEquals(3, testList.size());
        testList.forEach(reader -> {
            assertEquals("test", reader.getFirstName());
            assertEquals("test test", reader.getLastName());
        });
        //cleanUp
        readerRepositoryDao.delete(reader1);
        readerRepositoryDao.delete(reader2);
        readerRepositoryDao.delete(reader3);
    }

    @Test
    public void findById() {
        //given
        final Reader reader1 = new Reader("test", "test test");
        readerRepositoryDao.save(reader1);
        //when
        final Optional<Reader> testReader = readerRepositoryDao.findById(reader1.getId());
        //then
        assertEquals(reader1.getId(), testReader.get().getId());
        assertEquals(reader1.getFirstName(), testReader.get().getFirstName());
        assertEquals(reader1.getLastName(), testReader.get().getLastName());
        //cleanUp
        readerRepositoryDao.delete(reader1);
    }
}