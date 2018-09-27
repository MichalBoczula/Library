package com.library.repository;

import com.library.domain.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTestSuite {
    @Autowired
    private BookRepository bookRepository;

    @Test
    public void save() {
        //given
        final Book book = new Book("test title", "test author", "2000");
        //when
        final Book testBook = bookRepository.save(book);
        //then
        assertEquals(1, bookRepository.count());
        assertEquals(book.getTitle(), testBook.getTitle());
        assertEquals(book.getAuthor(), testBook.getAuthor());
        assertEquals(book.getDateOfPublication(), testBook.getDateOfPublication());
        //cleanUp
        bookRepository.delete(book);
    }

    @Test
    public void findAll(){
        //given
        final List<Book> bookList = new ArrayList<>();
        final Book book1 = new Book("test title", "test author", "2000");
        final Book book2 = new Book("test title", "test author", "2000");
        final Book book3 = new Book("test title", "test author", "2000");
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
        //when
        final List<Book> testList = bookRepository.findAll();
        //then
        assertEquals(3, testList.size());
        testList.forEach(book -> {
            assertEquals("test title", book.getTitle());
            assertEquals("test author", book.getAuthor());
            assertEquals("2000", book.getDateOfPublication());
        });
        //cleanUp
        bookRepository.delete(book1);
        bookRepository.delete(book2);
        bookRepository.delete(book3);
    }

    @Test
    public void findById(){
        //given
        final Book book = new Book("test", "test", "2000");
        bookRepository.save(book);
        //when
        final Optional<Book> testBook = bookRepository.findById(book.getId());
        //then
        assertNotNull(testBook);
        assertEquals(book.getId(), testBook.get().getId());
        //cleanUp
        bookRepository.delete(book);
    }

}