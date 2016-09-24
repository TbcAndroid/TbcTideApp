package jp.co.net_tbc.android.tbctideapp.model;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by Kenji Nagai on 2016/08/06.
 */
public class WeatherModel implements Serializable {
    private static WeatherModel weatherModel = new WeatherModel();
    public static final int INIT_DATA = 0;
    private static final long serialVersionUID = 6604895831662062981L;
    private int id = INIT_DATA;
    private float minTemp = 0;
    private float maxTemp = 0;
    private String weather = "晴れ";
    private String icon = "01d";
    private long weatherDt;
    private float speed = 0;
    private int deg = 0;
    private float pressure = 0;
    private String degDirection = "";

    /* constructor */
    private WeatherModel() {
    }

    /* Singleton */
    public static WeatherModel getInstance() {
        return weatherModel;
    }

    public void setWeatherModel(WeatherModel weatherModel) {
        if(weatherModel != null){
            setId(weatherModel.getId());
            setMinTemp(weatherModel.getMinTemp());
            setMaxTemp(weatherModel.getMaxTemp());
            setWeather(weatherModel.getId());
            setIcon(weatherModel.getIcon());
            setWeatherDt(weatherModel.getWeatherDt());
            setSpeed(weatherModel.getSpeed());
            setDeg(weatherModel.getDeg());
            setPressure(weatherModel.getPressure());
            setDegDirection(weatherModel.getDegDirection());
        }
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

    public void setWeather(int id) {
        this.weather = getWeatherDesciption(id);
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public long getWeatherDt() {
        return weatherDt;
    }

    public void setWeatherDt(long weatherDt) {
        this.weatherDt = weatherDt;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public String getDegDirection() {
        return degDirection;
    }

    public void setDegDirection(String degDirection) {
        this.degDirection = degDirection;
    }

    public void setDegDirection(int deg) {
        this.degDirection = formatBearing(deg) + "の風";
    }

    public static boolean canGetWeatherInfo() {
        int difDay = getDifDay();
        int minDay = 0;
        int maxDay = 6;
        if (difDay <= maxDay && difDay >= minDay) {
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

    private String formatBearing(double bearing) {
        if (bearing < 0 && bearing > -180) {
            // Normalize to [0,360]
            bearing = 360.0 + bearing;
        }
        if (bearing > 360 || bearing < -180) {
            return "Unknown";
        }

        String directions[] = {
                "北", "北北東", "北東", "北東北", "東", "東南東", "南東", "南南東",
                "南", "南南西", "南西", "西南西", "西", "西北西", "西北", "北北西",
                "北"};
        String cardinal = directions[(int) Math.floor(((bearing + 11.25) % 360) / 22.5)];
        return cardinal;
    }

    public boolean needInit() {
        return id == INIT_DATA;
    }
}
