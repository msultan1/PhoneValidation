package com.sumo.phoneValidation;

import com.sumo.phoneValidation.model.country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:63342")
@RepositoryRestResource(collectionResourceRel = "countries",path = "countries")
public interface countryRepo extends JpaRepository<country,Integer> {
}
