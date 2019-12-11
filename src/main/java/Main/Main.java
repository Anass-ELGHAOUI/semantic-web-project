package Main;

import Constants.Constants;
import Models.BikeStation;
import Models.City;
import XMLParser.XMLParser;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import FileReader.UserFilesParser;

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

            /* Parse CSV files from users */
            UserFilesParser fr = new UserFilesParser();
            String directory = System.getProperty("user.dir") + "/Manually-added-files";
            final File folder = new File(directory);
            List<String> files = new ArrayList<>();
            files = fr.listFilesForFolder(folder);

            fr.csvParser(files);
        }
    }
}
