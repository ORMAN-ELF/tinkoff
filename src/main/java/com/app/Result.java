package com.app;
import java.util.HashMap;
import java.util.Map;

public class Result {

    private Name name;
    private Location location;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Result withName(Name name) {
        this.name = name;
        return this;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Result withLocation(Location location) {
        this.location = location;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Result withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
