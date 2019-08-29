package com.estomatologia.estomatologia.repository;

import com.estomatologia.estomatologia.model.Receptionist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceptionistRepository extends CrudRepository<Receptionist, Long> {
}
