package com.estomatologia.estomatologia.service.orm;

import com.estomatologia.estomatologia.model.Authorities;
import com.estomatologia.estomatologia.repository.AuthorityRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
public class AuthoritiesService implements CrudService<Authorities, Long> {

    private AuthorityRepository authorityRepository;

    public AuthoritiesService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public Set<Authorities> findAll() {
        Set<Authorities> authorities = new HashSet<>();
        authorityRepository.findAll().forEach(authorities::add);
        return authorities;
    }

    @Override
    public Authorities findById(Long aLong) {
        return authorityRepository.findById(aLong).orElse(null);
    }

    @Override
    public Authorities save(Authorities object) {
        return authorityRepository.save(object);
    }

    @Override
    public void delete(Authorities object) {
        authorityRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        authorityRepository.deleteById(aLong);
    }
}
