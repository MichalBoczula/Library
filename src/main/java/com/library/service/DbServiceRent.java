package com.library.service;

import com.library.domain.Rent;
import com.library.repository.RentRepositoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbServiceRent {
    @Autowired
    private RentRepositoryDao rentRepositoryDao;

    public List<Rent> findAll() {
        return rentRepositoryDao.findAll();
    }

    public Optional<Rent> findById(final Long rentId) {
        return rentRepositoryDao.findById(rentId);
    }

    public Rent save(final Rent rent) {
        return rentRepositoryDao.save(rent);
    }

    public void delete(final Rent rent) {
        rentRepositoryDao.delete(rent);
    }
}
