package com.library.service;

import com.library.controller.ReaderNotFoundException;
import com.library.domain.Rent;
import com.library.repository.RentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DbServiceRent {
    private final RentRepository rentRepository;

    public List<Rent> findAll() {
        return rentRepository.findAll();
    }

    public Rent findById(final Long rentId) {
        return rentRepository.findById(rentId)
                .orElseThrow(() -> new ReaderNotFoundException(rentId));
    }

    public Rent save(final Rent rent) {
        return rentRepository.save(rent);
    }

    public void delete(final Rent rent) {
        rentRepository.delete(rent);
    }
}
