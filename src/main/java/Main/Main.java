package Main;

import Constants.Constants;
import RDFExtractor.RDFExtractor;
import XMLParser.XMLParser;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFactory;

public class Main {
    public static void main(String[] args) {
        /* Delete triplestore content before uploading new one */
        RDFConnection conn = RDFConnectionFactory.connect(Constants.triplestore);
        conn.delete();

        /* Parse bike station files */
        /* Montpellier - France */
        XMLParser xmlParser = new XMLParser();
        xmlParser.XMLFileParser("https://data.montpellier3m.fr/sites/default/files/ressources/TAM_MMM_VELOMAG.xml", "Montpellier");

        /* Strasbourg - France */
        xmlParser.XMLFileParser("http://velhop.strasbourg.eu/tvcstations.xml", "Strasbourg");

        /* Get info from triplestore */
        RDFExtractor rdfExt = new RDFExtractor();
        /* Get all cities names */
        rdfExt.getCities();

        /* Get specific city bike stations with all info */
        rdfExt.getBikeStations("Montpellier");
    }
}
