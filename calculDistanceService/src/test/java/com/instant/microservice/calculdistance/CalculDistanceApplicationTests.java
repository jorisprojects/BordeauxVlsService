package com.instant.microservice.calculdistance;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import com.instant.microservice.calculdistance.controller.DistanceController;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CalculDistanceApplicationTests {

	private DistanceController DistanceController = new DistanceController();
	
	@Test
	public void getDistance() {
		
		Double distance = DistanceController.distance("5.0", "-4.0", "6.0", "-3.2");
		 Assert.assertTrue(distance.doubleValue() ==  142.03123267829451);		
		
	}


}
