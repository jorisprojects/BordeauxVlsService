package com.instant.microservice.vls.controller;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.instant.microservice.vls.model.FeatureCollection;
import com.instant.microservice.vls.model.FeatureMember;
import com.instant.microservice.vls.model.PointGps;

@RestController
@RequestMapping("vls")
public class VlsController {

	@RequestMapping(method=RequestMethod.GET,produces = "application/json")
	public List<FeatureMember> vls(@RequestParam(value="latitude", defaultValue="0.0") String latitude, @RequestParam(value="longitude", defaultValue="0.0") String longitude) {
		CustomRestTemplate customRestTemplate = new CustomRestTemplate();
		FeatureCollection featureCollection =  customRestTemplate.getForObject("http://data.lacub.fr/wfs?key=9Y2RU3FTE8&SERVICE=WFS&VERSION=1.1.0&REQUEST=GetFeature&TYPENAME=CI_VCUB_P&SRSNAME=EPSG:4326", FeatureCollection.class);

		PointGps pointGpsFrom = new PointGps(Double.parseDouble(latitude),Double.parseDouble(longitude));
		
		RestTemplate restTemplate = new RestTemplate();
		List<FeatureMember> featureMembers = new ArrayList<FeatureMember>();
		featureCollection.getFeatureMembers().forEach(featureMember -> {
		String[] positions = featureMember.getPosition().split(" ");
		
		PointGps pointGpsTo = new PointGps(Double.parseDouble(positions[0]),Double.parseDouble(positions[1]));
		
		Double distance = restTemplate.getForObject("http://calcul-distance-service:8081/distance?latitudeFrom={latitudeFrom}&longitudeFrom={longitudeFrom}&latitudeTo={latitudeTo}&longitudeTo={longitudeTo}", Double.class,
				pointGpsFrom.getLatitude(),pointGpsFrom.getLongitude(),pointGpsTo.getLatitude(),pointGpsTo.getLongitude());
		featureMember.setDistance(distance);	
		featureMembers.add(featureMember);		
		});
		return featureMembers.stream().sorted(Comparator.comparingDouble(FeatureMember::getDistance))
				.collect(Collectors.toList());
	}
}
