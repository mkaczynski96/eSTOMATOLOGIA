package com.estomatologia.estomatologia.repository;

import com.estomatologia.estomatologia.model.Visit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends CrudRepository<Visit, Long> {
}
