package com.estomatologia.estomatologia.repository;

import com.estomatologia.estomatologia.model.ProposedVisit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProposedVisitRepository extends CrudRepository<ProposedVisit, Long> {
}
