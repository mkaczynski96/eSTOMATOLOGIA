package com.estomatologia.estomatologia.service.orm;

import com.estomatologia.estomatologia.model.ProposedVisit;
import com.estomatologia.estomatologia.repository.ProposedVisitRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
public class ProposedVisitService implements CrudService<ProposedVisit, Long> {

    private ProposedVisitRepository proposedVisitRepository;

    public ProposedVisitService(ProposedVisitRepository proposedVisitRepository) {
        this.proposedVisitRepository = proposedVisitRepository;
    }

    @Override
    public Set<ProposedVisit> findAll() {
        Set<ProposedVisit> proposedVisits = new HashSet<>();
        proposedVisits.forEach(proposedVisits::add);
        return proposedVisits;
    }

    @Override
    public ProposedVisit findById(Long aLong) {
        return proposedVisitRepository.findById(aLong).orElse(null);
    }

    @Override
    public ProposedVisit save(ProposedVisit object) {
        return proposedVisitRepository.save(object);
    }

    @Override
    public void delete(ProposedVisit object) {
        proposedVisitRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        proposedVisitRepository.deleteById(aLong);
    }


}
