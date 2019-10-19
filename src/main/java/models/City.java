package models;

import java.util.ArrayList;
import java.util.List;

public class City {
    private String name;
    private List<BikeStation> bikeStations = new ArrayList<BikeStation>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BikeStation> getBikeStations() {
        return bikeStations;
    }

    public void setBikeStations(List<BikeStation> bikeStations) {
        this.bikeStations = bikeStations;
    }

    public void addBikeStation(BikeStation bikeStation) {
        this.bikeStations.add(bikeStation);
    }
}
