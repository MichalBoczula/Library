package com.library.service;

import com.library.controller.SpecimenNotFoundException;
import com.library.domain.Specimen;
import com.library.repository.SpecimenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SpecimenService {
    private final SpecimenRepository specimenRepository;

    public List<Specimen> findAll() {
        return specimenRepository.findAll();
    }

    public Specimen findById(final Long specimenId) {
        return specimenRepository.findById(specimenId)
                .orElseThrow(() -> new SpecimenNotFoundException(specimenId));
    }

    public Specimen save(final Specimen specimen) {
        return specimenRepository.save(specimen);
    }

    public void delete(final Specimen specimen) {
        specimenRepository.delete(specimen);
    }
}
