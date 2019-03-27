package com.app;
import java.util.List;

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
