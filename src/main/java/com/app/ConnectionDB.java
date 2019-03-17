package com.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.app.ConfigDB.PASS;
import static com.app.ConfigDB.URL;
import static com.app.ConfigDB.USER;

public class ConnectionDB {

    public static void ConnectionDB(){

        try{
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            if (!conn.isClosed()){
                System.out.println("Соединение с БД Установлено!");
            }

            //conn.close();

            //if (conn.isClosed()){
            //    System.out.println("Соединение с БД Закрыто!");
            //}

        } catch (SQLException e){
            System.err.println("Не удалось подключиться к БД");
        }

    }
}
