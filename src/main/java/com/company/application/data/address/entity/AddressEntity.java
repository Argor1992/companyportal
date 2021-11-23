package com.company.application.data.address.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.company.application.data.core.data.AbstractEntity;

/**
 * @author Thorsten Zieres, 1297197
 */
@Entity
@Table(name = "Address")
public class AddressEntity extends AbstractEntity {

    private String street;
    private String postalCode;
    private String city;
    private String state;
    private String country;

    public AddressEntity() {
    }

    public AddressEntity(String street, String postalCode, String city, String state, String country) {
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

}
