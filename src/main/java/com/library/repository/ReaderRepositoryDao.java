package com.library.repository;

import com.library.domain.Reader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ReaderRepositoryDao extends CrudRepository<Reader, Long> {
    @Override
    List<Reader> findAll();

    Optional<Reader> findById(final Long readerId);
}
