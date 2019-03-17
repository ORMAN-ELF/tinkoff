package com.app;

import java.sql.*;

import static com.app.ConfigDB.PASS;
import static com.app.ConfigDB.URL;
import static com.app.ConfigDB.USER;

class DataModelDB {

    private Connection conn = DriverManager.getConnection(URL, USER, PASS);

    DataModelDB() throws SQLException {
    }

    String returnDataFromDB(String nameVar, String nameDB, int id){
        String varFromDB = "SELECT" + nameVar
                + "FROM" + nameDB + "WHERE id =" + id;

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

    /*String getNameFromDB(int id) {
        String nameFromDB = "SELECT name FROM persons WHERE id =" + id;

        String name = null;
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(nameFromDB);
            while (rs.next()) {
                name = rs.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return name;
    }


    String getSurnameFromDB(int id) {
        String surnameFromDB = "SELECT surname FROM persons WHERE id =" + id;

        String surname = null;
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(surnameFromDB);
            while (rs.next()) {
                surname = rs.getString("surname");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return surname;
    }

    String getMiddlenameFromDB(int id) {
        String middlenameFromDB = "SELECT middlename FROM persons WHERE id =" + id;

        String middlename = null;
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(middlenameFromDB);
            while (rs.next()) {
                middlename = rs.getString("middlename");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return middlename;
    }

    String getGenderFromDB(int id) {
        String genderFromDB = "SELECT gender FROM persons WHERE id =" + id;

        String gender = null;
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(genderFromDB);
            while (rs.next()) {
                gender = rs.getString("gender");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return gender;
    }

    String getDateFromDB(int id) {
        String dateFromDB = "SELECT birthday FROM persons WHERE id =" + id;

        String date = null;
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(dateFromDB);
            while (rs.next()) {
                date = rs.getString("birthday");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return date;
    }


    String getInnFromDB(int id) {
        String innFromDB = "SELECT inn FROM persons WHERE id =" + id;

        String inn = null;
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(innFromDB);
            while (rs.next()) {
                inn = rs.getString("inn");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inn;
    }

    String getZipFromDB(int id) {
        String zipFromDB = "SELECT postcode FROM address WHERE id =" + id;

        String zip = null;
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(zipFromDB);
            while (rs.next()) {
                zip = rs.getString("postcode");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return zip;
    }

    String getCountryFromDB(int id) {
        String countryFromDB = "SELECT country FROM address WHERE id =" + id;

        String country = null;
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(countryFromDB);
            while (rs.next()) {
                country = rs.getString("country");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return country;
    }

    String getRegionFromDB(int id) {
        String regionFromDB = "SELECT region FROM address WHERE id =" + id;

        String region = null;
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(regionFromDB);
            while (rs.next()) {
                region = rs.getString("region");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return region;
    }

    String getCityFromDB(int id) {
        String cityFromDB = "SELECT city FROM address WHERE id =" + id;

        String city = null;
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(cityFromDB);
            while (rs.next()) {
                city = rs.getString("city");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return city;
    }

    String getStreetFromDB(int id) {
        String streetFromDB = "SELECT street FROM address WHERE id =" + id;

        String street = null;
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(streetFromDB);
            while (rs.next()) {
                street = rs.getString("street");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return street;
    }

    String getHouseFromDB(int id) {
        String houseFromDB = "SELECT house FROM address WHERE id =" + id;

        String house = null;
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(houseFromDB);
            while (rs.next()) {
                house = rs.getString("house");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return house;
    }

    String getRoomFromDB(int id) {
        String roomFromDB = "SELECT flat FROM address WHERE id =" + id;

        String room = null;
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(roomFromDB);
            while (rs.next()) {
                room = rs.getString("flat");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return room;
    }*/
}
