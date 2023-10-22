package com.usyd.emergency.controller;

import com.usyd.emergency.dto.DiseaseDTO;
import com.usyd.emergency.pojo.Disease;
import com.usyd.emergency.pojo.ResponseResult;
import com.usyd.emergency.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/disease")
public class DiseaseController {
    @Autowired
    DiseaseService diseaseService;
    public DiseaseController(DiseaseService diseaseService) {
        this.diseaseService = diseaseService;
    }

    @GetMapping("/{diseaseId}")
    public ResponseResult getDiseaseInfo(@PathVariable Integer diseaseId) {
        Disease disease = diseaseService.findDiseaseById(diseaseId);
        Map<String, String> map = new HashMap<>();
        map.put("disease_level", String.valueOf(disease.getLevel()));
        map.put("disease_name", disease.getDiseaseName());
        return new ResponseResult(200,"get disease info successful", map);
    }

    @GetMapping()
    public ResponseResult getDiseaseInfo() {
        List<Disease> diseases = diseaseService.getAllDisease();
        Map<String, List<Disease>> map = new HashMap<>();
        map.put("diseases", diseases);
        return new ResponseResult(200,"get disease info successful", map);
    }

    @PostMapping
    public ResponseResult createDisease(@RequestBody DiseaseDTO.addDiseaseDTO add) {
        Disease disease = new Disease();
        disease.setDiseaseName(add.diseaseName);
        disease.setLevel(add.diseaseLevel);
        diseaseService.addDisease(disease);

        return new ResponseResult(200,"add disease successful");
    }

    //delete disease by name
    @DeleteMapping("/{diseaseId}")
    public ResponseResult deleteDisease(@PathVariable Integer diseaseId) {
        diseaseService.deleteDiseaseById(diseaseId);
        return new ResponseResult(200,"delete disease successful");
    }

    //add disease

    //update disease
    @PutMapping
    public ResponseResult updataDisease(@RequestBody Disease disease){
        diseaseService.updateDiseasebyId(disease.getDiseaseId(), disease.getDiseaseName(), disease.getLevel());
        return new ResponseResult(200,"disease level update successful");

    }

}