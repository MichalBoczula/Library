package com.library.domain;

import com.library.repository.BookRepository;
import com.library.repository.ReaderRepository;
import com.library.repository.RentRepository;
import com.library.repository.SpecimenRepository;
import com.library.service.RentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentTestSuite {
    @Autowired
    private RentRepository rentRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private SpecimenRepository specimenRepository;

    @Test
    public void get(){
        System.out.println(
                bookRepository.findById((long) 1)
                        .get().getCreatedTime());
    }
}