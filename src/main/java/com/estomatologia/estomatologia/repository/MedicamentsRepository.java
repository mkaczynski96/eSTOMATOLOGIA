package com.estomatologia.estomatologia.repository;

import com.estomatologia.estomatologia.model.Medicaments;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentsRepository extends CrudRepository<Medicaments, Long> {
}
