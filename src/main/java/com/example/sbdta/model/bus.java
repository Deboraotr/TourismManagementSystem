package com.example.sbdta.model;

public class bus {

    private Integer id_bus;
    private  String arrival_time;
    private String departure_time;
    private String seats;
    private Integer price;
    private String destination;

    public Integer getId_bus() {
        return id_bus;
    }
    public void setId_bus(Integer id_bus) {
        this.id_bus = id_bus;
    }

    public String getArrival_time() {
        return arrival_time;
    }
    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String getDeparture_time() {
        return departure_time;
    }
    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public String getSeats() {
        return seats;
    }
    public void setSeats(String seats) {
        this.seats = seats;
    }

    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }

}
