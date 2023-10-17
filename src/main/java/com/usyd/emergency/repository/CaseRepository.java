package com.usyd.emergency.repository;

import com.usyd.emergency.dto.CaseDTO;
import com.usyd.emergency.pojo.Case;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaseRepository extends CrudRepository<Case, Integer>, JpaSpecificationExecutor<Case> {

    Case findByCaseId(Integer CaseId);

    @Query("SELECT '*' FROM Case ") // 使用别名
    List<Case> findAllCases();
}