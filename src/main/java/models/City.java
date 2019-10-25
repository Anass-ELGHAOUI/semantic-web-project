package models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class City {
    private String name;
    private List<BikeStation> bikeStations = new ArrayList<BikeStation>();


    public void addBikeStation(BikeStation bikeStation) {
        this.bikeStations.add(bikeStation);
    }
}
