package com.estomatologia.estomatologia.service.orm;

import com.estomatologia.estomatologia.model.Prescription;
import com.estomatologia.estomatologia.repository.PrescriptionRepository;
import com.estomatologia.estomatologia.service.orm.CrudService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PrescriptionService implements CrudService<Prescription, Long> {

    private PrescriptionRepository prescriptionRepository;

    public PrescriptionService(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    @Override
    public Set<Prescription> findAll() {
        Set<Prescription> prescriptions = new HashSet<>();
        prescriptionRepository.findAll().forEach(prescriptions::add);
        return prescriptions;
    }

    @Override
    public Prescription findById(Long aLong) {
        return prescriptionRepository.findById(aLong).orElse(null);
    }

    @Override
    public Prescription save(Prescription object) {
        return prescriptionRepository.save(object);
    }

    @Override
    public void delete(Prescription object) {
        prescriptionRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        prescriptionRepository.deleteById(aLong);
    }
}
