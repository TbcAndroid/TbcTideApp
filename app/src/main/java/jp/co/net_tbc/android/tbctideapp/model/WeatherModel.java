package jp.co.net_tbc.android.tbctideapp.model;

/**
 * Created by Kenji Nagai on 2016/08/06.
 */
public class WeatherModel {
    private final static WeatherModel weatherModel = new WeatherModel();
    private int id;
    private int minTemp;
    private int maxTemp;
    private String weather;
    private String icon;

    /* constructor */
    public WeatherModel() {
    }

    /* Singleton */
    public static WeatherModel getInstance() {
        return weatherModel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(int minTemp) {
        this.minTemp = minTemp;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(int maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
