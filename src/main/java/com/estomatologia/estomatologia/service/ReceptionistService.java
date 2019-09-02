package com.estomatologia.estomatologia.service;

import com.estomatologia.estomatologia.model.Receptionist;
import com.estomatologia.estomatologia.repository.ReceptionistRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ReceptionistService implements CrudService<Receptionist, Long> {

    private ReceptionistRepository receptionistRepository;

    public ReceptionistService(ReceptionistRepository receptionistRepository) {
        this.receptionistRepository = receptionistRepository;
    }

    @Override
    public Set<Receptionist> findAll() {
        Set<Receptionist> receptionists = new HashSet<>();
        receptionistRepository.findAll().forEach(receptionists::add);
        return receptionists;
    }

    @Override
    public Receptionist findById(Long aLong) {
        return receptionistRepository.findById(aLong).orElse(null);
    }

    @Override
    public Receptionist save(Receptionist object) {
        return receptionistRepository.save(object);
    }

    @Override
    public void delete(Receptionist object) {
        receptionistRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        receptionistRepository.deleteById(aLong);
    }
}
