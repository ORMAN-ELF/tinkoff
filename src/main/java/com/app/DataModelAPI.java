package com.app;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * DataModelAPI. Класс представляет собой подготовленные данные из JSON для создания файлов pdf, excel.
 *
 * @version:   0.1 10 марта 2019
 * @Copyright  Наталья
 */

class DataModelAPI {

    DataModelAPI() {}

    private static MyPojo getModelAPI() throws Exception {
        RequestToAPI getJSON = new RequestToAPI();
        StringBuffer response = getJSON.getJson();
        var mapper = new ObjectMapper();
        var rootNode = mapper.readTree(String.valueOf(response));
        return mapper.readValue(rootNode.toString(), MyPojo.class);
    }

    String getGenderAPI() throws Exception {
        String gender = getModelAPI().getResults().iterator().next().getName().getTitle();
        if (gender.equals("mr")){ return "м"; } else{ return "ж"; }
    }

    String getNameAPI() throws Exception {
        return getModelAPI().getResults().iterator().next().getName().getFirst();
    }

    String getSurnameAPI() throws Exception {
        return getModelAPI().getResults().iterator().next().getName().getLast();
    }

    String getMiddlenameAPI() {
        return "-";
    }

    String getCountryAPI() throws Exception{
        return getModelAPI().getResults().iterator().next().getLocation().getState();
    }

    String getRegionAPI() {
        return "-";
    }

    String getCityAPI() throws Exception{
        return getModelAPI().getResults().iterator().next().getLocation().getCity();
    }

    String getStreetAPI() throws Exception{
        return getModelAPI().getResults().iterator().next().getLocation().getStreet();
    }

    String getZipAPI() throws Exception{
        return getModelAPI().getResults().iterator().next().getLocation().getPostcode();
    }

}
