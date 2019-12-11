package FileReader;

import Models.BikeStation;
import Models.City;
import RDFGenerator.RDFGenerator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserFilesParser {

    /* Method that return all file names from a selected directory
    * taken from Stackoverflow */
    public List<String> listFilesForFolder(final File folder) {
<<<<<<< HEAD
        List<String> files = new ArrayList();
=======
        List<String> files = new ArrayList<>();
>>>>>>> f312f314d6487c0620866e76178b0e6e2ff24f1e
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                files.add(fileEntry.getName());
            }
        }
        return files;
    }

    public void csvParser (List<String> files) {
        RDFGenerator rdfGenerator = new RDFGenerator();

        for (int i = 0; i < files.size(); i++) {
            String csvFile = files.get(i);
            BufferedReader br = null;
            String line = "";
            String csvSplitBy = ",";

            City city = new City();
            String[] cityNameCountry = csvFile.split("::");
            city.setCountry(cityNameCountry[0]);
            city.setName(cityNameCountry[1].replace(".txt", ""));

            List<BikeStation> bikeStations = new ArrayList<BikeStation>();

            try {
                String directory = System.getProperty("user.dir") + "/Manually-added-files/";
                br = new BufferedReader(new FileReader(directory + csvFile));
                while ((line = br.readLine()) != null) {
                    String[] cityInfo = line.split(csvSplitBy);

                    BikeStation bikeStation = new BikeStation();
                    bikeStation.setName(cityInfo[0]);
                    bikeStation.setLattitude(cityInfo[1].replace(" ", ""));
                    bikeStation.setLongitude(cityInfo[2].replace(" ", ""));
                    bikeStation.setAvailable(cityInfo[3].replace(" ", ""));
                    bikeStation.setFree(cityInfo[4].replace(" ", ""));
                    bikeStation.setTotal(cityInfo[5].replace(" ", ""));
                    bikeStation.setCardPaiement(cityInfo[6].replace(" ", ""));

                    bikeStations.add(bikeStation);
                }
                city.setBikeStations(bikeStations);

                /* Create RDF */
                rdfGenerator.generateRDF(city);
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if (br != null) {
                    try {
                        br.close();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
}
