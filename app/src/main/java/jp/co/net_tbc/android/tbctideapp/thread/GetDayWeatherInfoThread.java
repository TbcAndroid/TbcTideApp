package jp.co.net_tbc.android.tbctideapp.thread;
import android.content.SharedPreferences;
import android.util.Log;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import jp.co.net_tbc.android.tbctideapp.activity.SpotActivity;
import jp.co.net_tbc.android.tbctideapp.model.CalendarModel;
import jp.co.net_tbc.android.tbctideapp.model.SpotModel;
import jp.co.net_tbc.android.tbctideapp.model.WeatherModel;

/**
 * Created by kenji on 2016/05/07.
 */
public class GetDayWeatherInfoThread implements Runnable {
    private final String OPEN_WEATHER_API_URL = "http://api.openweathermap.org/data/2.5/";
    private final String GET_DAY_URL = OPEN_WEATHER_API_URL + "forecast/daily";
    private final String WEATHER_APPID = "43fd160caf288f4a2bbeb3c78427f409";


    @Override
    public void run() {
        double lat = SpotModel.getInstance().getLatitude();
        double lng = SpotModel.getInstance().getLongitude();

        //create client
        String weatherUrlStr = GET_DAY_URL + "?appid=" + WEATHER_APPID + "&lat=" + String.valueOf(lat) + "&lon=" + String.valueOf(lng);
        String str = connet(weatherUrlStr);
        JsonNode jsonNode = null;
        try {
            //Json Object for Jackson
            ObjectMapper mapper = new ObjectMapper();
            jsonNode = mapper.readTree(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setWeatherModel(jsonNode);
    }

    private String connet(String weatherUrlStr) {
        String str = null;
        try {
            URL weatherUrl = new URL(weatherUrlStr);
            HttpURLConnection con = (HttpURLConnection) weatherUrl.openConnection();
            str = InputStreamToString(con.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    private String InputStreamToString(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

    private void setWeatherModel(JsonNode jsonNode) {
        int difDay = WeatherModel.getDifDay();
        WeatherModel weatherModel = WeatherModel.getInstance();
        weatherModel.setId(jsonNode.path("list").get(difDay).path("weather").get(0).get("id").intValue());
        weatherModel.setWeather(jsonNode.path("list").get(difDay).path("weather").get(0).get("main").toString().replace("\"", ""));

        float toCelcius = 273.15f;
        float kerbin = jsonNode.path("list").get(difDay).path("temp").path("day").floatValue();
        float temp = kerbin - toCelcius;
        float minKerbin = jsonNode.path("list").get(difDay).path("temp").path("min").floatValue();
        weatherModel.setMinTemp(minKerbin - toCelcius);
        float maxKerbin = jsonNode.path("list").get(difDay).path("temp").path("max").floatValue();
        weatherModel.setMaxTemp(maxKerbin - toCelcius);

        weatherModel.setIcon(jsonNode.path("list").get(difDay).path("weather").get(0).get("icon").toString().replace("\"", ""));
    }
}


