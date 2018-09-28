package com.library.controller;

import com.library.domain.Specimen;
import com.library.domain.SpecimenDto;
import com.library.service.SpecimenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library/specimens")
public class SpecimenController {
    @Autowired
    private SpecimenService specimenService;

    @GetMapping
    public List<SpecimenDto> getSpecimens() {
        final List<Specimen> specimens = specimenService.findAll();
        return SpecimenDto.fromSpecimenListToSpecimenDtoList(specimens);
    }

    @GetMapping("{id}")
    public SpecimenDto getSpecimen(@PathVariable("id") final Long specimenId) {
        final Specimen specimen = specimenService.findById(specimenId);
        return SpecimenDto.fromSpecimenToSpecimenDto(specimen);
    }

    @PostMapping("/createSpecimen")
    public void createSpecimen(@RequestBody final SpecimenDto specimenDto) {
        final Specimen specimen = SpecimenDto.fromSpecimenDtoToSpecimen(specimenDto);
        specimenService.save(specimen);
    }

    @PutMapping("/return/{id}")
    public SpecimenDto returnSpecimen(@PathVariable("id") final Long specimenId) {
        final Specimen specimen = specimenService.findById(specimenId);
        specimen.setStatus(Specimen.SpecimenStatus.IN_LIBRARY);
        return SpecimenDto.fromSpecimenToSpecimenDto(specimen);
    }

}
