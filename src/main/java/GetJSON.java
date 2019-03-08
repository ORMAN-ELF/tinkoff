import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


class GetJSON {

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
            System.out.println("Отсутствует подключение к сети Интернет");
        }

        return null;
    }
}
