package com.usyd.emergency;

import com.usyd.emergency.exception.ConflictException;
import com.usyd.emergency.pojo.Disease;
import com.usyd.emergency.repository.DiseaseRepository;
import com.usyd.emergency.service.DiseaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DiseaseServiceTests {

	@InjectMocks
	private DiseaseService diseaseService;

	@Mock
	private DiseaseRepository diseaseRepository;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}



	@Test
	public void testFindDiseaseById_nonExistingDisease() {
		// Prepare data
		when(diseaseRepository.findById(anyInt())).thenReturn(Optional.empty());

		// Expect exception
		assertThrows(ConflictException.class, () -> {
			diseaseService.findDiseaseById(1);
		});
	}



	@Test
	public void testGetAllDisease() {
		// Prepare data
		when(diseaseRepository.findAll()).thenReturn(new ArrayList<Disease>());

		// Call the method
		List<Disease> result = diseaseService.getAllDisease();

		// Assertions and verification
		assertNotNull(result);
		verify(diseaseRepository, times(1)).findAll();
	}


	@Test
	public void testUpdateDiseaseById_nonExistingDisease() {
		// Prepare data
		when(diseaseRepository.findById(anyInt())).thenReturn(Optional.empty());

		// Expect exception
		assertThrows(ConflictException.class, () -> {
			diseaseService.updateDiseasebyId(1, "UpdatedDiseaseName", 2);
		});
	}

	@Test
	public void testUpdateDiseaseById_existingDiseaseName() {
		// Prepare data
		Disease existingDisease = new Disease();
		existingDisease.setDiseaseId(1);
		when(diseaseRepository.findById(anyInt())).thenReturn(Optional.of(existingDisease));
		when(diseaseRepository.findByDiseaseName(anyString())).thenReturn(existingDisease);

		// Expect exception
		assertThrows(ConflictException.class, () -> {
			diseaseService.updateDiseasebyId(1, "UpdatedDiseaseName", 2);
		});
	}


	@Test
	public void testDeleteDiseaseById_nonExistingDisease() {
		// Prepare data
		when(diseaseRepository.findById(anyInt())).thenReturn(Optional.empty());

		// Expect exception
		assertThrows(ConflictException.class, () -> {
			diseaseService.deleteDiseaseById(1);
		});
	}
}
