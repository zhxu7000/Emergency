package com.usyd.emergency.config.controller;

import com.usyd.emergency.pojo.Disease;
import com.usyd.emergency.pojo.ResponseResult;
import com.usyd.emergency.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Controller
public class DiseaseController {
    @Autowired
    DiseaseService diseaseService;
    public DiseaseController(DiseaseService diseaseService) {
        this.diseaseService = diseaseService;
    }
    @PostMapping("/{diseaseName}/getdiseaselevel")
    public ResponseResult getDiseaseLevel(@PathVariable String diseaseName) {
        Map<String, Integer> map = new HashMap<>();
        map.put("level", diseaseService.findDiseaseLevelByName(diseaseName));
        return new ResponseResult(200,"login successful", map);
    }

    //delete disease by name
    @PostMapping("/{diseaseName}/deletedisease")
    public ResponseResult deleteDisease(@PathVariable String diseaseName) {
        diseaseService.deleteDiseaseByName(diseaseName);
        return new ResponseResult(200,"delete disease successful");
    }

    //add disease
    @PostMapping("/{diseaseName}/adddisease")
    public ResponseResult addDisease(@PathVariable String diseaseName, @PathVariable Integer level){
        diseaseService.addDiseaseByName(diseaseName,level);
        return new ResponseResult(200,"disease add successful");
    }

    //update disease
    @PostMapping("/{diseaseName}/updatedisease")
    public ResponseResult updataDisease(@PathVariable String diseaseName, @PathVariable Integer level){
        diseaseService.updateDisease(diseaseName,level);
        return new ResponseResult(200,"disease level update successful");

    }

}