package RDFExtractor;

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
    /* This method will get all the triples from the triplestore in a JSON file */
    public List<City> getTriples() {
        RDFConnection conn = RDFConnectionFactory.connect("http://localhost:3030/bikstation_db");
        /* Returns url to json response file */
        String result = conn.query("SELECT ?subject ?predicate ?object" +
                " WHERE {" +
                " ?subject ?predicate ?object" +
                "}" ).toString().replace("GET ", "").concat("&output=json");
        return parseJson(result);
    }

    /* This method parses the json file and returns the information about cities */
    public List<City> parseJson(String sURL) {
        /* List to store all cities */
        List<City> cities = new ArrayList<City>();

        try {
            URL url = new URL(sURL);
            URLConnection request = url.openConnection();
            request.connect();

            JsonParser jp = new JsonParser();
            JsonObject rdfTriples = jp.parse(new InputStreamReader((InputStream) request.getContent())).getAsJsonObject();

            JsonObject results = (JsonObject) rdfTriples.get("results");
            JsonArray bindings = (JsonArray) results.get("bindings");

            /* Get city */
            for (int i = 0; i < bindings.size(); i++) {
                City city = new City();
                JsonObject triple = (JsonObject) bindings.get(i);

                JsonObject subject = (JsonObject) triple.get("subject");
                JsonObject predicate = (JsonObject) triple.get("predicate");
                JsonObject object = (JsonObject) triple.get("object");

                String type = object.get("type").toString().replace("\"", "");
                String value = object.get("value").toString().replace("\"", "");

                if (type.equals("uri") && value.equals("http://dbpedia.org/page/city")) {
                    String cityName = subject.get("value").toString().replace("http://dbpedia.org/page/", "").replace("\"", "");
                    city.setName(cityName);
                    cities.add(city);
                }
            }

            /* Get all bike stations of the city */
            for (int i = 0; i < cities.size(); i++) {
                List<BikeStation> bikeStations = new ArrayList<BikeStation>();
                for (int j = 0; j < bindings.size(); j++) {
                    JsonObject triple = (JsonObject) bindings.get(j);

                    JsonObject subject = (JsonObject) triple.get("subject");
                    JsonObject predicate = (JsonObject) triple.get("predicate");
                    JsonObject object = (JsonObject) triple.get("object");

                    String type = predicate.get("type").toString().replace("\"", "");
                    String value = predicate.get("value").toString().replace("\"", "");
                    String cityName = subject.get("value").toString().replace("\"", "").replace("http://dbpedia.org/page/", "");

                    /* Check if it's the bike stations of the corresponding city */
                    if (type.equals("uri") && value.equals("http://www.example.com/bikeStations") && cityName.equals(cities.get(i).getName())) {
                        String bikeStationId = object.get("value").toString().replace("http://www.example.com/", "").replace("\"", "");

                        /* Get bike station info */
                        BikeStation bikeStation = new BikeStation();
                        for (int k = 0; k < bindings.size(); k++) {
                            JsonObject triple1 = (JsonObject) bindings.get(k);

                            JsonObject subject1 = (JsonObject) triple1.get("subject");
                            JsonObject predicate1 = (JsonObject) triple1.get("predicate");
                            JsonObject object1 = (JsonObject) triple1.get("object");

                            String type1 = subject1.get("type").toString().replace("\"", "");
                            String value1 = subject1.get("value").toString().replace("\"", "");

                            if (type1.equals("uri") && value1.equals("http://www.example.com/" + bikeStationId)) {
                                String info = predicate1.get("value").toString().replace("\"", "");

                                if (info.equals("http://www.w3.org/2000/01/rdf-schema#label")) {
                                    String infoValue = object1.get("value").toString().replace("\"", "");
                                    bikeStation.setName(infoValue);
                                }
                                else if (info.equals("http://www.example.com/available")) {
                                    String infoValue = object1.get("value").toString().replace("\"", "");
                                    bikeStation.setAvailable(infoValue);
                                }
                                else if (info.equals("http://www.example.com/id")) {
                                    String infoValue = object1.get("value").toString().replace("\"", "");
                                    bikeStation.setId(infoValue);
                                }
                                else if (info.equals("http://www.example.com/total")) {
                                    String infoValue = object1.get("value").toString().replace("\"", "");
                                    bikeStation.setTotal(infoValue);
                                }
                                else if (info.equals("http://www.w3.org/2003/01/geo/wgs84_pos#lat")) {
                                    String infoValue = object1.get("value").toString().replace("\"", "");
                                    bikeStation.setLattitude(infoValue);
                                }
                                else if (info.equals("http://www.w3.org/2003/01/geo/wgs84_pos#long")) {
                                    String infoValue = object1.get("value").toString().replace("\"", "");
                                    bikeStation.setLongitude(infoValue);
                                }
                                else if (info.equals("http://www.example.com/cardPaiement")) {
                                    String infoValue = object1.get("value").toString().replace("\"", "");
                                    bikeStation.setCardPaiement(infoValue);
                                }
                            }
                        }
                        bikeStations.add(bikeStation);
                    }
                }
                cities.get(i).setBikeStations(bikeStations);
            }

            /* Print all cities information */
            for (int i = 0; i < cities.size(); i++) {
                System.out.println("City name: " + cities.get(i).getName());
                System.out.println("number of stations " + cities.get(i).getBikeStations().size());
                for (int j = 0; j < cities.get(i).getBikeStations().size(); j++) {
                    System.out.println("Bike station name: " + cities.get(i).getBikeStations().get(j).getName());
                    System.out.println("Id: " + cities.get(i).getBikeStations().get(j).getId());
                    System.out.println("Lattitude: " + cities.get(i).getBikeStations().get(j).getLattitude());
                    System.out.println("Longitude: " + cities.get(i).getBikeStations().get(j).getLongitude());
                    System.out.println("Available: " + cities.get(i).getBikeStations().get(j).getAvailable());
                    System.out.println("Free: " + cities.get(i).getBikeStations().get(j).getFree());
                    System.out.println("Total: " + cities.get(i).getBikeStations().get(j).getTotal());
                    System.out.println("Card paiement: " + cities.get(i).getBikeStations().get(j).getCardPaiement());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return cities;
    }
}
