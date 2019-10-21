package JSONParser;

import models.BikeStation;
import models.City;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;

public class JSONReadExample
{ 
    public static void main(String[] args) throws Exception  
    { 
        // parsing file "JSONExample.json" 
        Object obj = new JSONParser().parse(new FileReader("C:/Users/Anass/Documents/JSONExample.json"));

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
    }
}