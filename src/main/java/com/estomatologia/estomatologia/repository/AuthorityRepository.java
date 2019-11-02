package com.estomatologia.estomatologia.repository;

import com.estomatologia.estomatologia.model.Authorities;
import org.springframework.data.repository.CrudRepository;

public interface AuthorityRepository extends CrudRepository<Authorities, Long> {

    void deleteAllByUsername(String username);
}
