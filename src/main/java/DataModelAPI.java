import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

class DataModelAPI {

    DataModelAPI() {}

    private static MyPojo getModelAPI() throws Exception {
        JSON getJSON = new JSON();
        StringBuffer response = getJSON.getJson();
        System.out.println(response);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(String.valueOf(response));
        return mapper.readValue(rootNode.toString(), MyPojo.class);
    }

    String getName() throws Exception {

        return getModelAPI().getResults().iterator().next().getName().getFirst();
    }

    String getSurname() throws Exception {

        return getModelAPI().getResults().iterator().next().getName().getLast();
    }

    String getMiddlename() {

        return "-";
    }

    String getCountry() throws Exception{

        return getModelAPI().getResults().iterator().next().getLocation().getState();
    }

    String getRegion() {
        return "-";
    }

    String getCity() throws Exception{

        return getModelAPI().getResults().iterator().next().getLocation().getCity();
    }

    String getStreet() throws Exception{

        return getModelAPI().getResults().iterator().next().getLocation().getStreet();
    }

}
