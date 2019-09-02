package com.estomatologia.estomatologia.service;

import com.estomatologia.estomatologia.model.DoctorSpecialization;
import com.estomatologia.estomatologia.repository.DoctorSpecializationRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorSpecializationService implements CrudRepository<DoctorSpecialization, Long> {

    private DoctorSpecializationRepository doctorspecializationRepostiory;

    public DoctorSpecializationService(DoctorSpecializationRepository doctorspecializationRepostiory) {
        this.doctorspecializationRepostiory = doctorspecializationRepostiory;
    }


    @Override
    public <S extends DoctorSpecialization> S save(S s) {
        return null;
    }

    @Override
    public <S extends DoctorSpecialization> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<DoctorSpecialization> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<DoctorSpecialization> findAll() {
        return null;
    }

    @Override
    public Iterable<DoctorSpecialization> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(DoctorSpecialization doctorSpecialization) {

    }

    @Override
    public void deleteAll(Iterable<? extends DoctorSpecialization> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
