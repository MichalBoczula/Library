package com.library.controller;

import com.library.domain.Reader;
import com.library.domain.Rent;
import com.library.domain.Specimen;
import com.library.dto.RentDto;
import com.library.exception.ReaderNotFoundException;
import com.library.exception.RentNotFoundException;
import com.library.service.ReadersService;
import com.library.service.RentService;
import com.library.service.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library/rents")
@RequiredArgsConstructor
public class RentController {
    private final RentService rentService;
    private final ReadersService readersService;
    private final Validator validator;

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
                        .orElseThrow(() -> new RentNotFoundException(rentId))
        );
    }

    @PostMapping("/rentBook")
    public RentDto rentBook(
            @RequestParam final Long specimenId,
            @RequestParam final Long readerId
    ) {
        final Specimen specimen = validator.validateSpecimenIsAvailable(specimenId);
        final Reader reader = readersService.findById(readerId)
                .orElseThrow(()-> new ReaderNotFoundException(readerId));
        final Rent rent = new Rent(specimen, reader);
        return RentDto.fromRentToRentDto(
                rentService.save(
                        RentDto.fromRentToRentDto(rent)
                )
        );
    }

    @PutMapping("/returnBook")
    public RentDto returnBook(@RequestParam final Long rentId) {
        final Rent rent = validator.validateEndRent(rentId);
        return RentDto.fromRentToRentDto(
                rentService.save(
                        RentDto.fromRentToRentDto(rent)
                )
        );
    }

    @PutMapping("/returnDestroyedBook")
    public RentDto returnDestroyedBook(@RequestParam final Long rentId) {
        final Rent rent = validator.validateEndRentBookIsDestroyed(rentId);
        return RentDto.fromRentToRentDto(
                rentService.save(
                        RentDto.fromRentToRentDto(rent)
                )
        );
    }
}
