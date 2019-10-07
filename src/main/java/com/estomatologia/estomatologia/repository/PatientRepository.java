package com.estomatologia.estomatologia.repository;

import com.estomatologia.estomatologia.model.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {

    Optional<Patient> findByName(String name);
}
