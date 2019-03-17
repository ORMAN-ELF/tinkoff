package com.app;

import java.sql.*;

import static com.app.ConfigDB.*;

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
        Statement stmt = conn.createStatement();

        try (Statement stmt2 = conn.createStatement()) {
            ResultSet rs = stmt2.executeQuery(updateIDPersons);
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
            stmt.executeUpdate(updateAddressData);
            stmt.executeUpdate(updatePersonsData);

        } else {
            String queryAddress = "INSERT INTO tinkoff.address " +
                    "(postcode, country, region, city, street, house, flat) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            String queryPersons = "INSERT INTO tinkoff.persons " +
                    "(surname, name, middlename, birthday, gender, inn, address_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmtP2 = conn.prepareStatement(queryAddress);
            stmtP2.setString(1, zipAPI);
            stmtP2.setString(2, country);
            stmtP2.setString(3, region);
            stmtP2.setString(4, cityAPI);
            stmtP2.setString(5, streetAPI);
            stmtP2.setInt(6, house);
            stmtP2.setInt(7, room);
            stmtP2.executeUpdate();

            PreparedStatement stmtP = conn.prepareStatement(queryPersons);
            stmtP.setString(1, surnameAPI);
            stmtP.setString(2, nameAPI);
            stmtP.setString(3, middlenameAPI);
            stmtP.setString(4, dateAPI);
            stmtP.setString(5, genderAPI);
            stmtP.setString(6, inn);
            stmtP.setInt(7, 1);
            stmtP.executeUpdate();

            String queryAddressID = "UPDATE tinkoff.persons inner join tinkoff.address " +
                    "on address.id=persons.id set persons.address_id=address.id";
            stmt = conn.createStatement();
            stmt.executeUpdate(queryAddressID);
        }

    }
}
