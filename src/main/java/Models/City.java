package Models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class City {
    private String name;
    private List<BikeStation> bikeStations = new ArrayList<BikeStation>();
    private List<String> bikeStationIds = new ArrayList<String>();

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

    public List<String> getBikeStationIds() {
        return bikeStationIds;
    }

    public void setBikeStationIds(List<String> bikeStationIds) {
        this.bikeStationIds = bikeStationIds;
    }
}
