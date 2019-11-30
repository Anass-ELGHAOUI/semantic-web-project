package Models;

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
}
