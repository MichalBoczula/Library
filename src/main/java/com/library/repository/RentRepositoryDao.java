package com.library.repository;

import com.library.domain.Rent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface RentRepositoryDao extends CrudRepository<Rent, Long> {
    @Override
    List<Rent> findAll();

    Optional<Rent> findById(final Long rentId);

    @Query(nativeQuery = true)
    void setReturnDate(@Param("paramRentId") Long rentId);

//    @Query()
//    Rent testQuery(@Param("paramRentId") Long rendID);

}
