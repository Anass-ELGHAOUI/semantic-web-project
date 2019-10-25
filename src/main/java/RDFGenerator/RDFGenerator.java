package RDFGenerator;

import Models.City;
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

        m.setNsPrefix("ex", ex);
        m.setNsPrefix("geo", geo);
        m.setNsPrefix("rdfs", rdfs);
        m.setNsPrefix("xsd", xsd);
        m.setNsPrefix("dbo", dbo);

        Resource cityRsrc = m.createResource(dbo + city.getName())
                .addProperty(typeProp, cityProp);
        for (int i = 0; i < city.getBikeStations().size(); i++) {
            cityRsrc.addProperty(bikeStationsProp, bikeStationProp[i]);
        }

//        for (int i = 0; i < city.getBikeStations().size(); i++) {
//            Resource bikeStationRsrc = m.createResource(ex + "bikeStation" + (i + 1))
//                    .addProperty(typeProp, )
//        }

        m.write(System.out, "Turtle");

//        RDFDataMgr.write(new OutputStream());
    }
}
