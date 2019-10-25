package JSONParser;

import Models.BikeStation;
import Models.City;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;

public class JSONReadExample
{ 
    public static void main(String[] args) throws Exception  
    {
        String sURL = "https://download.data.grandlyon.com/wfs/rdata?SERVICE=WFS&VERSION=1.1.0&outputformat=GEOJSON&request=GetFeature&typename=jcd_jcdecaux.jcdvelov&SRSNAME=urn:ogc:def:crs:EPSG::4171"; //just a string

        // Connect to the URL using java's native library
        URL url = new URL(sURL);
        URLConnection request = url.openConnection();
        request.connect();

        // parsing file "JSONExample.json" 
        Object obj = new JSONParser().parse(new InputStreamReader((InputStream) request.getContent()));

        City lyon = new City();
        lyon.setName("Lyon");
          
        // typecasting obj to JSONObject 
        JSONObject jo = (JSONObject) obj; 
          
        // getting type and name
        String typex = (String) jo.get("type");
        String namex = (String) jo.get("name");

        // getting features
        JSONArray ja = (JSONArray) jo.get("features");

        // iterating features
        Iterator itr2 = ja.iterator();

        while (itr2.hasNext())
        {
            Map feature = ((Map) itr2.next());
            Map properties = (Map) feature.get("properties");
            System.out.println();
            BikeStation bikeStation = new BikeStation();
            bikeStation.setName((String)properties.get("name"));
            bikeStation.setId((String)properties.get("number"));
            bikeStation.setLattitude((String)properties.get("lat"));
            bikeStation.setLongitude((String)properties.get("lng"));
            bikeStation.setTotal((String)properties.get("bike_stands"));
            bikeStation.setAvailable((String)properties.get("available_bikes"));
            bikeStation.setFree((String)properties.get("available_bike_stands"));
            bikeStation.setCardPaiement((String)properties.get("banking"));
            lyon.addBikeStation(bikeStation);
        }

        for (BikeStation bikeStation : lyon.getBikeStations()){
            System.out.println("Name : "+bikeStation.getName());
            System.out.println("Id : "+bikeStation.getId());
            System.out.println("Lattitude : "+bikeStation.getLattitude());
            System.out.println("Longtitude : "+bikeStation.getLongitude());
            System.out.println("Toal : "+bikeStation.getTotal());
            System.out.println("Available : "+bikeStation.getAvailable());
            System.out.println("Free : "+bikeStation.getFree());
            System.out.println("Card Paiement : "+bikeStation.getCardPaiement()+"\n\n");


        }
    }
}