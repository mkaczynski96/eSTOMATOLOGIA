package com.estomatologia.estomatologia.repository;

import com.estomatologia.estomatologia.model.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {
}
