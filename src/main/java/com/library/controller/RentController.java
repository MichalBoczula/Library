package com.library.controller;

import com.library.domain.RentDto;
import com.library.mapper.RentMapper;
import com.library.service.DbServiceRent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/library/rent")
public class RentController {
    @Autowired
    private DbServiceRent dbServiceRent;
    @Autowired
    private RentMapper rentMapper;

    @RequestMapping(method = GET, value = "getRents")
    public List<RentDto> getRents() {
        return rentMapper.mapToRentDtoList(dbServiceRent.findAll());
    }

    @RequestMapping(method = GET, value = "getRent")
    public RentDto getRent(@RequestParam final Long rentId) throws RentNotFoundException {
        return rentMapper.mapToRentDto(dbServiceRent.findById(rentId).orElseThrow(RentNotFoundException::new));
    }

    @RequestMapping(method = POST, value = "createRent", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createRent(@RequestBody final RentDto rentDto) {
        dbServiceRent.save(rentMapper.mapToRent(rentDto));
    }

    @RequestMapping(method = PUT, value = "updateRent")
    public RentDto updateRent(@RequestBody final RentDto rentDto) {
        return rentMapper.mapToRentDto(dbServiceRent.save(rentMapper.mapToRent(rentDto)));
    }

    @RequestMapping(method = DELETE, value = "deleteRent")
    public void deleteRent(@RequestParam final Long rentId) throws RentNotFoundException {
        dbServiceRent.delete(dbServiceRent.findById(rentId).orElseThrow(RentNotFoundException::new));
    }
}
