package com.library.service;

import com.library.domain.Specimen;
import com.library.dto.SpecimenDto;
import com.library.repository.SpecimenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SpecimenService {
    private final SpecimenRepository specimenRepository;

    public List<Specimen> findAll() {
        return specimenRepository.findAll();
    }

    public Optional<Specimen> findById(final Long specimenId) {
        return specimenRepository.findById(specimenId);
    }

    public Specimen save(final SpecimenDto specimenDto) {
        return specimenRepository.save(
                SpecimenDto.fromSpecimenDtoToSpecimen(specimenDto)
        );
    }

}
