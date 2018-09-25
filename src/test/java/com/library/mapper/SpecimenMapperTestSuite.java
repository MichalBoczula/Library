package com.library.mapper;

import com.library.domain.Book;
import com.library.domain.Specimen;
import com.library.domain.SpecimenDto;
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
public class SpecimenMapperTestSuite {
    @Autowired
    private SpecimenMapper specimenMapper;

    @Test
    public void mapToSpecimen() {
        //given
        final Specimen specimen = new Specimen(
                1L,
                "good",
                new Book("test", "test", "2000"));
        final Specimen specimenNull = null;
        //when
        final SpecimenDto specimenDto = specimenMapper.mapToSpecimenDto(specimen);
        final SpecimenDto specimenDtoNull = specimenMapper.mapToSpecimenDto(specimenNull);
        //then
        assertEquals(specimen.getId(), specimenDto.getId());
        assertEquals(specimen.getStatus(), specimenDto.getStatus());
        assertEquals(specimen.getBook().getId(), specimenDto.getBook().getId());
        assertNotNull(specimenDtoNull);
    }

    @Test
    public void mapToSpecimenDto() {
        //given
        final SpecimenDto specimenDto = new SpecimenDto(
                1L,
                "good",
                new Book("test", "test", "2000"));
        final SpecimenDto specimenDtoNull = null;
        //when
        final Specimen specimen = specimenMapper.mapToSpecimen(specimenDto);
        final Specimen specimenNull = specimenMapper.mapToSpecimen(specimenDtoNull);
        //then
        assertEquals(specimenDto.getStatus(), specimen.getStatus());
        assertEquals(specimenDto.getBook().getId(), specimen.getBook().getId());
        assertNotNull(specimenNull);
    }

    @Test
    public void mapToSpecimenDtoList(){
        //given
        final List<Specimen> specimens = new ArrayList<>();
        final Specimen specimen = new Specimen(
                1L,
                "good",
                new Book("test", "test", "2000"));
        final List<Specimen> specimensNull = null;
        specimens.add(specimen);
        //when
        final List<SpecimenDto> specimenDtos = specimenMapper.mapToSpecimenDtoList(specimens);
        final List<SpecimenDto> specimenDtosNull =specimenMapper.mapToSpecimenDtoList(specimensNull);
        //then
        assertNotNull(specimenDtosNull);
        assertEquals(1, specimenDtos.size());
    }
}