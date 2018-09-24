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

    public Optional<Reader> getReader(Long readerId) {
        return readerRepositoryDao.findById(readerId);
    }

    public Reader save(Reader reader) {
        return readerRepositoryDao.save(reader);
    }

    public void delete(Reader reader) {
        readerRepositoryDao.delete(reader);
    }
}
