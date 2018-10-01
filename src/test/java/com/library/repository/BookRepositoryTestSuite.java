package com.library.repository;

import com.library.domain.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTestSuite {
    @Autowired
    private BookRepository bookRepository;

    @Test
    public void  test(){
        Book book = new Book("test", "test test", "2000");
        bookRepository.saveAndFlush(book);
    }
}