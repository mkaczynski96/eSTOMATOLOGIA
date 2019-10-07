package com.estomatologia.estomatologia.service.orm;

import com.estomatologia.estomatologia.model.DoctorSpecialization;
import com.estomatologia.estomatologia.repository.DoctorSpecializationRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class DoctorSpecializationService implements CrudService<DoctorSpecialization, Long> {

    private DoctorSpecializationRepository doctorspecializationRepostiory;

    public DoctorSpecializationService(DoctorSpecializationRepository doctorspecializationRepostiory) {
        this.doctorspecializationRepostiory = doctorspecializationRepostiory;
    }


    @Override
    public Set<DoctorSpecialization> findAll() {
        Set<DoctorSpecialization> doctorSpecializations = new HashSet<>();
       doctorspecializationRepostiory.findAll().forEach(doctorSpecializations::add);
        return doctorSpecializations;
    }

    @Override
    public DoctorSpecialization findById(Long aLong) {
        return doctorspecializationRepostiory.findById(aLong).orElse(null);
    }

    @Override
    public DoctorSpecialization save(DoctorSpecialization object) {
        return doctorspecializationRepostiory.save(object);
    }

    @Override
    public void delete(DoctorSpecialization object) {
        doctorspecializationRepostiory.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        doctorspecializationRepostiory.deleteById(aLong);
    }
}
