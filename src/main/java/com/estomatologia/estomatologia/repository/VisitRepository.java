package com.estomatologia.estomatologia.repository;

import com.estomatologia.estomatologia.model.Visit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRepository extends CrudRepository<Visit, Long> {

    List<Visit> findAllByPatientId(Long id);

    List<Visit> findAllByDoctorId(Long id);
}
