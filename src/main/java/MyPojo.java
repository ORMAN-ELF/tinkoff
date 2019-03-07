import java.util.List;

public class MyPojo
{
    private List<Results> results;

    public void setResults(List<Results> results){
        this.results = results;
    }
    public List<Results> getResults(){
        return this.results;
    }
}

class Results
{
    private Name name;

    private Location location;

    public void setName(Name name){
        this.name = name;
    }
    public Name getName(){
        return this.name;
    }
    public void setLocation(Location location){
        this.location = location;
    }
    public Location getLocation(){
        return this.location;
    }
}
