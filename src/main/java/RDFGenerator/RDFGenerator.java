package RDFGenerator;

import Models.City;
import com.sun.org.apache.xml.internal.utils.StringBufferPool;
import org.apache.jena.rdf.model.*;
import org.apache.jena.riot.RDFDataMgr;

import java.io.OutputStream;
import java.math.BigDecimal;

public class RDFGenerator {
    public void generateRDF(City city) {
        Model m = ModelFactory.createDefaultModel();

        String ex = "http://www.example.com/";
        String geo = "http://www.w3.org/2003/01/geo/wgs84_pos#";
        String rdfs = "http://www.w3.org/2000/01/rdf-schema#";
        String rdf = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
        String xsd = "http://www.w3.org/2001/XMLSchema#";
        String dbo = "<http://dbpedia.org/ontology/>";

        Property typeProp = m.createProperty(rdf, "type");
        Property cityProp = m.createProperty(dbo + "city");
        Property labelProp = m.createProperty(rdfs + "label");
        Property bikeStationsProp = m.createProperty(ex + "bikeStations");
        Property[] bikeStationProp = new Property[city.getBikeStations().size()];
        for (int i = 0; i < city.getBikeStations().size(); i++) {
            bikeStationProp[i] = m.createProperty(ex + "bikeStation" + (i + 1));
        }

        Property spatialThingProp = m.createProperty(geo + "SpatialThing");
        Property idProp = m.createProperty(ex + "id");
        Property availableProp = m.createProperty(ex + "available");
        Property freeProp = m.createProperty(ex + "free");
        Property totalProp = m.createProperty(ex + "total");
        Property cardPaiementProp = m.createProperty(ex + "cardPaiement");
        Property latProp = m.createProperty(geo + "lat");
        Property longProp = m.createProperty(geo + "long");

        m.setNsPrefix("ex", ex);
        m.setNsPrefix("geo", geo);
        m.setNsPrefix("rdfs", rdfs);
        m.setNsPrefix("xsd", xsd);
        m.setNsPrefix("dbo", dbo);

        Resource cityRsrc = m.createResource(dbo + city.getName())
                .addProperty(typeProp, cityProp)
                .addProperty(labelProp, city.getName());
        for (int i = 0; i < city.getBikeStations().size(); i++) {
            cityRsrc.addProperty(bikeStationsProp, bikeStationProp[i]);
        }

        for (int i = 0; i < city.getBikeStations().size(); i++) {
            Resource bikeStationRsrc = m.createResource(ex + "bikeStation" + (i + 1))
                    .addProperty(typeProp, spatialThingProp);

            if (city.getBikeStations().get(i).getName() != null) {
                bikeStationRsrc.addProperty(labelProp, city.getBikeStations().get(i).getName());
            }
            if (city.getBikeStations().get(i).getId() != null) {
                bikeStationRsrc.addProperty(idProp, city.getBikeStations().get(i).getId());
            }
            if (city.getBikeStations().get(i).getLattitude() != null) {
                bikeStationRsrc.addProperty(latProp, city.getBikeStations().get(i).getLattitude());
            }
            if (city.getBikeStations().get(i).getLongitude() != null) {
                bikeStationRsrc.addProperty(longProp, city.getBikeStations().get(i).getLongitude());
            }
            if (city.getBikeStations().get(i).getAvailable() != null) {
                bikeStationRsrc.addProperty(availableProp, city.getBikeStations().get(i).getAvailable());
            }
            if (city.getBikeStations().get(i).getFree() != null) {
                bikeStationRsrc.addProperty(freeProp, city.getBikeStations().get(i).getFree());
            }
            if (city.getBikeStations().get(i).getTotal() != null) {
                bikeStationRsrc.addProperty(totalProp, city.getBikeStations().get(i).getTotal());
            }
            if (city.getBikeStations().get(i).getCardPaiement() != null) {
                bikeStationRsrc.addProperty(cardPaiementProp, city.getBikeStations().get(i).getCardPaiement());
            }
        }

        m.write(System.out, "Turtle");
    }
}
