package com.library.service;

import com.library.domain.Reader;
import com.library.repository.ReaderRepositoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbServiceReaders {
    @Autowired
    private ReaderRepositoryDao readerRepositoryDao;

    public List<Reader> getReaders() {
        return readerRepositoryDao.findAll();
    }

    public Optional<Reader> getReader(final Long readerId) {
        return readerRepositoryDao.findById(readerId);
    }

    public Reader save(final Reader reader) {
        return readerRepositoryDao.save(reader);
    }

    public void delete(final Reader reader) {
        readerRepositoryDao.delete(reader);
    }
}
