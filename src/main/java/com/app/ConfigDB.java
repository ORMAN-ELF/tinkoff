package com.app;

/**
 * ConfigDB. Класс для настройки конфигурации к БД.
 *
 * @version:   18 марта 2019
 * @Copyright  Наталья
 */

class ConfigDB {

    static String URL = "jdbc:mysql://localhost:3306/tinkoff?useUnicode=true&" +
            "useJDBCCompliantTimezoneShift=true&" +
            "useLegacyDatetimeCode=false&" +
            "serverTimezone=UTC";
    static String USER = "root";
    static String PASS = "qwerty123";
}
