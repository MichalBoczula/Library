package com.library.service;

import com.library.domain.Specimen;
import com.library.repository.SpecimenRepositoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbServiceSpecimen {
    @Autowired
    private SpecimenRepositoryDao specimenRepositoryDao;

    public List<Specimen> findAll() {
        return specimenRepositoryDao.findAll();
    }

    public Optional<Specimen> findById(final Long specimenId) {
        return specimenRepositoryDao.findById(specimenId);
    }

    public Specimen save(final Specimen specimen) {
        return specimenRepositoryDao.save(specimen);
    }

    public void delete(final Specimen specimen) {
        specimenRepositoryDao.delete(specimen);
    }
}
