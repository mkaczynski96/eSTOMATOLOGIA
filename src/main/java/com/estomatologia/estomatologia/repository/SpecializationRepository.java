package com.estomatologia.estomatologia.repository;

import com.estomatologia.estomatologia.model.Specialization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecializationRepository extends CrudRepository<Specialization, Long> {
}
