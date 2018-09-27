package com.library.repository;

import com.library.domain.Rent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentRepository extends CrudRepository<Rent, Long> {
    @Override
    List<Rent> findAll();

    Optional<Rent> findById(final Long rentId);
}
