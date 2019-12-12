package RDFGenerator;

import Constants.Constants;
import Models.City;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

public class RDFGenerator {
    public void generateRDF(City city) throws IOException {
        Model m = ModelFactory.createDefaultModel();

        String ex = "http://www.example.com/";
        String geo = "http://www.w3.org/2003/01/geo/wgs84_pos#";
        String rdfs = "http://www.w3.org/2000/01/rdf-schema#";
        String rdf = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
        String xsd = "http://www.w3.org/2001/XMLSchema#";
        String dbo = "http://dbpedia.org/ontology/";
        String dbr = "http://dbpedia.org/resource/";

        Property typeProp = m.createProperty(rdf, "type");
        Property cityProp = m.createProperty(dbo + "city");
        Property countryProp = m.createProperty(dbo + "country");
        Property labelProp = m.createProperty(rdfs + "label");
        Property bikeStationsProp = m.createProperty(ex + "bikeStations");
        Property[] bikeStationProp = new Property[city.getBikeStations().size()];
        for (int i = 0; i < city.getBikeStations().size(); i++) {
            bikeStationProp[i] = m.createProperty(ex + city.getName() + (i + 1));
        }

        Property bicycleSharingProp = m.createProperty(dbr + "Bicycle-sharing_system");
//        Property idProp = m.createProperty(ex + "id");
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

        Resource cityRsrc = m.createResource(dbr + city.getName())
                .addProperty(typeProp, cityProp)
                .addProperty(labelProp, city.getName())
                .addProperty(countryProp, city.getCountry());

//        ex:Montpellier a dbo:city;
//        rdfs:label  "Montpellier";
//        dbo:country "France";
//        ex:bikeStations ex:Montpellier1, ex:Montpellier2, ex:Montpellier3 .
        for (int i = 0; i < city.getBikeStations().size(); i++) {
            cityRsrc.addProperty(bikeStationsProp, bikeStationProp[i]);
        }

//        ex:Montpellier1  a geo:SpatialThing;
//        dbo:city	ex:Montpellier;
//        dbo:country	"France";
//        rdfs:label  "Bike Station Name";
//        ex:id 1;
//        geo:lat  23.232^^xsd:decimal;
//        geo:long  221.21^^xsd:decimal;
//        ex:available  3;
//        ex:free  4;
//        ex:total  20;
//        ex:cardPaiement 1 .
        for (int i = 0; i < city.getBikeStations().size(); i++) {
            Resource bikeStationRsrc = m.createResource(ex + city.getName() + (i + 1))
                    .addProperty(typeProp, bicycleSharingProp);

            bikeStationRsrc.addProperty(cityProp, city.getName());
            if (city.getBikeStations().get(i).getName() != null) {
                bikeStationRsrc.addProperty(labelProp, city.getBikeStations().get(i).getName());
            }
//            if (city.getBikeStations().get(i).getId() != null) {
//                bikeStationRsrc.addProperty(idProp, city.getBikeStations().get(i).getId());
//            }
            if (city.getBikeStations().get(i).getLattitude() != null) {
                bikeStationRsrc.addProperty(latProp, m.createTypedLiteral(new BigDecimal(city.getBikeStations().get(i).getLattitude())));
            }
            if (city.getBikeStations().get(i).getLongitude() != null) {
                bikeStationRsrc.addProperty(longProp,  m.createTypedLiteral(new BigDecimal(city.getBikeStations().get(i).getLongitude())));
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
            else {
                bikeStationRsrc.addProperty(cardPaiementProp, "0");
            }
        }

        FileWriter out = new FileWriter(new File("turtle-files/", city.getName() + ".ttl"));
        try {
            m.write( out, "Turtle" );
        }
        finally {
            try {
                out.close();
            }
            catch (IOException closeException) {
                // ignore
            }
        }

        RDFConnection conn = RDFConnectionFactory.connect(Constants.triplestore);
        conn.load("turtle-files/" + city.getName() + ".ttl");
        conn.close();
    }
}
