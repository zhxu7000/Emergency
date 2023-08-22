package com.usyd.emergency.service;

import com.usyd.emergency.pojo.Disease;
import com.usyd.emergency.repository.DiseaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class DiseaseService {

    private final DiseaseRepository diseaseRepository;

    @Autowired
    public DiseaseService(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    public Disease addDisease(String diseaseName,int level){
        Disease disease = new Disease();
        disease.setDiseaseName("covid19");
        disease.setLevel(1);
        diseaseRepository.save(disease);
        return disease;
    }

    public Disease findDiseaseByName(String diseaseName) {
        return diseaseRepository.findByDiseaseName(diseaseName);
    }

    public Disease saveDisease(Disease disease) {
        return diseaseRepository.save(disease);
    }

    public void deleteDiseaseById(int diseaseId) {
        if (!diseaseRepository.existsById(diseaseId)) {
            throw new EntityNotFoundException("Disease not found with ID: " + diseaseId);
        }
        diseaseRepository.deleteById(diseaseId);
    }
}
