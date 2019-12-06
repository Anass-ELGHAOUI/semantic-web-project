package XMLParser;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import Models.BikeStation;
import Models.City;
import RDFGenerator.RDFGenerator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParser {

    public void XMLFileParser (String url, String cityName, String country) {
        try {
            RDFGenerator rdfGenerator = new RDFGenerator();

            /* Getting the XML file from a url */
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new URL(url).openStream());

            /* Printing the XML file */
            DOMSource domSource = new DOMSource(doc);
            StringWriter stringWriter = new StringWriter();
            StreamResult streamResult = new StreamResult(stringWriter);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer;
            transformer = transformerFactory.newTransformer();
            transformer.transform(domSource, streamResult);
//            System.out.println(stringWriter.toString());

            /* Parsing the XML file */
            Element root = doc.getDocumentElement();
            if (root.getNodeName().equals("vcs")) {
                /* Get child nodes of root element */
                NodeList nodeList = root.getChildNodes();

                /* Create city */
                City city = new City();
                city.setName(cityName);
                city.setCountry(country);

                for (int i = 0; i < nodeList.getLength(); i++) {
                    if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        if (nodeList.item(i).getNodeName().equals("sl")) {
                            NodeList bikeStations = nodeList.item(i).getChildNodes();
                            /* Get bike stations of city */
                            for (int j = 0; j < bikeStations.getLength(); j++) {
                                if (bikeStations.item(j).getNodeType() == Node.ELEMENT_NODE) {
                                    if (bikeStations.item(j).getNodeName().equals("si")) {
                                        /* Get bike station information and create its object */
                                        BikeStation bikeStation = new BikeStation();

                                        if (bikeStations.item(j).getAttributes().getNamedItem("na") != null) {
                                            String name = bikeStations.item(j).getAttributes().getNamedItem("na").getNodeValue();
                                            String [] removeId = name.split(" ", 2);
                                            bikeStation.setName(removeId[1]);
                                        }
                                        if (bikeStations.item(j).getAttributes().getNamedItem("id") != null) {
                                            String id = bikeStations.item(j).getAttributes().getNamedItem("id").getNodeValue();
                                            bikeStation.setId(id);
                                        }
                                        if (bikeStations.item(j).getAttributes().getNamedItem("la") != null) {
                                            String lattitude = bikeStations.item(j).getAttributes().getNamedItem("la").getNodeValue();
                                            bikeStation.setLattitude(lattitude);
                                        }
                                        if (bikeStations.item(j).getAttributes().getNamedItem("lg") != null) {
                                            String longitude = bikeStations.item(j).getAttributes().getNamedItem("lg").getNodeValue();
                                            bikeStation.setLongitude(longitude);
                                        }
                                        if (bikeStations.item(j).getAttributes().getNamedItem("av") != null) {
                                            String available = bikeStations.item(j).getAttributes().getNamedItem("av").getNodeValue();
                                            bikeStation.setAvailable(available);
                                        }
                                        if (bikeStations.item(j).getAttributes().getNamedItem("na") != null) {
                                            String free = bikeStations.item(j).getAttributes().getNamedItem("fr").getNodeValue();
                                            bikeStation.setFree(free);
                                        }
                                        if (bikeStations.item(j).getAttributes().getNamedItem("to") != null) {
                                            String total = bikeStations.item(j).getAttributes().getNamedItem("to").getNodeValue();
                                            bikeStation.setTotal(total);
                                        }
                                        if (bikeStations.item(j).getAttributes().getNamedItem("cb") != null) {
                                            String cardPaiement = bikeStations.item(j).getAttributes().getNamedItem("cb").getNodeValue();
                                            bikeStation.setCardPaiement(cardPaiement);
                                        }

                                        city.addBikeStation(bikeStation);
                                    }
                                }
                            }
                        }
                    }

                }

                /* Print all bike stations */
//                for (int i = 0; i < montpellier.getBikeStations().size(); i++) {
//                    System.out.println("Bike station name: " + montpellier.getBikeStations().get(i).getName());
//                    System.out.println("Id: " + montpellier.getBikeStations().get(i).getId());
//                    System.out.println("Lattitude: " + montpellier.getBikeStations().get(i).getLattitude());
//                    System.out.println("Longitude: " + montpellier.getBikeStations().get(i).getLongitude());
//                    System.out.println("Available: " + montpellier.getBikeStations().get(i).getAvailable());
//                    System.out.println("Free: " + montpellier.getBikeStations().get(i).getFree());
//                    System.out.println("Total: " + montpellier.getBikeStations().get(i).getTotal());
//                    System.out.println("Card paiement: " + montpellier.getBikeStations().get(i).getCardPaiement());
//                    System.out.println();
//                }

                rdfGenerator.generateRDF(city);
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}