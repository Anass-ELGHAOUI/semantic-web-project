package Main;

import Constants.Constants;
import JSONCityParser.JSONCityParser;
import XMLParser.XMLParser;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFactory;

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

            /* Rennes - France */
            jsonParser.jsonParserV2("https://data.rennesmetropole.fr/api/records/1.0/search/?dataset=etat-des-stations-le-velo-star-en-temps-reel", "Rennes", "France");

            /* Avignon - France */
            jsonParser.jsonParserV3("https://data.opendatasoft.com/api/records/1.0/search/?dataset=osm-fr-stations-de-velo-en-libre-service%40babel&facet=network&facet=operator&facet=source", "Avignon", "France");

            /* Nice - France */
            jsonParser.jsonParserV4("http://opendata.nicecotedazur.org/data/storage/f/2014-05-13T08%3A20%3A37.361Z/velobleu.geojson", "Nice", "France");

            /* Marseille - France */
            jsonParser.jcdecauxParser("https://public.opendatasoft.com/api/records/1.0/search/?dataset=jcdecaux_bike_data&facet=banking&facet=bonus&facet=status&facet=contract_name&refine.contract_name=marseille", "Marseille", "France");

            /* Valence - France */
            jsonParser.jcdecauxParser("https://public.opendatasoft.com/api/records/1.0/search/?dataset=jcdecaux_bike_data&facet=banking&facet=bonus&facet=status&facet=contract_name&refine.contract_name=valence", "Valence", "France");

            /* Toulouse - France */
            jsonParser.jcdecauxParser("https://public.opendatasoft.com/api/records/1.0/search/?dataset=jcdecaux_bike_data&facet=banking&facet=bonus&facet=status&facet=contract_name&refine.contract_name=toulouse", "Toulouse", "France");

            /* Bruxelles - Belgique */
            jsonParser.jcdecauxParser("https://public.opendatasoft.com/api/records/1.0/search/?dataset=jcdecaux_bike_data&facet=banking&facet=bonus&facet=status&facet=contract_name&refine.contract_name=bruxelles", "Bruxelles", "Belgique");

            /* Seville - Spain */
            jsonParser.jcdecauxParser("https://public.opendatasoft.com/api/records/1.0/search/?dataset=jcdecaux_bike_data&facet=banking&facet=bonus&facet=status&facet=contract_name&refine.contract_name=seville", "Seville", "Spain");

            /* Brisbane - Australia */
            jsonParser.jcdecauxParser("https://public.opendatasoft.com/api/records/1.0/search/?dataset=jcdecaux_bike_data&facet=banking&facet=bonus&facet=status&facet=contract_name&refine.contract_name=brisbane", "Brisbane", "Australia");

            /* Nantes - France */
            jsonParser.jcdecauxParser("https://public.opendatasoft.com/api/records/1.0/search/?dataset=jcdecaux_bike_data&facet=banking&facet=bonus&facet=status&facet=contract_name&refine.contract_name=nantes", "Nantes", "France");

            /* Dublin - Irland */
            jsonParser.jcdecauxParser("https://public.opendatasoft.com/api/records/1.0/search/?dataset=jcdecaux_bike_data&facet=banking&facet=bonus&facet=status&facet=contract_name&refine.contract_name=dublin", "Dublin", "Irland");

            /* Nancy - France */
            jsonParser.jcdecauxParser("https://public.opendatasoft.com/api/records/1.0/search/?dataset=jcdecaux_bike_data&facet=banking&facet=bonus&facet=status&facet=contract_name&refine.contract_name=nancy", "Nancy", "France");

            /* Amiens - France */
            jsonParser.jcdecauxParser("https://public.opendatasoft.com/api/records/1.0/search/?dataset=jcdecaux_bike_data&facet=banking&facet=bonus&facet=status&facet=contract_name&refine.contract_name=amiens", "Amiens", "France");

            /* Rouen - France */
            jsonParser.jcdecauxParser("https://public.opendatasoft.com/api/records/1.0/search/?dataset=jcdecaux_bike_data&facet=banking&facet=bonus&facet=status&facet=contract_name&refine.contract_name=rouen", "Rouen", "France");

            /* Besancon - France */
            jsonParser.jcdecauxParser("https://public.opendatasoft.com/api/records/1.0/search/?dataset=jcdecaux_bike_data&facet=banking&facet=bonus&facet=status&facet=contract_name&refine.contract_name=besancon", "Besancon", "France");

            /* Kazan - Russia */
            jsonParser.jcdecauxParser("https://public.opendatasoft.com/api/records/1.0/search/?dataset=jcdecaux_bike_data&facet=banking&facet=bonus&facet=status&facet=contract_name&refine.contract_name=kazan", "Kazan", "Russia");

            /* Stockholm - Sweden */
            jsonParser.jcdecauxParser("https://public.opendatasoft.com/api/records/1.0/search/?dataset=jcdecaux_bike_data&facet=banking&facet=bonus&facet=status&facet=contract_name&refine.contract_name=Stockholm", "Stockholm", "Sweden");

            /* Creteil - France */
            jsonParser.jcdecauxParser("https://public.opendatasoft.com/api/records/1.0/search/?dataset=jcdecaux_bike_data&facet=banking&facet=bonus&facet=status&facet=contract_name&refine.contract_name=creteil", "Creteil", "France");

            /* Namur - Belgique */
            jsonParser.jcdecauxParser("https://public.opendatasoft.com/api/records/1.0/search/?dataset=jcdecaux_bike_data&facet=banking&facet=bonus&facet=status&facet=contract_name&refine.contract_name=namur", "Namur", "Belgique");

            /* Cergy-Pontoise - France */
            jsonParser.jcdecauxParser("https://public.opendatasoft.com/api/records/1.0/search/?dataset=jcdecaux_bike_data&facet=banking&facet=bonus&facet=status&facet=contract_name&refine.contract_name=Cergy-Pontoise", "Cergy-Pontoise", "France");

            /* Parse CSV files from users */
            /*
            UserFilesParser fr = new UserFilesParser();
            String directory = System.getProperty("user.dir") + "/Manually-added-files";
            final File folder = new File(directory);
            List<String> files = new ArrayList();
            files = fr.listFilesForFolder(folder);

            fr.csvParser(files); */

        }
    }
}
