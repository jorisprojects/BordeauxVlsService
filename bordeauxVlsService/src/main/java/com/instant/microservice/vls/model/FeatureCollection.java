
package com.instant.microservice.vls.model;

import java.util.List;

import javax.xml.bind.annotation.*;

import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlRootElement(name="FeatureCollection", namespace = "http://www.opengis.net/wfs")
@XmlAccessorType(XmlAccessType.FIELD)
public class FeatureCollection {
	
    @XmlPath("gml:featureMember")
    private List<FeatureMember> featureMembers;

	public List<FeatureMember> getFeatureMembers() {
		return featureMembers;
	}

	public void setFeatureMembers(List<FeatureMember> featureMembers) {
		this.featureMembers = featureMembers;
	}

	
	
	

	
}
