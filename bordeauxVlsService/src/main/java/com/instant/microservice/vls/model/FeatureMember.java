
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((distance == null) ? 0 : distance.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nbPlaces == null) ? 0 : nbPlaces.hashCode());
		result = prime * result + ((nbVelos == null) ? 0 : nbVelos.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FeatureMember other = (FeatureMember) obj;
		if (distance == null) {
			if (other.distance != null)
				return false;
		} else if (!distance.equals(other.distance))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nbPlaces == null) {
			if (other.nbPlaces != null)
				return false;
		} else if (!nbPlaces.equals(other.nbPlaces))
			return false;
		if (nbVelos == null) {
			if (other.nbVelos != null)
				return false;
		} else if (!nbVelos.equals(other.nbVelos))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}

	
	
	
	

	
}
