package com.usyd.emergency.controller;

import com.usyd.emergency.pojo.Disease;
import com.usyd.emergency.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class DiseaseController {
    @Autowired
    DiseaseService diseaseService;
    public DiseaseController(DiseaseService diseaseService) {
        this.diseaseService = diseaseService;
    }

    @GetMapping("/{diseaseName}")
    public Disease getDiseaseByName(@PathVariable String diseaseName) {
        return diseaseService.findDiseaseByName(diseaseName);
    }

    @PostMapping
    public Disease createDisease(@RequestBody Disease disease) {
        return diseaseService.saveDisease(disease);
    }

    @DeleteMapping("/{diseaseId}")
    public void deleteDisease(@PathVariable int diseaseId) {
        diseaseService.deleteDiseaseById(diseaseId);
    }
}