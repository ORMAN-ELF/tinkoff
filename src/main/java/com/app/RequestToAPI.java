package com.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * JSON. Класс представляет собой запрос к API и ответ в формате JSON.
 *
 * @version:   0.1 10 марта 2019
 * @Copyright  Наталья
 */

class RequestToAPI {

    StringBuffer getJson() throws Exception{
        String url = "https://randomuser.me/api/?inc=name,location&noinfo";

        URL obj = new URL(url);
        try {
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = bufferedReader.readLine()) != null) {
                response.append(inputLine);
            }
            bufferedReader.close();
            return response;

        } catch (Exception e){
           System.out.println("Отсутствует сооединение с интернетом. Данные будут взяты из БД");
        }

        return null;
    }
}
