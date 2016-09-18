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
    private long weatherDt;

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

    public String getWeather() { return weather; }

    public void setWeather(String weather) { this.weather = weather; }

    public void setWeather(int id) { this.weather = getWeatherDesciption(id); }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public long getWeatherDt() { return weatherDt; }

    public void setWeatherDt(long weatherDt) { this.weatherDt = weatherDt; }

    public static boolean enaGetWeatherInfo() {
        int difDay = getDifDay();
        int maxDay = 6;
        if (difDay <= 6 && difDay >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public static int getDifDay() {
        Calendar nowCalendar = Calendar.getInstance();
        Calendar nowCalendar0hour = Calendar.getInstance();
        Calendar modelCalendar = Calendar.getInstance();

        nowCalendar0hour.set(nowCalendar.get(Calendar.YEAR), nowCalendar.get(Calendar.MONTH) + 1, nowCalendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        modelCalendar.set(CalendarModel.getInstance().getYear(), CalendarModel.getInstance().getMonth(), CalendarModel.getInstance().getDay(), 0, 0, 0);
        modelCalendar.set(Calendar.MILLISECOND, 0);
        nowCalendar0hour.set(Calendar.MILLISECOND, 0);
        long difMiliSecs = nowCalendar0hour.getTimeInMillis() - modelCalendar.getTimeInMillis();
        int MILLIS_OF_DAY = 1000 * 60 * 60 * 24;
        if (difMiliSecs != 0) {
            return (int) (difMiliSecs / MILLIS_OF_DAY);
        } else {
            return 0;
        }
    }

    public static String getWeatherDesciption(int id) {
        String weatherDescription = null;

        switch (id) {
            case 200:
                weatherDescription = "小雨と雷雨";
                break;
            case 201:
                weatherDescription = "雨と雷雨";
                break;
            case 202:
                weatherDescription = "大雨と雷雨";
                break;
            case 210:
                weatherDescription = "光雷雨";
                break;
            case 211:
                weatherDescription = "雷雨";
                break;
            case 212:
                weatherDescription = "重い雷雨";
                break;
            case 221:
                weatherDescription = "ぼろぼろの雷雨";
                break;
            case 230:
                weatherDescription = "小雨と雷雨";
                break;
            case 231:
                weatherDescription = "霧雨と雷雨";
                break;
            case 232:
                weatherDescription = "重い霧雨と雷雨";
                break;
            case 300:
                weatherDescription = "光強度霧雨";
                break;
            case 301:
                weatherDescription = "霧雨";
                break;
            case 302:
                weatherDescription = "重い強度霧雨";
                break;
            case 310:
                weatherDescription = "光強度霧雨の雨";
                break;
            case 311:
                weatherDescription = "霧雨の雨";
                break;
            case 312:
                weatherDescription = "重い強度霧雨の雨";
                break;
            case 313:
                weatherDescription = "にわかの雨と霧雨";
                break;
            case 314:
                weatherDescription = "重いにわかの雨と霧雨";
                break;
            case 321:
                weatherDescription = "にわか霧雨";
                break;
            case 500:
                weatherDescription = "小雨";
                break;
            case 501:
                weatherDescription = "適度な雨";
                break;
            case 502:
                weatherDescription = "重い強度の雨";
                break;
            case 503:
                weatherDescription = "非常に激しい雨";
                break;
            case 504:
                weatherDescription = "極端な雨";
                break;
            case 511:
                weatherDescription = "雨氷";
                break;
            case 520:
                weatherDescription = "光強度のにわかの雨";
                break;
            case 521:
                weatherDescription = "にわかの雨";
                break;
            case 522:
                weatherDescription = "重い強度にわかの雨";
                break;
            case 531:
                weatherDescription = "不規則なにわかの雨";
                break;
            case 600:
                weatherDescription = "小雪";
                break;
            case 601:
                weatherDescription = "雪";
                break;
            case 602:
                weatherDescription = "大雪";
                break;
            case 611:
                weatherDescription = "みぞれ";
                break;
            case 612:
                weatherDescription = "にわかみぞれ";
                break;
            case 615:
                weatherDescription = "光雨と雪";
                break;
            case 616:
                weatherDescription = "雨や雪";
                break;
            case 620:
                weatherDescription = "光のにわか雪";
                break;
            case 621:
                weatherDescription = "にわか雪";
                break;
            case 622:
                weatherDescription = "重いにわか雪";
                break;
            case 701:
                weatherDescription = "ミスト";
                break;
            case 711:
                weatherDescription = "煙";
                break;
            case 721:
                weatherDescription = "ヘイズ";
                break;
            case 731:
                weatherDescription = "砂、ほこり旋回する";
                break;
            case 741:
                weatherDescription = "霧";
                break;
            case 751:
                weatherDescription = "砂";
                break;
            case 761:
                weatherDescription = "ほこり";
                break;
            case 762:
                weatherDescription = "火山灰";
                break;
            case 771:
                weatherDescription = "スコール";
                break;
            case 781:
                weatherDescription = "竜巻";
                break;
            case 800:
                weatherDescription = "晴天";
                break;
            case 801:
                weatherDescription = "薄い雲";
                break;
            case 802:
                weatherDescription = "雲";
                break;
            case 803:
                weatherDescription = "曇りがち";
                break;
            case 804:
                weatherDescription = "厚い雲";
                break;
        }
        return weatherDescription;
    }

    public static String getJpWeather(String weather) {
        String jpWeather = null;
        switch (weather) {
            case "Thunderstorm":
                jpWeather = "雷雨";
                break;
            case "Drizzle":
                jpWeather = "霧雨";
                break;

            case "Clouds":
                jpWeather = "くもり";
                break;
            case "Rain":
                jpWeather = "雨";
                break;
            case "Clear":
                jpWeather = "晴れ";
                break;
            case "Snow":
                jpWeather = "雪";
                break;
            case "Extreme":
                jpWeather = "異常気象";
                break;
            case "Additional":
                jpWeather = "その他";
                break;
            default:
                jpWeather = weather;
                break;
        }
        return jpWeather;
    }
}
