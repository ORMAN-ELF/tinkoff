package com.app;

import java.sql.*;

import static com.app.ConfigDB.PASS;
import static com.app.ConfigDB.URL;
import static com.app.ConfigDB.USER;

/**
 * DataModelDB. Класс предназначен для получения данных из бд
 *
 * @version:   18 марта 2019
 * @Copyright  Наталья
 */


class DataModelDB {

    private Connection conn = DriverManager.getConnection(URL, USER, PASS);

    DataModelDB() throws SQLException {
    }

    private String returnDataFromDB(String nameVar, String nameDB, int id){
        String varFromDB = "SELECT " + nameVar + " FROM " + nameDB + " WHERE id = '" + id + "'";

        String returnVar = null;
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(varFromDB);
            while (rs.next()) {
               returnVar  = rs.getString(nameVar);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnVar;
    }

    String getNameFromDB(int id) {
        return returnDataFromDB("name", "persons", id);
    }

    String getSurnameFromDB(int id) {
        return returnDataFromDB("surname","persons", id);
    }

    String getMiddlenameFromDB(int id) {
        return returnDataFromDB("middlename","persons", id);
    }

    String getGenderFromDB(int id) {
        return returnDataFromDB("gender","persons", id);
    }

    String getDateFromDB(int id) {
        return returnDataFromDB("birthday","persons", id);
    }

    String getInnFromDB(int id) {
        return returnDataFromDB("inn","persons", id);
    }

    String getZipFromDB(int id) {
        return returnDataFromDB("postcode","address", id);
    }

    String getCountryFromDB(int id) {
        return returnDataFromDB("country","address", id);
    }

    String getRegionFromDB(int id) {
        return returnDataFromDB("region","address", id);
    }

    String getCityFromDB(int id) {
        return returnDataFromDB("city","address", id);
    }

    String getStreetFromDB(int id) {
        return returnDataFromDB("street","address", id);
    }

    String getHouseFromDB(int id) {
        return returnDataFromDB("house","address", id);
    }

    String getRoomFromDB(int id) {
        return returnDataFromDB("flat","address", id);
    }
}
