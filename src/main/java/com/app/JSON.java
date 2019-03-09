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

class JSON {

    StringBuffer getJson() throws Exception{
        String url = "https://randomuser.me/api/?inc=name,location&noinfo";

        URL obj = new URL(url);
        try {
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response;

        } catch (Exception e){
           e.printStackTrace();
        }

        return null;
    }
}
