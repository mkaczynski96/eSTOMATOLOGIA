package com.estomatologia.estomatologia.service;

import com.estomatologia.estomatologia.model.Specialization;
import com.estomatologia.estomatologia.repository.SpecializationRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class SpecializationService implements CrudService<Specialization, Long> {

    private SpecializationRepository specializationRepository;

    public SpecializationService(SpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;
    }

    @Override
    public Set<Specialization> findAll() {
        Set<Specialization> specializations = new HashSet<>();
        specializationRepository.findAll().forEach(specializations::add);
        return specializations;
    }

    @Override
    public Specialization findById(Long aLong) {
        return specializationRepository.findById(aLong).orElse(null);
    }

    @Override
    public Specialization save(Specialization object) {
        return specializationRepository.save(object);
    }

    @Override
    public void delete(Specialization object) {
        specializationRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        specializationRepository.deleteById(aLong);
    }
}
