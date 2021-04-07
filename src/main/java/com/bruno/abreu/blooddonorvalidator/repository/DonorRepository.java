package com.bruno.abreu.blooddonorvalidator.repository;

import com.bruno.abreu.blooddonorvalidator.model.Donor;
import org.springframework.data.repository.CrudRepository;

public interface DonorRepository extends CrudRepository<Donor, String> {
}
