package com.sumo.phoneValidation;
import com.sumo.phoneValidation.model.customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "customers",path = "customers")
public interface customerRepo extends JpaRepository<customer,Integer>
{

    List<customer> findByCountry(@Param("country") String country);
    List<customer> findByState(@Param("state") String State);
    List<customer> findByCode(@Param("code") String Code);
    List<customer> findByCountryAndState (@Param("country") String country,@Param("state") String State);
    List<customer> findByCountryAndCode (@Param("country") String country,@Param("code") String code);
    List<customer> findByStateAndCode (@Param("state") String state,@Param("code") String code);
    List<customer> findByStateAndCodeAndCountry (@Param("state") String state,@Param("code") String code,@Param("country") String country);

}
