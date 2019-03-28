package com.app;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MyPojo
{
    private List<Results> results;

    public void setResults(List<Results> results){
        this.results = results;
    }
    List<Results> getResults(){
        return this.results;
    }
}
