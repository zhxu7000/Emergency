package com.usyd.emergency;

import com.usyd.emergency.service.CaseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmergencyApplicationTests {
	@Autowired
	CaseService caseService;
	@Test
	void contextLoads() {
	}

	@Test
	void test()
	{
		System.out.println(caseService.getCaseById(1));
	}

}
