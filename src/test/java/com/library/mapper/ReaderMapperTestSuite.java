package com.library.mapper;

import com.library.domain.Reader;
import com.library.domain.ReaderDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReaderMapperTestSuite {
    @Autowired
    private ReaderMapper readerMapper;

    @Test
    public void testMapToReader() {
        //given
        final ReaderDto readerDto = new ReaderDto(
                (long) 1,
                "test1",
                "test11",
                new Date(2000, 1, 1)
        );
        //when
        final Reader reader = readerMapper.mapToReader(readerDto);
        //then
        assertEquals(readerDto.getFirstName(), reader.getFirstName());
        assertEquals(readerDto.getLastName(), reader.getLastName());
        assertEquals(readerDto.getDateOfAccountCreation(), reader.getDateOfAccountCreation());
    }

    @Test
    public void testMapToReaderWithNull() {
        //given
        final ReaderDto readerDto = null;
        //when
        final Reader reader = readerMapper.mapToReader(readerDto);
        //then
        assertEquals("", reader.getFirstName());
        assertEquals("", reader.getLastName());
        assertEquals(new Date(), reader.getDateOfAccountCreation());
    }

    @Test
    public void testMapToReaderDto() {
        //given
        final Reader reader = new Reader(
                "test1",
                "test11",
                new Date(2000, 1, 1)
        );
        //when
        final ReaderDto readerDto = readerMapper.mapToReaderDto(reader);
        //then
        assertEquals(reader.getId(), readerDto.getId());
        assertEquals(reader.getFirstName(), readerDto.getFirstName());
        assertEquals(reader.getLastName(), readerDto.getLastName());
        assertEquals(reader.getDateOfAccountCreation(), readerDto.getDateOfAccountCreation());
    }

    @Test
    public void testMapToReaderDtoWithNull() {
        //given
        final Reader reader = null;
        //when
        final ReaderDto readerDto = readerMapper.mapToReaderDto(reader);
        long readerId = readerDto.getId();
        //then
        assertEquals(000, readerId);
        assertEquals("", readerDto.getFirstName());
        assertEquals("", readerDto.getLastName());
        assertEquals(new Date(), readerDto.getDateOfAccountCreation());
    }

    @Test
    public void testMapToReaderDtoList() {
        //given
        final List<Reader> readerList = new ArrayList<>();
        final Reader reader1 = new Reader(
                "test1",
                "test11",
                new Date(2000, 1, 1)
        );
        final Reader reader2 = new Reader(
                "test2",
                "test22",
                new Date(2002, 2, 2)
        );
        final Reader reader3 = new Reader(
                "test3",
                "test33",
                new Date(2003, 3, 3)
        );
        readerList.add(reader1);
        readerList.add(reader2);
        readerList.add(reader3);
        //when
        final List<ReaderDto> readerDtoList = readerMapper.mapToReaderDtoList(readerList);
        //then
        assertEquals(readerList.size(), readerDtoList.size());
        assertEquals(readerList.get(0).getId(), readerDtoList.get(0).getId());
        assertEquals(readerList.get(0).getFirstName(), readerDtoList.get(0).getFirstName());
        assertEquals(readerList.get(0).getLastName(), readerDtoList.get(0).getLastName());
        assertEquals(readerList.get(0).getDateOfAccountCreation(), readerDtoList.get(0).getDateOfAccountCreation());
        assertEquals(readerList.get(1).getId(), readerDtoList.get(1).getId());
        assertEquals(readerList.get(1).getFirstName(), readerDtoList.get(1).getFirstName());
        assertEquals(readerList.get(1).getLastName(), readerDtoList.get(1).getLastName());
        assertEquals(readerList.get(1).getDateOfAccountCreation(), readerDtoList.get(1).getDateOfAccountCreation());
        assertEquals(readerList.get(2).getId(), readerDtoList.get(2).getId());
        assertEquals(readerList.get(2).getFirstName(), readerDtoList.get(2).getFirstName());
        assertEquals(readerList.get(2).getLastName(), readerDtoList.get(2).getLastName());
        assertEquals(readerList.get(2).getDateOfAccountCreation(), readerDtoList.get(2).getDateOfAccountCreation());
    }

    @Test
    public void testMapToReaderDtoListWithNull() {
        //given
        final List<Reader> nullList = null;
        //when
        final List<ReaderDto> readerDtoList = readerMapper.mapToReaderDtoList(nullList);
        //then
        assertNotNull(readerDtoList);
        assertEquals(0, readerDtoList.size());
    }
}