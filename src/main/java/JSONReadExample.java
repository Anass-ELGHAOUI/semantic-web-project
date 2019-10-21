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
            System.out.println("type : "+feature.get("type"));
        }
    }
}