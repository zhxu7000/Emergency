package com.usyd.emergency.controller;
import com.usyd.emergency.dto.CaseDTO;
import com.usyd.emergency.pojo.Case;
import com.usyd.emergency.pojo.Disease;
import com.usyd.emergency.pojo.ResponseResult;
import com.usyd.emergency.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cases")
public class CaseController {
    private final CaseService caseService;

    @Autowired
    public CaseController(CaseService caseService) {
        this.caseService = caseService;
    }

    @GetMapping
    public ResponseResult getAllCases(){
        List<Case> cas = caseService.getAllCases();
        Map<String, List<Case>> map = new HashMap<>();
        map.put("Cases", cas);
        return new ResponseResult(200,"get disease info successful", map);
    }

    @GetMapping("/{caseId}")
    public ResponseResult getCaseById(@PathVariable Integer caseId) {
        Case ca = caseService.getCaseById(caseId);
        Map<String, Case> map = new HashMap<>();
        map.put("case_info", ca);
        return  new ResponseResult(200,"retrieve successful", map);
    }

    @PostMapping
    public ResponseResult createCase(@RequestBody CaseDTO.addCaseDTO add) {
        Case ca = new Case();
        ca.setDiseaseId(add.disease_id);
        ca.setLatitude(add.latitude);
        ca.setLongitude(add.longitude);
        caseService.addCase(ca);
        return new ResponseResult(200,"add case successful");
    }

    @PutMapping()
    public ResponseResult updateCase(@RequestBody CaseDTO.updateCaseDTO up) {
        caseService.updateCase(up.caseId, up.longitude, up.longitude, up.disease_id);
        return new ResponseResult(200,"update case successful");
    }

    @DeleteMapping("/{caseId}")
    public void deleteCase(@PathVariable Integer caseId) {
        caseService.deleteCase(caseId);
    }

    @GetMapping("/location")
    public ResponseResult getAllCasesWithLocation(@RequestBody CaseDTO.locationDTO locationDTO ) {
        Map<String, List<Case>> map = new HashMap<>();
        List<Case> cas =  caseService.getAllCasesWithLocation(locationDTO.fromLatitude, locationDTO.toLongitude,
                locationDTO.fromLongitude, locationDTO.toLatitude);
        map.put("cases", cas);
        return new ResponseResult(200,"update case successful", map);

    }
}