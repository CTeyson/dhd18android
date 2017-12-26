package com.unikoeln.mazey.dhdexamplesecond.activities.utils.httpcommunication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConfToolConnection {

    public String getResource(String url) {
        String result = "";

        try {
            URL resource = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) resource.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            result = this.readResource(conn);
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private String readResource(HttpURLConnection conn) {
        StringBuilder stringBuilder = new StringBuilder();
        String result;

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            while ((result = br.readLine()) != null) {
                stringBuilder.append(result);
                stringBuilder.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

}
