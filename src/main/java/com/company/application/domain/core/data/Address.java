package com.company.application.domain.core.data;

public class Address {
    private String street;
    private String postalCode;
    private String city;
    private String state;
    private String country;

    public Address(String street, String postalCode, String city, String state, String country) {
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }
    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getUiText() {
        return street + ", " + postalCode + " "  + city;
    }
}
