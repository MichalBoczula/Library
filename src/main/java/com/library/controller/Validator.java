package com.library.controller;

import com.library.domain.Rent;
import com.library.domain.Specimen;
import com.library.service.RentService;
import com.library.service.SpecimenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.library.domain.Specimen.SpecimenStatus.*;

@Component
public class Validator {
    @Autowired
    private SpecimenService specimenService;
    @Autowired
    private RentService rentService;

    public Specimen validateSpecimenIsAvailable(Long specimenId) {
        final Specimen specimen = specimenService.findById(specimenId);
        if (specimen.getStatus().equals(IN_LIBRARY)) {
            specimen.setStatus(RENTED);
            return specimen;
        } else {
            final Specimen availableSpecimen = findAvailableSpecimenInLibrary();
            if (availableSpecimen == null) {
                throw new NotAvailableSpecimenException();
            } else {
                availableSpecimen.setStatus(RENTED);
                return availableSpecimen;
            }
        }
    }

    private Specimen findAvailableSpecimenInLibrary() {
        final List<Specimen> specimenList = specimenService.findAll();
        final List<Specimen> availableSpecimens = specimenList.stream()
                .filter(specimen -> specimen.getStatus().equals(IN_LIBRARY))
                .collect(Collectors.toList());
        return availableSpecimens != null ?
                specimenList.get(0)
                : null;
    }

    public Rent validateEndRent(Long rentId) {
        Rent rent = rentService.findById(rentId);
        rent.setReturnDate(new Date());
        rent.getSpecimen()
                .setStatus(IN_LIBRARY);
        return rent;
    }

    public Rent validateEndRentBookIsDestroyed(Long rentId) {
        Rent rent = rentService.findById(rentId);
        rent.setReturnDate(new Date());
        rent.getSpecimen()
                .setStatus(DESTROYED);
        return rent;
    }
}
