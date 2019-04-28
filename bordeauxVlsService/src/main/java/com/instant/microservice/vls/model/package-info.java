@XmlSchema(
    namespace="http:// www.example.com/working/gtm",
    xmlns={
        @XmlNs(prefix="gml", namespaceURI="http://www.opengis.net/gml"),
        @XmlNs(prefix="wfs", namespaceURI="http://www.opengis.net/wfs"),
        @XmlNs(prefix="ogc", namespaceURI="http://www.opengis.net/ogc"),
        @XmlNs(prefix="bm", namespaceURI="http://data.bordeaux-metropole.fr/wfs")
        

    },
    elementFormDefault=XmlNsForm.UNQUALIFIED)
package com.instant.microservice.vls.model;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.XmlSchema;
