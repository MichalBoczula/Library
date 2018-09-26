package com.library.repository;

import com.library.domain.Specimen;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface SpecimenRepositoryDao extends CrudRepository<Specimen, Long> {
    @Override
    List<Specimen> findAll();

    Optional<Specimen> findById(final Long specimenId);

//    @Query(nativeQuery = true)
//    void setStatusOnRented(@Param("paramSpecimenId") Long SpecimenId);
}
