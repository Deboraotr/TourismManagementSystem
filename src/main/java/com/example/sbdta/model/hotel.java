package com.example.sbdta.model;

public class hotel {
    private Integer id_hotel;
    private  String hotel_name;
    private String hotel_type;
    private Integer price;

    public Integer getId_hotel() {
        return id_hotel;
    }
    public void setId_hotel(Integer id_hotel) {
        this.id_hotel = id_hotel;
    }

    public String getHotel_name() {
        return hotel_name;
    }
    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getHotel_type() {
        return hotel_type;
    }
    public void setHotel_type(String hotel_type) {
        this.hotel_type = hotel_type;
    }

    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }


}

