package com.usyd.emergency;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.usyd.emergency.exception.ConflictException;
import com.usyd.emergency.pojo.Case;
import com.usyd.emergency.pojo.Disease;
import com.usyd.emergency.repository.CaseRepository;
import com.usyd.emergency.repository.DiseaseRepository;
import com.usyd.emergency.service.CaseService;
import com.usyd.emergency.service.MapService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

public class CaseServiceTest {

    @InjectMocks
    private CaseService caseService;

    @Mock
    private CaseRepository caseRepository;

    @Mock
    private DiseaseRepository diseaseRepository;

    @Mock
    private MapService mapService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllCases() {
        // Prepare data
        when(caseRepository.findAll()).thenReturn(new ArrayList<Case>());

        // Call the method
        List<Case> result = caseService.getAllCases();

        // Assertions and verification
        assertNotNull(result);
        verify(caseRepository, times(1)).findAll();
    }

    @Test
    public void testGetCaseById_existingCase() {
        // Prepare data
        Optional<Case> optCase = Optional.of(new Case());
        when(caseRepository.findById(anyInt())).thenReturn(optCase);

        // Call the method
        Case result = caseService.getCaseById(1);

        // Assertions and verification
        assertNotNull(result);
        verify(caseRepository, times(1)).findById(anyInt());
    }

    @Test
    public void testGetCaseById_nonExistingCase() {
        // Prepare data
        when(caseRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Expect exception
        assertThrows(ConflictException.class, () -> {
            caseService.getCaseById(1);
        });
    }





    @Test
    public void testDeleteCase_existingCase() {
        // Prepare data
        when(caseRepository.findById(anyInt())).thenReturn(Optional.of(new Case()));

        // Call the method without exception
        caseService.deleteCase(1);

        // Verification
        verify(caseRepository, times(1)).deleteById(anyInt());
    }



    @Test
    public void testGetAllCasesWithLocation() {
        // Prepare data
        when(caseRepository.findAll()).thenReturn(new ArrayList<Case>());

        // Call the method
        List<Case> result = caseService.getAllCasesWithLocation("1", "2", "3", "4");

        // Assertions and verification
        assertNotNull(result);
        verify(caseRepository, times(1)).findAll();
    }

}
