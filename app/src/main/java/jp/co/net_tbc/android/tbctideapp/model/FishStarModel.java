package jp.co.net_tbc.android.tbctideapp.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Kenji Nagai on 2016/08/06.
 */
public class FishStarModel implements Serializable {
    private static FishStarModel fishStarModel = new FishStarModel();
    private static final long serialVersionUID = 561472575341079847L;
    private String sunriseTime = "0:00";
    private String sunsetTime = "0:00";
    private String moonriseTime = "0:00";
    private String moonsetTime = "0:00";
    private String tideName = "";
    private List<TideTailModel> tideTails = Arrays.asList(new TideTailModel("1:00", 1), new TideTailModel("2:00", 2), new TideTailModel("3:00", 3), new TideTailModel("4:00", 4));

    private FishStarModel() {
    }

    public static FishStarModel getInstance() {
        return fishStarModel;
    }

    public void setFishStarModel(FishStarModel fishStarModel){
        if(fishStarModel != null){
            setSunriseTime(fishStarModel.getSunriseTime());
            setSunsetTime(fishStarModel.getSunsetTime());
            setMoonriseTime(fishStarModel.getMoonriseTime());
            setMoonsetTime(fishStarModel.getMoonsetTime());
            setTideName(fishStarModel.getTideName());
            setTideTails(fishStarModel.getTideTails());
        }
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