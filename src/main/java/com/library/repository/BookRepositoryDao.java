package com.library.repository;

import com.library.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface BookRepositoryDao extends CrudRepository<Book, Long> {
    @Override
    List<Book> findAll();

    Optional<Book> findById(final Long bookId);
}
