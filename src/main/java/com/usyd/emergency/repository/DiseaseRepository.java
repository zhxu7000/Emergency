package com.usyd.emergency.repository;

import com.usyd.emergency.pojo.Disease;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiseaseRepository extends CrudRepository<Disease, Integer>, JpaSpecificationExecutor<Disease> {
    Disease findByDiseaseName(String diseaseName);
}
