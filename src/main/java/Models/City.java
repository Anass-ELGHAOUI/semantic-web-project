package Models;

import java.util.ArrayList;
import java.util.List;

public class City {
    private String name;
    private String country;
    private List<BikeStation> bikeStations = new ArrayList<BikeStation>();

    public void addBikeStation(BikeStation bikeStation) {
        this.bikeStations.add(bikeStation);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<BikeStation> getBikeStations() {
        return bikeStations;
    }

    public void setBikeStations(List<BikeStation> bikeStations) {
        this.bikeStations = bikeStations;
    }
}
