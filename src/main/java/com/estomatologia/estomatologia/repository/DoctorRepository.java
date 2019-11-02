package com.estomatologia.estomatologia.repository;

import com.estomatologia.estomatologia.model.Doctor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Long> {

   Optional<Doctor> findByName(String name);

   @Transactional
   Doctor findAllByUserDoctorId(Long id);
}
