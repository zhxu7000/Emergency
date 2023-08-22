package com.usyd.emergency.controller;

import com.usyd.emergency.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiseaseController {

    @Autowired
    DiseaseService diseaseService;

    @PostMapping("/")
    public String

}
