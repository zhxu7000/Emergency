package com.usyd.emergency;

import com.usyd.emergency.exception.ConflictException;
import com.usyd.emergency.pojo.Disease;
import com.usyd.emergency.pojo.Subscribes;
import com.usyd.emergency.pojo.User;
import com.usyd.emergency.repository.DiseaseRepository;
import com.usyd.emergency.repository.SubscribeRepository;
import com.usyd.emergency.repository.UserRepository;
import com.usyd.emergency.service.SubscribeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class SubscribeServiceTest {

    @InjectMocks
    private SubscribeService subscribeService;

    @Mock
    private SubscribeRepository subscribeRepository;

    @Mock
    private DiseaseRepository diseaseRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }



    @Test
    public void testGetDidsByUserId() {
        when(userRepository.findById(1)).thenReturn(Optional.of(new User()));
        when(subscribeRepository.findByUserId(1)).thenReturn(Optional.of(Arrays.asList(new Subscribes(1, 1))));

        List<Integer> dids = subscribeService.getDidsByUserId(1);
        assertEquals(1, dids.size());
        assertTrue(dids.contains(1));
    }

    @Test
    public void testGetUidsByDiseaseId() {
        when(diseaseRepository.findById(1)).thenReturn(Optional.of(new Disease()));
        when(subscribeRepository.findByDiseaseId(1)).thenReturn(Arrays.asList(new Subscribes(1, 1)));

        List<Integer> uids = subscribeService.getUidsByDiseaseId(1);
        assertEquals(1, uids.size());
        assertTrue(uids.contains(1));
    }

    @Test
    public void testUnsubscribeByDid() {
        when(userRepository.findById(1)).thenReturn(Optional.of(new User()));
        when(diseaseRepository.findById(1)).thenReturn(Optional.of(new Disease()));

        subscribeService.unsubscribeByDid(1, 1);

        verify(subscribeRepository, times(1)).deleteByDiseaseId(1);
    }
}
