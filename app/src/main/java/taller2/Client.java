package taller2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Client {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://localhost:8080/numbers");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int status = con.getResponseCode();

            if (status == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println(inputLine);
                }
                in.close();
            } else {
                System.out.println("Failed to get response: " + status);
            }

            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}