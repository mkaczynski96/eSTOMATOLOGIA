package com.estomatologia.estomatologia.service.orm;

import com.estomatologia.estomatologia.model.Administrator;
import com.estomatologia.estomatologia.repository.AdministratorRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AdministratorService implements CrudService<Administrator, Long> {

    private AdministratorRepository administratorRepository;

    public AdministratorService(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    @Override
    public Set<Administrator> findAll() {
        Set<Administrator> administrators = new HashSet<>();
        administratorRepository.findAll().forEach(administrators::add);
        return administrators;
    }

    @Override
    public Administrator findById(Long aLong) {
        return administratorRepository.findById(aLong).orElse(null);
    }

    @Override
    public Administrator save(Administrator object) {
        return administratorRepository.save(object);
    }

    @Override
    public void delete(Administrator object) {
        administratorRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        administratorRepository.deleteById(aLong);
    }
}
