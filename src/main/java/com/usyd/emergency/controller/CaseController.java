package com.usyd.emergency.controller;
import com.usyd.emergency.dto.CaseDTO;
import com.usyd.emergency.pojo.Case;
import com.usyd.emergency.pojo.Disease;
import com.usyd.emergency.pojo.ResponseResult;
import com.usyd.emergency.service.CaseService;
import com.usyd.emergency.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cases")
public class CaseController {
    private final CaseService caseService;
    private final MapService mapService;

    @Autowired
    public CaseController(CaseService caseService, MapService mapService) {

        this.caseService = caseService;
        this.mapService = mapService;
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
    public ResponseResult createCase(@RequestBody CaseDTO.addCaseDTO add) throws Exception {
        Case ca = new Case();
        System.out.println(add.disease_id);
        System.out.println(add.location);
        ca.setDiseaseId(add.disease_id);
        Map<String, String> res = mapService.getLongitudeAndLatitude(add.location);
        ca.setLongitude(res.get("Longitude"));
        ca.setLatitude(res.get("Latitude"));
        System.out.println(ca);
        caseService.addCase(ca);
        return new ResponseResult(200,"add case successful");
    }
    
    @PutMapping()
    public ResponseResult updateCase(@RequestBody CaseDTO.updateCaseDTO up) throws Exception {
        caseService.updateCase(up.caseId, up.location, up.disease_id);
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