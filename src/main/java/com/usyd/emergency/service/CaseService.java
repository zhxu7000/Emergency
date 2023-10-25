package com.usyd.emergency.service;

import com.usyd.emergency.constant.XError;
import com.usyd.emergency.dto.CaseDTO;
import com.usyd.emergency.exception.ConflictException;
import com.usyd.emergency.pojo.Case;
import com.usyd.emergency.repository.CaseRepository;
import com.usyd.emergency.repository.DiseaseRepository;
import com.usyd.emergency.repository.UserRepository;
import com.usyd.emergency.utils.XUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class CaseService {
    private final CaseRepository caseRepository;
    private final MapService mapService;
    private final DiseaseRepository diseaseRepository;

    @Autowired
    public CaseService(CaseRepository caseRepository,
                       DiseaseRepository diseaseRepository,
                       MapService mapService) {

        this.caseRepository = caseRepository;
        this.diseaseRepository = diseaseRepository;
        this.mapService = mapService;

    }

    public List<Case> getAllCases() {
        Iterator<Case> iterator = caseRepository.findAll().iterator();
        ArrayList<Case> cases = new ArrayList<>();
        while (iterator.hasNext()) {
            Case ca = iterator.next();

            cases.add(ca);
        }
        return cases;
    }

    public Case getCaseById(Integer caseId) {
        Optional<Case> caseOpt = caseRepository.findById(caseId);
        if (caseOpt.isEmpty()) {
            throw new ConflictException(XError.DATABASE_ERROR.getCode(), "get case failed");
        }
        return caseOpt.get();
    }

    public Case updateCase(int caseId, String location, int diseaseId) throws Exception {
        if (diseaseRepository.findById(diseaseId) == null) {
            throw new ConflictException(XError.DATABASE_ERROR.getCode(), "update case failed, disease with this id does not exist");
        }
        if (caseRepository.findByCaseId(caseId) == null) {
            throw new ConflictException(XError.DATABASE_ERROR.getCode(), "update case failed, case with this id does not exist");
        }
        Case ca = caseRepository.findByCaseId(caseId);
        Map<String, String> res = mapService.getLongitudeAndLatitude(location);
        ca.setLongitude(res.get("Longitutde"));
        ca.setLatitude(res.get("Latitude"));
        ca.setDiseaseId(diseaseId);
        return caseRepository.save(ca);
    }

    public Case addCase(Case ca) {
        if (diseaseRepository.findById(ca.getDiseaseId()) == null) {
            throw new ConflictException(XError.DATABASE_ERROR.getCode(), "disease with this id does not exist");
        }
        return caseRepository.save(ca);
    }

    public void deleteCase(Integer caseId) {
        if (caseRepository.findById(caseId) == null) {
            throw new ConflictException(XError.DATABASE_ERROR.getCode(), "case with this id does not exist");
        }
        caseRepository.deleteById(caseId);
    }

    public List<Case> getAllCasesWithLocation(String fromLong,
                                              String fromLa,
                                              String toLong,
                                             String toLa) {
        Iterator<Case> iterator = caseRepository.findAll().iterator();
        ArrayList<Case> cases = new ArrayList<>();
        while (iterator.hasNext()) {
            Case ca = iterator.next();
            if (Float.valueOf(ca.getLongitude())  <= Float.valueOf(fromLong)  && Float.valueOf( ca.getLongitude()) >= Float.valueOf(toLong)
                    && Float.valueOf(ca.getLatitude())  <= Float.valueOf(fromLa)  && Float.valueOf(ca.getLatitude()) >= Float.valueOf(toLa))
//            {
            cases.add(ca);

        }

        return cases;
    }
}