package com.library.mapper;

import com.library.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org  .springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentMapperTestSuite {
    @Autowired
    private RentMapper rentMapper;

    @Test
    public void mapToRent(){
        //given
        final RentDto rentDto = new RentDto(
                1L,
                new Date(),
                new Date(),
                new Specimen("good",
                        new Book("test", "test", "2000")),
                new Reader("test", "test")
        );
        final RentDto rentDtoNull = null;
        //when
        final Rent rent = rentMapper.mapToRent(rentDto);
        final Rent rentNull = rentMapper.mapToRent(rentDtoNull);
        //then
        assertNotNull(rentNull);
        assertEquals(rent.getId(), rentDto.getId());
        assertEquals(rent.getSpecimen().getId(), rentDto.getSpecimen().getId());
        assertEquals(rent.getReader().getId(), rentDto.getReader().getId());
    }

    @Test
    public void mapToRentDto(){
        //given
        final Rent rent = new Rent(
                1L,
                new Date(),
                new Date(),
                new Specimen("good",
                        new Book("test", "test", "2000")),
                new Reader("test", "test")
        );
        final Rent rentNull = null;
        //when
        final RentDto rentDto = rentMapper.mapToRentDto(rent);
        final RentDto rentDtoNull = rentMapper.mapToRentDto(rentNull);
        //then
        assertNotNull(rentDtoNull);
        assertEquals(rent.getId(), rentDto.getId());
        assertNotNull(rentDto.getRentDate());
        assertNotNull(rentDto.getRentDate());
        assertEquals(rentDto.getSpecimen().getId(), rent.getSpecimen().getId());
        assertEquals(rentDto.getReader().getId(), rent.getReader().getId());
    }

    @Test
    public void mapToRentDtoList(){
        //given
        final Rent rent = new Rent(
                1L,
                new Date(),
                new Date(),
                new Specimen("good",
                        new Book("test", "test", "2000")),
                new Reader("test", "test")
        );
        final List<Rent> rentList = new ArrayList<>();
        final List<Rent> rentListNull = null;
        rentList.add(rent);
        //when
        final List<RentDto> rentDtos = rentMapper.mapToRentDtoList(rentList);
        final List<RentDto> rentDtosNull = rentMapper.mapToRentDtoList(rentListNull);
        //then
        assertEquals(1, rentDtos.size());
        assertNotNull(rentDtosNull);
    }
}