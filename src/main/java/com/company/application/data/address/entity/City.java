package com.company.application.data.address.entity;

/**
 * @author Thorsten Zieres, 1297197
 */
public class City {
    private String city;
    private String plz;
    private String state;
    private String country;

    public City(String city, String plz, String state, String country) {
        this.city = city;
        this.plz = plz;
        this.state = state;
        this.country = country;
    }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getPlz() { return plz; }
    public void setPlz(String plz) { this.plz = plz; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}
