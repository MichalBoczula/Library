package com.library.service;

import com.library.controller.SpecimenNotFoundException;
import com.library.domain.Specimen;
import com.library.repository.SpecimenRepositoryDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SpecimenService {
    private final SpecimenRepositoryDao specimenRepositoryDao;

    public List<Specimen> findAll() {
        return specimenRepositoryDao.findAll();
    }

    public Specimen findById(final Long specimenId) {
        return specimenRepositoryDao.findById(specimenId)
                .orElseThrow(() -> new SpecimenNotFoundException(specimenId));
    }

    public Specimen save(final Specimen specimen) {
        return specimenRepositoryDao.save(specimen);
    }

    public void delete(final Specimen specimen) {
        specimenRepositoryDao.delete(specimen);
    }
}
