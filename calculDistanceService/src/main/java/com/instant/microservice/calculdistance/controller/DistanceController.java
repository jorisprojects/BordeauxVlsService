package com.instant.microservice.calculdistance.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("distance")
public class DistanceController {

	

		@RequestMapping(method=RequestMethod.GET)
		public Double distance(@RequestParam(value="latitudeFrom", defaultValue="0.0") String latitudeFrom, @RequestParam(value="longitudeFrom", defaultValue="0.0") String longitudeFrom,
				@RequestParam(value="latitudeTo", defaultValue="0.0") String latitudeTo, @RequestParam(value="longitudeTo", defaultValue="0.0") String longitudeTo) {
			
			Double latitudeFromDouble = Double.parseDouble(latitudeFrom);
			Double longitudeFromDouble = Double.parseDouble(longitudeFrom);
			Double latitudeToDouble = Double.parseDouble(latitudeTo);
			Double longitudeToDouble = Double.parseDouble(longitudeTo);
			
			
			return distanceBetweenTwoPoints( latitudeFromDouble, longitudeFromDouble, latitudeToDouble,longitudeToDouble);
			
		}

		private Double distanceBetweenTwoPoints(Double latitudeFrom, Double longitudeFrom, Double latitudeTo,
				Double longitudeTo) {
			
			Double latitudeFromRadian = Math.toRadians(latitudeFrom);
			Double longitudeFromRadian = Math.toRadians(longitudeFrom);
			Double latitudeToRadian = Math.toRadians(latitudeTo);
			Double longitudeToRadian = Math.toRadians(longitudeTo);
			
			return Math.acos(Math.sin(latitudeFromRadian)*Math.sin(latitudeToRadian)+Math.cos(latitudeFromRadian)*Math.cos(latitudeToRadian)*Math.cos(longitudeFromRadian-longitudeToRadian))*6366;
		}
		
		
	}


