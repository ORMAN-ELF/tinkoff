package com.app;
import java.util.HashMap;
import java.util.Map;

public class Name {

    private String title;
    private String first;
    private String last;

    private Map<String, Object> additionalProperties = new HashMap<>();


    String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Name withTitle(String title) {
        this.title = title;
        return this;
    }

    String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public Name withFirst(String first) {
        this.first = first;
        return this;
    }

    String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public Name withLast(String last) {
        this.last = last;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Name withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
