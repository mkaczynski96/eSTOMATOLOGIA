package com.estomatologia.estomatologia.repository;

import com.estomatologia.estomatologia.model.Medicament;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicamentRepository extends CrudRepository<Medicament, Long> {
}
