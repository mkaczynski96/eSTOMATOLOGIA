package com.estomatologia.estomatologia.service;

import com.estomatologia.estomatologia.model.Doctor;
import com.estomatologia.estomatologia.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class DoctorService implements CrudService<Doctor, Long> {

    private DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Set<Doctor> findAll() {
        Set<Doctor> doctors = new HashSet<>();
        doctorRepository.findAll().forEach(doctors::add);
        return doctors;
    }

    @Override
    public Doctor findById(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    @Override
    public Doctor save(Doctor object) {
        return doctorRepository.save(object);
    }

    @Override
    public void delete(Doctor object) {
        doctorRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        doctorRepository.deleteById(id);
    }
}
