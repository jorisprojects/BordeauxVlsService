package com.instant.microservice.vls.model;

public class PointGps {
	
	private Double latitude;
	
	private Double longitude;

	public Double getLatitude() {
		return latitude;
	}
	
	

	public PointGps(Double latitude, Double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}



	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	
	

}
