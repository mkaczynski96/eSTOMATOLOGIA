package com.estomatologia.estomatologia.repository;

import com.estomatologia.estomatologia.model.DoctorSpecialization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorSpecializationRepository extends CrudRepository<DoctorSpecialization, Long> {
}
