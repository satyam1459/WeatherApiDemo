package com.example.WeatherApi;

import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.SneakyThrows;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherApplication {
    @SneakyThrows
    public static void main(String[] args) {
        URL getUrl = new URL("https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&current_weather=true");
        HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();

        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();


        if(responseCode == HttpURLConnection.HTTP_OK){
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer jsonResponseData = new StringBuffer();
            String readLine = null;

            //Append response line by line
            while((readLine = br.readLine()) != null){
                jsonResponseData.append(readLine);
            }
            br.close();
            JSONObject masterData = new JSONObject(jsonResponseData.toString());
            System.out.println(masterData);
            System.out.println(masterData.get("timezone"));
            System.out.println("Current elevation "+masterData.get("elevation"));
            System.out.println();
            JSONObject currentWeatherObj = (JSONObject) masterData.get("current_weather");
            System.out.println(currentWeatherObj);
            System.out.println("Windspeed is "+currentWeatherObj.get("windspeed"));
            System.out.println("Temperature is "+currentWeatherObj.get("temperature"));
        }else{
            System.out.println(responseCode);
        }
    }
}
