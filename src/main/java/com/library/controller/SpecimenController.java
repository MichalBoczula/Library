package com.library.controller;

import com.library.domain.SpecimenDto;
import com.library.mapper.SpecimenMapper;
import com.library.service.DbServiceSpecimen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/library/books")
public class SpecimenController {
    @Autowired
    private DbServiceSpecimen dbServiceSpecimen;
    @Autowired
    private SpecimenMapper specimenMapper;

    @RequestMapping(method = GET, value = "getSpecimens")
    public List<SpecimenDto> getSpecimens() {
        return specimenMapper.mapToSpecimenDtoList(dbServiceSpecimen.findAll());
    }

    @RequestMapping(method = GET, value = "getSpecimen")
    public SpecimenDto getSpecimen(@RequestParam final Long specimenId) throws SpecimenNotFoundException {
        return specimenMapper.mapToSpecimenDto(dbServiceSpecimen.findById(specimenId).orElseThrow(SpecimenNotFoundException::new));
    }

    @RequestMapping(method = POST, value = "createSpecimen", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createSpecimen(@RequestBody final SpecimenDto specimenDto){
        dbServiceSpecimen.save(specimenMapper.mapToSpecimen(specimenDto));
    }

    @RequestMapping(method = DELETE, value = "deleteSpecimen")
    public void deleteSpecimen(@RequestParam final Long specimenId) throws SpecimenNotFoundException {
        dbServiceSpecimen.delete(dbServiceSpecimen.findById(specimenId).orElseThrow(SpecimenNotFoundException::new));
    }

    @RequestMapping(method = PUT, value = "updateSpecimen")
    public SpecimenDto updateSpecimen(@RequestBody final SpecimenDto specimenDto){
        return specimenMapper.mapToSpecimenDto(dbServiceSpecimen.save(specimenMapper.mapToSpecimen(specimenDto)));
    }

}
