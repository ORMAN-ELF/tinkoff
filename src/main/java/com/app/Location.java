package com.app;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {

    private String street;
    private String city;
    private String state;
    private String postcode;

    String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

}
