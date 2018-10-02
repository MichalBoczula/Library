package com.library.repository;

import com.library.domain.Book;
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
public class BookRepositoryTestSuite {
    @Autowired
    private BookRepository bookRepository;

    private Book book1;
    private Book book2;
    private Book book3;

    @Before
    public void init() {
        book1 = new Book("test1", "test test1", "2001");
        book3 = new Book("test2", "test test2", "2002");
        book2 = new Book("test3", "test test3", "2003");
        bookRepository.saveAndFlush(book1);
        bookRepository.saveAndFlush(book2);
        bookRepository.saveAndFlush(book3);
    }

    @After
    public void cleanUp() {
        bookRepository.delete(book1);
        bookRepository.delete(book2);
        bookRepository.delete(book3);
    }

    @Test
    public void save() {
        //given
        //init
        //when
        //init
        //then
        assertEquals(3, bookRepository.count());
    }

    @Test
    public void findAll() {
        //given
        //init
        //when
        final List<Book> books = bookRepository.findAll();
        //then
        assertEquals(3, books.size());
    }

    @Test
    public void findById() {
        //given
        //init
        //when
        final Optional<Book> testBook = bookRepository.findById(book1.getId());
        //then
        assertEquals(
                testBook.get().getId(),
                bookRepository.findById(book1.getId()).get().getId()
        );
        assertEquals(
                testBook.get().getTitle(),
                bookRepository.findById(book1.getId()).get().getTitle()
        );
        assertEquals(
                testBook.get().getAuthor(),
                bookRepository.findById(book1.getId()).get().getAuthor()
        );
        assertEquals(
                testBook.get().getDateOfPublication(),
                bookRepository.findById(book1.getId()).get().getDateOfPublication()
        );
        assertEquals(
                testBook.get().getCreatedTime(),
                bookRepository.findById(book1.getId()).get().getCreatedTime()
        );
        assertEquals(
                testBook.get().getLastModifiedTime(),
                bookRepository.findById(book1.getId()).get().getLastModifiedTime()
        );
    }
}