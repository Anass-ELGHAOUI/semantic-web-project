package Main;

import RDFClient.RDFClient;
import XMLParser.XMLParser;

public class Main {
    public static void main(String[] args) {
        /* Parse bike station files */
        /* Montpellier - France */
        XMLParser xmlParser = new XMLParser();
        xmlParser.XMLFileParser("https://data.montpellier3m.fr/sites/default/files/ressources/TAM_MMM_VELOMAG.xml");

        /* Get info from triplestore */
        RDFClient rdfClient = new RDFClient();
        rdfClient.getTriples();
    }
}
