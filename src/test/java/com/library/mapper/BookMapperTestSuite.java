package com.library.mapper;

import com.library.domain.Book;
import com.library.domain.BookDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookMapperTestSuite {
    @Autowired
    private BookMapper bookMapper;

    @Test
    public void testMapToBook() {
        //given
        final BookDto testBookDto = new BookDto(1L, "test", "test", "2000", new ArrayList<>());
        //when
        final Book testBook = bookMapper.mapToBook(testBookDto);
        //then
        assertEquals(testBookDto.getId(), testBook.getId());
        assertEquals(testBookDto.getTitle(), testBook.getTitle());
        assertEquals(testBookDto.getAuthor(), testBook.getAuthor());
        assertEquals(testBookDto.getDateOfPublication(), testBook.getDateOfPublication());
        assertEquals(testBookDto.getSpecimens(), testBook.getSpecimens());
    }

    @Test
    public void testMapToBookWithNull(){
        //given
        final Book testNullBook = null;
        //when
        final BookDto testBookDto = bookMapper.mapToBookDto(testNullBook);
        long bookId = testBookDto.getId();
        //then
        assertEquals(0L, bookId);
        assertEquals("", testBookDto.getTitle());
        assertEquals("", testBookDto.getAuthor());
        assertEquals("", testBookDto.getDateOfPublication());
        assertNotNull(testBookDto.getSpecimens());
    }

    @Test
    public void testMapToBookDto() {
        //given
        final Book testBook = new Book(1L,"test", "test", "2000", new ArrayList<>());
        //when
        final BookDto testBookDto = bookMapper.mapToBookDto(testBook);
        //then
        assertEquals(testBook.getId(), testBookDto.getId());
        assertEquals(testBook.getTitle(), testBookDto.getTitle());
        assertEquals(testBook.getAuthor(), testBookDto.getAuthor());
        assertEquals(testBook.getDateOfPublication(), testBookDto.getDateOfPublication());
        assertEquals(testBook.getSpecimens(), testBookDto.getSpecimens());

    }

    @Test
    public void testMapToBookDtoWithNull(){
        //given
        final BookDto testNullBookDto = null;
        //when
        final Book testBook = bookMapper.mapToBook(testNullBookDto);
        long id = testBook.getId();
        //then
        assertEquals(0L, id);
        assertEquals("", testBook.getTitle());
        assertEquals("", testBook.getAuthor());
        assertEquals("", testBook.getDateOfPublication());
        assertNotNull(testBook.getSpecimens());
    }

    @Test
    public void testMapToBookDtoList() {
        //given
        final List<Book> testBookList = new ArrayList<>();
        final Book testBook1 = new Book("test1", "test1", "2001");
        final Book testBook2 = new Book("test2", "test2", "2002");
        final Book testBook3 = new Book("test3", "test3", "2003");
        testBookList.add(testBook1);
        testBookList.add(testBook2);
        testBookList.add(testBook3);
        //when
        final List<BookDto> testBookDtoList = bookMapper.mapToBookDtoList(testBookList);
        //then
        assertEquals(testBookList.size(), testBookDtoList.size());
        assertEquals(testBookList.get(0).getId(), testBookDtoList.get(0).getId());
        assertEquals(testBookList.get(1).getId(), testBookDtoList.get(1).getId());
        assertEquals(testBookList.get(2).getId(), testBookDtoList.get(2).getId());
        assertEquals(testBookList.get(0).getTitle(), testBookDtoList.get(0).getTitle());
        assertEquals(testBookList.get(1).getTitle(), testBookDtoList.get(1).getTitle());
        assertEquals(testBookList.get(2).getTitle(), testBookDtoList.get(2).getTitle());
        assertEquals(testBookList.get(0).getAuthor(), testBookDtoList.get(0).getAuthor());
        assertEquals(testBookList.get(1).getAuthor(), testBookDtoList.get(1).getAuthor());
        assertEquals(testBookList.get(2).getAuthor(), testBookDtoList.get(2).getAuthor());
        assertEquals(testBookList.get(0).getDateOfPublication(), testBookDtoList.get(0).getDateOfPublication());
        assertEquals(testBookList.get(1).getDateOfPublication(), testBookDtoList.get(1).getDateOfPublication());
        assertEquals(testBookList.get(2).getDateOfPublication(), testBookDtoList.get(2).getDateOfPublication());
    }

    @Test
    public void testMapToBookDtoListWithNull(){
        //given
        final List<Book> nullList = null;
        //when
        final List<BookDto> bookDtoList = bookMapper.mapToBookDtoList(nullList);
        //then
        assertNotNull(bookDtoList);
        assertEquals(0, bookDtoList.size());
    }

}