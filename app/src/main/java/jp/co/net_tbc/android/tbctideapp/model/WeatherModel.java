package jp.co.net_tbc.android.tbctideapp.model;

import java.util.Calendar;

/**
 * Created by Kenji Nagai on 2016/08/06.
 */
public class WeatherModel {
    private final static WeatherModel weatherModel = new WeatherModel();
    private int id;
    private float minTemp;
    private float maxTemp;
    private String weather;
    private String icon;

    /* constructor */
    private WeatherModel() {
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

    public float getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(float minTemp) {
        this.minTemp = minTemp;
    }

    public float getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(float maxTemp) {
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

    public static boolean enaGetWeatherInfo(){
        int difDay = getDifDay();
        int maxDay = 6;
        if(difDay <= 6 && difDay >= 0){
            return true;
        } else{
            return false;
        }
    }

    public static int getDifDay(){
        Calendar nowCalendar = Calendar.getInstance();
        Calendar nowCalendar0hour = Calendar.getInstance();
        Calendar modelCalendar = Calendar.getInstance();

        nowCalendar0hour.set(nowCalendar.get(Calendar.YEAR), nowCalendar.get(Calendar.MONTH) + 1, nowCalendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        modelCalendar.set(CalendarModel.getInstance().getYear(), CalendarModel.getInstance().getMonth(), CalendarModel.getInstance().getDay(), 0, 0, 0);
        modelCalendar.set(Calendar.MILLISECOND, 0);
        nowCalendar0hour.set(Calendar.MILLISECOND, 0);
        long difMiliSecs = nowCalendar0hour.getTimeInMillis() - modelCalendar.getTimeInMillis();
        int MILLIS_OF_DAY = 1000 * 60 * 60 * 24;
        if(difMiliSecs != 0){
            return (int) (difMiliSecs / MILLIS_OF_DAY);
        }else{
            return 0;
        }
    }
}
