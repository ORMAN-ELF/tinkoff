import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class DataModelAPI {

    static String name;
    static String surname;
    static String street;
    static String city;
    static String country;


    DataModelAPI() throws IOException {}



    public static void getModelAPI() throws Exception {
        //GetJSON getJSON = new GetJSON();
        //StringBuffer response = getJSON.getJson();
        //System.out.println(response);


        //ObjectMapper mapper = new ObjectMapper();
        //JsonNode rootNode = mapper.readTree(String.valueOf(response));
        //try {
            //if (rootNode instanceof ArrayNode) {

                //MyPojo[] objects = mapper.readValue(rootNode.toString(), MyPojo[].class);
                //System.out.println(Arrays.toString(objects));

            //} else if (rootNode instanceof JsonNode) {

                //MyPojo object = mapper.readValue(rootNode.toString(), MyPojo.class);
                //title = object.getResults().iterator().next().getName().getTitle();
                //name = object.getResults().iterator().next().getName().getFirst();
                //lastname = object.getResults().iterator().next().getName().getLast();

                //street = object.getResults().iterator().next().getLocation().getStreet();
                //city = object.getResults().iterator().next().getLocation().getCity();
                //state = object.getResults().iterator().next().getName().getLast();

                //System.out.println(title + " " + name + " " + lastname + " " + street + " " + city + " " + state);
            //}

        //} catch (Exception e){
            //e.printStackTrace();
        //}
    }

    String getName() throws Exception {

        GetJSON getJSON = new GetJSON();
        StringBuffer response = getJSON.getJson();
        System.out.println(response);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(String.valueOf(response));
        MyPojo object = mapper.readValue(rootNode.toString(), MyPojo.class);
        name = object.getResults().iterator().next().getName().getFirst();

        return name;
    }

    String getSurname() throws Exception {

        GetJSON getJSON = new GetJSON();
        StringBuffer response = getJSON.getJson();
        System.out.println(response);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(String.valueOf(response));
        MyPojo object = mapper.readValue(rootNode.toString(), MyPojo.class);
        surname = object.getResults().iterator().next().getName().getLast();

        return surname;
    }

    String getMiddlename() throws Exception {

        return "-";
    }

    String getCountry() throws Exception{
        GetJSON getJSON = new GetJSON();
        StringBuffer response = getJSON.getJson();
        System.out.println(response);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(String.valueOf(response));
        MyPojo object = mapper.readValue(rootNode.toString(), MyPojo.class);
        country = object.getResults().iterator().next().getLocation().getState();

        return country;
    }

    String getRegion() {
        return "-";
    }

    String getCity() throws Exception{
        GetJSON getJSON = new GetJSON();
        StringBuffer response = getJSON.getJson();
        System.out.println(response);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(String.valueOf(response));
        MyPojo object = mapper.readValue(rootNode.toString(), MyPojo.class);
        city = object.getResults().iterator().next().getLocation().getCity();

        return city;
    }

    String getStreet() throws Exception{
        GetJSON getJSON = new GetJSON();
        StringBuffer response = getJSON.getJson();
        System.out.println(response);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(String.valueOf(response));
        MyPojo object = mapper.readValue(rootNode.toString(), MyPojo.class);
        street = object.getResults().iterator().next().getLocation().getStreet();

        return street;
    }

}
