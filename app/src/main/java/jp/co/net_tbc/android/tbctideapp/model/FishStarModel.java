package jp.co.net_tbc.android.tbctideapp.model;

import java.util.List;

/**
 * Created by Kenji Nagai on 2016/08/06.
 */
public class FishStarModel {
    private static final FishStarModel fishStarModel = new FishStarModel();
    private String sunriseTime;
    private String sunsetTime;
    private String moonriseTime;
    private String moonsetTime;
    private String tideName;
    private List<TideTailModel> tideTails;

    private FishStarModel() {
    }

    public static FishStarModel getInstance() {
        return fishStarModel;
    }

    public String getSunriseTime() {
        return sunriseTime;
    }

    public void setSunriseTime(String sunriseTime) {
        this.sunriseTime = sunriseTime;
    }

    public String getSunsetTime() {
        return sunsetTime;
    }

    public void setSunsetTime(String sunsetTime) {
        this.sunsetTime = sunsetTime;
    }

    public String getMoonriseTime() {
        return moonriseTime;
    }

    public void setMoonriseTime(String moonriseTime) {
        this.moonriseTime = moonriseTime;
    }

    public String getMoonsetTime() {
        return moonsetTime;
    }

    public void setMoonsetTime(String moonsetTime) {
        this.moonsetTime = moonsetTime;
    }

    public String getTideName() {
        return tideName;
    }

    public void setTideName(String tideName) {
        this.tideName = tideName;
    }

    public List<TideTailModel> getTideTails() {
        return tideTails;
    }

    public void setTideTails(List<TideTailModel> tideTails) {
        this.tideTails = tideTails;
    }
}
