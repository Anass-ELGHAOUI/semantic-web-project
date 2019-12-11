package Main;

import Constants.Constants;
import FileReader.UserFilesParser;
import XMLParser.XMLParser;
import JSONCityParser.JSONCityParser;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        while (true) {
            try {
                Thread.sleep(9000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            /* Delete triplestore content before uploading new one */
            RDFConnection conn = RDFConnectionFactory.connect(Constants.triplestore);
            conn.delete();

            /* Parse bike station files */
            /* Montpellier - France */
            XMLParser xmlParser = new XMLParser();
            xmlParser.XMLFileParser("https://data.montpellier3m.fr/sites/default/files/ressources/TAM_MMM_VELOMAG.xml", "Montpellier", "France");

            /* Strasbourg - France */
            xmlParser.XMLFileParser("http://velhop.strasbourg.eu/tvcstations.xml", "Strasbourg", "France");

            /* Lyon - France */
            JSONCityParser jsonParser = new JSONCityParser();
            jsonParser.jsonParser("https://download.data.grandlyon.com/wfs/rdata?SERVICE=WFS&VERSION=1.1.0&outputformat=GEOJSON&request=GetFeature&typename=jcd_jcdecaux.jcdvelov&SRSNAME=urn:ogc:def:crs:EPSG::4171", "Lyon", "France");

<<<<<<< HEAD
            /* Rennes - France */
            jsonParser.jsonParserV2("https://data.rennesmetropole.fr/api/records/1.0/search/?dataset=etat-des-stations-le-velo-star-en-temps-reel", "Rennes", "France");

            /* Avignon - France */
            jsonParser.jsonParserV3("https://data.opendatasoft.com/api/records/1.0/search/?dataset=osm-fr-stations-de-velo-en-libre-service%40babel&facet=network&facet=operator&facet=source", "Avignon", "France");


            /* Toulouse - France */
            jsonParser.jsonParserV3("https://data.toulouse-metropole.fr/api/records/1.0/search/?dataset=velo-toulouse", "Toulouse", "France");

            /* Nice - France */
            jsonParser.jsonParserV4("http://opendata.nicecotedazur.org/data/storage/f/2014-05-13T08%3A20%3A37.361Z/velobleu.geojson", "Nice", "France");


=======
>>>>>>> f312f314d6487c0620866e76178b0e6e2ff24f1e
            /* Parse CSV files from users */
            UserFilesParser fr = new UserFilesParser();
            String directory = System.getProperty("user.dir") + "/Manually-added-files";
            final File folder = new File(directory);
<<<<<<< HEAD
            List<String> files = new ArrayList();
=======
            List<String> files = new ArrayList<>();
>>>>>>> f312f314d6487c0620866e76178b0e6e2ff24f1e
            files = fr.listFilesForFolder(folder);

            fr.csvParser(files);
        }
    }
}
