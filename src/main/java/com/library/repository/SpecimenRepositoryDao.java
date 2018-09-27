package com.library.repository;

import com.library.domain.Specimen;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpecimenRepositoryDao extends CrudRepository<Specimen, Long> {
    @Override
    List<Specimen> findAll();

    Optional<Specimen> findById(final Long specimenId);
}
