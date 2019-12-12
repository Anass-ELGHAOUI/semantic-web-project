package JSONCityParser;

import Models.BikeStation;
import Models.City;
import RDFGenerator.RDFGenerator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class JSONCityParser {
    public void jsonParser (String sURL, String cityName, String country) {
        // Connect to the URL using java's native library
        try {
            RDFGenerator rdfGenerator = new RDFGenerator();

            URL url = new URL(sURL);
            URLConnection request = url.openConnection();
            request.connect();

            // parsing file "JSONExample.json"
            Object obj = new org.json.simple.parser.JSONParser().parse(new InputStreamReader((InputStream) request.getContent()));

            City city = new City();
            city.setName(cityName);
            city.setCountry(country);

            // typecasting obj to JSONObject
            JSONObject jo = (JSONObject) obj;

            // getting type and name
            String typex = (String) jo.get("type");
            String namex = (String) jo.get("name");

            // getting features
            JSONArray ja = (JSONArray) jo.get("features");

            // iterating features
            Iterator itr2 = ja.iterator();

            while (itr2.hasNext()) {
                Map feature = ((Map) itr2.next());
                Map properties = (Map) feature.get("properties");
                BikeStation bikeStation = new BikeStation();
                bikeStation.setName((String)properties.get("name"));
                bikeStation.setId((String)properties.get("number"));
                bikeStation.setLattitude((String)properties.get("lat"));
                bikeStation.setLongitude((String)properties.get("lng"));
                bikeStation.setTotal((String)properties.get("bike_stands"));
                bikeStation.setAvailable((String)properties.get("available_bikes"));
                bikeStation.setFree((String)properties.get("available_bike_stands"));
                if (((String) properties.get("banking")).equals("false")) {
                    bikeStation.setCardPaiement("0");
                }
                else if (((String)properties.get("banking")).equals("true")) {
                    bikeStation.setCardPaiement("1");
                }
                city.addBikeStation(bikeStation);
            }

//        for (BikeStation bikeStation : lyon.getBikeStations()) {
//            System.out.println("Name : "+bikeStation.getName());
//            System.out.println("Id : "+bikeStation.getId());
//            System.out.println("Lattitude : "+bikeStation.getLattitude());
//            System.out.println("Longtitude : "+bikeStation.getLongitude());
//            System.out.println("Toal : "+bikeStation.getTotal());
//            System.out.println("Available : "+bikeStation.getAvailable());
//            System.out.println("Free : "+bikeStation.getFree());
//            System.out.println("Card Paiement : "+bikeStation.getCardPaiement()+"\n\n");
//        }

            rdfGenerator.generateRDF(city);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void jsonParserV2 (String sURL, String cityName, String country){
        // Connect to the URL using java's native library
        try {
            RDFGenerator rdfGenerator = new RDFGenerator();

            URL url = new URL(sURL);
            URLConnection request = url.openConnection();
            request.connect();

            // parsing file "JSONExample.json"
            Object ob = new org.json.simple.parser.JSONParser().parse(new InputStreamReader((InputStream) request.getContent()));

            City city = new City();
            city.setName(cityName);
            city.setCountry(country);

            // typecasting obj to JSONObject
            JSONObject jo = (JSONObject) ob;

            // getting features
            JSONArray ja = (JSONArray) jo.get("records");

            // iterating features
            Iterator itr = ja.iterator();

            while (itr.hasNext()) {
                Map feature = ((Map) itr.next());
                Map properties = (Map) feature.get("fields");
                BikeStation bikeStation = new BikeStation();
                bikeStation.setName((String)properties.get("nom"));
                bikeStation.setId((String)feature.get("recordid"));
                bikeStation.setTotal(String.valueOf(properties.get("nombreemplacementsactuels")));
                bikeStation.setAvailable(String.valueOf(properties.get("nombrevelosdisponibles")));
                bikeStation.setFree(String.valueOf(properties.get("nombreemplacementsdisponibles")));
                Map geometry = (Map) feature.get("geometry");
                ArrayList coordinates = (ArrayList) geometry.get("coordinates");
                bikeStation.setLattitude(String.valueOf(coordinates.get(1)));
                bikeStation.setLongitude(String.valueOf(coordinates.get(0)));
                bikeStation.setCardPaiement("0");
                city.addBikeStation(bikeStation);
            }

//        for (BikeStation bikeStation : lyon.getBikeStations()) {
//            System.out.println("Name : "+bikeStation.getName());
//            System.out.println("Id : "+bikeStation.getId());
//            System.out.println("Lattitude : "+bikeStation.getLattitude());
//            System.out.println("Longtitude : "+bikeStation.getLongitude());
//            System.out.println("Toal : "+bikeStation.getTotal());
//            System.out.println("Available : "+bikeStation.getAvailable());
//            System.out.println("Free : "+bikeStation.getFree());
//            System.out.println("Card Paiement : "+bikeStation.getCardPaiement()+"\n\n");
//        }

            rdfGenerator.generateRDF(city);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void jsonParserV3 (String sURL, String cityName, String country){
        // Connect to the URL using java's native library
        try {
            RDFGenerator rdfGenerator = new RDFGenerator();

            URL url = new URL(sURL);
            URLConnection request = url.openConnection();
            request.connect();

            // parsing file "JSONExample.json"
            Object ob = new org.json.simple.parser.JSONParser().parse(new InputStreamReader((InputStream) request.getContent()));

            City city = new City();
            city.setName(cityName);
            city.setCountry(country);

            // typecasting obj to JSONObject
            JSONObject jo = (JSONObject) ob;

            // getting features
            JSONArray ja = (JSONArray) jo.get("records");

            // iterating features
            Iterator itr = ja.iterator();

            while (itr.hasNext()) {
                Map feature = ((Map) itr.next());
                Map properties = (Map) feature.get("fields");
                BikeStation bikeStation = new BikeStation();
                bikeStation.setName((String)properties.get("name"));
                bikeStation.setId((String)feature.get("recordid"));
                bikeStation.setTotal(String.valueOf(properties.get("capacity")));
                bikeStation.setAvailable("-");
                bikeStation.setFree("-");
                ArrayList coordinates = (ArrayList) properties.get("geo_point_2d");
                bikeStation.setLattitude(String.valueOf(coordinates.get(0)));
                bikeStation.setLongitude(String.valueOf(coordinates.get(1)));
                bikeStation.setCardPaiement("0");
                city.addBikeStation(bikeStation);
            }

//        for (BikeStation bikeStation : lyon.getBikeStations()) {
//            System.out.println("Name : "+bikeStation.getName());
//            System.out.println("Id : "+bikeStation.getId());
//            System.out.println("Lattitude : "+bikeStation.getLattitude());
//            System.out.println("Longtitude : "+bikeStation.getLongitude());
//            System.out.println("Toal : "+bikeStation.getTotal());
//            System.out.println("Available : "+bikeStation.getAvailable());
//            System.out.println("Free : "+bikeStation.getFree());
//            System.out.print  ln("Card Paiement : "+bikeStation.getCardPaiement()+"\n\n");
//        }

            rdfGenerator.generateRDF(city);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void jsonParserV4 (String sURL, String cityName, String country){
        // Connect to the URL using java's native library
        try {
            RDFGenerator rdfGenerator = new RDFGenerator();

            URL url = new URL(sURL);
            URLConnection request = url.openConnection();
            request.connect();

            // parsing file "JSONExample.json"
            Object ob = new org.json.simple.parser.JSONParser().parse(new InputStreamReader((InputStream) request.getContent()));

            City city = new City();
            city.setName(cityName);
            city.setCountry(country);

            // typecasting obj to JSONObject
            JSONObject jo = (JSONObject) ob;

            // getting features
            JSONArray ja = (JSONArray) jo.get("features");

            // iterating features
            Iterator itr = ja.iterator();

            while (itr.hasNext()) {
                Map feature = ((Map) itr.next());
                Map properties = (Map) feature.get("properties");
                BikeStation bikeStation = new BikeStation();
                bikeStation.setName((String)properties.get("NOM_VOIE"));
                bikeStation.setId(String.valueOf(feature.get("id")));
                bikeStation.setTotal(String.valueOf(properties.get("NBR_PT_ACC")));
                bikeStation.setAvailable("NBR_PT_ACC");
                bikeStation.setFree("-");
                Map geometry = (Map) feature.get("geometry");
                ArrayList coordinates = (ArrayList) geometry.get("coordinates");
                bikeStation.setLattitude(String.valueOf(coordinates.get(1)));
                bikeStation.setLongitude(String.valueOf(coordinates.get(0)));
                bikeStation.setCardPaiement("0");
                city.addBikeStation(bikeStation);
            }

//        for (BikeStation bikeStation : lyon.getBikeStations()) {
//            System.out.println("Name : "+bikeStation.getName());
//            System.out.println("Id : "+bikeStation.getId());
//            System.out.println("Lattitude : "+bikeStation.getLattitude());
//            System.out.println("Longtitude : "+bikeStation.getLongitude());
//            System.out.println("Toal : "+bikeStation.getTotal());
//            System.out.println("Available : "+bikeStation.getAvailable());
//            System.out.println("Free : "+bikeStation.getFree());
//            System.out.println("Card Paiement : "+bikeStation.getCardPaiement()+"\n\n");
//        }

            rdfGenerator.generateRDF(city);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void jcdecauxParser (String sURL, String cityName, String country) {
        // Connect to the URL using java's native library
        try {
            RDFGenerator rdfGenerator = new RDFGenerator();

            URL url = new URL(sURL);
            URLConnection request = url.openConnection();
            request.connect();

            // parsing file "JSONExample.json"
            Object obj = new org.json.simple.parser.JSONParser().parse(new InputStreamReader((InputStream) request.getContent()));

            City city = new City();
            city.setName(cityName);
            city.setCountry(country);

            // typecasting obj to JSONObject
            JSONObject jo = (JSONObject) obj;

            // getting features
            JSONArray ja = (JSONArray) jo.get("records");

            // iterating features
            Iterator itr2 = ja.iterator();

            while (itr2.hasNext()) {
                Map feature = ((Map) itr2.next());
                Map fields = (Map) feature.get("fields");
                BikeStation bikeStation = new BikeStation();
                bikeStation.setName((String)fields.get("name"));
                bikeStation.setId(String.valueOf(fields.get("number")));
                bikeStation.setTotal(String.valueOf(fields.get("bike_stands")));
                bikeStation.setAvailable(String.valueOf(fields.get("available_bikes")));
                bikeStation.setFree(String.valueOf(fields.get("available_bike_stands")));
                String cardValue = String.valueOf(fields.get("banking")).equals("false") ? "0" : "1";
                bikeStation.setCardPaiement(cardValue);
                ArrayList position = (ArrayList)fields.get("position");
                bikeStation.setLattitude(String.valueOf(position.get(0)));
                bikeStation.setLongitude(String.valueOf(position.get(1)));
                city.addBikeStation(bikeStation);
            }

//        for (BikeStation bikeStation : lyon.getBikeStations()) {
//            System.out.println("Name : "+bikeStation.getName());
//            System.out.println("Id : "+bikeStation.getId());
//            System.out.println("Lattitude : "+bikeStation.getLattitude());
//            System.out.println("Longtitude : "+bikeStation.getLongitude());
//            System.out.println("Toal : "+bikeStation.getTotal());
//            System.out.println("Available : "+bikeStation.getAvailable());
//            System.out.println("Free : "+bikeStation.getFree());
//            System.out.println("Card Paiement : "+bikeStation.getCardPaiement()+"\n\n");
//        }

            rdfGenerator.generateRDF(city);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}