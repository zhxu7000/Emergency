package com.usyd.emergency;
import static org.junit.jupiter.api.Assertions.*;
import com.usyd.emergency.service.MailService;
import com.usyd.emergency.service.UserService;
import com.usyd.emergency.utils.MailClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.util.Assert;

import static org.mockito.Mockito.*;

public class MailServiceTest {

    @InjectMocks
    private MailClient mailClient;



    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSendEmail() {


        assertTrue(mailClient.sendMail("346657558@qq.com","test","test"));
    }
}
