package models;

import lombok.Data;

@Data
public class BikeStation {
    private String name;
    private String id;
    private String lattitude;
    private String longitude;
    private String available;
    private String free;
    private String total;
    private String cardPaiement;

}
