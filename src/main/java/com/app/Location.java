package com.app;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;

public class Location {

    private String street;
    private String city;
    private String state;
    private String postcode;
    private Coordinates coordinates;
    private Timezone timezone;
    private Map<String, Object> additionalProperties = new HashMap<>();

    String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Location withStreet(String street) {
        this.street = street;
        return this;
    }

    String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Location withCity(String city) {
        this.city = city;
        return this;
    }

    String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Location withState(String state) {
        this.state = state;
        return this;
    }

    String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Location withPostcode(String postcode) {
        this.postcode = postcode;
        return this;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Location withCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
        return this;
    }

    public Timezone getTimezone() {
        return timezone;
    }

    public void setTimezone(Timezone timezone) {
        this.timezone = timezone;
    }

    public Location withTimezone(Timezone timezone) {
        this.timezone = timezone;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @JsonIgnore
    public Location withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
