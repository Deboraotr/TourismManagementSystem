package com.example.sbdta.model;

public class travel_agent {
    private Integer id_agent;
    private  String agent_name;
    private Integer id_bus;
    private Integer id_hotel;
    private Integer agent_contact;
    private Integer id_costumer;


    public Integer getId_agent() {
        return id_agent;
    }
    public void setId_agent(Integer id_agent) {
        this.id_agent = id_agent;
    }

    public Integer getId_costumer() {
        return id_costumer;
    }
    public void setId_costumer(Integer id_costumer) {
        this.id_costumer = id_costumer;
    }

    public String getAgent_name() {return agent_name;}
    public void setAgent_name(String agent_name) {this.agent_name = agent_name;}


    public Integer getId_bus() {
        return id_bus;
    }
    public void setId_bus(Integer id_bus) {
        this.id_bus= id_bus;
    }

    public Integer getId_hotel() {
        return id_hotel;
    }
    public void setId_hotel(Integer id_hotel) {
        this.id_hotel = id_hotel;
    }

    public Integer getAgent_contact() {
        return agent_contact;
    }
    public void setAgent_contact(Integer agent_contact) {
        this.agent_contact = agent_contact;
    }
}
