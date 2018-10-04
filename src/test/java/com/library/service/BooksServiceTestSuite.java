package com.library.service;

import com.library.domain.Book;
import com.library.dto.BookDto;
import com.library.repository.BookRepository;
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
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BooksServiceTestSuite {
    private static final Logger LOGGER = LoggerFactory.getLogger(BooksServiceTestSuite.class);
    @Autowired
    private BooksService booksService;
    @Autowired
    private BookRepository repository;
    private Book book1;
    private Book book2;
    private Book book3;

    @Before
    public void init() {
        LOGGER.info("init started");
        repository.deleteAll();
        book1 = new Book((long) 1, "test1", "test test1", "2001");
        book2 = new Book((long) 2, "test2", "test test2", "2002");
        book3 = new Book((long) 3, "test3", "test test3", "2003");
        booksService.save(
                BookDto.fromBookToBookDto(book1)
        );
        booksService.save(
                BookDto.fromBookToBookDto(book2)
        );
        booksService.save(
                BookDto.fromBookToBookDto(book3)
        );
        LOGGER.info("init finished");
    }

    @After
    public void cleanUp() {
        LOGGER.info("cleanUp started");
        repository.deleteAll();
        LOGGER.info("cleanUp finished");
    }

    @Test
    public void save() {
        LOGGER.info("save started");
        //given
        //init
        //when
        //init
        //then
        assertEquals(3, booksService.findAll().size());
        LOGGER.info("save finished");
    }

    @Test
    public void findById() {
        LOGGER.info("findById started");
        //given
        //init
        //when
        final Optional<Book> testBook = booksService.findById(book1.getId());
        assertNotNull(testBook);
        LOGGER.info("findById finished");
    }

    @Test
    public void findAll() {
        LOGGER.info("findAll started");
        //given
        //init
        //when
        final List<Book> testList = booksService.findAll();
        //then
        assertEquals(3, testList.size());
        LOGGER.info("findAll finished");
    }
}