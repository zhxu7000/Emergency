package com.usyd.emergency.service;

import com.usyd.emergency.constant.XError;
import com.usyd.emergency.exception.ConflictException;
import com.usyd.emergency.pojo.Disease;
import com.usyd.emergency.repository.DiseaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.security.interfaces.XECKey;

@Service
public class DiseaseService {

    private final DiseaseRepository diseaseRepository;

    @Autowired
    public DiseaseService(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }
    public int findDiseaseLevelByName(String diseaseName) {
        Disease disease = diseaseRepository.findByDiseaseName(diseaseName);
        return disease.getLevel();
    }
    public Disease saveDisease(Disease disease) {
        return diseaseRepository.save(disease);
    }

    public void updateDisease(String diseaseName, Integer level){
        Disease disease =diseaseRepository.findByDiseaseName(diseaseName);
        if (disease == null) {
            throw new ConflictException(XError.DATABASE_ERROR.getCode(), XError.DATABASE_ERROR.getMsg());
        }
        disease.setLevel(level);
        diseaseRepository.save(disease);

    }
    public void deleteDiseaseByName(String diseaseName) {
        Disease disease =diseaseRepository.findByDiseaseName(diseaseName);
        if (disease == null) {
            throw new ConflictException(XError.DATABASE_ERROR.getCode(), XError.DATABASE_ERROR.getMsg());

        }
        diseaseRepository.deleteById(disease.getDiseaseId());
    }

    public void addDiseaseByName(String diseaseName,Integer level) {

        if (diseaseRepository.findByDiseaseName(diseaseName)!= null) {
            throw new ConflictException(XError.DATABASE_ERROR.getCode(), "has already existed");
        }
       Disease disease = new Disease();
        disease.setLevel(level);
        disease.setDiseaseName(diseaseName);
        diseaseRepository.save(disease);
    }
}
