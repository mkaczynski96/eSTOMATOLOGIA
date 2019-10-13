package com.estomatologia.estomatologia.repository;

import com.estomatologia.estomatologia.model.ProposedVisit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProposedVisitRepository extends CrudRepository<ProposedVisit, Long> {

    List<ProposedVisit> findAllByPatientId(Long id);
}
