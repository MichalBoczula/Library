package com.library.repository;

import com.library.domain.Specimen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpecimenRepository extends JpaRepository<Specimen, Long> {
    Optional<Specimen> findById(final Long specimenId);
}
