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
public class BookRepositoryDaoTestSuite {
    @Autowired
    private BookRepositoryDao bookRepositoryDao;

    @Test
    public void save() {
        //given
        final Book book = new Book("test title", "test author", "2000");
        //when
        final Book testBook = bookRepositoryDao.save(book);
        //then
        assertEquals(1, bookRepositoryDao.count());
        assertEquals(book.getTitle(), testBook.getTitle());
        assertEquals(book.getAuthor(), testBook.getAuthor());
        assertEquals(book.getDateOfPublication(), testBook.getDateOfPublication());
        //cleanUp
        bookRepositoryDao.delete(book);
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
        bookRepositoryDao.save(book1);
        bookRepositoryDao.save(book2);
        bookRepositoryDao.save(book3);
        //when
        final List<Book> testList = bookRepositoryDao.findAll();
        //then
        assertEquals(3, testList.size());
        testList.forEach(book -> {
            assertEquals("test title", book.getTitle());
            assertEquals("test author", book.getAuthor());
            assertEquals("2000", book.getDateOfPublication());
        });
        //cleanUp
        bookRepositoryDao.delete(book1);
        bookRepositoryDao.delete(book2);
        bookRepositoryDao.delete(book3);
    }

    @Test
    public void findById(){
        //given
        final Book book = new Book("test", "test", "2000");
        bookRepositoryDao.save(book);
        //when
        final Optional<Book> testBook = bookRepositoryDao.findById(book.getId());
        //then
        assertNotNull(testBook);
        assertEquals(book.getId(), testBook.get().getId());
        //cleanUp
        bookRepositoryDao.delete(book);
    }

}