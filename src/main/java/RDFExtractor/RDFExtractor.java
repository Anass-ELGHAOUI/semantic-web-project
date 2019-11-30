package RDFExtractor;

import Constants.Constants;
import Models.BikeStation;
import Models.City;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class RDFExtractor {
    /* This method returns all the cities stored in the triplestore */
    public List<City> getCities() {
        /* List to store all cities */
        List<City> cities = new ArrayList<City>();

        RDFConnection conn = RDFConnectionFactory.connect(Constants.triplestore);
        /* Returns url to json response file */
        String result = conn.query("PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                "PREFIX dbo: <http://dbpedia.org/ontology/>" +
                "SELECT ?label WHERE {" +
                "  ?city a dbo:city ." +
                "  ?city rdfs:label ?label ." +
                "} " ).toString().replace("GET ", "").concat("&output=json");
        try {
            URL url = new URL(result);
            URLConnection request = url.openConnection();
            request.connect();

            JsonParser jp = new JsonParser();
            JsonObject rdfTriples = jp.parse(new InputStreamReader((InputStream) request.getContent())).getAsJsonObject();

            JsonObject results = (JsonObject) rdfTriples.get("results");
            JsonArray bindings = (JsonArray) results.get("bindings");

            /* Get city */
            for (int i = 0; i < bindings.size(); i++) {
                City city = new City();

                JsonObject object = (JsonObject) bindings.get(i);
                JsonObject obj = (JsonObject) object.get("label");
                String cityName = obj.get("value").toString().replace("\"", "");

                city.setName(cityName);
                cities.add(city);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

//        /* Print all cities information */
//        System.out.println("Number of cities: " + cities.size());
//        for (int i = 0; i < cities.size(); i++) {
//            System.out.println("City name: " + cities.get(i).getName());
//            System.out.println("Number of stations: " + cities.get(i).getBikeStations().size());
//            for (int j = 0; j < cities.get(i).getBikeStations().size(); j++) {
//                System.out.println("Bike station name: " + cities.get(i).getBikeStations().get(j).getName());
//                System.out.println("Id: " + cities.get(i).getBikeStations().get(j).getId());
//                System.out.println("Lattitude: " + cities.get(i).getBikeStations().get(j).getLattitude());
//                System.out.println("Longitude: " + cities.get(i).getBikeStations().get(j).getLongitude());
//                System.out.println("Available: " + cities.get(i).getBikeStations().get(j).getAvailable());
//                System.out.println("Free: " + cities.get(i).getBikeStations().get(j).getFree());
//                System.out.println("Total: " + cities.get(i).getBikeStations().get(j).getTotal());
//                System.out.println("Card paiement: " + cities.get(i).getBikeStations().get(j).getCardPaiement());
//                System.out.println();
//            }
//            System.out.println();
//        }

        return cities;
    }

    /* This method returns bike stations of given city
    * returns result in a city object */
    public City getBikeStations(String cityName) {
        /* List to store all bik */
        City city = new City();
        city.setName(cityName);

        RDFConnection conn = RDFConnectionFactory.connect(Constants.triplestore);
        /* Returns url to json response file */
        String result = conn.query("PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                " SELECT DISTINCT ?label ?id ?lat ?long ?available ?free ?total ?cardPaiement WHERE {" +
                " ?city rdfs:label \"" + cityName + "\" ." +
                " ?city <http://www.example.com/bikeStations> ?bikeStation ." +
                " ?bikeStation rdfs:label ?label ." +
                " ?bikeStation <http://www.example.com/id> ?id ." +
                " ?bikeStation <http://www.w3.org/2003/01/geo/wgs84_pos#lat> ?lat ." +
                " ?bikeStation <http://www.w3.org/2003/01/geo/wgs84_pos#long> ?long ." +
                " ?bikeStation <http://www.example.com/available> ?available ." +
                " ?bikeStation <http://www.example.com/free> ?free ." +
                " ?bikeStation <http://www.example.com/total> ?total ." +
                " ?bikeStation <http://www.example.com/cardPaiement> ?cardPaiement ." +
                "} " ).toString().replace("GET ", "").concat("&output=json");
        try {
            URL url = new URL(result);
            URLConnection request = url.openConnection();
            request.connect();

            JsonParser jp = new JsonParser();
            JsonObject rdfTriples = jp.parse(new InputStreamReader((InputStream) request.getContent())).getAsJsonObject();

            JsonObject results = (JsonObject) rdfTriples.get("results");
            JsonArray bindings = (JsonArray) results.get("bindings");

            /* Get citie's bikestations */
            List<BikeStation> bikeStations = new ArrayList<BikeStation>();
            for (int i = 0; i < bindings.size(); i++) {
                JsonObject object = (JsonObject) bindings.get(i);
                JsonObject label = (JsonObject) object.get("label");
                String bikeStationName = label.get("value").toString().replace("\"", "");

                JsonObject id = (JsonObject) object.get("id");
                String bikeStationId = id.get("value").toString().replace("\"", "");

                JsonObject lat = (JsonObject) object.get("lat");
                String bikeStationLat = lat.get("value").toString().replace("\"", "");

                JsonObject longitude = (JsonObject) object.get("long");
                String bikeStationLong = longitude.get("value").toString().replace("\"", "");

                JsonObject available = (JsonObject) object.get("available");
                String bikeStationAvailable = available.get("value").toString().replace("\"", "");

                JsonObject free = (JsonObject) object.get("free");
                String bikeStationFree = free.get("value").toString().replace("\"", "");

                JsonObject total = (JsonObject) object.get("total");
                String bikeStationTotal = total.get("value").toString().replace("\"", "");

                JsonObject cardPaiement = (JsonObject) object.get("cardPaiement");
                String bikeStationCardPaiement = cardPaiement.get("value").toString().replace("\"", "");

                BikeStation bikeStation = new BikeStation();
                bikeStation.setName(bikeStationName);
                bikeStation.setId(bikeStationId);
                bikeStation.setLattitude(bikeStationLat);
                bikeStation.setLongitude(bikeStationLong);
                bikeStation.setAvailable(bikeStationAvailable);
                bikeStation.setFree(bikeStationFree);
                bikeStation.setTotal(bikeStationTotal);
                bikeStation.setCardPaiement(bikeStationCardPaiement);

                bikeStations.add(bikeStation);
            }

            city.setBikeStations(bikeStations);

        } catch (IOException e) {
            e.printStackTrace();
        }

//        /* Print all citie's bike stations information */
//        System.out.println("City name: " + city.getName());
//        System.out.println("Number of stations: " + city.getBikeStations().size());
//        for (int i = 0; i < city.getBikeStations().size(); i++) {
//            System.out.println("Bike station name: " + city.getBikeStations().get(i).getName());
//            System.out.println("Id: " + city.getBikeStations().get(i).getId());
//            System.out.println("Lattitude: " + city.getBikeStations().get(i).getLattitude());
//            System.out.println("Longitude: " + city.getBikeStations().get(i).getLongitude());
//            System.out.println("Available: " + city.getBikeStations().get(i).getAvailable());
//            System.out.println("Free: " + city.getBikeStations().get(i).getFree());
//            System.out.println("Total: " + city.getBikeStations().get(i).getTotal());
//            System.out.println("Card paiement: " + city.getBikeStations().get(i).getCardPaiement());
//            System.out.println();
//        }

        return city;
    }
}
