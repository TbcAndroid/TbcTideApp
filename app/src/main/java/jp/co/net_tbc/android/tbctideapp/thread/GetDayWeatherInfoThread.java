package jp.co.net_tbc.android.tbctideapp.thread;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import jp.co.net_tbc.android.tbctideapp.helper.ApiClient;
import jp.co.net_tbc.android.tbctideapp.model.FishStarModel;
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
        double lat = FishStarModel.getInstance().getLatitude();
        double lng = FishStarModel.getInstance().getLongitude();
        //create client
        String weatherUrlStr = GET_DAY_URL + "?appid=" + WEATHER_APPID + "&lat=" + String.valueOf(lat) + "&lon=" + String.valueOf(lng);

        ApiClient client = new ApiClient();
        String str = client.connet(weatherUrlStr);

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

    private void setWeatherModel(JsonNode jsonNode) {
        int difDay = WeatherModel.getDifDay();
        WeatherModel weatherModel = WeatherModel.getInstance();
        int id = jsonNode.path("list").get(difDay).path("weather").get(0).get("id").intValue();
        weatherModel.setId(id);
        weatherModel.setWeather(id);

        float toCelcius = 273.15f;
        float kerbin = jsonNode.path("list").get(difDay).path("temp").path("day").floatValue();
        float temp = kerbin - toCelcius;
        float minKerbin = jsonNode.path("list").get(difDay).path("temp").path("min").floatValue();
        weatherModel.setMinTemp(minKerbin - toCelcius);
        float maxKerbin = jsonNode.path("list").get(difDay).path("temp").path("max").floatValue();
        weatherModel.setMaxTemp(maxKerbin - toCelcius);

        String icon = jsonNode.path("list").get(difDay).path("weather").get(0).get("icon").toString().replace("\"", "");
        weatherModel.setIcon(icon.replaceFirst("n", "d"));

        // set Millis
        weatherModel.setWeatherDt(jsonNode.path("list").get(difDay).path("dt").longValue() * 1000);
        weatherModel.setSpeed(jsonNode.path("list").get(difDay).path("speed").floatValue());
        weatherModel.setDeg(jsonNode.path("list").get(difDay).path("deg").intValue());
        weatherModel.setDegDirection(weatherModel.getDeg());
        weatherModel.setPressure(jsonNode.path("list").get(difDay).path("pressure").floatValue());
    }
}