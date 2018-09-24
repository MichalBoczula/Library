package com.library.repository;

import com.library.domain.Specimen;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface SpecimenRepositoryDao extends CrudRepository<Specimen, Long> {
    @Override
    List<Specimen> findAll();

    Optional<Specimen> findById(Long readerId);

}
