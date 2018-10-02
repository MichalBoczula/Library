package com.library.repository;

import com.library.domain.Reader;
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
public class ReaderRepositoryTestSuite {
    @Autowired
    private ReaderRepository readerRepository;

    private Reader reader1;
    private Reader reader2;
    private Reader reader3;

    @Before
    public void init(){
        reader1 = new Reader("testFirstName1", "testLastName1");
        reader2 = new Reader("testFirstName2", "testLastName2");
        reader3 = new Reader("testFirstName3", "testLastName3");
        readerRepository.saveAndFlush(reader1);
        readerRepository.saveAndFlush(reader2);
        readerRepository.saveAndFlush(reader3);
    }

    @After
    public void cleanUp(){
        readerRepository.delete(reader1);
        readerRepository.delete(reader2);
        readerRepository.delete(reader3);
    }

    @Test
    public void save(){
        //given
        //init
        //when
        //init
        //then
        assertEquals(3, readerRepository.count());
    }

    @Test
    public void finaAll(){
        //given
        //init
        //when
        final List<Reader> readers = readerRepository.findAll();
        //then
        assertEquals(3, readerRepository.count());
    }

    @Test
    public void findById(){
        //given
        //init
        //when
        final Optional<Reader> reader = readerRepository.findById(reader1.getId());
        //then
        assertEquals(reader1.getId(), reader.get().getId());
        assertEquals(reader1.getFirstName(), reader.get().getFirstName());
        assertEquals(reader1.getLastName(), reader.get().getLastName());
        assertEquals(reader1.getDateOfAccountCreation(), reader.get().getDateOfAccountCreation());
        assertEquals(reader1.getLastModifiedDate(), reader.get().getLastModifiedDate());
    }
}