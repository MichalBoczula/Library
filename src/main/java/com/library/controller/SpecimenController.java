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

    @PostMapping("/create")
    public SpecimenDto createSpecimen(@RequestBody final Specimen specimen) {
        return SpecimenDto.fromSpecimenToSpecimenDto(
                specimenService.save(specimen)
        );
    }

    @PutMapping("/update")
    public SpecimenDto returnSpecimen(@RequestBody final Specimen specimen) {
        return SpecimenDto.fromSpecimenToSpecimenDto(
                specimenService.save(specimen)
        );
    }


}
