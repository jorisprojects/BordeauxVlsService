
package com.instant.microservice.vls.model;

import java.beans.Transient;

import javax.xml.bind.annotation.*;

import org.eclipse.persistence.oxm.annotations.XmlPath;
import org.springframework.beans.factory.annotation.Autowired;

@XmlRootElement(name="gml:featureMember")
@XmlAccessorType(XmlAccessType.FIELD)
public class FeatureMember {
	
    @XmlPath("bm:CI_VCUB_P/bm:NOM/text()")
	private String name;

    @XmlPath("bm:CI_VCUB_P/bm:geometry/gml:Point/gml:pos/text()")
    private String position;
    
    @XmlPath("bm:CI_VCUB_P/bm:NBVELOS/text()")
    private String nbVelos;
    
    @XmlPath("bm:CI_VCUB_P/bm:NBPLACES/text()")
    private String nbPlaces;
    
    
    private Double distance;
    
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getNbVelos() {
		return nbVelos;
	}

	public void setNbVelos(String nbVelos) {
		this.nbVelos = nbVelos;
	}

	public String getNbPlaces() {
		return nbPlaces;
	}

	public void setNbPlaces(String nbPlaces) {
		this.nbPlaces = nbPlaces;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	
	
	
	

	
}
