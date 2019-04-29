package com.instant.microservice.vls.controller;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired	
	private CustomRestTemplate customRestTemplate;
	
	@Autowired
	private RestTemplate restTemplate;

	
	
	/*
	 * Ce service retourne la liste des stations de vélos en libre-service de Bordeaux 
	 * avec leur distance par rapport aux coordonnées GPS de référence
	 */
	@RequestMapping(method=RequestMethod.GET,produces = "application/json")
	public List<FeatureMember> getAllStations(@RequestParam(value="latitude", defaultValue="0.0") String latitude, @RequestParam(value="longitude", defaultValue="0.0") String longitude) {
		//Creer un PointGps depuis les coordonnées en paramètre
		PointGps pointGpsFrom = new PointGps(Double.parseDouble(latitude),Double.parseDouble(longitude));
		//Custom restTemplate pour forcer le content-type dans le header de retour

		//Récupère la liste des vls de Bordeaux
		FeatureCollection featureCollection =  customRestTemplate.getForObject("http://data.lacub.fr/wfs?key=9Y2RU3FTE8&SERVICE=WFS&VERSION=1.1.0&REQUEST=GetFeature&TYPENAME=CI_VCUB_P&SRSNAME=EPSG:4326", FeatureCollection.class);

		List<FeatureMember> featureMembers = new ArrayList<FeatureMember>();
		
		//Calcul de la distance entre les coordonnées du point GPS de référence et celles de chaque stations  
		featureCollection.getFeatureMembers().forEach(featureMember -> {
			//Split la position du featureMember pour créer un PointGps
			String[] positions = featureMember.getPosition().split(" ");
			PointGps pointGpsTo = new PointGps(Double.parseDouble(positions[0]),Double.parseDouble(positions[1]));

			//Appel du WS pour calculer la distance entre deux points GPS
			Double distance = restTemplate.getForObject("http://calcul-distance-service:8081/distance?latitudeFrom={latitudeFrom}&longitudeFrom={longitudeFrom}&latitudeTo={latitudeTo}&longitudeTo={longitudeTo}", Double.class,
					pointGpsFrom.getLatitude(),pointGpsFrom.getLongitude(),pointGpsTo.getLatitude(),pointGpsTo.getLongitude());
			//Ajout de la distance trouvée 
			featureMember.setDistance(distance);	
			featureMembers.add(featureMember);		
		});
		//Retourne la liste des stations de vélo par ordre de proximité
		return featureMembers.stream().sorted(Comparator.comparingDouble(FeatureMember::getDistance))
				.collect(Collectors.toList());
	}
}
