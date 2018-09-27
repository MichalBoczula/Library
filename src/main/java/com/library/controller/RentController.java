package com.library.controller;

import com.library.domain.Rent;
import com.library.domain.RentDto;
import com.library.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/library/rents")
public class RentController {
    @Autowired
    private RentService rentService;

    @GetMapping
    public List<RentDto> getRents() {
        return RentDto.fromRentListToRentDtoList(rentService.findAll());
    }

    @GetMapping("{id}")
    public RentDto getRent(@PathVariable("id") final Long rentId) {
        return RentDto.fromRentToRentDto(rentService.findById(rentId));
    }

    @PostMapping("/create")
    public void createRent(@RequestBody final RentDto rentDto) {
        final Rent rent = RentDto.fromRentDtoToRent(rentDto);
        rentService.save(rent);
    }

    @PutMapping("/return/{id}")
    public RentDto updateRent(@PathVariable("id") final Long rentId) {
        final Rent rent = rentService.findById(rentId);
        rent.setReturnDate(new Date());
        return RentDto.fromRentToRentDto(rentService.save(rent));
    }
}
