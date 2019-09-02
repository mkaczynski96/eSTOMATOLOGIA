package com.estomatologia.estomatologia.service;

import com.estomatologia.estomatologia.model.Patient;
import com.estomatologia.estomatologia.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PatientService implements CrudService<Patient, Long> {

    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Set<Patient> findAll() {
        Set<Patient> patients = new HashSet<>();
        patientRepository.findAll().forEach(patients::add);
        return patients;
    }

    @Override
    public Patient findById(Long aLong) {
        return patientRepository.findById(aLong).orElse(null);
    }

    @Override
    public Patient save(Patient object) {
        return patientRepository.save(object);
    }

    @Override
    public void delete(Patient object) {
        patientRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        patientRepository.deleteById(aLong);
    }
}
