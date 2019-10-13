package com.estomatologia.estomatologia.repository;

import com.estomatologia.estomatologia.model.Prescription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository extends CrudRepository<Prescription, Long> {

    Prescription findByVisitId(Long id);
}
