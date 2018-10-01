package com.library.controller;

import com.library.domain.Reader;
import com.library.domain.Rent;
import com.library.domain.RentDto;
import com.library.domain.Specimen;
import com.library.service.ReadersService;
import com.library.service.RentService;
import com.library.service.SpecimenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static com.library.domain.Specimen.SpecimenStatus.DESTROYED;
import static com.library.domain.Specimen.SpecimenStatus.IN_LIBRARY;
import static com.library.domain.Specimen.SpecimenStatus.RENTED;

@RestController
@RequestMapping("/library/rents")
public class RentController {
    @Autowired
    private RentService rentService;
    @Autowired
    private ReadersService readersService;
    @Autowired
    private Validator validator;

    @GetMapping
    public List<RentDto> getRents() {
        return RentDto.fromRentListToRentDtoList(
                rentService.findAll()
        );
    }

    @GetMapping("{id}")
    public RentDto getRent(@PathVariable("id") final Long rentId) {
        return RentDto.fromRentToRentDto(
                rentService.findById(rentId)
        );
    }

    @PostMapping("/rentBook")
    public RentDto rentBook(@RequestParam final Long specimenId, @RequestParam final Long readerId) {
        final Specimen specimen = validator.validateSpecimenIsAvailable(specimenId);
        final Reader reader = readersService.findByID(readerId);
        final Rent rent = new Rent(specimen, reader);
        return RentDto.fromRentToRentDto(
                rentService.save(rent)
        );
    }

    @PutMapping("/returnBook")
    public RentDto returnBook(@RequestParam final Long rentId) {
        final Rent rent = validator.validateEndRent(rentId);
        return RentDto.fromRentToRentDto(
                rentService.save(rent)
        );
    }

    @PutMapping("/returnDestroyedBook")
    public RentDto returnDestroyedBook(@RequestParam final Long rentId) {
        final Rent rent = validator.validateEndRentBookIsDestroyed(rentId);
        return RentDto.fromRentToRentDto(
                rentService.save(rent)
        );
    }
}
