package com.instant.microservice.vls;


import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.web.client.RestTemplate;

import com.instant.microservice.vls.controller.CustomRestTemplate;
import com.instant.microservice.vls.controller.VlsController;
import com.instant.microservice.vls.model.FeatureCollection;
import com.instant.microservice.vls.model.FeatureMember;

@RunWith(MockitoJUnitRunner.class)
public class VlsApplicationTests {

	@Mock
	private CustomRestTemplate customRestTemplate;
	
	@Mock
	private RestTemplate restTemplate;


	@InjectMocks
	private VlsController vlsController = new VlsController();

	@Test
	public void getListVlsWithDistance() {
		FeatureCollection fc = new FeatureCollection();
		FeatureMember fm = new FeatureMember();
		fm.setName("station1");
		fm.setNbPlaces("10");
		fm.setNbVelos("15");
		fm.setPosition("5.2 4.3");
		
		fc.setFeatureMembers(Collections.singletonList(fm));
		
		//Mock l'appel du WS Vcub de Bordeaux
		Mockito
        .when(customRestTemplate.getForObject("http://data.lacub.fr/wfs?key=9Y2RU3FTE8&SERVICE=WFS&VERSION=1.1.0&REQUEST=GetFeature&TYPENAME=CI_VCUB_P&SRSNAME=EPSG:4326", FeatureCollection.class))
        .thenReturn(fc);
		
		//Mock l'appel du WS de calcul de distance
		Mockito
        .when(restTemplate.getForObject(ArgumentMatchers.anyString(), ArgumentMatchers.any(Class.class),
        		ArgumentMatchers.anyDouble(),ArgumentMatchers.anyDouble(),ArgumentMatchers.anyDouble(),ArgumentMatchers.anyDouble()))
        .thenReturn(2.0);
		
		List<FeatureMember> stationVls = vlsController.getAllStations("5.2", "4.3");
		
		 Assert.assertEquals(fm, stationVls.get(0));
		
		
	}

}
