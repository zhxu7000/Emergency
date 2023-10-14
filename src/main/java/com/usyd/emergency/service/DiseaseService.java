package com.usyd.emergency.service;

import com.usyd.emergency.constant.XError;
import com.usyd.emergency.exception.ConflictException;
import com.usyd.emergency.pojo.Disease;
import com.usyd.emergency.repository.DiseaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.security.DigestException;
import java.security.interfaces.XECKey;
import java.util.Optional;

@Service
public class DiseaseService {

    private final DiseaseRepository diseaseRepository;

    @Autowired
    public DiseaseService(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }
    public Disease findDiseaseById(Integer id) {
        Optional<Disease> diseaseOpt = diseaseRepository.findById(id);
        if (diseaseOpt.isEmpty()) {
            throw new ConflictException(XError.DATABASE_ERROR.getCode(), "disease not found");
        }
        Disease disease = diseaseOpt.get();
        return disease;
    }
    public Disease addDisease(Disease disease) {
        if (disease == null || disease.getDiseaseName() == null || disease.getLevel() == 0) {
            throw new ConflictException(XError.DATABASE_ERROR.getCode(), "can not save this disease, some fields are null");
        }
        if (diseaseRepository.findByDiseaseName(disease.getDiseaseName()) != null) {
            throw new ConflictException(XError.DATABASE_ERROR.getCode(), "disease with this name already exists");
        }
        return diseaseRepository.save(disease);
    }

    public void updateDiseasebyId(Integer diseaseId, String diseaseName, Integer level){
        Optional<Disease> diseaseOpt = diseaseRepository.findById(diseaseId);
        if (diseaseOpt.isEmpty()) {
            throw new ConflictException(XError.DATABASE_ERROR.getCode(), "disease not found");
        }
        if (diseaseRepository.findByDiseaseName(diseaseName) != null) {
            throw new ConflictException(XError.DATABASE_ERROR.getCode(), "disease with this name already exists");
        }
        Disease disease = diseaseOpt.get();
        disease.setLevel(level);
        disease.setDiseaseName(diseaseName);
        diseaseRepository.save(disease);
    }
    public void deleteDiseaseById(Integer diseaseId) {
        Optional<Disease> diseaseOpt = diseaseRepository.findById(diseaseId);
        if (diseaseOpt.isEmpty()) {
            throw new ConflictException(XError.DATABASE_ERROR.getCode(), "disease not found");
        }
        diseaseRepository.deleteById(diseaseId);
    }

}
