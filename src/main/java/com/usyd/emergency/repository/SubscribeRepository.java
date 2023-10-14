package com.usyd.emergency.repository;

import com.usyd.emergency.pojo.Subscribes;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscribeRepository extends CrudRepository <Subscribes, Integer>, JpaSpecificationExecutor<Subscribes> {

    // 根据user_id和disease_id查询订阅信息
//    Subscribes findByUserIdAndDiseaseId(Integer userId, Integer diseaseId);
//
//    // 根据user_id删除订阅信息
//    void deleteByUserId(Integer userId);
//
    // 根据disease_id删除订阅信息
    void deleteByDiseaseId(Integer diseaseId);
//
    Optional<List<Subscribes>> findByUserId(Integer userId);


//
    List<Subscribes> findByDiseaseId(Integer diseaseId);
//
//    void deleteById(Integer userId);
}