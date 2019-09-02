package com.estomatologia.estomatologia.service;

import com.estomatologia.estomatologia.model.Medicament;
import com.estomatologia.estomatologia.repository.MedicamentRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class MedicamentService implements CrudService<Medicament, Long> {

    private MedicamentRepository medicamentRepository;

    public MedicamentService(MedicamentRepository medicamentRepository) {
        this.medicamentRepository = medicamentRepository;
    }

    @Override
    public Set<Medicament> findAll() {
        Set<Medicament> medicaments = new HashSet<>();
        medicamentRepository.findAll().forEach(medicaments::add);
        return medicaments;
    }

    @Override
    public Medicament findById(Long aLong) {
        return medicamentRepository.findById(aLong).orElse(null);
    }

    @Override
    public Medicament save(Medicament object) {
        return medicamentRepository.save(object);
    }

    @Override
    public void delete(Medicament object) {
        medicamentRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        medicamentRepository.deleteById(aLong);
    }
}
