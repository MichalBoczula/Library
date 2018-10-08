package com.library.service;

import com.library.domain.Rent;
import com.library.domain.Specimen;
import com.library.exception.NotAvailableSpecimenException;
import com.library.exception.RentNotFoundException;
import com.library.exception.SpecimenNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.library.domain.Specimen.SpecimenStatus.*;

@Component
@RequiredArgsConstructor
public class Validator {
    private final SpecimenService specimenService;
    private final RentService rentService;

    public Specimen validateSpecimenIsAvailable(Long specimenId) {
        final Specimen specimen = specimenService.findById(specimenId)
                .orElseThrow(() -> new SpecimenNotFoundException(specimenId));
        if (specimen.getStatus().equals(IN_LIBRARY)) {
            specimen.setStatus(RENTED);
            return specimen;
        } else {
            final Specimen availableSpecimen = findAvailableSpecimenInLibrary(specimen.getBook().getTitle());
            if (availableSpecimen == null) {
                throw new NotAvailableSpecimenException();
            } else {
                availableSpecimen.setStatus(RENTED);
                return availableSpecimen;
            }
        }
    }

    private Specimen findAvailableSpecimenInLibrary(String title) {
        final List<Specimen> specimenList = specimenService.findAll();
        final List<Specimen> specimens = specimenList.stream()
                .filter(s1 -> s1.getStatus().equals(IN_LIBRARY))
                .collect(Collectors.toList());
        final List<Specimen> availableSpecimensToRent = specimens.stream()
                .filter(s2 -> s2.getBook().getTitle().equals(title))
                .collect(Collectors.toList());
        return availableSpecimensToRent.size() != 0 ?
                availableSpecimensToRent.get(0) :
                null;
    }

    public Rent validateEndRent(Long rentId) {
        Rent rent = rentService.findById(rentId)
                .orElseThrow(() -> new RentNotFoundException(rentId));
        rent.setReturnDate(new Date());
        rent.getSpecimen().setStatus(IN_LIBRARY);
        return rent;
    }

    public Rent validateEndRentBookIsDestroyed(Long rentId) {
        Rent rent = rentService.findById(rentId)
                .orElseThrow(() -> new RentNotFoundException(rentId));
        rent.setReturnDate(new Date());
        rent.getSpecimen()
                .setStatus(DESTROYED);
        return rent;
    }
}
