package com.estomatologia.estomatologia.service;

import com.estomatologia.estomatologia.model.Equipment;
import com.estomatologia.estomatologia.repository.EquipmentRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class EquipmentService implements CrudService<Equipment, Long> {

    private EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public Set<Equipment> findAll() {
        Set<Equipment> equipment = new HashSet<>();
        equipmentRepository.findAll().forEach(equipment::add);
        return equipment;
    }

    @Override
    public Equipment findById(Long aLong) {
        return equipmentRepository.findById(aLong).orElse(null);
    }

    @Override
    public Equipment save(Equipment object) {
        return equipmentRepository.save(object);
    }

    @Override
    public void delete(Equipment object) {
        equipmentRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        equipmentRepository.deleteById(aLong);
    }
}
