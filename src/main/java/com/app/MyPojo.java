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

class Results {

    private Name name;
    private Location location;


    public Name getName ()
    {
        return name;
    }

    public void setName (Name name)
    {
        this.name = name;
    }

    Location getLocation () { return location; }

    public void setLocation (Location location) { this.location = location; }
}
