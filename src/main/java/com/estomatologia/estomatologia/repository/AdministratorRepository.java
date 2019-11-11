package com.estomatologia.estomatologia.repository;

import com.estomatologia.estomatologia.model.Administrator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministratorRepository extends CrudRepository<Administrator, Long> {
}
