package com.app;

import java.sql.*;

import static com.app.ConfigDB.*;

/**
 * DataAPI. Класс предназначен для сбора данных из API и записи этих данных в БД.
 *
 * @version:   18 марта 2019
 * @Copyright  Наталья
 */

class DataAPI {

    private DataModelAPI dataModelAPI = new DataModelAPI();
    private DataModel dataModel = new DataModel();
    private Connection conn = DriverManager.getConnection(URL, USER, PASS);
    private int id;
    String nameAPI = dataModelAPI.getNameAPI();
    String surnameAPI = dataModelAPI.getSurnameAPI();
    String middlenameAPI = dataModelAPI.getMiddlenameAPI();
    String genderAPI = dataModelAPI.getGenderAPI();
    String zipAPI = dataModelAPI.getZipAPI();
    String country = dataModelAPI.getCountryAPI();
    String region = dataModelAPI.getRegionAPI();
    String cityAPI = dataModelAPI.getCityAPI();
    String streetAPI = dataModelAPI.getStreetAPI();
    String dateAPI = String.valueOf(dataModel.getDateForAge());
    String inn = dataModel.getInn();
    Integer house = dataModel.getHouse();
    Integer room = dataModel.getRoom();


    DataAPI() throws Exception { }

    void getDataAPI() throws Exception {

        String updateIDPersons ="SELECT id FROM persons where surname = '" + surnameAPI + "' and name = '"
                + nameAPI + "' and middlename = '" + middlenameAPI + "'";
        Statement statement = conn.createStatement();

        try (Statement statement2 = conn.createStatement()) {
            ResultSet rs = statement2.executeQuery(updateIDPersons);
            while (rs.next()) {
            id = rs.getInt("id"); }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(id >= 1){
            String updateAddressData = "UPDATE address SET postcode = '" + zipAPI +
                    "', country = '" + country + "', region = '" + region +
                    "', city = '" + cityAPI + "', street = '" + streetAPI +
                    "', house = '" + house + "', flat = '" + room + "' WHERE id = '" + id + "'";

            String updatePersonsData = "UPDATE persons SET birthday = '" + dateAPI +
                    "', gender = '" + genderAPI + "', inn = '" + inn +
                    "', address_id = '" + id + "' WHERE id = '" + id + "'";
            statement.executeUpdate(updateAddressData);
            statement.executeUpdate(updatePersonsData);

        } else {
            String queryAddress = "INSERT INTO address " +
                    "(postcode, country, region, city, street, house, flat) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            String queryPersons = "INSERT INTO persons " +
                    "(surname, name, middlename, birthday, gender, inn, address_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statementPrepared2 = conn.prepareStatement(queryAddress);
            statementPrepared2.setString(1, zipAPI);
            statementPrepared2.setString(2, country);
            statementPrepared2.setString(3, region);
            statementPrepared2.setString(4, cityAPI);
            statementPrepared2.setString(5, streetAPI);
            statementPrepared2.setInt(6, house);
            statementPrepared2.setInt(7, room);
            statementPrepared2.executeUpdate();

            PreparedStatement statementPrepared = conn.prepareStatement(queryPersons);
            statementPrepared.setString(1, surnameAPI);
            statementPrepared.setString(2, nameAPI);
            statementPrepared.setString(3, middlenameAPI);
            statementPrepared.setString(4, dateAPI);
            statementPrepared.setString(5, genderAPI);
            statementPrepared.setString(6, inn);
            statementPrepared.setInt(7, 1);
            statementPrepared.executeUpdate();

            String queryAddressID = "UPDATE persons inner join address " +
                    "on address.id=persons.id set persons.address_id=address.id";
            statement = conn.createStatement();
            statement.executeUpdate(queryAddressID);
        }

    }
}
